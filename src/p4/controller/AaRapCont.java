/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p4.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import p4.dao.AdminYksusDao;
import p4.domain.AdminYksus;

/**
 *
 * @author reget.kalamees
 */
@Controller
//@SessionAttributes({"username","admYksused","alluvad","vaYksus"})
public class AaRapCont {

    @Resource
    private AdminYksusDao adminYksusDao;
    @Resource
    private MessageSource resources;

    //GET päring
    @RequestMapping(value = "/aaRap", method = RequestMethod.GET)
    @Transactional
    public String ayRap(ModelMap model) {
       String ay="0"; 
       model=sameStuff(ay,model);
        return "aaRap";
    }

    private ModelMap sameStuff(String ay,ModelMap model){
     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        //korja adminüksused
        System.out.println("peale turvat");
        List<AdminYksus> ady = adminYksusDao.getAdmYksAll();
        System.out.println("peale getAdmyksall");
        model.addAttribute("admYksused", ady);
        System.out.println("peale model add");
        if(ady!=null){
            AdminYksus vaYksus;
            //valitud esimene adminüksus,kui ay on tühi
            //kui ei siis valitakse id järgi
            if(ay.equals("0")) {
                vaYksus=ady.get(0);
                System.out.println("valitud 0");
                System.out.println("valitud esimene adminüksus " + vaYksus.getNimetus());
            }
              else
            {  
            long id=Long.parseLong(ay);
            vaYksus=adminYksusDao.getYksusById(id);
            
            }
            model.addAttribute("vaYksus", vaYksus);
            Collection<AdminYksus> alluvad=adminYksusDao.getChilds(vaYksus);
           
            //String err="OK";
            //if(alluvad==null){err="alluvad null";}
            model.addAttribute("alluvad", alluvad);
           // model.addAttribute("viiga",err);
        }
     return model;
    }
    //see saab vormilt andmed postiga
    @RequestMapping(value = "/aaRap", method = RequestMethod.POST)
    public String saveForm(@RequestParam("aYksus") String ay,@Valid AdminYksus ayks,
    BindingResult result, ModelMap model) {
        
        System.out.println(result.getModel().values().toArray());
      //TODO sisendikontroll
        
       // System.out.println("Hei! Valiti" + ay);
        
       /* if(ay!=null){
            long id=Long.parseLong(ay);
            AdminYksus aYksus=adminYksusDao.getYksusById(id);
            Collection<AdminYksus> alluvad=adminYksusDao.getChilds(aYksus);
            model.addAttribute("alluvad", alluvad);
        }
        */
       // if(!result.hasErrors()){
            model=sameStuff(ay,model);
       // }
        return "aaRap";
    }

    
    @RequestMapping(value = "/loginfailed")
    public String loginerror(ModelMap model) {
       model.addAttribute("error", "message.loginError");
        return "login";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }
}

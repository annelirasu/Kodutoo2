/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p4.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
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
@SessionAttributes({"username","admYksused","alluvad","vaYksus"})
public class AaRapCont {

    @Resource
    private AdminYksusDao adminYksusDao;
    @Resource
    private MessageSource resources;

    //GET päring
    @RequestMapping(value = "/aaRap")
    @Transactional
    public String ayRap(ModelMap model) {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", authentication.getName());
        //korja adminüksused
        List<AdminYksus> ady = adminYksusDao.getAdmYksAll();
        model.addAttribute("admYksused", ady);
        //võta esimene ja otsi selle alluvad
        if(ady!=null){
            //valitud esimene adminüksus
            
            AdminYksus vaYksus=ady.get(0);
            System.out.println("valitud esimene adminüksus " + vaYksus.getNimetus());
            model.addAttribute("vaYksus", vaYksus);
           Collection<AdminYksus> alluvad=adminYksusDao.getChilds(vaYksus);
           
            String err="OK";
            if(alluvad==null){err="alluvad null";}
            model.addAttribute("alluvad", alluvad);
            model.addAttribute("viiga",err);
        }
        return "aaRap";
    }

    //see saab vormilt andmed postiga
    @RequestMapping(value = "/aaRap", method = RequestMethod.POST)
    public String saveForm(@RequestParam("aYksus") String ay, ModelMap model) {

      //TODO sisendikontroll
        
        System.out.println("Hei! Valiti" + ay);
        
        if(ay!=null){
            long id=Long.parseLong(ay);
            AdminYksus aYksus=adminYksusDao.getYksusById(id);
            Collection<AdminYksus> alluvad=adminYksusDao.getChilds(aYksus);
            model.addAttribute("alluvad", alluvad);
        }
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

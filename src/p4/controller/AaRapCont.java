/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p4.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import p4.dao.AdminYksusDao;
import p4.domain.AdminYksus;

/**
 *
 * @author reget.kalamees
 */

@Controller
public class AaRapCont {
    
    @Resource
    private AdminYksusDao adminYksusDao;
    
    @RequestMapping(value = "/aaRap")
    public String ayRap(ModelMap model) {
        List<AdminYksus> ady = adminYksusDao.getAdmYksAll();
        model.addAttribute("admYksused",ady);
        
        return "aaRap";
        
    }
    
    //see saab vormilt andmed
    @RequestMapping(value = "/aaRap", method = RequestMethod.POST)
    public String saveForm(@RequestParam("ayLiik") String ay, @RequestParam("kuupaev") String kp, ModelMap model) {
             
           
            System.out.println("Hei! Praegu lisatav on..."+ ay + kp);
            return "aaRap";
        }
    
    
}

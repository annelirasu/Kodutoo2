/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p4.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Resource
    private MessageSource resources;

    //GET päring
    @RequestMapping(value = "/aaRap")
    public String ayRap(ModelMap model) {
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //model.addAttribute("username", authentication.getName());
        List<AdminYksus> ady = adminYksusDao.getAdmYksAll();
        model.addAttribute("admYksused", ady);
        

        return "aaRap";

    }

    //see saab vormilt andmed
    @RequestMapping(value = "/aaRap", method = RequestMethod.POST)
    public String saveForm(@RequestParam("ayLiik") String ay, @RequestParam("kuupaev") String kp, ModelMap model) {


        System.out.println("Hei! Praegu lisatav on..." + ay + kp);
        return "aaRap";
    }

    @RequestMapping(value = "/loginfailed")
    public String loginerror(ModelMap model) {

        //i18 peab ka tehtud olema, siis muutub siin	
        model.addAttribute("error", "message.loginError");

        return "login";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }
}

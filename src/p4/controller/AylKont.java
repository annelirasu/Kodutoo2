package p4.controller;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import p4.dao.*;
import p4.domain.*;
import p4.view.*;

@Controller
public class AylKont {

    @Resource
    private AdminYksLiikDao aylDao;
//    @Resource
//    private AdminYksLiik ayl;
    
    

    @RequestMapping(value = "/ayl")
    public String Ayl(ModelMap model) {

    	List<AdminYksLiik> ylemused=aylDao.getLiigid();
    	AdminYksLiikView adw =  new AdminYksLiikView(); ;
        adw.setYlemad(ylemused);
        
        //siin sellised  liigid, milledel pole veel alluvaid
        List<AdminYksLiik> alluvad=aylDao.getAlluvad();
        adw.setAlluvad(alluvad);
       
       model.addAttribute("adw", adw);
       
        return "ayl"; //tagastame puu nime, käivitamisel muuda URL http://localhost:8080/Kodutoo2/ayl
    }
}

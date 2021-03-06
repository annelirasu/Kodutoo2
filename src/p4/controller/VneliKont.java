package p4.controller;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import p4.domain.InsertTestAyData;
import p4.view.AdminYksLiikView;


/**
 *
 * @author reget.kalamees
 */
@Controller
public class VneliKont  {
    
    @Resource
    private InsertTestAyData itd;
    
    @RequestMapping(value = "/vorm4")
    public String personTree(ModelMap model) {
    	itd.insertData();
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("username", authentication.getName());
    	
    	
        return "home"; //tagastame puu nime, käivitamisel muuda URL http://localhost:8080/Kodutoo2/tree
    }

    
   
}

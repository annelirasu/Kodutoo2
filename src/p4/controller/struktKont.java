package p4.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import p4.dao.*;
import p4.view.*;

@Controller
public class struktKont {
	  @Resource
	    private AdminYksLiikDao aylDao;

	    @RequestMapping(value = "/strukt")
	    public String aylTree(ModelMap model) {
	        
	    	List<StruktSolm> aylRows = aylDao. struktforEach(); //esimese kohta praegu ainult
	    	
	    	model.addAttribute("aylRows", aylRows);
	    	
	    	return "strukt";
	    }
	    @RequestMapping(value = "/strukt", method = RequestMethod.POST)
	    public String backHome() {
   	
	    	return "redirect:/home";
	    }
}

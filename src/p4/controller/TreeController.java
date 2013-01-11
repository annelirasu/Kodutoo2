package p4.controller;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import p4.dao.*;
import p4.domain.InsertTestData;
import p4.view.*;

@Controller
public class TreeController {

   // @Resource
    //private PersonDao personDao;
	
    @Resource
    private AdminYksLiikDao adminYksLiikDao;
    
    @Resource
    private InsertTestData insertTestData;

    @RequestMapping(value = "/tree")
    public String personTree(ModelMap model) {
    	//peale testandmete sisestamist kaval välja kommenteerida
    	insertTestData.doTheMagic();
    	
    	
    	
    	List<AdminYksLiikView> adw = adminYksLiikDao.getAdmYksLiikAll();
    	model.addAttribute("admLiigid",adw);
    	 
    	
    	
    	//List<TreeNode> personRows= personDao.getPersonRows();
    	//model.addAttribute("personRows", personRows);   	
    	
    	
        return "tree"; //tagastame puu nime, käivitamisel muuda URL http://localhost:8080/Kodutoo2/tree
    }

}
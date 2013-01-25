package p4.controller;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import p4.dao.*;
import p4.domain.AdminYksus;
import p4.domain.InsertTestAyData;
import p4.domain.InsertTestData;
import p4.view.*;

@Controller
public class TreeController {

   // @Resource
    //private PersonDao personDao;
	
    @Resource
    private AdminYksLiikDao aylDao;
    @Resource
    private InsertTestAyData itd;
    @Resource
    private AdminYksusDao ayDao;
    
    @Resource
    private InsertTestData insertTestData;

    @RequestMapping(value = "/tree")
    public String personTree(ModelMap model) {
    	
    	//vanad minema
        ayDao.dropAdminYksusTable();
    	aylDao.dropAdminYksLiikTable();
    	// otsast peale
    	insertTestData.doTheMagic();
        itd.insertData();
    	
        List<AdminYksus> yksused=  ayDao.getAdmYksAll();
        model.addAttribute("yksused",yksused); 
    	AdminYksLiikView adw = aylDao.getAdmYksLiikAll();
    	model.addAttribute("adw",adw);    	
    	
        return "tree"; //tagastame puu nime, käivitamisel muuda URL http://localhost:8080/Kodutoo2/tree
    }

}

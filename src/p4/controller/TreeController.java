package p4.controller;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import p4.dao.PersonDao;
import p4.domain.InsertTestData;

import p4.view.*;

@Controller
public class TreeController {

    @Resource
    private PersonDao personDao;
    
    @Resource
    private InsertTestData insertTestData;

    @RequestMapping(value = "/tree")
    public String personTree(ModelMap model) {
    	
    	insertTestData.doTheMagic();
    	
    	List<TreeNode> personRows= personDao.getPersonRows();
    	model.addAttribute("personRows", personRows);   	
    	
    	
        return "tree"; //tagastame puu nime, käivitamisel muuda URL http://localhost:8080/p4mvcJpa/tree
    }

}
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

    	AdminYksLiikView adw = getYlsAls();
       
       model.addAttribute("adw", adw);
       
        return "ayl"; //tagastame puu nime, käivitamisel muuda URL http://localhost:8080/Kodutoo2/ayl
    }

	private AdminYksLiikView getYlsAls() {
		List<AdminYksLiik> ylemused=aylDao.getLiigid();
    	AdminYksLiikView adw =  new AdminYksLiikView(); 
        adw.setYlemad(ylemused);
        
        //siin sellised  liigid, milledel pole veel alluvaid
        List<AdminYksLiik> alluvad=aylDao.getAlluvad();
        adw.setAlluvad(alluvad);
		return adw;
	}
    
    //see saab vormilt andmed
    @RequestMapping(value = "/ayl", method = RequestMethod.POST)
    public String saveForm(@ModelAttribute("adminYksLiikView") AdminYksLiikView adw, @RequestParam("ayl_alluv_ID") String alluv_ID,
    	            		ModelMap model) {
    	
    	   int alluvID;
    	    if(!alluv_ID.isEmpty()){
    		alluvID=Integer.parseInt(alluv_ID);
    	    }
    	    else{
    	    alluvID=0;	
    	    }
    	    
    		if(alluvID>0){
    		//otsi see person välja , võta ta ära view allujate listist ja pane valitudallujate listi
    		Long aylID=Long.parseLong(alluv_ID, 10);
    		 AdminYksLiik alluv = aylDao.aylById(aylID);
    			
    		 List<AdminYksLiik> alluvad=aylDao.getAlluvad();
             alluvad.remove(alluv);//see remove ei tööta?
    		 adw.setAlluvad(alluvad);
    		 
    		 
    		 List<AdminYksLiik> valitudalluvad=new ArrayList<AdminYksLiik>();
    		 valitudalluvad.add(alluv);
    		 adw.setValitudalluvad(valitudalluvad);
    		  		
    		}
    		else{ //kui alluvaid ei valita, siis salvestame baasi, hiljem tuleb siiski uurida, mis on parajasti valitudalluvad listi sisu ja sellega tegeleda
            AdminYksLiik curr= new AdminYksLiik();
            curr.setKood(adw.getKood());
            curr.setNimetus(adw.getNimetus());
            curr.setKommentaar(adw.getKommentaar());
            //proovime baasi salvestada
            aylDao.store(curr);
            //to do tagasipärimine nt koodi järgi (koodid peavad seega unikaalsed olema)
            //ülemuse Id järgi väljapärimine 
            //
            
            
          //peale lisamist uuesti ülemused ja alluvad välja
            
            AdminYksLiikView adw2 =  getYlsAls();
            
                   
            model.addAttribute("adw2", adw2);
            
            return"ayl";
    		}
            
            
    		model.addAttribute("adw", adw);
           
            
            return "ayl";
        }
    

}

package p4.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.context.MessageSource;
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
    @Resource
    private MessageSource resources;

    @RequestMapping(value = "/ayl")
    public String Ayl(ModelMap model) {

        AdminYksLiikView adw = getYlsAls();

        model.addAttribute("adw", adw);

        return "ayl"; //tagastame puu nime, käivitamisel muuda URL http://localhost:8080/Kodutoo2/ayl
    }

    //see saab vormilt andmed
    @RequestMapping(value = "/ayl", method = RequestMethod.POST)
    public String saveForm(@ModelAttribute("adminYksLiik") AdminYksLiik ayl, 
    		               @RequestParam("ayl_alluv_ID") String alluv_ID,
    		               @RequestParam(required=false , value = "lisa_alluv") String lisa_alluv_Sub ,
//    		               @RequestParam("lisa_alluv") String lisa_alluv_Sub,//nupud
    		               @RequestParam(required=false , value = "save_ayl") String save_ayl_Sub,
    		               @RequestParam(required=false , value = "cancel_ayl") String cancel_Sub,
    		               @RequestParam(required=false , value = "eemalda_alluv") String eemalda_alluv_Sub,
                            ModelMap model) {

        int alluvID;
        if (!alluv_ID.isEmpty()) {
            alluvID = Integer.parseInt(alluv_ID);
        } else {
            alluvID = 0;
        }

        if (alluvID > 0) {
            //otsi see person välja , võta ta ära view allujate listist ja pane valitudallujate listi
            Long aylID = Long.parseLong(alluv_ID, 10);
            AdminYksLiik alluv = aylDao.aylById(aylID);
            
            AdminYksLiikView adw = getYlsAls();//view koos ülemuste ja alluvatega baasist
            adw.getAlluvad().remove(alluv);//see remove ei tööta?
            
            List<AdminYksLiik> valitudalluvad = new ArrayList<AdminYksLiik>();
            valitudalluvad.add(alluv);
            adw.setValitudalluvad(valitudalluvad);
            
            adw.setCurrent(ayl);
            model.addAttribute("adw", adw);

        } else { //kui alluvaid ei valita, siis salvestame baasi, hiljem tuleb siiski uurida, mis on parajasti valitudalluvad listi sisu ja sellega tegeleda
//            AdminYksLiik curr = new AdminYksLiik();
//            curr.setKood(ayl.getKood());
//            curr.setNimetus(ayl.getNimetus());
//            curr.setKommentaar(ayl.getKommentaar());
            //proovime baasi salvestada
            aylDao.store(ayl);
            //to do tagasipärimine nt koodi järgi (koodid peavad seega unikaalsed olema)
            //ülemuse Id järgi väljapärimine 
            //käesoleva lisamine ülemusele alluvaks
            //peale lisamist uuesti ülemused ja alluvad välja

            AdminYksLiikView adw2 = getYlsAls();
            model.addAttribute("adw", adw2);
            return "ayl";
        } 

        return "ayl";
    }

    private AdminYksLiikView getYlsAls() {
         AdminYksLiikView adw = new AdminYksLiikView();
        List<AdminYksLiik> ylemused = aylDao.getLiigid();
        adw.setYlemad(ylemused);

        //siin sellised  liigid, milledel pole veel alluvaid
        List<AdminYksLiik> alluvad = aylDao.getAlluvad();
        adw.setAlluvad(alluvad);
        return adw;
    }
}

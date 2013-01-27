package p4.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import p4.dao.*;
import p4.domain.*;
import p4.view.*;

@Controller
@RequestMapping("/admin")
@SessionAttributes({"ayw","viiv"})
public class AyKont {

	@Resource
	private AdminYksusDao ayDao;

	@RequestMapping(value = "/ay")
	public String Ay(ModelMap model) {
	        AdminYksusView viiv=(AdminYksusView) model.get("viiv");
               System.out.println("kas siia ays jouab");
                AdminYksus aYksus=viiv.getValitudAlluvYksus();
                System.out.println("pärast viiv.getvalitud");
                ayView ayw = new ayView();
                if(aYksus!=null) {
                ayw.setCurrent(aYksus);
            }
                else {
                ayw.setCurrent(new AdminYksus());
            }  
		ayw.setLiigid(ayDao.getLiigid());

		model.addAttribute("ayw", ayw);
                
		return "ay";
	}

	// meetod selleks, kui vajutatakse salvesta nuppu
	@RequestMapping(value = "/ay", method = RequestMethod.POST, params = "refresh")
	public String salvestaAyl(@Valid @ModelAttribute("ayw") ayView ayw, // BindingResult
																		// result,
			ModelMap model) {
            System.out.println("kas siia ays POST jouab");
		String liik_ID = ayw.getCurrent().getLiik_id();
		// to do - id järgi teha kindlaks, kes on selle liigi alluvad ja kellele
		// ta ise allub
		List<AdminYksLiik> liigid = ayw.getLiigid();

		Long ylemusID = null;

		List<Long> alluvateIDd = new ArrayList<Long>();
		
		Long ayl_ID = Long.parseLong(liik_ID, 10);
		// null on tühi
		if (ayl_ID > 0||ayl_ID!=null) {

		

			for (AdminYksLiik liik : liigid) { // võtame kõik liigid
				Long liigiID = liik.getId();

				if (liigiID!=null) {
					List<AdminYksLiik> liigiAlluvad = (List<AdminYksLiik>) liik
							.getSubordinates();

					for (AdminYksLiik alluv : liigiAlluvad) {

						if (ayl_ID == liigiID) {

							alluvateIDd.add(alluv.getId());// leitakse alluvate
															// ID-d
						}

						Long alluvaID = alluv.getId();
						if (ayl_ID == alluvaID) {
							ylemusID = liik.getId(); // leitakse ülemusüksuse ID
						}
					}
				}
			}
		}
		
		//kysi välja kõik võimalikud ylemused ja pane view külge
		//kysi välja kõik võimalikud alluvad ja pane view külge
		
		model.addAttribute("ayw", ayw);

		return "ay";
	}

}

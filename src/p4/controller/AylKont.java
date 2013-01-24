package p4.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.springframework.context.MessageSource;
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
@SessionAttributes("adw")
// luuakse sessioon, kus sees on adw nimeline objekt
public class AylKont {

	@Resource
	private AdminYksLiikDao aylDao;

	// @Resource
	// private MessageSource resources;

	@RequestMapping(value = "/ayl")
	public String Ayl(ModelMap model) {
		AdminYksLiikView adw = getYlsAls();
		adw.setCurrent(new AdminYksLiik());
		model.addAttribute("adw", adw);
		return "ayl"; // tagastame puu nime, käivitamisel muuda URL
						// http://localhost:8080/Kodutoo2/ayl
	}

	// meetod selleks, kui vajutatakse salvesta nuppu
	@RequestMapping(value = "/ayl", method = RequestMethod.POST, params = "save_ayl")
	public String salvestaAyl(
			@Valid @ModelAttribute("adw") AdminYksLiikView adw, BindingResult result,
			ModelMap model
			) {
		if (result.hasErrors()) {
			System.out.println("error "+result);
//			model.addAttribute("adw", adw);
			return "ayl";		
		}
				
		if (!result.hasErrors()) {
			model.addAttribute("message", "message.ok");			
		}
		// int alluvID;
		// if (save_ayl_Sub != null) {
		AdminYksLiik ayl=adw.getCurrent();
		aylDao.store(ayl);
		
		//küsime äsja baasi salvestatud liigi tagasi kolme tunnuse järgi, mis loodetavasti moodustavad unikaalse kombinatsiooni
		String kood=ayl.getKood();
		String nimi=ayl.getNimetus();
		String komm=ayl.getKommentaar();
		AdminYksLiik curr=aylDao.getCurrentByCode(kood, nimi, komm);
		
		//leiame ID järgi ülemuse ja paneme alluvaks, test ega Yl_id pole null, siis pole vaja ülemust panna
		String ylID=ayl.getYl_id();
		if(ylID!=null&&!ylID.isEmpty()){
				
		aylDao.setCurrToSub(ylID, curr);
		}
		// alluvate listi menetlemine
		List<AdminYksLiik> loplikudAlluvad=adw.getValitudalluvad();
		aylDao.setSubsToCurr(curr,loplikudAlluvad);
		
		

		adw = getYlsAls(); // uuesti nullist
		model.addAttribute("adw", adw);

		// else if (cancel_Sub != null) {
		// return "/"; // siin peaks olema tagasipöördumine indexisse, mida
		// // meil veel aretatud pole
		// }
		return "ayl";
	}

	// menetleme alluvate lisamist
	@RequestMapping(value = "/ayl", method = RequestMethod.POST, params = "lisa_alluv")
	public String lisaAlluv(
			@ModelAttribute("adw") AdminYksLiikView adw, // sessioonist listid
			@RequestParam("ayl_alluv_ID") String alluv_ID,
			ModelMap model) {

		

		String Yl_ID = adw.getCurrent().getYl_id();
		int alluvID;
		if (!alluv_ID.isEmpty() && !alluv_ID.equals(Yl_ID)) {
			alluvID = Integer.parseInt(alluv_ID);
			if (alluvID > 0) {
				// otsi see person välja , võta ta ära view allujate listist ja
				// pane valitudallujate listi
				Long aylID = Long.parseLong(alluv_ID, 10);

				List<AdminYksLiik> alluvad = adw.getAlluvad();
				int i = 0;
				for (AdminYksLiik alluv : alluvad) {
					Long ID = alluv.getId();
					if (aylID == ID) {
						i = alluvad.indexOf(alluv);
					}
				}
				if (i > 0) {
					AdminYksLiik alluv = alluvad.get(i);
					alluvad.remove(i);
					adw.getValitudalluvad().add(alluv);

					// võtame ülemuste listist ka ära
					List<AdminYksLiik> ylemad = adw.getYlemad();
					int a = 0;
					for (AdminYksLiik alluv2 : ylemad) {
						Long ID = alluv2.getId();
						if (aylID == ID) {
							a = ylemad.indexOf(alluv2);
						}
					}
					if (a > 0) {
						ylemad.remove(a);

					}
				}
			}
			adw.setErrormessage(null);// puhasta eelmisest veateatest
			model.addAttribute("adw", adw);

		} else { // keegi tola valib tühja alluva
			// kõik tagasi
			adw.setErrormessage("Seda administratiivüksust ei saa alluvaks panna, sest ta on valitud antud üksuse ülemuseks.");
			model.addAttribute("adw", adw);
		}
		return "ayl";
	}

	// menetleme valitud alluvate eemaldamist
	@RequestMapping(value = "/ayl", method = RequestMethod.POST)
	public String eemaldaAlluv(
			@ModelAttribute("adw") AdminYksLiikView adw, // sessioonist listid
			HttpServletRequest request, ModelMap model) {


		String alluv_ID = "";
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String paramName = params.nextElement();
			if (paramName.startsWith("eemalda_")) {
				// found the button from the list, get the id
				alluv_ID = paramName.substring(8);
			}
		}
		// otsi see person välja , võta ta ära view valitudalluvate listist ja
		// pane alluvate listi
		Long aylID = Long.parseLong(alluv_ID, 10);

		List<AdminYksLiik> valitudAlluvad = adw.getValitudalluvad();
		int i = 0;
		for (AdminYksLiik alluv : valitudAlluvad) {
			Long ID = alluv.getId();
			if (aylID == ID) {
				i = valitudAlluvad.indexOf(alluv);
			}
		}

		AdminYksLiik alluv = valitudAlluvad.get(i);
		valitudAlluvad.remove(i);

		adw.getAlluvad().add(alluv);
		// ylemuste listi ka tagasi
		adw.getYlemad().add(alluv);

		model.addAttribute("adw", adw);

		return "ayl";
	}

	private AdminYksLiikView getYlsAls() {
		AdminYksLiikView adw = new AdminYksLiikView();
		List<AdminYksLiik> ylemused = aylDao.getLiigid();
		adw.setYlemad(ylemused);

		// siin sellised liigid, milledel pole veel alluvaid
		List<AdminYksLiik> alluvad = aylDao.getAlluvad();
		adw.setAlluvad(alluvad);
		// tühi list alluvate ootele
		List<AdminYksLiik> valitudAlluvad = new ArrayList<AdminYksLiik>();
		adw.setValitudalluvad(valitudAlluvad);
		return adw;
	}
}

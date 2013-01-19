package p4.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import p4.domain.AdminYksLiik;
import p4.view.*;

@Repository
public class AdminYksLiikDao {
	
	@PersistenceContext
    private EntityManager em;
	
	  @Transactional  //esialgseks katsetuseks
	    public List<AdminYksLiikView> getAdmYksLiikAll(){
	    	
	    		
		  AdminYksLiik ayl = em.find(AdminYksLiik.class, 1L);
	    	
	    	List <AdminYksLiikView> list = new ArrayList<AdminYksLiikView>();
	    	//tegelikult tahaks sellist listi, kus on kõik baasi liikmed sees
	    	lisaAYL(list,ayl);
	    	return list;
	    	
	    }
	  //meetod esialgseks katsetuseks
	  private void lisaAYL(List<AdminYksLiikView> list, AdminYksLiik ayl) {
			
		  AdminYksLiikView aylw = new AdminYksLiikView();
			aylw.setNimetus(ayl.getNimetus());
			aylw.setKood(ayl.getKood());
			aylw.setKommentaar(ayl.getKommentaar());
			list.add(aylw);
	  }
	  
	  @Transactional  //ylemusteks võivad uuele liigle sobida k6ik
	    public List<AdminYksLiik> getLiigid(){
		  
		  List <AdminYksLiik> liigid  = new ArrayList<AdminYksLiik>();
		//lisame kõige ette tyhja
		  liigid.add(new AdminYksLiik());
		  TypedQuery<AdminYksLiik> query = em.createQuery("select a from AdminYksLiik a", AdminYksLiik.class);
		  liigid.addAll(query.getResultList());
		  return liigid;
	  }
	public List<AdminYksLiik> getAlluvad() {
		//kõik liigid
		List <AdminYksLiik> koik  = new ArrayList<AdminYksLiik>();
		//juba alluvad
		List <AdminYksLiik> allujad  = new ArrayList<AdminYksLiik>();
		//potentsiaalsed alluvad
		List <AdminYksLiik> alluvad  = new ArrayList<AdminYksLiik>();
		 
		 TypedQuery<AdminYksLiik> all = em.createQuery("select a from AdminYksLiik a", AdminYksLiik.class);
		 
		 koik.addAll(all.getResultList());
		 for(AdminYksLiik adl:koik){
			allujad.addAll(adl.getSubordinates()) ;
		 }
		 koik.removeAll(allujad);
         alluvad.add(new AdminYksLiik());
         alluvad.addAll(koik);
		 
		  return alluvad;	
	}
	
	 @Transactional
	  public void store(AdminYksLiik ayl) {
	      //eelnevalt tuleb  moodustada korralik AdminYksLiik, sest vormilt tagasi saame praegu ainult osad andmed  
		 AdminYksLiik lisatav=createAdmYksLiik(ayl.getKood(),ayl.getNimetus(),ayl.getKommentaar());
		 em.merge(lisatav); 
	    }
	 
	    //vormilt tagasi tulnud andmed töödeldakse, et moodustada baasi lisamiseks korralik objekt
	    private AdminYksLiik createAdmYksLiik(String kood, String nimetus, String komm) {
	        
	        AdminYksLiik ayl = new AdminYksLiik();
	        ayl.setKood(kood);
	        ayl.setNimetus(nimetus);
	        ayl.setKommentaar(komm);
	        ayl.setAvaja("Anneli");// hardcoded
	        ayl.setAvatud(currDate());
	        ayl.setMuutja("PoleVeel");
	        ayl.setMuudetud("9999-01-01");
	        ayl.setSulgeja("pole");
	        ayl.setSuletud("9999-01-01");
	        
	        System.out.println("Lisan " + nimetus);

	        return ayl;
	    }
	    
	    private String currDate() {

	        Calendar jCal = Calendar.getInstance();

	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        String date = sdf.format(jCal.getTime());

	        return date;
	    }
	    
	    @Transactional  //hangib vastavalt ID-le
	    public AdminYksLiik aylById(Long aylID){
	    		    	
		  AdminYksLiik ayl = em.find(AdminYksLiik.class, aylID);
	  
	    	return ayl;
	    	
	    }
	  

}

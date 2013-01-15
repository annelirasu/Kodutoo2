package p4.dao;

import java.util.ArrayList;
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
	  
	  @Transactional
	    public List<AdminYksLiik> getLiigid(){
		  
		  List <AdminYksLiik> liigid  = new ArrayList<AdminYksLiik>();
		//lisame kõige ette tyhja
		  liigid.add(new AdminYksLiik());
		  TypedQuery<AdminYksLiik> query = em.createQuery("select a from AdminYksLiik a", AdminYksLiik.class);
		  liigid.addAll(query.getResultList());
		  return liigid;
	  }
	public List<AdminYksLiik> getAlluvad() {
		//potentsiaalsed alluvad
		List <AdminYksLiik> koik  = new ArrayList<AdminYksLiik>();
		//juba alluvad
		List <AdminYksLiik> allujad  = new ArrayList<AdminYksLiik>();
		
		 TypedQuery<AdminYksLiik> all = em.createQuery("select a from AdminYksLiik a", AdminYksLiik.class);
		 koik.addAll(all.getResultList());
		 for(AdminYksLiik adl:koik){
			allujad.addAll(adl.getSubordinates()) ;
						 
		 }
		 koik.removeAll(allujad);
		 
		  return koik;
		
	}
	  

}

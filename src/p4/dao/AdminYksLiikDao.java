package p4.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import p4.domain.AdminYksLiik;
import p4.view.*;

@Repository
public class AdminYksLiikDao {
	
	@PersistenceContext
    private EntityManager em;
	
	  @Transactional
	    public List<AdminYksLiikView> getAdmYksLiikAll(){
	    	
	    		
		  AdminYksLiik ayl = em.find(AdminYksLiik.class, 1L);
	    	
	    	List <AdminYksLiikView> list = new ArrayList<AdminYksLiikView>();
	    	//tegelikult tahaks sellist listi, kus on kõik baasi liikmed sees
	    	lisaAYL(list,ayl);
	    	return list;
	    	
	    }
	  
	  private void lisaAYL(List<AdminYksLiikView> list, AdminYksLiik ayl) {
			
		  AdminYksLiikView aylw = new AdminYksLiikView();
			aylw.setNimetus(ayl.getNimetus());
			aylw.setKood(ayl.getKood());
			aylw.setKommentaar(ayl.getKommentaar());
			list.add(aylw);
	  }

}

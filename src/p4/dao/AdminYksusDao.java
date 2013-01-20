package p4.dao;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import p4.domain.AdminYksus;

@Repository
public class AdminYksusDao {
   @PersistenceContext
    private EntityManager em;
	
	  @Transactional 
	    public List<AdminYksus> getAdmYksAll(){
	    	 Query q = em.createQuery("SELECT e FROM AdminYksus e");
	    return q.getResultList(); 
	     	
	    }
    
    
}

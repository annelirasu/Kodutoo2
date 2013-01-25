package p4.dao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import p4.domain.AdminYksLiik;
import p4.domain.AdminYksus;

@Repository
@Transactional 
@Service
public class AdminYksusDao {
   @PersistenceContext
    private EntityManager em;
	
	  @Transactional 
	    public List<AdminYksus> getAdmYksAll(){
	    	 Query q = em.createQuery("SELECT e FROM AdminYksus e");
	    return q.getResultList(); 
	     	
	    }
          
          //leiab esimese taseme alluvad
          @Transactional 
          public Collection<AdminYksus> getChilds(AdminYksus ay){
              /*
             "select riigi_admin_yksus_id,nimetus from RIIGI_ADMIN_YKSUS Where( riigi_admin_yksus_id  in (select alluv_yksus_id  from ADMIN_ALLUVUS where ylemus_yksus_ID=?))";
              TypedQuery<AdminYksus> query = em.createQuery("select e from ADMINALLUVUS e where e.id = :id",AdminYksus.class);
                query.setParameter("id", 4L);
            List<AdminYksus> employees = query.getResultList();
            */
              //tiri kõik päringutega mällu
              Long id=ay.getId();
              System.out.println("enne päringut sisenes ay nimega" + ay.getId());
               TypedQuery<AdminYksus> query = em.createQuery("select a from AdminYksus a where a.id="+id, AdminYksus.class);
               System.out.println("pärast päringut");
               List<AdminYksus> ayList=query.getResultList();
                System.out.println("pärast listi");
              Collection alluvad=ayList.get(0).getAlluvad();
              if(alluvad==null){System.out.println("Allvaid pole " + ay.getNimetus());}
              else
              {System.out.println("Alluvid on " + alluvad.size()+ " ayksusel " + ay.getNimetus());}
              return alluvad;
          
          }
    
         //anna adminüksus id järgi
          @Transactional 
         public AdminYksus getYksusById(Long id){
          AdminYksus ay = em.find(AdminYksus.class, id);
          return ay;
         }
    
}

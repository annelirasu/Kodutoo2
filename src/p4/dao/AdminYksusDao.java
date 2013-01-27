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
	public List<AdminYksus> getAdmYksAll() {
		Query q = em.createQuery("SELECT e FROM AdminYksus e");
		return q.getResultList();

	}

	// leiab esimese taseme alluvad
	@Transactional
	public Collection<AdminYksus> getChilds(AdminYksus ay) {
		
		// tiri kõik päringutega mällu
		Long id = ay.getId();
		//System.out.println("enne päringut sisenes ay nimega" + ay.getId());
		TypedQuery<AdminYksus> query = em
				.createQuery("select a from AdminYksus a where a.id=" + id,
						AdminYksus.class);
		//System.out.println("pärast päringut");
		List<AdminYksus> ayList = query.getResultList();
		//System.out.println("pärast listi");
		Collection alluvad = ayList.get(0).getAlluvad();
		//see if peab olema sees muidu
                //org.hibernate.LazyInitializationException: could not initialize proxy - no Session
                if (alluvad == null) {
			System.out.println("Allvaid pole " + ay.getNimetus());
		} else {
			System.out.println("Alluvaid on " + alluvad.size() + " ayksusel "
					+ ay.getNimetus());
		}
                
                
		return alluvad;

	}

	// anna adminüksus id järgi
	@Transactional
	public AdminYksus getYksusById(Long id) {
		AdminYksus ay = em.find(AdminYksus.class, id);
		return ay;
	}
        
        // anna adminüksused liigi id järgi
	@Transactional
	public List<AdminYksus> getYksusByLiikId(Long id) {
            TypedQuery<AdminYksus> query = em.createQuery("select a from AdminYksus a",
						AdminYksus.class);
		//System.out.println("pärast päringut");
		List<AdminYksus> ayList = query.getResultList();
                ArrayList<AdminYksus> valitudAyList=new ArrayList<AdminYksus>();
                System.out.println("aylisti suurus"+ayList.size());
                for(int a=0;a<ayList.size();a++){
                    AdminYksus ay=ayList.get(a);
                    
                if(ay.getAdmykId().getId()==id){
                    System.out.println("Leiti"+ay.getNimetus());
                    valitudAyList.add(ay);
                }
                }
		return valitudAyList;
	}
        
        
        // anna adminüksused liigi id järgi
	@Transactional
	public List<AdminYksus> getYksusByLiik(AdminYksLiik ayl) {
            TypedQuery<AdminYksus> query = em.createQuery("select a from AdminYksus a",
						AdminYksus.class);
		//System.out.println("pärast päringut");
		List<AdminYksus> ayList = query.getResultList();
                ArrayList<AdminYksus> valitudAyList=new ArrayList<AdminYksus>();
                System.out.println("aylisti suurus"+ayList.size());
                for(int a=0;a<ayList.size();a++){
                    AdminYksus ay=ayList.get(a);
                    
                if(ay.getAdmykId().getId()==ayl.getId()){
                    System.out.println("Leiti"+ay.getNimetus());
                    valitudAyList.add(ay);
                }
                }
		return valitudAyList;
	}
        

        
	@Transactional
	public void dropAdminYksusTable() {

		List<AdminYksus> yd = new ArrayList<AdminYksus>();
		TypedQuery<AdminYksus> query = em.createQuery(
				"select a from AdminYksus a", AdminYksus.class);
		yd.addAll(query.getResultList());

		for (AdminYksus ay : yd) {
			em.remove(ay);
		}

	}

	public List<AdminYksLiik> getLiigid() {
		List<AdminYksLiik> liigid = new ArrayList<AdminYksLiik>();
		liigid.add(new AdminYksLiik());
		liigid.addAll(selectAll());
		return liigid;

	}

	@Transactional
	// kordan, praegu ei tea muud lahendust, meetod on liigi Daos küll olemas
	public List<AdminYksLiik> selectAll() {
		List<AdminYksLiik> liigid = new ArrayList<AdminYksLiik>();
		TypedQuery<AdminYksLiik> query = em.createQuery(
				"select a from AdminYksLiik a", AdminYksLiik.class);
		liigid.addAll(query.getResultList());
		return liigid;
	}

}

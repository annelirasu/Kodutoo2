package p4.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import javax.annotation.Resource;
import p4.dao.AdminYksLiikDao;




@Repository
public class InsertTestData {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void doTheMagic() {
        insertData();
        displayData();
    }

//	public static void main(String[] args) {
//		insertData();
//		displayData();
//	}
    @Transactional
    private void insertData() {
        
         AdminYksLiikDao aylDao= new AdminYksLiikDao();

        AdminYksLiik al1 = aylDao.createAdmYksLiik("mk", "maakond", "---");
        AdminYksLiik al2 = aylDao.createAdmYksLiik("ln", "linn", "allub maakonnale");
        AdminYksLiik al3 = aylDao.createAdmYksLiik("rk", "ringkond", "on suurem \u00fcksus kui maakond");
        AdminYksLiik al4 = aylDao.createAdmYksLiik("vl", "vald", "allub maakonnale");
        AdminYksLiik al5 = aylDao.createAdmYksLiik("kl", "k\u00fcla", "allub vallale");
        AdminYksLiik al6 = aylDao.createAdmYksLiik("lo", "linnaosa", "allub linnale");

        em.persist(al1); // Make an entity instance managed and persistent.
        em.persist(al2);
        em.persist(al3);
        em.persist(al4);
        em.persist(al5);
        em.persist(al6);

        em.flush();// Synchronize the persistence context to the underlying
        // database.
        em.refresh(al1); // Refresh the state of the instance from the database,
        // overwriting changes made to the entity, if any.
        em.refresh(al2);
        em.refresh(al3);
        em.refresh(al4);
        em.refresh(al5);
        em.refresh(al6);
        

        al1.getSubordinates().add(al2);// maakonnale allub linn
        al3.getSubordinates().add(al1);// ringkonnale maakond
        al1.getSubordinates().add(al4); // maakonnale vald
    }

 
    @Transactional
    private void displayData() {
        AdminYksLiik ayl = em.find(AdminYksLiik.class, 1L); //finds by primary key
        System.out.println(ayl.getAvatud());
        showSubs(ayl, 0);
    }

    private void showSubs(AdminYksLiik ayl, int level) {
        System.out.println(level + " " + ayl);

        for (AdminYksLiik sub : ayl.getSubordinates()) {
            showSubs(sub, level + 1);
        }

    }  
}

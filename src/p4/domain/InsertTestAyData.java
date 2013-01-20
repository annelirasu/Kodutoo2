/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package p4.domain;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.logging.Logger;




/**
 *
 * @author reget.kalamees
 */
@Repository
public class InsertTestAyData {
    	@PersistenceContext
    private EntityManager em;
	
	@Transactional
        public void insertData() {
            AdminYksus ay1=createAy("V145","Leisi vald","saarel",stod("2001-12-12"),stod("9999-12-31"),4);
            AdminYksus ay2=createAy("V146","Orissaare vald","saarel",stod("2001-12-12"),stod("9999-12-31"),4);
            AdminYksus ay3=createAy("M146","Harju maakond","suure rahvastikutihedusega",stod("2001-12-12"),stod("9999-12-31"),1);
            AdminYksus ay4=createAy("L145","Kuressaare linn","saarel",stod("2001-12-12"),stod("9999-12-31"),2);
            AdminYksus ay5=createAy("A145","Orissaare alev","saarel",stod("2001-12-12"),stod("9999-12-31"),3);
            AdminYksus ay6=createAy("M145","Saare maakond","saarel",stod("2001-12-12"),stod("9999-12-31"),4);
            //System.out.println(ay1.getAdmykId());
           
            em.persist(ay1);
            em.persist(ay2);
            em.persist(ay3);
            em.persist(ay4);
            em.persist(ay5);
            em.persist(ay6);
            em.flush();
            em.refresh(ay1);
            em.refresh(ay2);
            em.refresh(ay3);
            em.refresh(ay4);
            em.refresh(ay5);
            em.refresh(ay6);
            
            ay6.getAlluvad().add(ay1);
            ay6.getAlluvad().add(ay2);
            ay2.getAlluvad().add(ay5);
        
        }
        
        private Date stod(String kp){
        SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
         try {
            return sdf.parse(kp);
        } catch (ParseException ex) {
           return null;
        }
        
        }
        
        
        
        
        //mk -1, linn -2 -rk -3 vald 4
        private AdminYksus createAy(String kood,String nimi, String komm, Date alates,Date kuni,long liik_id){
        AdminYksus ay=new AdminYksus();
        AdminYksLiik ayl = em.find(AdminYksLiik.class, liik_id);
        ay.setAdmykId(ayl);
        ay.setAlates(alates);
        ay.setAvaja("R");
        ay.setAvatud(new Date());
        ay.setKommentaar(komm);
        ay.setKood(kood);
        ay.setKuni(kuni);
        ay.setMuudetud(new Date());
        ay.setMuutja("R");
        ay.setNimetus(nimi);
        ay.setSuletud(stod("9999-12-31"));
        ay.setSulgeja("-");
             
        return ay;
        }
    
    
}

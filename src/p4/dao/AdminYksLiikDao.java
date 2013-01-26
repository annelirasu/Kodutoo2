package p4.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import p4.domain.AdminYksLiik;
import p4.view.*;

@Repository
public class AdminYksLiikDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	// esialgseks katsetuseks
	public void dropAdminYksLiikTable() {

		List<AdminYksLiik> liigid = new ArrayList<AdminYksLiik>();
		TypedQuery<AdminYksLiik> query = em.createQuery(
				"select a from AdminYksLiik a", AdminYksLiik.class);
		liigid.addAll(query.getResultList());

		for (AdminYksLiik ayl : liigid) {
			em.remove(ayl);
		}
	}

	@Transactional
	public List<AdminYksLiik> selectAll() {
		List<AdminYksLiik> liigid = new ArrayList<AdminYksLiik>();
		TypedQuery<AdminYksLiik> query = em.createQuery(
				"select a from AdminYksLiik a", AdminYksLiik.class);
		liigid.addAll(query.getResultList());
		return liigid;
	}

	public AdminYksLiikView getAdmYksLiikAll() {
		List<AdminYksLiik> liigid = selectAll();
		AdminYksLiikView adw = new AdminYksLiikView();
		adw.setYlemad(liigid);
		return adw;
	}

	@Transactional
	// küsime tagasi äsjasisestatud liigi
	public AdminYksLiik getCurrentByCode(String kood, String nimi, String komm) {

		TypedQuery<AdminYksLiik> query = em
				.createQuery(
						"select a from AdminYksLiik a where a.kood=? AND a.nimetus=? AND a.kommentaar=?",
						AdminYksLiik.class);
		query.setParameter(1, kood);
		query.setParameter(2, nimi);
		query.setParameter(3, komm);
		AdminYksLiik curr = query.getSingleResult();

		return curr;
	}

	@Transactional
	// küsime tagasi äsjasisestatud liigi
	public void setCurrToSub(String Yl_id, AdminYksLiik alluv) {
		Long ylem_ID = Long.parseLong(Yl_id, 10);
		AdminYksLiik ylemus = aylById(ylem_ID);
		ylemus.getSubordinates().add(alluv);
	}

	@Transactional
	public void setSubsToCurr(AdminYksLiik curr,
			List<AdminYksLiik> loplikudAlluvad) {
		Long currID = curr.getId();
		AdminYksLiik ylemus = aylById(currID);
		for (AdminYksLiik alluv : loplikudAlluvad) {
			ylemus.getSubordinates().add(alluv);
		}

	}

	@Transactional
	// ylemusteks võivad uuele liigle sobida k6ik
	public List<AdminYksLiik> getLiigid() {

		List<AdminYksLiik> liigid = new ArrayList<AdminYksLiik>();
		// lisame kõige ette tyhja
		liigid.add(new AdminYksLiik());
		liigid.addAll(selectAll());
		return liigid;
	}

	// meetod tagastab kõik liigid, kes veel ei allu, ehk kes võivad olla ka
	// potentsiaalsed alluvad, pole välistatud et on ülemad.
	public List<AdminYksLiik> getAlluvad() {
		// kõik liigid
		List<AdminYksLiik> koik = selectAll();
		// juba alluvad
		List<AdminYksLiik> allujad = new ArrayList<AdminYksLiik>();
		// potentsiaalsed alluvad
		List<AdminYksLiik> alluvad = new ArrayList<AdminYksLiik>();

		for (AdminYksLiik adl : koik) {
			allujad.addAll(adl.getSubordinates());
		}
		koik.removeAll(allujad);
		alluvad.add(new AdminYksLiik());
		alluvad.addAll(koik);

		return alluvad;
	}

	@Transactional
	public void store(AdminYksLiik ayl) {
		// eelnevalt tuleb moodustada korralik AdminYksLiik, sest vormilt tagasi
		// saame praegu ainult osad andmed
		AdminYksLiik lisatav = createAdmYksLiik(ayl.getKood(),
				ayl.getNimetus(), ayl.getKommentaar());
		em.merge(lisatav);
	}

	// vormilt tagasi tulnud andmed töödeldakse, et moodustada baasi lisamiseks
	// korralik objekt
	public AdminYksLiik createAdmYksLiik(String kood, String nimetus,
			String komm) {

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

	@Transactional
	// hangib vastavalt ID-le
	public AdminYksLiik aylById(Long aylID) {

		AdminYksLiik ayl = em.find(AdminYksLiik.class, aylID);

		return ayl;

	}

	public List<StruktSolm> struktforEach() {

		List<StruktSolm> forEach = new ArrayList<StruktSolm>();

		List<AdminYksLiik> potYlemused = getAlluvad(); // eksitav meetodi
														// nimi,kuna tegemist
														// võib olla
														// potentsiaalsete
														// alluvatega aga ka
														// võimalike ülemustega
		for (AdminYksLiik ayl : potYlemused) {
			List<StruktSolm> solm = getAylRows(ayl);
			forEach.addAll(solm);
		}

		return forEach;
	}

	@Transactional
	public List<StruktSolm> getAylRows(AdminYksLiik ayl) { // see list on
															// tegelikult ainult
															// ühe alluva kohta

		// me ei tea, ID-sid, küsime kõik välja
		// on maakond ja välja joonistuvad maakond ja maakonna alluvad.
		// samas maakond on ise riigi alluv?

		List<StruktSolm> list = new ArrayList<StruktSolm>();

		dumpDataTo(list, ayl, 0);

		return list;
	}

	private void dumpDataTo(List<StruktSolm> list, AdminYksLiik ayl, int tasand) {
		StruktSolm solm = new StruktSolm();
		solm.setTasand(tasand);
		solm.setNimetus(ayl.getNimetus());
		list.add(solm);

		Collection<AdminYksLiik> aylAlluvad = ayl.getSubordinates();
		if (aylAlluvad != null) {
			for (AdminYksLiik alluv : aylAlluvad) {
				dumpDataTo(list, alluv, tasand + 1);
			}

		}
	}
}

package p4.domain;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class AdminYksLiik {

	@Id
	@GeneratedValue
	@Column(name = "admYksLiik_id")
	private Long id;  

	private String kood; 
	private String nimetus;
	private String kommentaar;
	private String avaja;
	private String avatud;
	private String muutja;
	private String muudetud;
	private String sulgeja;
	private String suletud;
	
	//ylemuse ID vormilt kinnipüüdmiseks
	private Long yl_id;
	
	public AdminYksLiik() {

	}

	   @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	            name="VoimalikAlluvus",
	            joinColumns={@JoinColumn(name="ylem_id", referencedColumnName="admYksLiik_id")},
	            inverseJoinColumns={@JoinColumn(name="alluv_id", referencedColumnName="admYksLiik_id")})
	    private Collection<AdminYksLiik> subordinates;

           //seos liigi ja üksuse vahel
           @OneToMany(mappedBy="admykId")
           private Collection<AdminYksus> aYs; 
           public Collection<AdminYksus> getAys() { return aYs; }


	@Override
		public String toString() {
		return  nimetus ;
		}  

	//getters-setters

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKood() {
		return kood;
	}
	public void setKood(String kood) {
		this.kood = kood;
	}
	public String getNimetus() {
		return nimetus;
	}
	public void setNimetus(String nimetus) {
		this.nimetus = nimetus;
	}
	public String getKommentaar() {
		return kommentaar;
	}
	public void setKommentaar(String kommentaar) {
		this.kommentaar = kommentaar;
	}
	public String getAvaja() {
		return avaja;
	}
	public void setAvaja(String avaja) {
		this.avaja = avaja;
	}
	public String getAvatud() {
		return avatud;
	}
	public void setAvatud(String avatud) {
		this.avatud = avatud;
	}
	public String getMuutja() {
		return muutja;
	}
	public void setMuutja(String muutja) {
		this.muutja = muutja;
	}
	public String getMuudetud() {
		return muudetud;
	}
	public void setMuudetud(String muudetud) {
		this.muudetud = muudetud;
	}
	public String getSulgeja() {
		return sulgeja;
	}
	public void setSulgeja(String sulgeja) {
		this.sulgeja = sulgeja;
	}
	public String getSuletud() {
		return suletud;
	}
	public void setSuletud(String suletud) {
		this.suletud = suletud;
	}

	 public Collection<AdminYksLiik> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Collection<AdminYksLiik> subordinates) {
		this.subordinates = subordinates;
	}


	public Long getYl_id() {
		return yl_id;
	}


	public void setYl_id(Long yl_id) {
		this.yl_id = yl_id;
	}


}
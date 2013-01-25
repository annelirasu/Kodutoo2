package p4.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
public class AdminYksLiik implements Serializable {
     private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "admYksLiik_id")
	private Long id;  
	
	@NotEmpty
	@Size(min = 1, max = 10)
	private String kood; 
    
	@NotEmpty
	@Size(min = 1, max = 50)
	private String nimetus;
	
	@NotEmpty
	@Size(min = 1, max = 300)
	private String kommentaar;
	
        @Column(length = 50, nullable = false)
	private String avaja;
        @Column(length = 12, nullable = false)
	private String avatud;
        @Column(length = 50, nullable = false)
	private String muutja;
        @Column(length = 12, nullable = false)
	private String muudetud;
        @Column(length = 50)
	private String sulgeja;
        @Column(length = 32, nullable = false)
	private String suletud;
	
	//ylemuse ID vormilt kinnipüüdmiseks
        private String yl_id;
	


	public AdminYksLiik() {

	}

	   @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	            name="VoimalikAlluvus",
	            joinColumns={@JoinColumn(name="ylem_id", referencedColumnName="admYksLiik_id")},
	            inverseJoinColumns={@JoinColumn(name="alluv_id", referencedColumnName="admYksLiik_id")})
	    private Collection<AdminYksLiik> subordinates;

           //seos liigi ja üksuse vahel
           @OneToMany(mappedBy="admykId", orphanRemoval=true)
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
	public String getYl_id() {
		return yl_id;
	}

	public void setYl_id(String yl_id) {
		this.yl_id = yl_id;
	}


}

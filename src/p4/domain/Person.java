package p4.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Person {

    @Id
    @GeneratedValue
    @Column(name = "tootaja_id")
    private Long id;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToMany
    @JoinTable(
            name="alluvus",
            joinColumns={@JoinColumn(name="ylem_id", referencedColumnName="tootaja_id")},
            inverseJoinColumns={@JoinColumn(name="alluv_id", referencedColumnName="tootaja_id")})
    private Collection<Person> subordinates;
    
    public Collection<Person> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Collection<Person> subordinates) {
		this.subordinates = subordinates;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		//return "Person [id=" + id + ", subordinates=" + subordinates
		//		+ ", name=" + name + "]";
		
		return "Person [id=" + getId() + ", name=" + getName() + "]";
	}
	

}

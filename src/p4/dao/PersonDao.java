package p4.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import p4.domain.Person;
import p4.view.*;


@Repository
public class PersonDao {

    @PersistenceContext
    private EntityManager em;
    
    //tahame saada jspe-le tree-d valja. Siin see rekursioon on kahtlane kah.
    @Transactional
    public List<TreeNode> getPersonRows(){
    	//rekursioon tuleks kuidagi siin transaktsioonis lahendada, väljaprint astmeline
    	
    	
    	Person person = em.find(Person.class, 1L);
    	
    	List <TreeNode> list = new ArrayList<TreeNode>();
    	//rek valjakutse alustades levelist 0
    	dumpDataTo(list, person, 0);
    	return list;
    	
    }

	private void dumpDataTo(List<TreeNode> list, Person person, int level) {
		
		TreeNode treeNode = new TreeNode();
		treeNode.setLevel(level);
		treeNode.setName(person.getName());
		list.add(treeNode);
		
		//rekursiivne väljakutse aste-astmelt
		for (Person sub : person.getSubordinates()) {
			dumpDataTo(list, sub, level + 1);
		}
		
	}

}

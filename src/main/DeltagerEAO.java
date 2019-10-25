package main;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class DeltagerEAO {
	
	@PersistenceContext(name = "DeltagerPersistenceUnit")
	private EntityManager em;
	
//	LEGG TIL DELTAGER
	public void leggTilDeltager(Deltager deltager) {
		em.persist(deltager);
	}
	
//	LEGG TIL PASSORD
	public void leggTilPassord(Passord passord) {
		em.persist(passord);
	}
	
//	SJEKK OM DELTAGER FINNES
	public boolean deltagerFinnes(String mobil) {
	     return getDeltagere(mobil).size() == 1;
	}
	
	private List<Deltager> getDeltagere(String mobil) {
	      //create an ejbql expression
	      String ejbQL = "FROM Deltager d WHERE d.mobil LIKE ?1";
	      //create query
	      Query query = em.createQuery(ejbQL);
	      //substitute parameter.
	      query.setParameter(1, mobil);   
	      //execute the query
	      return query.getResultList();
	   } 
	
	public Deltager getDeltager(String mobil) {
		//create an ejbql expression
	      String ejbQL = "FROM Deltager d WHERE d.mobil LIKE ?1";
	      //create query
	      Query query = em.createQuery(ejbQL);
	      //substitute parameter.
	      query.setParameter(1, mobil);   
	      //execute the query
	      return (Deltager)query.getSingleResult();
	}
	
	public List<Deltager> getDeltagere() {
	      //create an ejbql expression
	      String ejbQL = "FROM Deltager d";
	      //create query
	      Query query = em.createQuery(ejbQL);
	      //execute the query
	      return query.getResultList();
	   } 

	public String getPassord(String mobil) {
		Deltager d = getDeltager(mobil);
		return d.getPassord().getPassord();
	}
}

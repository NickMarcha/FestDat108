package main;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	// Bruker EM er sql injection safe
	public boolean deltagerFinnes(String mobil) {
		Deltager d = em.find(Deltager.class, mobil);
		return d != null && d.getMobil().equals(mobil);
	}

	/*
	@SuppressWarnings("unchecked")
	private List<Deltager> getDeltagere(String mobil) {
	      //create an ejbql expression
	      String ejbQL = "FROM Deltager d WHERE d.mobil LIKE ?1";
	      //create query
	      Query query = em.createQuery(ejbQL);
	      //substitute parameter.
	      query.setParameter(1, mobil);   
	      //execute the query
	      return (List<Deltager>) query.getResultList();
	   } 
	 */
	//denne går gjennom em direkte og er safe for injeksjon
	public Deltager getDeltager(String mobil) {
		return em.find(Deltager.class, mobil);
	}


	//safe fordi den ikke bruker user input
	@SuppressWarnings("unchecked")
	public List<Deltager> getDeltagere() {
		//create an ejbql expression
		String ejbQL = "FROM Deltager d";
		//create query
		Query query = em.createQuery(ejbQL);
		//execute the query
		return (List<Deltager>)query.getResultList();
	} 

	public String getPassord(String mobil) {
		Deltager d = getDeltager(mobil);
		return d.getPassord().getPassord();
	}
}

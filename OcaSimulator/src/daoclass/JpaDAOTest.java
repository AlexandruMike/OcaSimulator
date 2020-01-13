package daoclass;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import daofactory.JpaDAOFactory;
import daointerface.TestDAO;
import model.Test;
import model.Utente;

public class JpaDAOTest implements TestDAO {
	
	// Singleton//
	private static JpaDAOTest instance = null;

	private JpaDAOTest() {
		// TODO Auto-generated constructor stub
	}

	public synchronized static JpaDAOTest getInstance() {
		if (instance == null) {
			instance = new JpaDAOTest();
		}
		return instance;
	}

	@Override
	public List<Test> visualizzaStorico(Utente utente) {
		if(utente.getRuolo().equalsIgnoreCase("dip")){
			return visualizzaStoricoSingoloDipendente();
		}else{
			return visualizzaStoricoSingoloUtente(utente);
		}
			}

	@Override
	public List<Test> visualizzaStoricoSingoloUtente(Utente studente) {
		// TODO Auto-generated method stub
	    List<Test> storico = null;
	    try {
	      EntityManager manager = JpaDAOFactory.getEntityManager();
	      //String query = "SELECT t FROM Test t JOIN Utente u WHERE u.id=:idstud ";
	      String query="SELECT t from Test t WHERE t.utente.id=:idstud";
	      				//select * from test join utente where utente.id=test.id_studente;//
	      Query q = manager.createQuery(query);
	      Utente u=manager.find(Utente.class,studente.getId());
	      q.setParameter("idstud", u.getId()); 
	      EntityTransaction transaction = manager.getTransaction();
	      transaction.begin();
	      storico =  q.getResultList();
	      transaction.commit();
	      return storico;
	    } catch (Exception e) {
	      System.out.println("problema in jpaDAOUtente.visualizzaRichiesteRegistrazione()");
	      e.printStackTrace();
	      return null;
	    }

	}

	@Override
	public List<Test> visualizzaStoricoSingoloDipendente() {
		List<Test> tests= null;
		try {
			EntityManager manager = JpaDAOFactory.getEntityManager();
			tests = manager.createNamedQuery("Test.findAll").getResultList();
			Collections.sort(tests);
			return tests;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean salvaTest(Test test) {
		// TODO Auto-generated method stub
		try {
			EntityManager manager = JpaDAOFactory.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(test);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
}

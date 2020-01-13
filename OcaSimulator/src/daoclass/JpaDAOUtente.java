package daoclass;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import daofactory.JpaDAOFactory;
import daointerface.UtenteDAO;
import model.Utente;

public class JpaDAOUtente implements UtenteDAO {
	
	// Singleton//
	private static JpaDAOUtente instance = null;

	private JpaDAOUtente() {
		// TODO Auto-generated constructor stub
	}

	public synchronized static JpaDAOUtente getInstance() {
		if (instance == null) {
			instance = new JpaDAOUtente();
		}
		return instance;
	}

	@Override
	public Utente login(String email, String password) {
		// TODO Auto-generated method stub
		Utente u = null;
		
		try {
			EntityManager manager = JpaDAOFactory.getEntityManager();
			String query = "SELECT u FROM Utente u WHERE u.email=:email AND u.password=:password";
			Query q = manager.createQuery(query);
			q.setParameter("email", email);
			q.setParameter("password", password);
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			u = (Utente) q.getSingleResult();
			transaction.commit();
			return u;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public boolean aggiungiUtente(Utente utente) {
		// TODO Auto-generated method stub
		try {
			EntityManager manager = JpaDAOFactory.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(utente);
			transaction.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Utente> selezionaDipendenti() {
		// TODO Auto-generated method stub
	    List<Utente> u = null;
	    try {
	      EntityManager manager = JpaDAOFactory.getEntityManager();
	      String query = "SELECT u FROM Utente u WHERE u.ruolo=:dip AND u.accesso=:access";
	      Query q = manager.createQuery(query);
	      q.setParameter("dip", "dip");
	      q.setParameter("access", 1);  
	      EntityTransaction transaction = manager.getTransaction();
	      transaction.begin();
	      u = (List<Utente>) q.getResultList();
	      transaction.commit();
	      return u;
	    } catch (Exception e) {
	      System.out.println("problema in jpaDAOUtente.visualizzaRichiesteRegistrazione()");
	      e.printStackTrace();
	      return null;
	    }
		
	}

	@Override
	public boolean eliminaDipendente(Utente utente) {
		// TODO Auto-generated method stub
			
		try {
			EntityManager manager = JpaDAOFactory.getEntityManager();
			String query = "UPDATE Utente u SET u.accesso=:remove WHERE u.id=:idu";
			Query q = manager.createQuery(query);
			q.setParameter("remove", 0);
			q.setParameter("idu", utente.getId());
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			q.executeUpdate();
			transaction.commit();
			return true;
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		//UPDATE utente SET accesso = 0 where id=?;
	}

	@Override
	public List<Utente> visualizzaRichiesteRegistrazione() {
		// TODO Auto-generated method stub
	    List<Utente> u = null;
	    try {
	      EntityManager manager = JpaDAOFactory.getEntityManager();
	      String query = "SELECT u FROM Utente u WHERE u.ruolo=:stud AND u.accesso=:access";
	      Query q = manager.createQuery(query);
	      q.setParameter("stud", "stud");
	      q.setParameter("access", 0);  
	      EntityTransaction transaction = manager.getTransaction();
	      transaction.begin();
	      u = (List<Utente>) q.getResultList();
	      transaction.commit();
	      return u;
	    } catch (Exception e) {
	      System.out.println("problema in jpaDAOUtente.visualizzaRichiesteRegistrazione()");
	      e.printStackTrace();
	      return null;
	    }
	}

	private boolean rifiutaStudente(Utente studente) {
		// TODO Auto-generated method stub
		 /*try {
		      EntityManager manager = JpaDAOFactory.getEntityManager();
		      EntityTransaction transaction = manager.getTransaction();
		      transaction.begin();
		      manager.remove(studente);
		      transaction.commit();
		      return true;
		    } catch (Exception e) {
		      // TODO: handle exception
		      e.printStackTrace();
		      return false;
		    }*/
		
		try {
			EntityManager manager = JpaDAOFactory.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Utente c = manager.find(Utente.class, studente.getId());
			System.out.println(c);
			manager.remove(c);
			transaction.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	
	private boolean accettaStudente(Utente studente) {
		// TODO Auto-generated method stub
		try {
			EntityManager manager = JpaDAOFactory.getEntityManager();
			System.out.println("lzagfhndfgh "+studente.getId());
			/*String query = "UPDATE Utente u SET u.accesso=:insert WHERE u.id=:idu";
			Query q = manager.createQuery(query);
			q.setParameter("insert", 1);
			q.setParameter("idu", studente.getId());*/
			
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Utente u=manager.find(Utente.class, studente.getId());
			u.setAccesso(1);
			manager.merge(u);
			transaction.commit();
			return true;
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean gestisciRichiesta(Utente studente, boolean accettato) {
		// TODO Auto-generated method stub
		if (accettato) {
			accettaStudente(studente);	
			System.out.println("studente aggiunto");
			return true;
		}else {
			rifiutaStudente(studente);
			System.out.println("studente rifiutato");
			return false;
		}
	
	}

	@Override
	public boolean modificaPassword(Utente utente) {
		// TODO Auto-generated method stub
		try {
			EntityManager manager = JpaDAOFactory.getEntityManager();
			String query = "UPDATE Utente u SET u.password=:pass WHERE u.id=:idu";
			Query q = manager.createQuery(query);
			q.setParameter("pass", utente.getPassword());
			q.setParameter("idu", utente.getId());
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			q.executeUpdate();
			transaction.commit();
			return true;
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Utente visualizzaUtente(int id) {
		// TODO Auto-generated method stub
		Utente u = null;
		
		try {
			EntityManager manager = JpaDAOFactory.getEntityManager();
			String query = "SELECT u FROM Utente u WHERE u.id=:id";
			Query q = manager.createQuery(query);
			q.setParameter("id", id);
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			u = (Utente) q.getSingleResult();
			transaction.commit();
			return u;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}

	// Fine Singleton//
}

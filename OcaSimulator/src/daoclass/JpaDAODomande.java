package daoclass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import daofactory.JpaDAOFactory;
import daointerface.DomandeDAO;
import model.Domande;
import model.Opzioni;
import model.Utente;

public class JpaDAODomande implements DomandeDAO {
	// Singleton//
	private static JpaDAODomande instance = null;

	private JpaDAODomande() {
		// TODO Auto-generated constructor stub
	}

	public synchronized static JpaDAODomande getInstance() {
		if (instance == null) {
			instance = new JpaDAODomande();
		}
		return instance;
	}

	@Override
	public List<Domande> selezionaDomande() {
		// TODO Auto-generated method stub
		try {
			EntityManager manager = JpaDAOFactory.getEntityManager();
			List<Domande> d= manager.createNamedQuery("Domande.findAll").getResultList();
			Collections.sort(d);
			return d;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean eliminaDomanda(Domande domanda) {
		 try {
		      EntityManager manager = JpaDAOFactory.getEntityManager();
		      EntityTransaction transaction = manager.getTransaction();
		      transaction.begin();
		      Domande daEliminare=manager.find(Domande.class, domanda.getId());
		      manager.remove(daEliminare);
		      transaction.commit();
		      return true;
		    } catch (Exception e) {
		      // TODO: handle exception
		      e.printStackTrace();
		      return false;
		    }
	}
	private Domande selezionaDomandaPerTesto(String testo) {
		EntityManager em=JpaDAOFactory.getEntityManager();
		Query q= em.createQuery("SELECT d from Domande d where d.testo=:testo");
		q.setParameter("testo", testo);
		try {
			Domande d=(Domande)q.getResultList().get(0);
			return d;
		}catch(Exception e) {
			return null;
		}
	}

	public boolean aggiungiDomanda(Domande domanda, Opzioni... opzioni) {
		try {
			EntityManager manager = JpaDAOFactory.getEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			domanda.setOpzionis(new ArrayList<Opzioni>());
			manager.persist(domanda);			
			transaction.commit();
			System.out.println("primo step fatto");
			domanda=selezionaDomandaPerTesto(domanda.getTesto());
			System.out.println(domanda);
			if(domanda.getOpzionis()==null)domanda.setOpzionis(new ArrayList<Opzioni>());
			
			for(Opzioni o: opzioni) {
				domanda.addOpzioni(o);
				o.setDomande(domanda);
			}/*
			for(Opzioni o:domanda.getOpzionis()) {
				em.persist(o);
				System.out.println("aggiunta "+o);
			}*/
			for(Opzioni o:domanda.getOpzionis()) {
				EntityManager em = JpaDAOFactory.getEntityManager();
				EntityTransaction et=em.getTransaction();
				et.begin();
				System.out.println(em.createNativeQuery("Insert into Opzioni(testo,corretto,id_domanda) values('"+o.getTesto()+"','"+o.getCorretto()+"','"+o.getDomande().getId()+"');").executeUpdate());
				et.commit();
			}
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	@Override
    public List<Domande> generaTest(int nDomande) {
      List<Domande> test = null;
      try {
        EntityManager manager = JpaDAOFactory.getEntityManager();
        String query="SELECT * FROM Domande ORDER BY RAND()";
        //String query = "SELECT d FROM Domande d ORDER BY random()";
        //Query q = manager.createQuery(query);
        Query q=manager.createNativeQuery(query, Domande.class);
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        test = q.setMaxResults(nDomande).getResultList();
        transaction.commit();
        return test;
      } catch (Exception e) {
        System.out.println("problema in jpaDAOUtente.visualizzaRichiesteRegistrazione()");
        e.printStackTrace();
        return null;
      }
	}

	// METODI PIENI//
}

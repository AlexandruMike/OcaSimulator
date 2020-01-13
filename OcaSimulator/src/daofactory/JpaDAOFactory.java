package daofactory;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import daoclass.JpaDAODomande;
import daoclass.JpaDAOTest;
import daoclass.JpaDAOUtente;
import daointerface.DomandeDAO;
import daointerface.TestDAO;
import daointerface.UtenteDAO;

public class JpaDAOFactory extends DAOFactory {
	@Override
	public UtenteDAO getUtenteDAO() {
		// TODO Auto-generated method stub
		return JpaDAOUtente.getInstance();
	}

	@Override
	public TestDAO getTestDAO() {
		// TODO Auto-generated method stub
		return JpaDAOTest.getInstance();
	}
	
	@Override
	public DomandeDAO getDomandeDAO() {
		// TODO Auto-generated method stub
		return JpaDAODomande.getInstance();
	}
	
	private static void loadDriver() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
						
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static EntityManager getEntityManager() {
		loadDriver();
		//
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("OcaSimulator");
		//
		EntityManager entity = factory.createEntityManager();
		return entity;
	}

}

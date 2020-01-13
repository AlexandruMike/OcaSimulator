package daofactory;

import daointerface.DomandeDAO;
import daointerface.TestDAO;
import daointerface.UtenteDAO;

public abstract class DAOFactory {
	

	public abstract UtenteDAO getUtenteDAO();

	public abstract TestDAO getTestDAO();
	
	public abstract DomandeDAO getDomandeDAO();
	


	public static DAOFactory getDAOFactory() {
	
		return new JpaDAOFactory();
	}
}

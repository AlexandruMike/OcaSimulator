package daointerface;

import java.util.List;

import model.Domande;
import model.Opzioni;

public interface DomandeDAO {
	public List<Domande> selezionaDomande();
	public boolean eliminaDomanda(Domande domanda);
	public boolean aggiungiDomanda(Domande domanda, Opzioni...opzioni);
	public List<Domande> generaTest(int nDomande);
}

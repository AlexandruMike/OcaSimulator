package daointerface;

import java.util.List;

import model.Test;
import model.Utente;

public interface TestDAO {
	public List<Test> visualizzaStorico(Utente utente);
	public List<Test> visualizzaStoricoSingoloUtente(Utente studente);
	public List<Test> visualizzaStoricoSingoloDipendente();
	public boolean salvaTest(Test test);
}

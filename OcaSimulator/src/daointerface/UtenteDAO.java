package daointerface;

import java.util.List;

import model.Utente;

public interface UtenteDAO {
	
	public Utente login(String email, String password);
	public boolean aggiungiUtente(Utente utente);
	public List<Utente> selezionaDipendenti();
	public boolean eliminaDipendente(Utente utente);
	public List<Utente> visualizzaRichiesteRegistrazione();
	public boolean gestisciRichiesta(Utente studente,boolean accettato);
	public boolean modificaPassword(Utente utente);
	public Utente visualizzaUtente(int id);
	
	
}

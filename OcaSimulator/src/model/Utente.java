package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the utente database table.
 * 
 */
@Entity
@NamedQuery(name="Utente.findAll", query="SELECT u FROM Utente u")
public class Utente implements Serializable, Comparable<Utente> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private int id;

	@Column(name="Nome")
	private String nome;
	
	@Column(name="Cognome")
	private String cognome;
	
	@Column(name="Email")
	private String email;

	@Column(name="Password")
	private String password;
	
	@Column(name="Accesso")
	private int accesso;

	@Column(name="Ruolo")
	private String ruolo;

	//bi-directional many-to-one association to Domande
	@OneToMany(mappedBy="utente")
	private List<Domande> domandes;

	//bi-directional many-to-one association to Test
	@OneToMany(mappedBy="utente")
	private List<Test> tests;

	public Utente() {
	}
	
	public Utente(String nome, String cognome, String email, String password, int accesso, String ruolo) {
			    this.nome=nome;
			    this.cognome=cognome;
			    this.email=email;
			    this.password=password;
			    this.accesso=accesso;
			    this.ruolo=ruolo;
			    
			  }

	public Utente(int id) {
		this.id=id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccesso() {
		return this.accesso;
	}

	public void setAccesso(int accesso) {
		this.accesso = accesso;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getRuolo() {
		return this.ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public List<Domande> getDomandes() {
		return this.domandes;
	}

	public void setDomandes(List<Domande> domandes) {
		this.domandes = domandes;
	}

	public Domande addDomande(Domande domande) {
		getDomandes().add(domande);
		domande.setUtente(this);

		return domande;
	}

	public Domande removeDomande(Domande domande) {
		getDomandes().remove(domande);
		domande.setUtente(null);

		return domande;
	}

	public List<Test> getTests() {
		return this.tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	public Test addTest(Test test) {
		getTests().add(test);
		test.setUtente(this);

		return test;
	}

	public Test removeTest(Test test) {
		getTests().remove(test);
		test.setUtente(null);

		return test;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", password="
				+ password + ", accesso=" + accesso + ", ruolo=" + ruolo + ", domandes=" + domandes + ", tests=" + tests
				+ "]";
	}

	@Override
	public int compareTo(Utente o) {
		int valore=nome.compareTo(o.nome);
		if(valore==0) {
			valore=cognome.compareTo(o.cognome);
		}
		return valore;
	}
	
	

}
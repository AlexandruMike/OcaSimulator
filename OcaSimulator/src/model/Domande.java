package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the domande database table.
 * 
 */
@Entity
@NamedQuery(name = "Domande.findAll", query = "SELECT d FROM Domande d")
public class Domande implements Serializable,Comparable<Domande> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "testo")
	private String testo;

	// bi-directional many-to-one association to Utente
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_utente")
	private Utente utente;

	// bi-directional many-to-one association to Opzioni
	@OneToMany(mappedBy = "domande")
	private List<Opzioni> opzionis;

	public Domande() {
	}

	public Domande(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTesto() {
		return this.testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public Utente getUtente() {
		return this.utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public List<Opzioni> getOpzionis() {
		return this.opzionis;
	}

	public void setOpzionis(List<Opzioni> opzionis) {
		this.opzionis = opzionis;
	}

	public Opzioni addOpzioni(Opzioni opzioni) {
		getOpzionis().add(opzioni);
		opzioni.setDomande(this);

		return opzioni;
	}

	public Opzioni removeOpzioni(Opzioni opzioni) {
		getOpzionis().remove(opzioni);
		opzioni.setDomande(null);

		return opzioni;
	}

	@Override
	public int compareTo(Domande o) {
		int confronto=utente.compareTo(o.utente);
		if(confronto==0)confronto=id-o.id;
		return confronto;
	}

}
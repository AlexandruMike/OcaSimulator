package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the opzioni database table.
 * 
 */
@Entity
@NamedQuery(name="Opzioni.findAll", query="SELECT o FROM Opzioni o")
public class Opzioni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="corretto")
	private int corretto;
	
	@Column(name="testo")
	private String testo;

	//bi-directional many-to-one association to Domande
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_domanda")
	private Domande domande;

	public Opzioni() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCorretto() {
		return this.corretto;
	}

	public void setCorretto(int corretto) {
		this.corretto = corretto;
	}

	public String getTesto() {
		return this.testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public Domande getDomande() {
		return this.domande;
	}

	public void setDomande(Domande domande) {
		this.domande = domande;
	}

}
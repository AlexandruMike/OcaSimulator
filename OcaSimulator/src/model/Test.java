package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the test database table.
 * 
 */
@Entity
@NamedQuery(name="Test.findAll", query="SELECT t FROM Test t")
public class Test implements Serializable, Comparable<Test> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private int id;

	@Temporal(TemporalType.DATE)
	private Date data;

	@Column(name="n_domande")
	private int nDomande;
	
	@Column(name="punteggio")
	private int punteggio;

	//bi-directional many-to-one association to Utente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_studente")
	private Utente utente;

	public Test() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getNDomande() {
		return this.nDomande;
	}

	public void setNDomande(int nDomande) {
		this.nDomande = nDomande;
	}

	public int getPunteggio() {
		return this.punteggio;
	}

	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}

	public Utente getUtente() {
		return this.utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", data=" + data + ", nDomande=" + nDomande + ", punteggio=" + punteggio + ", utente="
				+ utente + "]";
	}
	
	@Override
	public int compareTo(Test o) {
		int valore=utente.compareTo(o.utente);
		if(valore==0) {
			valore=data.compareTo(o.data);
		}
		if(valore==0) {
			valore=id-o.id;
		}
		return valore;
	}
	
}
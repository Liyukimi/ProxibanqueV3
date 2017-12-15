package fr.gtm.formation.proxibanque.domaine;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.Transient;

/**
 * @author adminl Class Compte belongs to the layer domain This class allows to
 * implement Compte Object This class contains 7 attributes (numeroCompte,
 * solde, dateOuverture, typeCompte, decouvert, taux, idClientClient, This class
 * contains constructors and getters and setters
 */
@Entity
public class Compte implements Serializable {

    /**
     * numéro de version
     */
    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "generatorCompte", sequenceName = "seq_compte", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorCompte")
    @Column(name = "numero_compte")
    private int numeroCompte;

    @Column(name = "solde")
    private double solde;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_ouverture")
    private Date dateOuverture;
    @Column(name = "type_compte")
    private String typeCompte;
    @Column(name = "decouvert")
    private double decouvert;
    @Column(name = "taux")
    private double taux;

    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    @ManyToOne
    private Client client;

    @ManyToMany(cascade
	    = {
		CascadeType.PERSIST, CascadeType.MERGE
	    })
    @JoinTable(name = "Compte_Virement",
	    joinColumns
	    = {
		@JoinColumn(name = "numero_compte",
			referencedColumnName = "numero_compte")
	    },
	    inverseJoinColumns
	    = {
		@JoinColumn(name = "id_virement",
			referencedColumnName = "id_virement")
	    })
    private Collection<Virement> listeVirements = new ArrayList<Virement>();

    public Compte() {
    }

    // Constructor
    public Compte(int numeroCompte, double solde, Date dateOuverture, String typeCompte, double decouvert,
	    double taux) {
	super();
	this.numeroCompte = numeroCompte;
	this.solde = solde;
	this.dateOuverture = dateOuverture;
	this.typeCompte = typeCompte;
	this.decouvert = decouvert;
	this.taux = taux;
    }

    // Surcharge
    public Compte(int numeroCompte, double solde, Date dateOuverture, String typeCompte, double compteProperty) {
	super();
	this.numeroCompte = numeroCompte;
	this.solde = solde;
	this.dateOuverture = dateOuverture;
	this.typeCompte = typeCompte;
	if (typeCompte.equals("courant")) {
	    this.decouvert = compteProperty;
	    this.taux = 0;
	} else if (typeCompte.equals("epargne")) {
	    this.taux = compteProperty;
	    this.decouvert = 0;
	}
    }

    // Surcharge
    public Compte(int numeroCompte) {
	super();
	this.numeroCompte = numeroCompte;
    }

    public Collection<Virement> getListeVirements() {
	return listeVirements;
    }

    public void setListeVirements(Collection<Virement> listeVirements) {
	this.listeVirements = listeVirements;
    }

    public int getNumeroCompte() {
	return numeroCompte;
    }

    public void setNumeroCompte(int numeroCompte) {
	this.numeroCompte = numeroCompte;
    }

    public double getSolde() {
	return solde;
    }

    public void setSolde(double solde) {
	this.solde = solde;
    }

    public Date getDateOuverture() {
	return dateOuverture;
    }

    public void setDateOuverture(Date dateOuverture) {
	this.dateOuverture = dateOuverture;
    }

    public String getTypeCompte() {
	return typeCompte;
    }

    public void setTypeCompte(String typeCompte) {
	this.typeCompte = typeCompte;
    }

    public double getDecouvert() {
	return decouvert;
    }

    public void setDecouvert(double decouvert) {
	this.decouvert = decouvert;
    }

    public double getTaux() {
	return taux;
    }

    public void setTaux(double taux) {
	this.taux = taux;
    }

    public Client getClient() {
	return client;
    }

    public void setClient(Client client) {
	this.client = client;
    }

}

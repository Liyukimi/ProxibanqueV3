package fr.gtm.formation.proxibanque.domaine;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

/**
 * @author adminl Class Client belongs to the layer domain. This class allows to
 * implement Client Object This class extends from the Class : Person. This
 * class contains 8 attributes (idClient, adresse, codePostal, ville, telephone,
 * mail, listeComptes and login. This class contains constructors and getters
 * and setters.
 */
@Entity
@NamedQueries(
	{
	    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")
	    ,
    @NamedQuery(name = "Client.findById", query = "SELECT c FROM Client c WHERE c.idClient = :idClient")
	})
public class Client implements Serializable {

    /**
     * numéro de version
     */
    @Transient
    private static final long serialVersionUID = 1L;

    // Attributs
    @Id
    @SequenceGenerator(name = "generatorClient", sequenceName = "seq_client", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorClient")
    @Column(name = "id_client", updatable = false, nullable = false)
    private int idClient;
    private String nom;
    private String prenom;
    private String adresse;
    @Column(name = "code_postal")
    private String codePostal;
    private String ville;
    private String telephone;
    private String mail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private Collection<Compte> listeComptes = new ArrayList<Compte>();

    @JoinColumn(name = "login_conseiller", referencedColumnName = "login")
    @ManyToOne
    private Conseiller conseiller;

    // Constructors
    public Client() {
    }

    public Client(String nom, String prenom) {
	this.nom = nom;
	this.prenom = prenom;
    }

    public Client(int idClient) {
	this.idClient = idClient;
    }

    public Client(int idClient, String nom, String prenom) {
	this.idClient = idClient;
	this.nom = nom;
	this.prenom = prenom;
    }

    public Client(int idClient, String nom, String prenom, String adresse, String codePostal, String ville, String mail,
	    Conseiller conseiller) {
	this.idClient = idClient;
	this.nom = nom;
	this.prenom = prenom;
	this.adresse = adresse;
	this.codePostal = codePostal;
	this.ville = ville;
	this.mail = mail;
	this.conseiller = conseiller;
    }

    public Client(String nom, String prenom, String adresse, String codePostal, String ville, String mail,
	    Conseiller conseiller) {
	this.nom = nom;
	this.prenom = prenom;
	this.adresse = adresse;
	this.codePostal = codePostal;
	this.ville = ville;
	this.mail = mail;
	this.conseiller = conseiller;
    }

    public Client(String nom, String prenom, String adresse, String codePostal, String ville, String telephone,
	    String mail) {
	this.nom = nom;
	this.prenom = prenom;
	this.adresse = adresse;
	this.codePostal = codePostal;
	this.ville = ville;
	this.telephone = telephone;
	this.mail = mail;
    }

    public Client(String nom, String prenom, String adresse, String codePostal, String ville, String telephone,
	    String mail, Collection<Compte> listeComptes) {
	this.nom = nom;
	this.prenom = prenom;
	this.adresse = adresse;
	this.codePostal = codePostal;
	this.ville = ville;
	this.telephone = telephone;
	this.mail = mail;
	this.listeComptes = listeComptes;
    }

    // Getters and Setters
    public int getIdClient() {
	return idClient;
    }

    public void setIdClient(int idClient) {
	this.idClient = idClient;
    }

    public String getAdresse() {
	return adresse;
    }

    public void setAdresse(String adresse) {
	this.adresse = adresse;
    }

    public String getCodePostal() {
	return codePostal;
    }

    public void setCodePostal(String codePostal) {
	this.codePostal = codePostal;
    }

    public String getVille() {
	return ville;
    }

    public void setVille(String ville) {
	this.ville = ville;
    }

    public String getTelephone() {
	return telephone;
    }

    public void setTelephone(String telephone) {
	this.telephone = telephone;
    }

    public String getMail() {
	return mail;
    }

    public void setMail(String mail) {
	this.mail = mail;
    }

    public Conseiller getConseiller() {
	return conseiller;
    }

    public void setConseiller(Conseiller conseiller) {
	this.conseiller = conseiller;
    }

    public Collection<Compte> getListeComptes() {
	return listeComptes;
    }

    public void setListeComptes(Collection<Compte> listeComptes) {
	this.listeComptes = listeComptes;
    }

    public String getNom() {
	return nom;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }

    public String getPrenom() {
	return prenom;
    }

    public void setPrenom(String prenom) {
	this.prenom = prenom;
    }

    @Override
    public String toString() {
	return "Client{" + "idClient=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", codePostal=" + codePostal + ", ville=" + ville + ", telephone=" + telephone + ", mail=" + mail + ", listeComptes=" + listeComptes + ", conseiller=" + conseiller + '}';
    }

}

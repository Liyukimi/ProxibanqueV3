package fr.gtm.formation.proxibanque.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * @author adminl Class Conseiller belongs to the layer domain This class allows
 * to implement Conseillers Object This class extends from the Class : Person
 * This class contains 3 attributes (login, password, listeClients) This class
 * contains constructors and getters and setters
 */
@Entity
public class Conseiller implements Serializable
{

    /**
     *
     */
    @Transient
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "login")
    private String login;
    private String password;
    private String nom;
    private String prenom;

    @OneToMany(mappedBy = "conseiller")//, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Collection<Client> listeClients = new ArrayList<Client>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "conseiller")
    private Collection<Virement> listeVirements = new ArrayList<Virement>();

    public Collection<Virement> getListeVirements()
    {
	return listeVirements;
    }

    public void setListeVirements(Collection<Virement> listeVirements)
    {
	this.listeVirements = listeVirements;
    }

    // Constructors
    public Conseiller()
    {
	super();
    }

    public Conseiller(String login, String password)
    {
	super();
	this.login = login;
	this.password = password;
    }

    public Conseiller(String login, String password, Collection<Client> listeClients)
    {
	super();
	this.login = login;
	this.password = password;
	this.listeClients = listeClients;
    }

    public Conseiller(String nom, String prenom, String login, String password)
    {
	this.nom = nom;
	this.prenom = prenom;
	this.login = login;
	this.password = password;
    }

    public Conseiller(String nom, String prenom, String login, String password, Collection<Client> listeClients)
    {
	this.nom = nom;
	this.prenom = prenom;
	this.login = login;
	this.password = password;
	this.listeClients = listeClients;
    }

    // Getters and Setters
    public String getLogin()
    {
	return login;
    }

    public void setLogin(String login)
    {
	this.login = login;
    }

    public String getPassword()
    {
	return password;
    }

    public void setPassword(String password)
    {
	this.password = password;
    }

    public Collection<Client> getListeClients()
    {
	return listeClients;
    }

    public void setListeClients(Collection<Client> listeClients)
    {
	this.listeClients = listeClients;
    }

    public String getNom()
    {
	return nom;
    }

    public void setNom(String nom)
    {
	this.nom = nom;
    }

    public String getPrenom()
    {
	return prenom;
    }

    public void setPrenom(String prenom)
    {
	this.prenom = prenom;
    }
}

package fr.gtm.formation.proxibanque.domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author adminl
 */
@Entity
@NamedQueries(
	{
	    @NamedQuery(name = "Virement.findAll", query = "SELECT v FROM Virement v")
	    , @NamedQuery(name = "Virement.findByIdVirement", query = "SELECT v FROM Virement v WHERE v.idVirement = :idVirement")
	    , @NamedQuery(name = "Virement.findByMontant", query = "SELECT v FROM Virement v WHERE v.montant = :montant")
	    , @NamedQuery(name = "Virement.findByDateVirement", query = "SELECT v FROM Virement v WHERE v.dateVirement = :dateVirement")
	})
public class Virement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "generatorVirement", sequenceName = "seq_virement", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generatorVirement")
    @Column(name = "id_virement")
    private int idVirement;
    @Column(name = "montant")
    private double montant;
    @Column(name = "date_virement")
    @Temporal(TemporalType.DATE)
    private Date dateVirement;

    @JoinColumn(name = "login_conseiller", referencedColumnName = "login")
    @ManyToOne
    private Conseiller conseiller;

    @ManyToMany(cascade
	    = {
		CascadeType.PERSIST, CascadeType.MERGE
	    }, mappedBy = "listeVirements")
    private Collection<Compte> listeComptes = new ArrayList<Compte>();

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

    public int getIdVirement() {
	return idVirement;
    }

    public void setIdVirement(int idVirement) {
	this.idVirement = idVirement;
    }

    public double getMontant() {
	return montant;
    }

    public void setMontant(double montant) {
	this.montant = montant;
    }

    public Date getDateVirement() {
	return dateVirement;
    }

    public void setDateVirement(Date dateVirement) {
	this.dateVirement = dateVirement;
    }

    public Virement() {
    }

    public Virement(double montant, Date dateVirement) {
	this.montant = montant;
	this.dateVirement = dateVirement;
    }

    public Virement(double montant, Conseiller conseiller) {
	this.montant = montant;
	this.conseiller = conseiller;
    }

    public Virement(double montant, Date dateVirement, Conseiller conseiller) {
	this.montant = montant;
	this.dateVirement = dateVirement;
	this.conseiller = conseiller;
    }

    @Override
    public String toString() {
	return "fr.gtm.formation.proxibanque.domaine.Virement[ idVirement=" + idVirement + " ]";
    }

}

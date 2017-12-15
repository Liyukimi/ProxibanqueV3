package fr.gtm.formation.proxibanque.dao;

import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
import fr.gtm.formation.proxibanque.domaine.Compte;
import fr.gtm.formation.proxibanque.domaine.Virement;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class VirementDao extends GenericDaoImpl<Virement, Integer> implements VirementDaoInterface {

    public VirementDao(Class<Virement> entityType) {
	super(entityType);
    }

    public VirementDao() {
	super(Virement.class);
    }

    @Override
    public Collection getComptesByViremenent(Long idVirement) throws DaoException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection getVirementsByCompte(int numeroCompte) throws DaoException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * Méthode qui suppose que le virement a été autorisé. Prend en paramètre le
     * compte à débiter, le compte à créditer et le montant et met à jour le
     * solde de chacun des comptes. Si la mise à jour échoue, la transaction est
     * annulée
     *
     * @param virement
     * @param compteDebiteur
     * @param compteCrediteur
     * @param montant
     * @throws DaoException
     */
    public void createVirement(Virement virement, Compte compteDebiteur, Compte compteCrediteur, double montant) throws DaoException {
	EntityManager em = this.emf.createEntityManager();
	EntityTransaction tx = em.getTransaction();
	try {
	    tx.begin();
	    double soldeDebiteur = compteDebiteur.getSolde();
	    double soldeCrediteur = compteCrediteur.getSolde();

	    //Modifie le solde des deux comptes
	    compteDebiteur.setSolde(soldeDebiteur - montant);
	    compteCrediteur.setSolde(soldeCrediteur + montant);

	    //Persiste le virement en base et met à jour les compte
	    em.persist(virement);

	    //Mise à jour en base de données
	    em.merge(compteDebiteur);
	    em.merge(compteCrediteur);
	    em.merge(virement);

	    //Ajout du virement dans la table Compte_Virement (maj impossible avec les fetch lazy)
	    Query query = em.createNativeQuery("INSERT INTO Compte_Virement VALUES(:num_compte, :id_vir)");
	    query.setParameter("id_vir", virement.getIdVirement());
	    query.setParameter("num_compte", compteCrediteur.getNumeroCompte());
	    query.executeUpdate();

	    query = em.createNativeQuery("INSERT INTO Compte_Virement VALUES(:num_compte, :id_vir)");
	    query.setParameter("id_vir", virement.getIdVirement());
	    query.setParameter("num_compte", compteDebiteur.getNumeroCompte());
	    query.executeUpdate();
	    tx.commit();
	} catch (Exception e) {
	    // Si erreur lors de l'exécution, la transaction est annulée
	    if (tx != null) {
		tx.rollback();
	    }
	    throw new DaoException("Transaction annulé, les comptes n'ont pas été débités/crédités", e);
	} finally {
	    em.close();
	}
    }

}

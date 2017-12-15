package fr.gtm.formation.proxibanque.service;

import java.util.Collection;

import fr.gtm.formation.proxibanque.domaine.Client;
import fr.gtm.formation.proxibanque.dao.ClientDao;
import fr.gtm.formation.proxibanque.dao.exceptions.DaoException;
import fr.gtm.formation.proxibanque.service.exceptions.ServiceException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * la classe ClientServices appartient à la couche service. Elle permet
 * d'instancier des objets ClientServices. Elle possède 5 méthodes et communique
 * avec le module dao et presentation.
 *
 * @author adminl
 */
public class ClientServices {

    private ClientDao clientDao;

    /**
     * méthode recuperant la liste des clients d'un conseiller par le biais son
     * login pour cela elle fait appel à la méthode 'getClientByConseiller' du
     * module dao
     *
     * @param login
     * @return Collection de clients
     * @throws ServiceException
     */
    public Collection<Client> getClientsByConseiller(String login) throws ServiceException {
	clientDao = new ClientDao();
	Collection listeClients = new ArrayList<>();
	try {
	    listeClients = clientDao.getClientsByConseiller(login);
	} catch (DaoException ex) {
	    Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
	    throw new ServiceException("Erreur, le conseiller n'existe pas");
	}
	return listeClients;
    }

    /**
     * méthode recuperant les informations d'un client par son idClient pour
     * cela elle fait appel à la methode 'read' de la couche dao
     *
     * @param idClient
     * @return client
     */
    public Client getClientById(int idClient) throws ServiceException {
	clientDao = new ClientDao();
	Client client = null;
	try {
	    client = clientDao.read(idClient);
	} catch (DaoException ex) {
	    Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
	    throw new ServiceException("Erreur, le client n'existe pas");
	}
	return client;
    }

    /**
     * méthode mettant à jour les informations d'un client pour cela elle fait
     * appel à la methode 'update' du module dao
     *
     * @param client
     */
    public void updateClient(Client client) throws ServiceException {
	clientDao = new ClientDao();
	try {
	    clientDao.update(client);
	} catch (DaoException ex) {
	    Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
	    throw new ServiceException("Erreur, le client n'a pas été modifié");
	}
    }

    /**
     * méthode permettant de créer un client pour cela elle fait appel à la
     * methode 'create' du module dao
     *
     * @param client
     */
    public void createClient(Client client) throws ServiceException {
	clientDao = new ClientDao();
	try {
	    clientDao.create(client);
	} catch (DaoException ex) {
	    Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
	    throw new ServiceException("Erreur, le client n'a pas été créé");
	}
    }

    /**
     * méthode permettant de supprimer un client pour cela elle fait appel à la
     * methode 'delete' du module dao
     *
     * @param client
     * @param idClient
     */
    public void deleteClient(Client client) throws ServiceException {
	try {
	    clientDao.delete(client);
	} catch (DaoException ex) {
	    Logger.getLogger(ClientServices.class.getName()).log(Level.SEVERE, null, ex);
	    throw new ServiceException("Erreur, le client n'a pas été supprimé");
	}
    }

}

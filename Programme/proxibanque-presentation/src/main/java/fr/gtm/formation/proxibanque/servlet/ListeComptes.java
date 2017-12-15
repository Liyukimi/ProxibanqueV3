package fr.gtm.formation.proxibanque.servlet;

import fr.gtm.formation.proxibanque.service.exceptions.ServiceException;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.gtm.formation.proxibanque.domaine.Compte;
import fr.gtm.formation.proxibanque.domaine.Client;
import fr.gtm.formation.proxibanque.service.CompteServices;
import fr.gtm.formation.proxibanque.service.ClientServices;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class belongs to the prensentation layer This class is an extension of
 * HttpServlet and redefine doPost(HttpServletRequest request,
 * HttpServletResponse response) and doGet(HttpServletRequest request,
 * HttpServletResponse response) methods.
 *
 * The doPost and doGet methods redirect the request and response attributes to
 * a public method traitement(HttpServletRequest request, HttpServletResponse
 * response) witch : 1/ get and analyse request parameters 2/ transfer those
 * parameters to the corresponding service layer 3/ forward the request and
 * response to an url or another servlet
 *
 * Calls the service layer to check if the client exist (genetaux error if
 * not)</br>
 * If the client exist get back its compte list
 *
 * @author proxibanque
 */
@WebServlet("/ListeComptes")
public class ListeComptes extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeComptes() {
	super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	traitement(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	traitement(request, response);
    }

    /**
     * This method is called by doPost or doGet methods of this class This
     * method get a request from an Http request and :</br>
     * 1/ get and analyse request parameters</br>
     * 2/ transfer those parameters to the corresponding service layer</br>
     * 3/ forward the request and response to an url or another servlet</br>
     *
     * Given an idClient the method calls the service layer to check if the
     * client exist (genetaux error if not)</br>
     * If the client exist get back its compte list</br>
     * Redirection to listeComptes.jsp
     *
     * @param request : the request
     * @param response : the response to dispatch
     * @throws ServletException
     * @throws IOException
     */
    protected void traitement(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// Step 1 : extract request parameters
	int idClient = Integer.parseInt(request.getParameter("idClientSelect"));

	// Step 2 : send request parameters to services package
	HttpSession mySession = request.getSession();
	mySession.setAttribute("success", null);
	mySession.setAttribute("error", null);
	ClientServices clientServices = new ClientServices();

	Client clientBdd;
	ArrayList<Compte> listeComptes = null;
	try {
	    clientBdd = clientServices.getClientById(idClient);
	    CompteServices compteServices = new CompteServices();
	    listeComptes = (ArrayList<Compte>) compteServices.getCompteByClient(clientBdd);
	} catch (ServiceException ex) {
	    Logger.getLogger(ListeComptes.class.getName()).log(Level.SEVERE, null, ex);
	    mySession.setAttribute("error", "Le client n'existe pas");
	}

	mySession.setAttribute("ListeComptes", listeComptes);

	// Step 3 : response to the user
	RequestDispatcher dispatcher;
	dispatcher = request.getRequestDispatcher("listeComptes.jsp");
	dispatcher.forward(request, response);
    }
}

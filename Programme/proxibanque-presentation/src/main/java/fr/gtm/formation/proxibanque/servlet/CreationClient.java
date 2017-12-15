package fr.gtm.formation.proxibanque.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.gtm.formation.proxibanque.domaine.Client;
import fr.gtm.formation.proxibanque.service.exceptions.ServiceException;
import fr.gtm.formation.proxibanque.service.ClientServices;

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
 * @author proxibanque
 */
@WebServlet("/CreationClient")
public class CreationClient extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
     * @param request : the request
     * @param response : the response to dispatch
     * @throws ServletException
     * @throws IOException
     */
    public void traitement(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	{
	    ClientServices clientServices = new ClientServices();
	    // Step 1 : Get request parameters
	    String nom = request.getParameter("nom");
	    String prenom = request.getParameter("prenom");
	    String adresse = request.getParameter("adresse");
	    String codePostal = request.getParameter("codePostal");
	    String ville = request.getParameter("ville");
	    String telephone = request.getParameter("telephone");
	    String mail = request.getParameter("mail");
	    String login = request.getParameter("login");

	    Client client = new Client(nom.toUpperCase(), prenom.toUpperCase(), adresse.toUpperCase(),
		    codePostal, ville.toUpperCase(), telephone, mail);

	    // Step 2 : transfer parameters to service layer
	    RequestDispatcher dispatcher;
	    HttpSession session = request.getSession();
	    session.setAttribute("success", null);
	    session.setAttribute("error", null);

	    try {
		clientServices.createClient(client);
		System.out.println("Client " + prenom + " " + nom + " créé");
		session.setAttribute("success", "Le client a été créé");
		session.setAttribute("idClientSelect", client.getIdClient());
		session.setAttribute("listeClients", clientServices.getClientsByConseiller(login));
		dispatcher = getServletContext().getRequestDispatcher("/ClientSelect");
	    } catch (ServiceException e) {
		String error = "Le client " + prenom + " " + nom + " n'a pas été créé";
		session.setAttribute("error", error);
		System.out.println("Erreur, le client " + prenom + " " + nom + " n'a pas pas été créé");
		dispatcher = getServletContext().getRequestDispatcher("/creationClient.jsp");
	    }

	    // Step 3 : Answer
	    dispatcher.forward(request, response);
	}
    }

}

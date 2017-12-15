<%@include file="header.jsp" %>
<%@include file="nav.jsp" %>
<!------------------------------------------------ DEBUIT DE LA PAGE PRINCIPALE ------------------------------------------------>
        <div id="page-wrapper">
            <div class="container-fluid">
				<!------------------------------ CONTENU HEADING ------------------------------>
                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            <i class="fa fa-list-alt"></i> Liste des comptes
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="index.jsp">Tableau de bord</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-list-alt"></i> Liste des comptes
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /Page heading -->
				<!------------------------------ FIN CONTENU HEADING ------------------------------>
				<!------------------------------ CONTENU PRINCIPAL ------------------------------>             
				<div class="row">
					<div class="col-lg-12">
					<c:if test = "${!empty error}">
							<div class="alert alert-danger">
								<h3><strong>Erreur !</strong></h3>
								<h5>${error}</h5> 
							</div>
						</c:if>
						<fieldset>
							<legend>S�lectionner un de vos clients dans la liste ci-dessous pour afficher ses comptes</legend>
							
							<form class="form" method="post" action="ListeComptes">
								<div class="form-group">
								    <label  for="IdClient">Choisissez votre client :</label>
								    <c:if test = "${!empty listeClients}">
								    <select class="form-control" required="required" id="IdClient" name="idClientSelect">
									<c:forEach items="${listeClients}" var="client" >
									    <option  name="idClientSelect" value="${client.idClient}">${client.prenom} ${client.nom}</option>
									</c:forEach>
                                                                    </select>
								    </c:if>
								    <c:if test = "${empty listeClients}">
                                                                        Vous n'avez pas de client !
								    </c:if>	
								    <!--<input type="text" class="form-control" required="required" name="idClientSelect"  placeholder="${client.idClient}">-->
								</div>
								<input type=submit class="btn btn-primary" value="Afficher Comptes"></input>
							</form>
						</fieldset>
					<br></br>

						<c:if test = "${!empty ListeComptes}">			
						<legend>Liste des comptes du client ${client.prenom} ${client.nom}</legend>
						<table class="table-striped">
							<thead>
								<tr>
									<th>Num�ro de compte</th>
									<th>Solde</th>
									<th>Date d'ouverture</th>
									<th>Type de compte</th>
									<th>D�couvert authoris�</th>
									<th>taux</th>
								</tr>
							</thead> 
							<tbody>
							<c:forEach items="${ListeComptes}" var="item" >
								<tr>
									<td>${item.numeroCompte}</td>
									<td>${item.solde}</td>									
									<td>${item.dateOuverture}</td>
									<td>${item.typeCompte}</td>
									<td>${item.decouvert}</td>
									<td>${item.taux}</td>
								</tr>
								</c:forEach>
							</tbody>				
						</table>						
						<br></br>	
						</c:if>
					</div>
				</div>
                <!-- /.row -->

				<!------------------------------ FIN CONTENU PRINCIPAL ------------------------------>
            </div>
            <!-- /.container-fluid -->
		</div>
<!------------------------------------------------ FIN DE LA PAGE PRINCIPALE ------------------------------------------------>
<%@include file="footer.jsp" %>   
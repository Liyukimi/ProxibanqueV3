<!------------------------------------------------ NAVIGATION ------------------------------------------------>
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Menu</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp"><i class="fa fa-fw fa-bank"></i>	PROXIBANQUE</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
				<li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${conseiller.prenom} ${conseiller.nom} <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="index.jsp"><i class="fa fa-fw fa-user"></i> Mon compte</a>
                        </li> 
                        <li class="divider"></li>
                        <li>
                            <a href="DeconnexionConseiller"><i class="fa fa-fw fa-power-off"></i> Se d�connecter</a>
                        </li>                        
                    </ul>
                </li>
            </ul>
			<!-- /Top Menu Items -->
			
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                     <li>
                        <a href="ListeClients"><!--class="active"--><i class="fa fa-fw fa-address-book"></i> Liste des clients</a>
                    </li>
                    <li>
                        <a href="creationClient.jsp"><i class="fa fa-fw fa-edit"></i> Cr�ation d'un client</a>
                    </li>
                    <li>
                        <a href="virement.jsp"><i class="fa fa-fw fa-credit-card"></i> Virement compte � compte</a>
                    </li>				
					<li>
                       	<a href="listeComptes.jsp"><i class="fa fa-fw fa-list-alt"></i> Liste des comptes</a>
                   	</li>
                    <c:if test = "${!empty listeClients}">
                    	<li>                    
                        <a href="javascript:;" data-toggle="collapse" data-target="#clients"><i class="fa fa-fw fa-arrows-v"></i> Mes clients <i class="fa fa-fw fa-caret-down"></i></a>
						<ul id="clients" class="collapse">
							<c:forEach items="${listeClients}" var="client" >
								<li>
									<a href="ClientSelect?idClientSelect=${client.idClient}"><option value="${client.prenom}+ " " +${client.nom}">${client.prenom} ${client.nom}</option></a>
								</li>						
							</c:forEach>								
                        </ul>							
                    </li>					
                    </c:if>
					
                </ul>
            </div>
			<!-- /.navbar-collapse -->
        </nav>
<!------------------------------------------------ /NAVIGATION ------------------------------------------------>
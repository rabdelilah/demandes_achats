<%@page import="services.services"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ include file="template/head.jsp" %> 
</head>
<body>
		<div class="wrap">
			<%@ include file="template/header.jsp" %> 
            <div id="content">
				<%
					String login=(String)session.getAttribute("login");
					//System.out.print(login);	
					if(login==null || login.equalsIgnoreCase("null")){
						response.setStatus(response.SC_MOVED_TEMPORARILY);	
						response.setHeader("Location", "login.jsp");
					}
				%>
                <div id="main">
                	<%@ include file="template/menu.jsp" %> 
                    <!--               main             -->
                    <h3>Liste Damande D'achat</h3>
                    <%
                    String v_user=(String)session.getAttribute("login"); 
        			String v_profil=services.getProfil(user);
                    List<String[]> list=services.getListeDa(v_profil);
                    String col[]={"Id","Date","Dépôt","Reference","Date Livraison","Acheteur","Fournisseur"};
                    //System.out.println("size : "+list.size());
                    %>
                    <table width='100%' cellpadding='0' cellspacing='0' border='0' class='tablesorter hasFilters'> 
						<thead> 
							<tr class='tablesorter-header'>
								<%for(int i=0;i<col.length;i++){ %>
									<th data-column='<%=i %>' class='tablesorter-header tablesorter-headerSortDown'>
										<div class='tablesorter-header-inner'><%=col[i] %></div>
									</th>
								<%}%>
								<td></td>
							</tr>
						</thead> 
						<tbody>
							<%for(int i=0;i<list.size();i++){ %>
							<tr>
								<%for(int j=0;j<list.get(i).length;j++){ %>
									<td><%=list.get(i)[j] %></td>
								<%} %>
								<%if(v_profil.equals("2")){ %>
									<td><a href="add_da.jsp?mode2=addLigne&mode=editDoc&id=<%=list.get(i)[0] %>"><img src="img/i_edit.png"/></a></td>
								<%}else if(v_profil.equals("3")){ %>
									<td><a href="validation_da.jsp?mode2=addLigne&mode=editDoc&id=<%=list.get(i)[0] %>"><img src="img/i_edit.png"/></a></td>
								<%}else if(v_profil.equals("4")){ %>
									<td><a href="confirmation_da.jsp?mode2=addLigne&mode=editDoc&id=<%=list.get(i)[0] %>"><img src="img/i_edit.png"/></a></td>
								<%} %>
							</tr>
							<%} %>
						</tbody>
					</table>
					<!--               end  main        -->
                </div>
                <div class="clear"></div>
            </div>

            
        </div>
</body>
</html>
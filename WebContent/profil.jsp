<%@page import="dao.mssqlDao"%>
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
					//System.out.println(login);	
					if(login==null || login.equalsIgnoreCase("null")){
						response.setStatus(response.SC_MOVED_TEMPORARILY);	
						response.setHeader("Location", "login.jsp");
					}
					List<String[]> users=services.getUsers();
					List<String[]> profils=services.getProfils();
					
				%>
                <div id="main">
                	<%@ include file="template/menu.jsp" %> 
                    <!--               main             -->
                    <%if(request.getParameter("mode").equalsIgnoreCase("add")){ %>
                    <form action="action" method="post">
                    <table>
                    	<tr>
                    		<td>Utilisateur : </td>
                    		<td>
                    		<select name="id_user">
							<% for(int i=0;i<users.size();i++){%>
                    			<option value="<%=users.get(i)[0].replace("<", "&lt;").replace(">", "&gt;")%>"><%=users.get(i)[0].replace("<", "&lt;").replace(">", "&gt;")%></option>
                    		<% }%>
                    		</select>
                    		</td>
                    	
                    		<td>Profil : </td>
                    		<td>
                    		<select name="id_profil">
							<% for(int i=0;i<profils.size();i++){%>
                    			<option value="<%=profils.get(i)[0]%>"><%=profils.get(i)[1] %></option>
                    		<% }%>
                    		</select>
                    		</td>
                    	
                    		<td>
                    			<input type="hidden" name="op" value="add_profil" />
                    			<input type="submit" class="ok" value="Ajouter">
                    		</td>
                    	</tr>
                    </table>
                    </form>
                    <%}else{ %>
                    <form action="action" method="post">
                    <table>
                    	<tr>
                    		<td>Utilisateur : </td>
                    		<td>
                    		<select name="id_user">
							<%List<String[]> ligne=mssqlDao.executeQuery("select * from m_profil_user where id="+request.getParameter("id_ligne"));
							  for(int i=0;i<users.size();i++){
                    			if(ligne.get(0)[1].equalsIgnoreCase(users.get(i)[0])){%>
                    			<option value="<%=users.get(i)[0].replace("<", "&lt;").replace(">", "&gt;")%>" selected="selected"><%=users.get(i)[0].replace("<", "&lt;").replace(">", "&gt;") %></option>
                    		<%	}else{%>
                    			<option value="<%=users.get(i)[0].replace("<", "&lt;").replace(">", "&gt;")%>"><%=users.get(i)[0].replace("<", "&lt;").replace(">", "&gt;")%></option>
                    		<%  }
                    		  }%>
                    		</select>
                    		</td>
                    	
                    		<td>Profil : </td>
                    		<td>
                    		<select name="id_profil">
							<%  for(int i=0;i<profils.size();i++){
                    			if(ligne.get(0)[2].equalsIgnoreCase(profils.get(i)[0])){%>
                    			<option value="<%=profils.get(i)[0]%>" selected="selected"><%=profils.get(i)[1] %></option>
                    		<%	}else{%>
                    			<option value="<%=profils.get(i)[0]%>"><%=profils.get(i)[1] %></option>
                    		<%  }
                    			}%>
                    		</select>
                    		</td>
                    	
                    		<td>
                    			<input type="hidden" name="id_ligne" value="<%=request.getParameter("id_ligne") %>" />
                    			<input type="hidden" name="op" value="edit_profil" />
                    			<input type="submit" class="ok" value="Enregistrer">
                    		</td>
                    	</tr>
                    </table>
                    </form>
                    <%} %>
                    <%List<String[]> profil_user=services.getProfilUser();
                      String col[]={"Id","Utilisateur","Profil"};%>
                    <table width='100%' cellpadding='0' cellspacing='0' border='0' class='tablesorter hasFilters'> 
						<thead> 
							<tr class='tablesorter-header'>
								<%for(int i=0;i<col.length;i++){ %>
									<th data-column='<%=i %>' class='tablesorter-header tablesorter-headerSortDown'>
										<div class='tablesorter-header-inner'><%=col[i] %></div>
									</th>
								<%}%>
								<td></td>
								<td></td>
							</tr>
						</thead> 
						<tbody>
							<%for(int i=0;i<profil_user.size();i++){ %>
							<tr>
								<%for(int j=0;j<profil_user.get(i).length;j++){ %>
									<td><%=profil_user.get(i)[j].replace("<", "&lt;").replace(">", "&gt;")%></td>
								<%} %>
								<td><a href="profil.jsp?mode=edit&id_ligne=<%=profil_user.get(i)[0] %>"><img src="img/i_edit.png"/></a></td>
								<td><a href="action?op=remove_profil&id_ligne=<%=profil_user.get(i)[0] %>"><img src="img/i_delete.png"/></a></td>
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
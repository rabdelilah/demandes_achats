<%@page import="com.mysql.jdbc.Util"%>
<%@page import="dao.mssqlDao"%>
<%@page import="java.util.List"%>
<%@page import="services.services"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
                    <h3>Confirmation Damande D'achat</h3>
                    <%if(request.getParameter("mode").equalsIgnoreCase("editDoc")){ 
                    	List<String[]> data=mssqlDao.executeQuery("select * from m_entete where id="+request.getParameter("id"));
                    %>
                    <form action="action" method="post">
                    <table>
                    	<tr>
                    		<td>Date :         <input type="text" readonly="readonly" value="<%=data.get(0)[1]%>"/></td>
                    		<td>Dépôt :        <input type="text" readonly="readonly" value="<%=services.getIntituleDepot(data.get(0)[2]) %>"/></td>
							<td>Date Livraison:<input type="text" readonly="readonly" value="<%=data.get(0)[6]%>" /></td>
                    	</tr>
                    	<tr>
                    		<td>Reference :  <input type="text" readonly="readonly" value="<%=data.get(0)[3]%>" /></td>
                    		<td>Fournisseur :<input type="text" readonly="readonly" value="<%=services.getIntituleClient(data.get(0)[8])%>" /></td>
							<td>Acheteur :   <input type="text" readonly="readonly" value="<%=data.get(0)[7]%>" /></td>
                    	</tr>
                    	<tr>
                    		<td colspan="3">
                    			<input type="hidden" name="id_doc" value="<%=request.getParameter("id") %>" />
                    			<input type="hidden" name="op" value="confirmation_da" />
                    			<input type="submit" class="ok" value="Confirmer">
                    		</td>
                    	</tr>
                    </table>
                    </form>
                    <%
                    	List<String[]> donnee=mssqlDao.executeQuery("select id,reference_article,pu,qte,pt from m_lignes where id_doc='"+request.getParameter("id")+"'");
                    	String[] col={"Id","Référence Article","Prix Unitaire","Quantité","Montant"};
                    %>
                    <SCRIPT language="Javascript">
				    	var tab_prix = new Array();
				    	<%
				    	List<String[]> articles=services.getReferenceArticle();
				    	List<String[]> tab=services.getArticlePrix(); 
				    	for(int j = 0; j<tab.size(); j++){%>
				    		tab_prix['<%=tab.get(j)[0]%>']=<%=tab.get(j)[1]%>;
				    	<%}%>
				    </SCRIPT>
                    <table width='100%' cellpadding='0' cellspacing='0' border='0' class='tablesorter hasFilters'> 
						<thead> 
							<tr>
								<%for(int i=0;i<col.length;i++){ %>
								<td><%=col[i] %></td>
								<%}%>
								<td></td>
								<td></td>
							</tr>
						</thead> 
						<tbody>
							<%if(request.getParameter("mode2").equalsIgnoreCase("addLigne")){ %>
							<form action="action" method="post">
							<tr>
								<td></td>
								<td>
								<select name="reference_article" onchange="pu.value=tab_prix[this.value];qte.value=1;pt.value=qte.value*pu.value;">
								<option value=""></option>
								<%for(int i=0;i<articles.size();i++){%>
	                    			<option value="<%=articles.get(i)[0]%>"><%=articles.get(i)[1] %></option>
	                    		<%}%>
	                    		</select>
								</td>
								<td><input type="text" name="pu" id="pu" onchange="pt.value=qte.value*pu.value;"/></td>
								<td><input type="text" name="qte" id="qte" onchange="pt.value=qte.value*pu.value;"/></td>
								<td><input type="text" name="pt" id="pt" readonly="readonly" /></td>
								<td>
									<input type="hidden" name="op" value="add_ligne_confirmation"/>
									<input type="hidden" name="id_doc" value="<%=request.getParameter("id")%>"/>
									<button type="submit" style="border:0; background:transparent ;cursor: pointer;"><img src="img/i_ok.png"/></button>
								</td>
							</tr>
							</form>
							<%}else if(request.getParameter("mode2").equalsIgnoreCase("editLigne")){ 
								List<String[]> ligne=mssqlDao.executeQuery("select id,reference_article,pu,qte,pt from m_lignes where id="+request.getParameter("id_ligne"));
								//System.out.println("id_ligne : "+request.getParameter("id_ligne")+" else edit");
							%>
							<form action="action" method="post">
							<tr>
								<td></td>
								<td>
								<select name="reference_article" onchange="pu.value=tab_prix[this.value];qte.value=1;pt.value=qte.value*pu.value;">
								<option value=""></option>
								<%for(int i=0;i<articles.size();i++){
								  if(articles.get(i)[0].equalsIgnoreCase(ligne.get(0)[1])){%>
	                    			<option value="<%=articles.get(i)[0]%>" selected="selected"><%=articles.get(i)[1] %></option>
	                    		<%}else{%>
	                    			<option value="<%=articles.get(i)[0]%>"><%=articles.get(i)[1] %></option>
	                    		<%}
								  }%>
	                    		</select>
								</td>
								<td><input type="text" name="pu"  value="<%=ligne.get(0)[2] %>"  id="pu"  onchange="pt.value=qte.value*pu.value;"/></td>
								<td><input type="text" name="qte" value="<%=ligne.get(0)[3] %>" id="qte" onchange="pt.value=qte.value*pu.value;"/></td>
								<td><input type="text" name="pt"  value="<%=ligne.get(0)[4] %>"  id="pt"  readonly="readonly" /></td>
								<td colspan="2">
									<input type="hidden" name="op" value="edit_ligne_confirmation"/>
									<input type="hidden" name="id_doc" value="<%=request.getParameter("id")%>"/>
									<input type="hidden" name="id_ligne" value="<%=request.getParameter("id_ligne")%>"/>
									<button type="submit" style="border:0; background:transparent ;cursor: pointer;"><img src="img/i_ok.png"/></button>
								</td>
							</tr>
							</form>
							<%} %>
							<%for(int i=0;i<donnee.size();i++){ %>
							<tr>
								<%for(int j=0;j<donnee.get(i).length;j++){ 
									if(j==0){%>
										<input type="hidden" name="id_ligne" value="<%=donnee.get(i)[j] %>"/>
								  <%}%>
									<td><%=donnee.get(i)[j] %></td>
								<%} %>
								<td><a href="confirmation_da.jsp?mode=editDoc&mode2=editLigne&id=<%=request.getParameter("id") %>&id_ligne=<%=donnee.get(i)[0] %>"><img src="img/i_edit.png"/></a></td>
								<td><a href="action?op=delete_ligne_confirmation&id_doc=<%=request.getParameter("id") %>&id_ligne=<%=donnee.get(i)[0] %>"><img src="img/i_delete.png"/></a></td>
							</tr>
							<%} %>
						</tbody>
					</table>
                    <%} %>
					<!--               end  main        -->
                </div>
                <div class="clear"></div>
            </div>

            
        </div>
</body>
</html>
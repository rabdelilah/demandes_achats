<%@page import="services.services"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="sidebar">
			<%
			String user=(String)session.getAttribute("login"); 
			String profil=services.getProfil(user);
			%>
			<div class="box">
				<div class="h_title">&#8250; Administrateur</div>
				<ul id="home">
					<li class="b1"><a class="icon page" href="index.jsp">Index</a></li>
					<%if(profil.equals("1")){ %>
					<li class="b1"><a class="icon page" href="profil.jsp?mode=add">Gestion Profil</a></li>
					<%} %>
				</ul>
			</div>
			<%if(!profil.equals("1")){ %>
			<div class="box">
				<div class="h_title">&#8250; Traitements</div>
				<ul id="home">
					<li class="b1"><a class="icon page" href="list.jsp">Liste Demande d'achat</a></li>
					<li class="b1"><a class="icon page" href="add_da.jsp?mode=addDoc">Saisie Demande d'achat</a></li>
				</ul>
			</div>
			<%} %>
</div>	
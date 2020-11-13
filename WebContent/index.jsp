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
                    
					<!--               end  main        -->
                </div>
                <div class="clear"></div>
            </div>

            
        </div>
</body>
</html>
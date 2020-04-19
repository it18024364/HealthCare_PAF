<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "com.javafiles.*"%>
    
<%
    	if(request.getParameter("itemCode") != null)
        	{ 	
        	items itemObj = new items();
        	String stsMsg = itemObj.insertItem(request.getParameter("itemCode"),
        								request.getParameter("itemName"),
        								request.getParameter("itemPrice"),
        								request.getParameter("itemDesc"));
        	session.setAttribute("statusMsg", stsMsg);
        	}

        if(request.getParameter("itemID") != null)
        	{
        		items itemObj = new items();
        		String stsMsg =itemObj.deleteitems(request.getParameter("itemID"));
        		session.setAttribute("statusMsg",stsMsg);
        	
        	}
    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Lab03</title>
</head>

<body>
	<h1>lab03</h1>
	<form method="post" action="index.jsp">
		UserName <input name="itemCode" type="text"><br>
		Name <input name="itemName" type="text"><br>
		phone no <input name="itemPrice" type="text"><br>
		Specialty <input name="itemDesc" type="text"><br>
		<input name="btnSubmit" type="submit" value="Sign up">
		
	</form>
	
<%	out.print(session.getAttribute("statusMsg")); %>
	
	<br>
	
	<%
			items itemObj = new items();
				out.print(itemObj.readitems());
	%>
	
	
</body>
</html>
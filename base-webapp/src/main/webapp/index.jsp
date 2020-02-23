<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style.css">
    <title>JSP Page</title>
</head>
<body>

<%@include file="menu.html" %>
<div class="container">
<%!
    public String companyInfo(){
        return  "Here lies company info";
    }
%>
<% out.println(companyInfo()); %>
</div>
</body>
</html>
<%@tag description="Page's template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<!doctype html>

<html lang="en">
	<head>
	  <meta charset="utf-8">
	  <title>Math Problems</title>
	  <link rel="stylesheet" type="text/css" href="./css/site.css">
	</head>
	 <body>
	   <div id="pageheader">
	     <jsp:invoke fragment="header"/>
	   </div>
	   <div id="body">
	     <jsp:doBody/>
	   </div>
	   <div id="pagefooter">
	     <jsp:invoke fragment="footer"/>
	   </div>
	 </body>
</html>
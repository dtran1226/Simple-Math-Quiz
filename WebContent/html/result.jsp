<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:custom>
    <jsp:attribute name="header">
      <h1 style="color:blue">Math Problems</h1>
    </jsp:attribute>
    <jsp:body>
	<h1>Congratulate <span style="color:green">${sessionScope.username}</span> for completing the quiz</h1>
	<p>Your final score is <span style="color:red">${requestScope.score}%</span></p>
    </jsp:body>
</t:custom>
</html>
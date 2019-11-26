<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:custom>
    <jsp:attribute name="header">
      <h1 style="color:blue">Math Problems</h1>
    </jsp:attribute>
    <jsp:body>
    <form action="Quiz" method="GET">
    	Username:
		<input class ="input" type="text" name="username" placeholder="Enter your username">
		<br />
		<br />
		Number of Questions:
		<input class ="input" type="number" min="1" name="numOfQues" placeholder="Choose a number of questions" required="required">
		<br />
		<p><b>What kinds of operations would you like to solve?</b></p>
		<ul class="THAN">
        	<li>
            	<input type="checkbox" name="cbAdd" />Addition
        	</li>
        	<li>
            	<input type="checkbox" name="cbSubtract" />Subtraction
        	</li>
        	<li>
            	<input type="checkbox" name="cbDivide" />Division
        	</li>
        	<li>
            	<input type="checkbox" name="cbMultiply" />Multiplication
        	</li>
    	</ul>
    	<br />
		<button type="submit">Start Quiz</button>
	</form>
    </jsp:body>
</t:custom>
</html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:custom>
    <jsp:attribute name="header">
      <h1 style="color:blue">Math Problems</h1>
    </jsp:attribute>
    <jsp:body>
    <form action="Quiz" method="GET">
		<input class="operand" type="text" name="leftOperand" readonly value="${requestScope.leftOperand}">
		<input id ="operator" type="text" name="operator" readonly value="${requestScope.operator}">
		<input class="operand" type="text" name="rightOperand" readonly value="${requestScope.rightOperand}">
		<br />
		<br />
		<input type="number" min="0" name="answer" placeholder="Your answer" required="required">
		<br />
		<br />
		<button class="btn" type="submit">Submit</button>
	</form>
	<p>${sessionScope.counter}/${sessionScope.numOfQues}</p>
	<p>Correct: <span style="color:blue">${sessionScope.correctAnswers}</span>, Incorrect: <span style="color:red">${sessionScope.incorrectAnswers}</span></p>
    </jsp:body>
</t:custom>
</html>
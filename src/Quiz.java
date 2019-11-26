import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Quiz
 */
@WebServlet("/Quiz")
public class Quiz extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Create an http session to get/set session attributes
		HttpSession session = request.getSession();

		// Set number of questions attribute from jsp request into session
		if (session.getAttribute("numOfQues") == null) {
			session.setAttribute("numOfQues", request.getParameter("numOfQues"));
		}

		// Collect a list of operators that user selected
		List<String> operators = new ArrayList<String>();
		if (session.getAttribute("operatorStr") == null) {
			String add = request.getParameter("cbAdd");
			String sub = request.getParameter("cbSubtract");
			String mul = request.getParameter("cbMultiply");
			String div = request.getParameter("cbDivide");

			if (add != null) {
				operators.add("+");
			}
			if (sub != null) {
				operators.add("-");
			}
			if (mul != null) {
				operators.add("*");
			}
			if (div != null) {
				operators.add("/");
			}

			// If user did not select any operator, all operators will be applied
			if (operators.size() == 0) {
				operators.add("+");
				operators.add("-");
				operators.add("*");
				operators.add("/");
			}

			// Store all selected operators to a session attribute to reuse during running
			// the application
			String operatorStr = "";
			for (int i = 0; i < operators.size(); i++) {
				operatorStr += operators.get(i) + ",";
			}
			session.setAttribute("operatorStr", operatorStr);
		} else {
			// Reuse selected operators
			String[] opers = session.getAttribute("operatorStr").toString().split(",");
			for (int i = 0; i < opers.length; i++) {
				operators.add(opers[i]);
			}
		}
		// Create random numbers between 1 and 100 for an operation
		Random rand = new Random();
		int leftOperandInt = rand.nextInt(99) + 1;
		int rightOperandInt = rand.nextInt(99) + 1;
		// Choose a random operator within a list of selected operators
		int operatorIndex = rand.nextInt(operators.size());
		String operatorStr = operators.get(operatorIndex);
		// Swap 2 numbers in case the left operand is less than the right operand (to
		// have positive result)
		if (operatorStr.equals("-")) {
			if (leftOperandInt < rightOperandInt) {
				int tmp = leftOperandInt;
				leftOperandInt = rightOperandInt;
				rightOperandInt = tmp;
			}
		} else if (operatorStr.equals("/")) {
			// Reselect the right operand randomly to make sure they can be evenly divided
			// by the left operand (to have an integer result)
			while (leftOperandInt % rightOperandInt != 0) {
				rightOperandInt = rand.nextInt(99) + 1;
			}
		}

		// Get the operands, operator and user's answer from request attribute
		String leftOperandStr = request.getParameter("leftOperand");
		String rightOperandStr = request.getParameter("rightOperand");
		String operator = request.getParameter("operator");
		String answer = request.getParameter("answer");
		// Set the operands and operator to request attribute to display on jsp page
		request.setAttribute("leftOperand", leftOperandInt);
		request.setAttribute("rightOperand", rightOperandInt);
		request.setAttribute("operator", operatorStr);

		// Set username into session attribute
		if (session.getAttribute("username") == null) {
			session.setAttribute("username", request.getParameter("username"));
		}

		// Set counter of current question to session attribute
		if (session.getAttribute("counter") == null) {
			session.setAttribute("counter", 1);
		} else {
			// Increase counter by 1 after each question
			session.setAttribute("counter", Integer.parseInt(session.getAttribute("counter").toString()) + 1);
		}

		// Set the number of correct answer to session attribute
		if (session.getAttribute("correctAnswers") == null) {
			session.setAttribute("correctAnswers", 0);
		}
		// Set the number of incorrect answer to session attribute
		if (session.getAttribute("incorrectAnswers") == null) {
			session.setAttribute("incorrectAnswers", 0);
		}

		// Check if the answer is correct or not
		if (leftOperandStr != null && rightOperandStr != null && answer != null) {
			int ans = Integer.parseInt(answer);
			int leftOperand = Integer.parseInt(leftOperandStr);
			int rightOperand = Integer.parseInt(rightOperandStr);
			int tempAns = 0;
			switch (operator) {
			case "+":
				tempAns = leftOperand + rightOperand;
				break;
			case "-":
				tempAns = leftOperand - rightOperand;
				break;
			case "*":
				tempAns = leftOperand * rightOperand;
				break;
			case "/":
				tempAns = leftOperand / rightOperand;
				break;
			}
			// Increase the number of correct answers by 1 after each correct answer
			if (tempAns == ans) {
				session.setAttribute("correctAnswers",
						Integer.parseInt(session.getAttribute("correctAnswers").toString()) + 1);
			} else {
				// Increase the number of correct answers by 1 after each correct answer
				session.setAttribute("incorrectAnswers",
						Integer.parseInt(session.getAttribute("incorrectAnswers").toString()) + 1);
			}
		}

		// Keep loading new math problems until the last problem
		if (Integer.parseInt(session.getAttribute("numOfQues").toString())
				- Integer.parseInt(session.getAttribute("counter").toString()) >= 0) {
			request.getRequestDispatcher("html/quiz.jsp").forward(request, response);
		} else {
			// IF there is no more problem, calculate score base on number of correct
			// answers over the total problems
			double score = Double.parseDouble(session.getAttribute("correctAnswers").toString())
					/ Double.parseDouble(session.getAttribute("numOfQues").toString()) * 100;
			// Go to result page to show score
			request.setAttribute("score", score);
			request.getRequestDispatcher("html/result.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

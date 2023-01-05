package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DonationDAO;
import beans.Donation;
import beans.User;

/**
 * Servlet implementation class DonationController
 */
@WebServlet("/DonationController")
public class DonationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		
		if (action == null) {
			response.sendRedirect("index.jsp");
		} else {
			switch (action) {
			case "donate":
				createDonate(request, response);
				break;
			case "showDonate":
				showDonate(request, response);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void createDonate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			HttpSession session = request.getSession(true);
			String amount = request.getParameter("amount");
			String postId = request.getParameter("postId");
			String description = request.getParameter("transaction-description");
			StringBuilder msg = new StringBuilder();
			DonationDAO DDAO = new DonationDAO();
		    
			//get user id
			User user = (User) session.getAttribute("user");
			if (user.equals(null)) {
				msg.append("You need to login first <br>");
			}
			int userID = user.getID();
			
			long millis=System.currentTimeMillis();  
		    Date date=new java.sql.Date(millis);
		    long amount2=Long.parseLong(amount);
		    int postId2 = Integer.parseInt(postId);
			
			if (!msg.toString().isBlank()) {
				String fullMsg = msg.toString();
				session.setAttribute("msg", fullMsg);
				response.sendRedirect("register.jsp");
				return;
			}
			
			Donation donation =  new Donation(userID, postId2, amount2, description, date);
			String result = DDAO.donate(donation);
			
			if (result == "Success") {
				session.setAttribute("msg", "Successfully Donated");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			} else if (result == "Failed") {
				session.setAttribute("msg", result);
				response.sendRedirect("create_donate.jsp");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	protected void showDonate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String postId = request.getParameter("id");
			request.setAttribute("postId", postId);
			RequestDispatcher rd = request.getRequestDispatcher("donate.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

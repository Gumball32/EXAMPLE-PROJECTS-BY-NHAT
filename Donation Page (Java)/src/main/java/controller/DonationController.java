package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CharityPostDAO;
import DAO.DonationDAO;
import beans.CharityPost;
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
			case "showDonates":
				showDonates(request, response);
				break;
			case "delete":
				deleteDonate(request, response);
				break;
			case "showDeleteAll" :
				showDeleteConfirmation(request, response);
				break;
			case "deleteAll":
				deleteDonations(request, response);
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
			CharityPostDAO CPDAO = new CharityPostDAO();
		    
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
		    
		    //check date
		    CharityPost testPost = CPDAO.getPost(postId);
		    if (testPost.getEndDate().compareTo(date) < 1) {
		    	session.setAttribute("msg", "Donation is closed");
				response.sendRedirect("index.jsp");
				return;
		    }
		    
		    //check amount
		    if (Integer.parseInt(amount) < 1) {
		    	session.setAttribute("msg", "Number of amount is not valid");
				response.sendRedirect("index.jsp");
				return;
		    }
			
			if (!msg.toString().isBlank()) {
				String fullMsg = msg.toString();
				session.setAttribute("msg", fullMsg);
				response.sendRedirect("index.jsp");
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
				response.sendRedirect("index.jsp");
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
	
	protected void showDonates(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		int page = 1;
		int recoredPage = 6;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		try {
			String userid = request.getParameter("userid");
			DonationDAO DDAO = new DonationDAO();
			int totalAmount = 0;
			
			int noOfRecords = DDAO.getTotalDonation();
			int noOfPage = (int) Math.ceil(noOfRecords * 1.0 / recoredPage);
			List<Donation> list = DDAO.getAllDonate((page - 1) * recoredPage, recoredPage, userid);
			
			for (Donation donation : list) {
				totalAmount += donation.getAmount();
			}
			
			request.setAttribute("list", list);
			request.setAttribute("noOfPage", noOfPage);
			request.setAttribute("currentPage", page);
			request.setAttribute("totalAmount", totalAmount);
			
			
			RequestDispatcher rd = request.getRequestDispatcher("total_donate.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void deleteDonate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String userid = request.getParameter("userid");
			String postid = request.getParameter("postid");
			DonationDAO DDAO = new DonationDAO();
			String result = DDAO.deleteDonate(userid, postid);
			HttpSession session = request.getSession(true);
			
			if (result == "Success") {
				session.setAttribute("msg", "Successfully Deleted");
			} else if (result == "Failed") {
				session.setAttribute("msg", result);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			response.getWriter().println(e);
		}
	}
	
	protected void showDeleteConfirmation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String[] ids = request.getParameterValues("check");
			String type = request.getParameter("deleteAllType");
			request.setAttribute("ids", ids);
			request.setAttribute("type", type);
			RequestDispatcher rd = request.getRequestDispatcher("deleteAllConfirmation.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void deleteDonations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String[] ids = request.getParameterValues("check");
			
			if (ids == null) {
				response.sendRedirect("profile.jsp");
				return;
			}
			
			String[] userIdS = new String[ids.length];
			String[] postIdS = new String[ids.length];
			
			for (int i = 0; i < ids.length; i++) {
				String[] temp = ids[i].split("&");
				
				userIdS[i] = temp[0];
				postIdS[i] = temp[1];
			}
			
			
			DonationDAO DDAO = new DonationDAO();
			HttpSession session = request.getSession(true);
			
			for (int i = 0; i < ids.length; i++) {
				DDAO.deleteDonate(userIdS[i], postIdS[i]);
			}
						
			session.setAttribute("msg", "Successfully Deleted");			
			RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			response.getWriter().println(e);
		}
	}
}

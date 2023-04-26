package Controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.UserDAO;
import Model.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
@MultipartConfig (
		location = "C:\\Users\\Administrator\\eclipse-workspace\\Shoppeoo\\src\\main\\webapp\\images",
		fileSizeThreshold = 1024 * 1024,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 11
		)
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
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
				case "login":
					login(request, response);
					break;
				case "create":
					createAccount(request, response);
					break;
				case "logout":
					logout(request, response);
					break;
				case "update":
					updateAccount(request, response);
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
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			UserDAO UDAO = new UserDAO();
			HttpSession session = request.getSession(true);
			
			Model.User user = UDAO.getUser(username);
			
			//check if there a account like that
			if (user == null) {
				session.setAttribute("msg", "This account is not created");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
				return;
			}
			
			//if account deleted
			if (user.getStatus() == 0) {
				session.setAttribute("msg", "The account is deleted");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
				return;
			}
			
			//Wrong Password
			if (user.getRole() != 1) {
				if (!password.equals(user.getPassword())) {
					session.setAttribute("msg", "Wrong Password");
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
					return;
				}
			}
						
			session.setAttribute("msg", "Logged In");
			session.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("ProductController");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();response.sendRedirect("your-error-page.jsp");
		}
	}
	
	protected String getFilename(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		
		if (!contentDisposition.contains("filename=")) {
			return null;
		}
		
		int beginIndex = contentDisposition.indexOf("filename=") + 10;
		int lastIndex = contentDisposition.length()  -  1;
		
		return contentDisposition.substring(beginIndex, lastIndex);
	}
	
	protected void createAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String username = request.getParameter("username_input");
			String fullname = request.getParameter("fullname_input");
			String password = request.getParameter("password_input");
			String email = request.getParameter("email_input");
			HttpSession session = request.getSession(true);
			UserDAO UDAO = new UserDAO();
			
			StringBuilder msg = new StringBuilder();
			
			//check valid username
			int checkUsername = UDAO.checkUsername(username);
			if (checkUsername == 1) {
				msg.append("Username is chosen <br>");
			}
			
			//Check password characters
			if (password.trim().isEmpty()) {
				msg.append("Password has invalid characters <br>");
			}
			if (password.length() < 6) {
				msg.append("Password must have at least 6 characters <br>");
			}

			//get image
			Part part = request.getPart("image_input");
			String filename = getFilename(part);
			StringBuilder imagePath = new StringBuilder();
			if (filename == null) {
				imagePath.append("images/shopeeo-logo.png");
			} else {
				imagePath.append("images/" + filename);
				part.write(filename);
			}
			
			if (!msg.toString().isBlank()) {
				String fullMsg = msg.toString();
				session.setAttribute("msg", fullMsg);
				response.sendRedirect("signup.jsp");
				return;
			}

			User user = new User(username, fullname, password, email, 0, 1, imagePath.toString());
			int result = UDAO.createAccount(user);
			if (result == 1) {
				session.setAttribute("msg", "Successfully Created");
				RequestDispatcher rd = request.getRequestDispatcher("UserController");
				rd.forward(request, response);
			} else if (result == 0) {
				session.setAttribute("msg",msg.toString());
				response.sendRedirect("signin.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();response.sendRedirect("your-error-page.jsp");
		}
	}
	
	protected void updateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String username = request.getParameter("username_input");
			String fullname = request.getParameter("fullname_input");
			String password = request.getParameter("password_input");
			String email = request.getParameter("email_input");
			String cur_image = request.getParameter("current_image");
			String id = request.getParameter("id");
			HttpSession session = request.getSession(true);
			UserDAO UDAO = new UserDAO();
			
			StringBuilder msg = new StringBuilder();
			
			//check valid username
			User checkUsername = UDAO.getUser(username);
			if (checkUsername != null) {
				if (checkUsername.getFullname().equals(username)) {
					msg.append("Username is chosen <br>");
				}
			}

			//Check password characters
			if (password.trim().isEmpty()) {
				msg.append("Password has invalid characters <br>");
			}
			if (password.length() < 6) {
				msg.append("Password must have at least 6 characters <br>");
			}

			//get image
			Part part = request.getPart("image_input");
			StringBuilder imagePath = new StringBuilder();
			if (part == null || !getFilename(part).equals("")) {
				String filename = getFilename(part);
				if (filename == null) {
					imagePath.append(cur_image);
				} else {
					imagePath.append("images/" + filename);
					part.write(filename);
				}
			} else {
				imagePath.append(cur_image);
			}
			
			
			if (!msg.toString().isBlank()) {
				String fullMsg = msg.toString();
				session.setAttribute("msg", fullMsg);
				response.sendRedirect("profile.jsp");
				return;
			}
			
			User user = new User(username, fullname, password, email, 0, 1, imagePath.toString());
			int result = UDAO.updateAccount(user, id);
			if (result == 1) {
				session.setAttribute("msg", "Successfully Updated");
				session.setAttribute("user", user);
			} else if (result == 0) {
				session.setAttribute("msg",msg.toString());
			}
			response.sendRedirect("profile.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();response.sendRedirect("your-error-page.jsp");
		}
	}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			HttpSession session = request.getSession(true);
			session.removeAttribute("user");
			session.setAttribute("msg", "Logged Out");
			response.sendRedirect("ProductController");
		} catch (Exception e) {
			e.printStackTrace();response.sendRedirect("your-error-page.jsp");
		}
	}
}

package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.CharityPostDAO;
import DAO.UserDAO;
import api.Mail;
import api.PasswordGenerator;
import beans.CharityPost;
import beans.User;
import api.MD5;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
@MultipartConfig (
		location = "C:\\Users\\Administrator\\Downloads\\eclipse-workspace\\SWP490x _Full Project\\src\\main\\webapp\\images",
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
				case "create":
					createAccount(request, response);
					break;
				case "login":
					login(request, response);
					break;
				case "edit_profile":
					editAccount(request, response);
					break;
				case "forgetPassword":
					forgetPassword(request, response);
					break;
				case "logout":
					logout(request, response);
					break;
				case "users":
					getAllUsers(request, response);
					break;
				case "delete":
					deleteAccount(request, response);
					break;
				case "deleteAll":
					deleteUsers(request, response);
					break;
				case "editAsAdmin":
					showEditUser(request, response);
					break;
				case "showDeleteAll" :
					showDeleteConfirmation(request, response);
					break;
				case "searchUser":
					findUser(request, response);
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
	
	public boolean containsIllegals(String toExamine) {
	    Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^]");
	    Matcher matcher = pattern.matcher(toExamine);
	    return matcher.find();
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
	
	protected void deleteUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String[] ids = request.getParameterValues("check");
			
			if (ids == null) {
				response.sendRedirect("UserController?action=users");
				return;
			}
			
			UserDAO UDAO = new UserDAO();
			HttpSession session = request.getSession(true);
			
			for (String id : ids) {
				if (UDAO.getUserID(id).getRole() == 1) {
					continue;
				}
				UDAO.deleteUser(id);
			}
						
			session.setAttribute("msg", "Successfully Deleted");			
			RequestDispatcher rd = request.getRequestDispatcher("UserController?action=users");
			rd.forward(request, response);
		} catch (Exception e) {
			response.getWriter().println(e);
		}
	}
	
	protected void getAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		int page = 1;
		int recoredPage = 6;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		try {
			UserDAO UAO = new UserDAO();
			
			int noOfRecords = UAO.getNoUsers();
			int noOfPage = (int) Math.ceil(noOfRecords * 1.0 / recoredPage);
			List<User> list = UAO.getAllUsers((page - 1) * recoredPage, recoredPage);
			
			request.setAttribute("list", list);
			request.setAttribute("noOfPage", noOfPage);
			request.setAttribute("currentPage", page);
			
			RequestDispatcher rd = request.getRequestDispatcher("users.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void findUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String username = request.getParameter("username");
			String id = request.getParameter("IDCard");
			HttpSession session = request.getSession(true);
			int page = 1;
			int noOfPage = 0;
			
			if (username.equals("None") && id.equals("0")) {
				session.setAttribute("msg", "Input values to search");
				RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
				rd.forward(request, response);
				return;
			}
			
			UserDAO UAO = new UserDAO();
			
			List<User> list = UAO.findUsers(username, id);
			System.out.println(list);
			
			request.setAttribute("list", list);
			request.setAttribute("noOfPage", noOfPage);
			request.setAttribute("currentPage", page);
			
			RequestDispatcher rd = request.getRequestDispatcher("users.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String id = request.getParameter("id");
			UserDAO UD = new UserDAO();
			HttpSession session = request.getSession(true);
			
			//check admin
			User tempUser = UD.getUserID(id);
			if (tempUser.getRole() == 1) {
				session.setAttribute("msg", "Cannot delete ADMIN");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
				return;
			}
			
			String result = UD.deleteUser(id);
			
			if (result == "Success") {
				session.setAttribute("msg", "Successfully Deleted");
				logout(request, response);
			} else if (result == "Failed") {
				session.setAttribute("msg", result);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void showEditUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String id = request.getParameter("ID");
			HttpSession session = request.getSession(true);
			UserDAO UD = new UserDAO();
			User user = UD.getUserID(id);
			session.setAttribute("userByAdmin", user);
			RequestDispatcher rd = request.getRequestDispatcher("edit_profile.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void editAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String username = request.getParameter("username");
			String oldpassword = request.getParameter("oldpassword");
			String newpassword = request.getParameter("newpassword");
			String confirmpassword = request.getParameter("re-newpassword");
			String firstName = request.getParameter("first-name");
			String lastName = request.getParameter("last-name");
			String birthday = request.getParameter("birthday");
			String convertedBirthday = birthday.replace("-", "/");
			String idCard = request.getParameter("id-card");
			String gender = request.getParameter("gender");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phone");
			HttpSession session = request.getSession(true);
			String ID = request.getParameter("id");
			UserDAO UD = new UserDAO();
			
			StringBuilder msg = new StringBuilder();
			
			//check valid username
			int checkUsername = UD.checkUsername(username);
			if (checkUsername == 1) {
				msg.append("Username is chosen <br>");
			}
			
			//Check password characters
			if (containsIllegals(newpassword)) {
				msg.append("Password has invalid characters <br>");
			}
			if (newpassword.length() < 6) {
				msg.append("Password must have at least 6 characters <br>");
			}
			if (!newpassword.equals(confirmpassword)) {
				msg.append("Password and confirm password must be the same <br>");
			}
			if (oldpassword.equals(newpassword)) {
				msg.append("New passord cannot be the same as old password <br>");
			}
			
			//set birthday
			Date date1 =new SimpleDateFormat("yyyy/MM/dd").parse(convertedBirthday);
			
			if (!msg.toString().isBlank()) {
				String fullMsg = msg.toString();
				session.setAttribute("msg", fullMsg);
				response.sendRedirect("edit_profile.jsp");
				return;
			}
			
			//password
//			PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
//			        .useDigits(true)
//			        .useLower(true)
//			        .useUpper(true)
//			        .build();
			MD5 md5 = new MD5();
			String encryptedPassword = md5.encrypt(newpassword);
			
			//get image
			Part part = request.getPart("image");
			StringBuilder imagePath = new StringBuilder();
			if (part == null || !getFilename(part).equals("")) {
				String filename = getFilename(part);
				if (filename == null) {
					imagePath.append("images/news/medium-shot-volunteers-with-clothing-donations.jpg");
				} else {
					imagePath.append("images/" + filename);
					part.write(filename);
				}
			}
			else {
				imagePath.append(request.getParameter("currentImage"));
			}
			
			
			User user = new User(lastName, firstName, gender, idCard, date1, username, encryptedPassword, email, phoneNumber, 0, 1, imagePath.toString());
			String result = UD.updateAccount(user, ID);
			if (result == "Success") {
				Mail.sendMail(email, "username: ".concat(username).concat(" password: ".concat(newpassword)), "Account Updated");
				session.setAttribute("msg", "Successfully Updated");
				RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
				rd.forward(request, response);
			} else if (result == "Failed") {
				session.setAttribute("msg","Failed");
				response.sendRedirect("edit_profile.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	protected void createAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String username = request.getParameter("username");
			String firstName = request.getParameter("first-name");
			String lastName = request.getParameter("last-name");
			String birthday = request.getParameter("birthday");
			System.out.println(birthday);
			String convertedBirthday = birthday.replace("-", "/");
			String idCard = request.getParameter("id-card");
			String gender = request.getParameter("gender");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phone");
			HttpSession session = request.getSession(true);
			UserDAO UD = new UserDAO();
			
			StringBuilder msg = new StringBuilder();
			
			//check valid username
			int checkUsername = UD.checkUsername(username);
			if (checkUsername == 1) {
				msg.append("Username is chosen <br>");
			}
			
			//set birthday
			Date date1 =new SimpleDateFormat("yyyy/MM/dd").parse(convertedBirthday);
			
			if (!msg.toString().isBlank()) {
				String fullMsg = msg.toString();
				session.setAttribute("msg", fullMsg);
				response.sendRedirect("register.jsp");
				return;
			}
			
			//Check password characters
			//if (containsIllegals(password)) {
				//msg.append("Password has invalid characters <br>");
			//}
			//if (password.length() < 6) {
				//msg.append("Password must have at least 6 characters <br>");
			//}
			//if (!password.equals(confirmPassword)) {
				//msg.append("Password and confirm password must be the same <br>");
			//}
			
			//get image
			Part part = request.getPart("image");
			String filename = getFilename(part);
			StringBuilder imagePath = new StringBuilder();
			if (filename == null) {
				imagePath.append("images/news/medium-shot-volunteers-with-clothing-donations.jpg");
			} else {
				imagePath.append("images/" + filename);
				part.write(filename);
			}
			
			PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
			        .useDigits(true)
			        .useLower(true)
			        .useUpper(true)
			        .build();
			String password = passwordGenerator.generate(8); // output ex.: lrU12fmM 75iwI90o
			MD5 md5 = new MD5();
			String encryptedPassword = md5.encrypt(password);
			
			User user = new User(lastName, firstName, gender, idCard, date1, username, encryptedPassword, email, phoneNumber, 0, 1, imagePath.toString());
			String result = UD.createAccount(user);
			if (result == "Success") {
				Mail.sendMail(email, "Successfully Created. This is your password: ".concat(password), "New mail created");
				session.setAttribute("msg", "Successfully Created");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			} else if (result == "Failed") {
				session.setAttribute("msg","Failed");
				response.sendRedirect("register.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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

	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			UserDAO UD = new UserDAO();
			HttpSession session = request.getSession(true);
			
			//List<User> list = UD.login(username, password);
			User user = UD.getUser(username);
			
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
				MD5 md5 = new MD5();
				if (!md5.compare(password, user.getPassword())) {
					session.setAttribute("msg", "Wrong Password");
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
					return;
				}
			} else {
				if (!password.equals(user.getPassword())) {
					session.setAttribute("msg", "Wrong Password");
					RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
					return;
				}
			}
			
			
			session.setAttribute("msg", "Logged In");
			session.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			HttpSession session = request.getSession(true);
			session.removeAttribute("user");
			session.setAttribute("msg", "Logged Out");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void forgetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String username = request.getParameter("username");
			
			StringBuilder msg = new StringBuilder();
			UserDAO UD = new UserDAO();
			HttpSession session = request.getSession(true);
			
			//check valid username
			int checkUsername;
			checkUsername = UD.checkUsername(username);
			if (checkUsername != 1) {
				msg.append("There is no username like that <br>");
				String fullMsg = msg.toString();
				session.setAttribute("msg", fullMsg);
				response.sendRedirect("forgetPassword.jsp");
				return;
			}
			
			//Generate new password
			PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
			        .useDigits(true)
			        .useLower(true)
			        .useUpper(true)
			        .build();
			String password = passwordGenerator.generate(8); // output ex.: lrU12fmM 75iwI90o
			MD5 md5 = new MD5();
			String encryptedPassword = md5.encrypt(password);
			
			User user = UD.getUser(username);
			UD.updatePassword(user.getID(), encryptedPassword);
			Mail.sendMail(user.getEmail(), "Password: ".concat(password), "New Password");
			session.setAttribute("msg", "Email Sent");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			
			
			if (!msg.toString().isBlank()) {
				String fullMsg = msg.toString();
				session.setAttribute("msg", fullMsg);
				response.sendRedirect("edit_profile.jsp");
				return;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

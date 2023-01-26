package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.CharityPostDAO;
import DAO.DonationDAO;
import beans.CharityPost;
import beans.PostImage;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class CharityPostController
 */
@WebServlet("/CharityPostController")
@MultipartConfig (
		location = "C:\\Users\\Administrator\\Downloads\\eclipse-workspace\\SWP490x _Full Project\\src\\main\\webapp\\images",
		fileSizeThreshold = 1024 * 1024,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 11
		)
public class CharityPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CharityPostController() {
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
					createPost(request, response);
					break;
				case "all":
					showPosts(request, response);
					break;
				case "edit":
					showEdit(request, response);
					break;
				case "update":
					updatePost(request, response);
					break;
				case "delete":
					deletePost(request, response);
					break;
				case "deleteAll":
					deletePosts(request, response);
					break;
				case "detailDonate":
					showPost(request, response);
					break;
				case "showDeleteAll" :
					showDeleteConfirmation(request, response);
					break;
				case "searchPost":
					searchPost(request, response);
					break;
				case "endDonate":
					endPost(request, response);
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
	
	protected String getFilename(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		
		if (!contentDisposition.contains("filename=")) {
			return null;
		}
		
		int beginIndex = contentDisposition.indexOf("filename=") + 10;
		int lastIndex = contentDisposition.length()  -  1;
		
		return contentDisposition.substring(beginIndex, lastIndex);
	}
	
	protected void createPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String startDate = request.getParameter("start_date");
			String startDateConverted = startDate.replace("-", "/");
			String endDate = request.getParameter("end_date");
			String endDateConverted = endDate.replace("-", "/");
			
//			String startDate = dayStart + "/" + monthStart + "/" + yearStart;
//			String endDate = dayEnd + "/" + monthEnd + "/" + yearEnd;
//			String regexMail = "^[A-Z0-9 a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
//			String regex = "[a-zA-Z0-9!@#$%^&*]+";
			HttpSession session = request.getSession(true);
			CharityPostDAO CPD = new CharityPostDAO();
			
			//check name
			if (CPD.getPostWithName(name).size() > 0) {
				session.setAttribute("msg", "Name is taken");
				RequestDispatcher rd = request.getRequestDispatcher("CharityPostController?action=all");
				rd.forward(request, response);
				return;
			}
			
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
			
			//add image
//			CharityPost afterAddPost = CPD.getPostWithName(name);
//			String resultImage = CPD.addImage(afterAddPost.getID(), "images\\" + filename, 1);
//			if (resultImage == "Failed") {
//				session.setAttribute("msg", "Failed");
//				response.sendRedirect("create_donate.jsp");
//				return;
//			}
			
//			if (!name.matches(regex) || !name.matches(regexMail)) {
//				session.setAttribute("msg", "Invalid syntax!");
//				response.sendRedirect("index.jsp");
//				return;
//			}
		
			
		    Date date = new Date();  
			Date date1 =new SimpleDateFormat("yyyy/MM/dd").parse(startDateConverted);
			Date date2 =new SimpleDateFormat("yyyy/MM/dd").parse(endDateConverted);
			
			if (date2.getTime() - date1.getTime() < 1) {
				session.setAttribute("msg", "Dates are not valid");
				response.sendRedirect("create_donate.jsp");
				return;
			}
			
			CharityPost newPost = new CharityPost(name, description, date, date1, date2, imagePath.toString());
			
			String result = CPD.createPost(newPost);
			
			if (result == "Success") {
				session.setAttribute("msg", "Successfully Updated");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			} else if (result == "Failed") {
				session.setAttribute("msg", "Failed");
				response.sendRedirect("create_donate.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			String startDate = request.getParameter("start_date");
			String startDateConverted = startDate.replace("-", "/");
			String endDate = request.getParameter("end_date");
			String endDateConverted = endDate.replace("-", "/");
			int ID = Integer.parseInt(request.getParameter("id"));
			
//			String startDate = dayStart + "/" + monthStart + "/" + yearStart;
//			String endDate = dayEnd + "/" + monthEnd + "/" + yearEnd;
//			String regexMail = "^[A-Z0-9 a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
//			String regex = "[a-zA-Z0-9!@#$%^&*]+";
			HttpSession session = request.getSession(true);
			
//			if (!name.matches(regex) || !name.matches(regexMail)) {
//				session.setAttribute("msg", "Invalid syntax!");
//				response.sendRedirect("index.jsp");
//				return;
//			}
			
			//get image
			Part part = request.getPart("image");
			String filename = getFilename(part);
			StringBuilder imagePath = new StringBuilder();
			System.out.println(filename);
			if (part == null || !getFilename(part).equals("")) {
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
			
		    Date date = new Date();  
		    Date date1 =new SimpleDateFormat("yyyy/MM/dd").parse(startDateConverted);
			Date date2 =new SimpleDateFormat("yyyy/MM/dd").parse(endDateConverted);
			
			if (date2.getTime() - date1.getTime() < 1) {
				session.setAttribute("msg", "Dates are not valid");
				response.sendRedirect("create_donate.jsp");
				return;
			}
			
			CharityPost newPost = new CharityPost(ID, name, description, date, date1, date2, imagePath.toString());
			CharityPostDAO CPD = new CharityPostDAO();
			
			String result = CPD.updatePost(newPost);
			
			if (result == "Success") {
				session.setAttribute("msg", "Successfully Updated");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			} else if (result == "Failed") {
				session.setAttribute("msg", result);
				response.sendRedirect("create_donate.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void endPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		try {
			String id = request.getParameter("id");
			CharityPostDAO CPD = new CharityPostDAO();
			HttpSession session = request.getSession(true);
			
			int result = CPD.endPost(id);
			
			if (result == 1) {
				session.setAttribute("msg", "Ended Donation");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			} else if (result == 0) {
				session.setAttribute("msg", result);
				response.sendRedirect("create_donate.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void showPosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		int recoredPage = 6;
		CharityPostDAO dao = new CharityPostDAO();
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		try {
			int noOfRecords = dao.getTotalPost();
			int noOfPage = (int) Math.ceil(noOfRecords * 1.0 / recoredPage);
			List<CharityPost> list = dao.getPosts((page - 1) * recoredPage, recoredPage);
//			List<CharityPost> list = dao.getRecord((page - 1) * recoredPage, recoredPage);
			request.setAttribute("postList", list);
			request.setAttribute("noOfPage", noOfPage);
			request.setAttribute("currentPage", page);
			
			RequestDispatcher rd = request.getRequestDispatcher("donations.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			response.getWriter().println(e.getMessage());
		}
	}
	
	protected void searchPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int page = 1;
			int noOfPage = 1;
			CharityPostDAO dao = new CharityPostDAO();
			HttpSession session = request.getSession(true);
			String name = request.getParameter("name");
			
			if (name.equals("Enter name to search")) {
				session.setAttribute("msg", "Input values to search");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
				return;
			}
			
			List<CharityPost> list = dao.getPostWithName(name);
			request.setAttribute("postList", list);
			request.setAttribute("noOfPage", noOfPage);
			request.setAttribute("currentPage", page);
			
			RequestDispatcher rd = request.getRequestDispatcher("donations.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			response.getWriter().println(e.getMessage());
		}
	}

	protected void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			CharityPostDAO CPD = new CharityPostDAO();
			CharityPost post = CPD.getPost(id);
			HttpSession session = request.getSession(true);
			
			session.setAttribute("id", id);
			session.setAttribute("name", post.getName());
			session.setAttribute("description", post.getDescription());
			session.setAttribute("start", post.getStartDate());
			session.setAttribute("end", post.getEndDate());
			session.setAttribute("image", post.getMainImage());
			session.setAttribute("status", post.getStatus());
			RequestDispatcher rd = request.getRequestDispatcher("edit_donation.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			 response.getWriter().println(e);
		}
	}

	protected void deletePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String id = request.getParameter("id");
			CharityPostDAO CPD = new CharityPostDAO();
			String result = CPD.deletePost(id);
			HttpSession session = request.getSession(true);
			
			if (result == "Success") {
				session.setAttribute("msg", "Successfully Deleted");
			} else if (result == "Failed") {
				session.setAttribute("msg", result);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("CharityPostController?action=all");
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
	
	protected void deletePosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String[] ids = request.getParameterValues("check");
			
			if (ids == null) {
				response.sendRedirect("CharityPostController?action=all");
				return;
			}
			
			CharityPostDAO CPD = new CharityPostDAO();
			HttpSession session = request.getSession(true);
			
			for (String id : ids) {
				CPD.deletePost(id);
			}
						
			session.setAttribute("msg", "Successfully Deleted");			
			RequestDispatcher rd = request.getRequestDispatcher("CharityPostController?action=all");
			rd.forward(request, response);
		} catch (Exception e) {
			response.getWriter().println(e);
		}
	}
	
	protected void showPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			HttpSession session = request.getSession(true);
			CharityPostDAO dao = new CharityPostDAO();
			DonationDAO DDAO = new DonationDAO();
			String id = request.getParameter("id");
			
			if (id.equals(null)) {
				session.setAttribute("msg", "Please log in first");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
			
			CharityPost post = dao.getPost(id);
			int total = DDAO.getTotalAmountDonation(id);
			request.setAttribute("post", post);
			request.setAttribute("total", total);
			
			RequestDispatcher rd = request.getRequestDispatcher("donation-detail.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

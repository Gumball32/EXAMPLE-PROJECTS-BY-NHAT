package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.ProductDAO;
import Model.Product;

/**
 * Servlet implementation class Product
 */
@WebServlet("/ProductController")
@MultipartConfig (
		location = "C:\\Users\\Administrator\\eclipse-workspace\\Shoppeoo\\src\\main\\webapp\\images",
		fileSizeThreshold = 1024 * 1024,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 11
		)
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
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
			showProducts(request, response);
		} else {
			switch (action) {
				case "delete":
					deleteProduct(request, response);
					break;
				case "all":
					showProducts(request, response);
					break;
				case "detail-product":
					showProduct(request, response);
					break;
				case "upload":
					createProduct(request, response);
					break;
				case "update":
					updateProduct(request, response);
					break;
				case "searchName":
					showProductsWithName(request, response);
					break;
				default:
					showProducts(request, response);
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
	
	protected void showProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		int recoredPage = 6;
		ProductDAO pdao = new ProductDAO();
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		try {
			int noOfRecords = pdao.getTotalProduct();
			int noOfPage = (int) Math.ceil(noOfRecords * 1.0 / recoredPage);
			List<Model.Product> list = pdao.getProducts((page - 1) * recoredPage, recoredPage);
			
			if (list.size() == 0) {
				System.out.println("Empty");
			}
			
			request.setAttribute("productList", list);
			request.setAttribute("noOfPage", noOfPage);
			request.setAttribute("currentPage", page);
			
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			response.getWriter().println(e.getMessage());
		}
	}
	
	protected void showProductsWithName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		int recoredPage = 6;
		ProductDAO pdao = new ProductDAO();
		HttpSession session = request.getSession(true);
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		try {
			String searchName = request.getParameter("seacrh");
			System.out.println("VO");
			if (searchName == null) {
				session.setAttribute("msg", "Enter name to search");
				RequestDispatcher rd = request.getRequestDispatcher("ProductController?action=all");
				rd.forward(request, response);
			}
			
			int noOfRecords = pdao.getTotalProduct();
			int noOfPage = (int) Math.ceil(noOfRecords * 1.0 / recoredPage);
			List<Model.Product> list = pdao.getProductsWithName((page - 1) * recoredPage, recoredPage, searchName);
			System.out.println(list);
			if (list.size() == 0) {
				System.out.println("Empty");
			}
			
			request.setAttribute("productList", list);
			request.setAttribute("noOfPage", noOfPage);
			request.setAttribute("currentPage", page);
			request.setAttribute("showMode", "search");
			
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			response.getWriter().println(e.getMessage());
		}
	}
	
	protected void showProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			HttpSession session = request.getSession(true);
			ProductDAO pdao = new ProductDAO();
//			DonationDAO DDAO = new DonationDAO();
			String id = request.getParameter("id");
			
			if (id.equals(null)) {
				session.setAttribute("msg", "Please log in first");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
			
			Product product = pdao.getProduct(id);
//			int total = DDAO.getTotalAmountDonation(id);
			session.setAttribute("product", product);
//			request.setAttribute("total", total);
			
			RequestDispatcher rd = request.getRequestDispatcher("product_detail.jsp");
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
	
	protected void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String name = request.getParameter("full_name");
			String description = request.getParameter("description");
			int price = Integer.parseInt(request.getParameter("price"));
			int amount = Integer.parseInt(request.getParameter("amount"));

			HttpSession session = request.getSession(true);
			ProductDAO PDAO = new ProductDAO();

			//get image
			Part part = request.getPart("image");
			StringBuilder imagePath = new StringBuilder();
			if (part == null) {
				imagePath.append("images/shopeeo-logo.png");
			} else {
				String filename = getFilename(part);
				
				if (filename == null) {
					imagePath.append("images/shopeeo-logo.png");
				} else {
					imagePath.append("images/" + filename);
					part.write(filename);
				}
			}
			
			
			long millis=System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			
			Product newPost = new Product(name, price, description, date, imagePath.toString(), 1, amount);
			
			int result = PDAO.CreateProduct(newPost);
			
			System.out.println(result);
			if (result == 1) {
				session.setAttribute("msg", "Successfully Created");
				RequestDispatcher rd = request.getRequestDispatcher("ProductController?action=all");
				rd.forward(request, response);
			} else if (result == 0) {
				session.setAttribute("msg", "Failed");
				response.sendRedirect("profile.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();response.sendRedirect("your-error-page.jsp");
		}
	}
	
	protected void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			int price = Integer.parseInt(request.getParameter("price"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			int id = Integer.parseInt(request.getParameter("id"));
			String cur_image = request.getParameter("current_image");

			HttpSession session = request.getSession(true);
			ProductDAO PDAO = new ProductDAO();

			//get image
			Part part = request.getPart("image_input");
			StringBuilder imagePath = new StringBuilder();
			if (part != null && !getFilename(part).equals("")) {
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
			
			
			long millis=System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);
			
			Product newPost = new Product(id, name, price, description, date, imagePath.toString(), 1, amount);
			
			int result = PDAO.updateProduct(newPost);
			
			System.out.println(result);
			if (result == 1) {
				session.setAttribute("msg", "Successfully Updated");
				RequestDispatcher rd = request.getRequestDispatcher("ProductController?action=all");
				rd.forward(request, response);
			} else if (result == 0) {
				session.setAttribute("msg", "Failed");
				response.sendRedirect("profile.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();response.sendRedirect("your-error-page.jsp");
		}
	}

	protected void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String id = request.getParameter("id");
			ProductDAO PDAO = new ProductDAO();
			int result = PDAO.deleteProduct(id);
			HttpSession session = request.getSession(true);
			
			if (result == 1) {
				session.setAttribute("msg", "Successfully Deleted");
			} else if (result == 0) {
				session.setAttribute("msg", result);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("ProductController?action=all");
			rd.forward(request, response);
		} catch (Exception e) {
			response.getWriter().println(e);
		}
	}
}

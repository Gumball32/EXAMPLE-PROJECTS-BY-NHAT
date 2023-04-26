package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.BankPaymentDAO;
import DAO.CODPaymentDAO;
import DAO.OrderDAO;
import DAO.OrderItemDAO;
import DAO.ProductDAO;
import Interface.IPayment;
import Interface.IPaymentDAO;
import Model.BankPayment;
import Model.CODPayment;
import Model.Cart;
import Model.Order;
import Model.OrderItem;
import Model.User;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/OrderController")
@MultipartConfig (
		location = "C:\\Users\\Administrator\\eclipse-workspace\\Shoppeoo\\src\\main\\webapp\\images",
		fileSizeThreshold = 1024 * 1024,
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 11
)
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderController() {
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
//			showProducts(request, response);
		} else {
			switch (action) {
				case "order":
					order(request, response);
					break;
				case "allOrders":
					showOrders(request, response);
					break;
				case "detail":
					showOrder(request, response);
					break;
				case "update":
					updateOrder(request, response);
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
	
	protected String getFilename(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		
		if (!contentDisposition.contains("filename=")) {
			return null;
		}
		
		int beginIndex = contentDisposition.indexOf("filename=") + 10;
		int lastIndex = contentDisposition.length()  -  1;
		
		return contentDisposition.substring(beginIndex, lastIndex);
	}
	
	protected void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			HttpSession session = request.getSession(true);
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phone");
			String typePayment = request.getParameter("payment");
			IPayment payment = null;
			IPaymentDAO PDAO = null;
			OrderDAO ODAO = new OrderDAO();
			ProductDAO PRODAO = new ProductDAO();
			OrderItemDAO IDAO = new OrderItemDAO();
			
			
			//get user
			User user = (User) session.getAttribute("user");
			if (user == null) {
				session.setAttribute("msg", "Sign in first");
				RequestDispatcher rd = request.getRequestDispatcher("ProductController?action=all");
				rd.forward(request, response);
				return;
			}
			
			//check type of payment
			if (typePayment.equals("cod")) {
				payment =  new CODPayment(phoneNumber, address, 0, 0);
				PDAO = new CODPaymentDAO();
			} else {
				Part part = request.getPart("image");
				StringBuilder imagePath = new StringBuilder();
				if (part != null && !getFilename(part).equals("")) {
					String filename = getFilename(part);
					if (filename == null) {
						session.setAttribute("msg", "Please input image");
						RequestDispatcher rd = request.getRequestDispatcher("ProductController?action=all");
						rd.forward(request, response);
						return;
					} else {
						imagePath.append("images/" + filename);
						part.write(filename);
					}
				} else {
					session.setAttribute("msg", "Please input image");
					RequestDispatcher rd = request.getRequestDispatcher("ProductController?action=all");
					rd.forward(request, response);
					return;
				}
				
				payment =  new BankPayment(phoneNumber, address, 0, 0, imagePath.toString());
				PDAO = new BankPaymentDAO();
			}
			
			//check cart
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart == null) {
				session.setAttribute("msg", "Cart is empty");
				RequestDispatcher rd = request.getRequestDispatcher("ProductController?action=all");
				rd.forward(request, response);
				return;
			}
//			if (cart.getItemCount() < 1) {
//				session.setAttribute("msg", "Cart is empty");
//				RequestDispatcher rd = request.getRequestDispatcher("ProductController?action=all");
//				rd.forward(request, response);
//				return;
//			}
			
			//get total cost
			int total = payment.getFee();
			ArrayList<OrderItem> listOfItems = new ArrayList<OrderItem>();
			 for (OrderItem item : cart.getItems()) {
				total += item.getAmount() * item.getPrice();
				listOfItems.add(item);
			}
			
			//put order in DB
			// Obtain the current date
	        LocalDate currentDate = LocalDate.now();
	        // Convert the LocalDate to a java.sql.Date
	        Date sqlDate = Date.valueOf(currentDate);
			Order order = new Order(user.getId(), sqlDate, total, 2); 
			int id = ODAO.order(order);
			
			if (id == 0) {
				session.setAttribute("msg", "Failed to make the order");
				RequestDispatcher rd = request.getRequestDispatcher("ProductController?action=all");
				rd.forward(request, response);
				return;
			}
			
			//put the items in DB
			for (OrderItem orderItem : listOfItems) {
				int result = IDAO.insertOrderItem(orderItem, id);
				if (result == 0) {
					session.setAttribute("msg", "Failed to add order items");
					RequestDispatcher rd = request.getRequestDispatcher("ProductController?action=all");
					rd.forward(request, response);
					return;
				}
				PRODAO.updateProductAmount(orderItem.getProductID());
			}
			
			//put the payment method in DB
			int check = PDAO.order(payment, id);
			if (check == 1) {
				//added to the list
				session.setAttribute("msg", "Waiting for the admin to check");
				RequestDispatcher rd = request.getRequestDispatcher("ProductController?action=all");
				rd.forward(request, response);
				return;
			} else {
				//added to the list
				session.setAttribute("msg", "Failed");
				RequestDispatcher rd = request.getRequestDispatcher("ProductController?action=all");
				rd.forward(request, response);
				return;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();response.sendRedirect("your-error-page.jsp");
		}
	}

	protected void showOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		int recoredPage = 10;
		OrderDAO ODAO = new OrderDAO();
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		try {
			int noOfRecords = ODAO.getTotalOrder();
			int noOfPage = (int) Math.ceil(noOfRecords * 1.0 / recoredPage);
			List<Order> list = ODAO.getAllOrders((page - 1) * recoredPage, recoredPage);
			
			if (list.size() == 0) {
				request.setAttribute("empty", "NO ORDER");
			}
			
			System.out.println(list);
			System.out.println(noOfRecords);
			
			request.setAttribute("orderList", list);
			request.setAttribute("noOfPage", noOfPage);
			request.setAttribute("currentPage", page);
			
			RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			response.getWriter().println(e.getMessage());
		}
	}

	protected void showOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			HttpSession session = request.getSession(true);
			OrderDAO ODAO = new OrderDAO();
			CODPaymentDAO CDAO = new CODPaymentDAO();
			BankPaymentDAO BDAO = new BankPaymentDAO();
			String id = request.getParameter("id");
			System.out.println(id);
			
			if (id.equals(null)) {
				session.setAttribute("msg", "Please log in as admin");
				RequestDispatcher rd = request.getRequestDispatcher("ProductController");
				rd.forward(request, response);
			}
			
			IPayment payment = CDAO.getOrder(id);
			if (payment == null) {
				payment = (BankPayment) BDAO.getOrder(id);
				session.setAttribute("method", "bank");
			} else {
				session.setAttribute("method", "cash");
			}
			
			Order order = ODAO.getOrder(id);
			session.setAttribute("order", order);
			session.setAttribute("payment", payment);
			
			RequestDispatcher rd = request.getRequestDispatcher("orderDetail.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();response.sendRedirect("your-error-page.jsp");
		}
	}
	
	protected void updateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			HttpSession session = request.getSession(true);
			OrderDAO ODAO = new OrderDAO();
			String id = request.getParameter("id");
			String status = request.getParameter("status");
			
			int updateResult = ODAO.updateStatus(id, status);
						
			if (updateResult == 1) {
				session.setAttribute("msg", "Confirmed order");
			} else {
				session.setAttribute("msg", "Failed to confirm");
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("OrderController?action=allOrders");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();response.sendRedirect("your-error-page.jsp");
		}
	}
}

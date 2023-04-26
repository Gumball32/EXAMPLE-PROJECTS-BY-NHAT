package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Cart;
import Model.OrderItem;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
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
			response.sendRedirect("ProductController?action=all");
		} else {
			switch (action) {
				case "showItems":
					showItems(request, response);
					break;
				case "addItem":
					addItem(request, response);
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
	
	protected void showItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			System.out.println("DA VO");
			HttpSession session = request.getSession(true);
			
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart == null) {
		      // If the cart object doesn't exist in the session, create a new one
		      cart = Cart.getInstance();
		      // Store the cart object in the session
		      session.setAttribute("cart", cart);
			}
			System.out.println(cart);
			
			//calculate total amount
			int totalAmount = 0;
		    for (OrderItem cartItem : cart.getItems()) {
		        totalAmount += cartItem.getPrice() * cartItem.getAmount();
		    }
		        
	        request.setAttribute("cartList", cart.getItems());
	        request.setAttribute("totalAmount", totalAmount);
		    RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();response.sendRedirect("your-error-page.jsp");
		}
	}
	
	protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
			String name = request.getParameter("name");
			String image = request.getParameter("image");
			int price = Integer.parseInt(request.getParameter("price"));
			int id = Integer.parseInt(request.getParameter("id"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			HttpSession session = request.getSession(true);
			
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart == null) {
		      // If the cart object doesn't exist in the session, create a new one
		      cart = Cart.getInstance();
		      // Store the cart object in the session
		      session.setAttribute("cart", cart);
			}
			
			//calculate total amount
			int totalAmount = 0;
		    for (OrderItem cartItem : cart.getItems()) {
		        totalAmount += cartItem.getPrice() * cartItem.getAmount();
		    }
		    
		    //Make an object item
		    OrderItem item =  new OrderItem(id, price, amount, name, image, 0);
		    cart.addItem(item);
			
		    session.setAttribute("msg", "Added To Cart");
		    request.setAttribute("totalAmount", totalAmount);
		    request.setAttribute("cartList", cart.getItems());
		    RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();response.sendRedirect("your-error-page.jsp");
		}
	}

}

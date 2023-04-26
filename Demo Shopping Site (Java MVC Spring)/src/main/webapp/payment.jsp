<%@page import="org.apache.catalina.connector.Response"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<div class="main-wrapper">
        
    <h1 class="text-center my-4 mx-5 pay-text">PAYMENT</h1> 
    <div class="container payment">
        <div class="    radio-payment payment-form px-5 py-3">
            <h4><i class="fa-solid fa-cart-shopping px-2"></i>Choose your payment method</h4>
            <div class="form-check check-01">
                <input class="form-check-input" type="radio" name="flexRadioDefault" value="payment_radio_1" >
                <label class="form-check-label" for="flexRadioDefault1">Pay on delivery</label>
            </div>
            <div class="form-check check-02">
                <input class="form-check-input" type="radio" name="flexRadioDefault" value="payment_radio_2" checked>
                <label class="form-check-label" for="flexRadioDefault2">Pay by bank transfer</label>
            </div> 
        </div>
        <form action="OrderController" method="POST" class="payment-form px-5 py-3" id="payment-post-1" >         
            <div class="row mt-1 mb-2">
               <div class="payment-detail col-md-4">
                <p class="mb-1">Enter your address</p>
                <input type="text" name="address"  class="payment-bar">
               </div>
               <div class="payment-detail col-md-4">
                <p class="mb-1">Enter your phone</p>
                <input type="text" name="phone"  class="payment-bar">
               </div>
            </div>   
            <div class="input-group p-2">
	            <input type="text" class="hidden" name="payment" value="cod" style="visibility:hidden">
	            <input type="text" class="hidden" name="action" value="order" style="visibility:hidden">
	            <div class="justify-content-center payment-button-container text-center mt-4">
                	<button class="payment-button border-0 w-50 p-3" name="submitPayment" type="submit" value="true">Proceed to payment</button>
            	</div> 
            </div>
        </form>      
        <form action="OrderController" method="POST" class="payment-form form-02 px-5 py-3"  id="payment-post-2" enctype='multipart/form-data'>
            <div class="text-center">
                <img src="images/qr-code.png" class="payment-transfer-img" alt="payment method">
            </div>
            <h3 class="text-center">Bank number: 123456</h3>
            <label for="image">After transfer, please enter the screenshot to procced</label>
            <input type="file" class="form-control my-2 phone-number-input " name="image" required>
            <div class="row mt-1 mb-2">
	             <div class="payment-detail col-md-4">
	              <p class="mb-1">Enter your address</p>
	              <input type="text" name="address"  class="payment-bar">
	             </div>
	             <div class="payment-detail col-md-4">
	              <p class="mb-1">Enter your phone</p>
	              <input type="text" name="phone"  class="payment-bar">
	             </div>
            </div>
            <input type="text" class="hidden" name="payment" value="bank" style="visibility:hidden">
	        <input type="text" class="hidden" name="action" value="order" style="visibility:hidden">
            <div class="justify-content-center payment-button-container text-center mt-4">
                <button class="payment-button border-0 w-50 p-3" name="submitPayment" type="submit" value="true">Proceed to payment</button>
            </div> 
        </form>
    </div>
    
    <script type="text/javascript" src="js/payment_handle.js"></script>
</div>
<%@ include file="footer.jsp" %>
package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaymentClinetAPI
 */
@WebServlet("/PaymentClinetAPI")
public class PaymentClientAPI extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PaymentClient paymentObj =new PaymentClient();
       
    
    public PaymentClientAPI() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = paymentObj.insertPayment(request.getParameter("name"), 
				request.getParameter("email"), 
				request.getParameter("address"), 
				request.getParameter("contact_number"),
				request.getParameter("card_name"),
				request.getParameter("card_number"),
				request.getParameter("expiry_date"),
				request.getParameter("cvc_number")); 
				response.getWriter().write(output); 
				
				
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> paras = getParasMap(request); 
		 String output = paymentObj.updatePayment(paras.get("hidPaymentIDSave").toString(), 
	    paras.get("name").toString(),
	    paras.get("email").toString(),
	    paras.get("address").toString(),
	    paras.get("contact_number").toString(),
	    paras.get("card_name").toString(),
		paras.get("card_number").toString(), 
		paras.get("expiry_date").toString(), 
		paras.get("cvc_number").toString()); 
		response.getWriter().write(output); 
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> paras = getParasMap(request); 
		 String output = paymentObj.deletePayment(paras.get("payment_ID").toString()); 
		response.getWriter().write(output);
	}
	
	private static Map<String, String> getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
	String[] p = param.split("=");
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}
	
	
	

}

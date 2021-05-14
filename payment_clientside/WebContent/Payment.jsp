<%@ page import= "com.PaymentClient" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Item.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">

<h1>Payment Management </h1>
<form id="formPayment" name="formPayment" method="post" action="Payment.jsp">
 Name: 
<input id="name" name="name" type="text" 
 class="form-control form-control-sm">
<br> Email: 
<input id="email" name="email" type="text" 
 class="form-control form-control-sm">
<br>
<br> Address: 
<input id="address" name="address" type="text" 
 class="form-control form-control-sm">

<br> Contact Number: 
<input id="contact_number" name="contact_number" type="text" 
 class="form-control form-control-sm">
<br>
<br> Card Name: 
<input id="card_name" name="card_name" type="text" 
 class="form-control form-control-sm">
<br>
<br> Card Number: 
<input id="card_number" name="card_number" type="text" 
 class="form-control form-control-sm">
<br>
<br> Expiration Date: 
<input id="expiry_date" name="expiry_date" type="text" 
 class="form-control form-control-sm">
<br>
<br> CVC Number: 
<input id="cvc_number" name="cvc_number" type="text" 
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
<input type="hidden" id="hidPaymentIDSave" name="hidPaymentIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<%
 out.print(session.getAttribute("statusMsg")); 
%> 
<br>
<div id="divItemsGrid">

<%
PaymentClient paymentObj =new PaymentClient(); 
 out.print(paymentObj.readPayments()); 
%>
</div>

</div></div></div>

</body>
</html>
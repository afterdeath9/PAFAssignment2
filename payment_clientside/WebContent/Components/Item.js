$(document).ready(function()
	{
	if ($("#alertSuccess").text().trim() == "")
	{
	$("#alertSuccess").hide();
	}
	$("#alertError").hide();
	});
	
// SAVE ============================================
	$(document).on("click", "#btnSave", function(event)
	{
		// Clear alerts---------------------
		$("#alertSuccess").text("");
		$("#alertSuccess").hide();
		$("#alertError").text("");
		$("#alertError").hide();
		
		// Form validation-------------------
	    var status = validatePaymentForm();
		if (status != true)
		{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
		}
		
		 // If valid------------------------
		 var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT"; 
		 $.ajax( 
		 { 
		 url : "PaymentClientAPI", 
		 type : type, 
		 data : $("#formPayment").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onItemSaveComplete(response.responseText, status); 
		 } 
 	}); 
});
		
// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
	$("#hidPaymentIDSave").val($(this).data("hidItemIDUpdate"));
	$("#name").val($(this).closest("tr").find('td:eq(0)').text());
	$("#email").val($(this).closest("tr").find('td:eq(1)').text());
	$("#adress").val($(this).closest("tr").find('td:eq(2)').text());
	$("#contact_number").val($(this).closest("tr").find('td:eq(3)').text());
	$("#card_name").val($(this).closest("tr").find('td:eq(4)').text());
	$("#card_number").val($(this).closest("tr").find('td:eq(5)').text());
	$("#expiry_date").val($(this).closest("tr").find('td:eq(6)').text());
	$("#cvc_number").val($(this).closest("tr").find('td:eq(7)').text());
	});
	
// DELETE===========================================
	$(document).on("click", ".btnRemove", function(event)
	{ 
	 $.ajax( 
	 { 
	 url : "PaymentClientAPI", 
	 type : "DELETE", 
	 data : "payment_ID=" + $(this).data("itemid"),
	 dataType : "text", 
	 complete : function(response, status) 
	 { 
	 onItemDeleteComplete(response.responseText, status); 
	 } 
	 }); 
});
// CLIENT-MODEL================================================================
function validatePaymentForm()
	{
	// name
	if ($("#name").val().trim() == "")
	{
	return "Insert name.";
	}
	// email
	if ($("#email").val().trim() == "")
	{
	return "Insert email.";
	}
	// address
	if ($("#address").val().trim() == "")
	{
	return "Insert address.";
	}
	// contact_number
	if ($("#contact_number").val().trim() == "")
	{
	return "Insert contact_number.";
	}
	// card_name
	if ($("#card_name").val().trim() == "")
	{
	return "Insert card_name.";
	}
	// card_number
	if ($("#card_number").val().trim() == "")
	{
	return "Insert card_number.";
	}
	// expiry_date
	if ($("#expiry_date").val().trim() == "")
	{
	return "Insert expiry_date.";
	}
	// cvc_number
	if ($("#cvc_number").val().trim() == "")
	{
	return "Insert cvc_number.";
	}



function onItemSaveComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully saved."); 
	 $("#alertSuccess").show();
	 $("#divItemsGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while saving."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while saving.."); 
	 $("#alertError").show(); 
	 } 
	 $("#hidPaymentIDSave").val(""); 
	 $("#formPayment")[0].reset(); 
}

function onItemDeleteComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully deleted."); 
	 $("#alertSuccess").show();
	 $("#divItemsGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while deleting."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while deleting.."); 
	 $("#alertError").show(); 
 } 
}

	}


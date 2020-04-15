package com;

import model.Payment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

	@Path("/Payments")
	public class PaymentService {
	Payment paymentObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return paymentObj.readPayment();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("cardHolderName") String cardHolderName,@FormParam("cardNumber") String cardNumber)
	{
	String output = paymentObj.insertPayment(cardHolderName, cardNumber);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData)
	{
	//Convert the input string to a JSON object
	JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
	//Read the values from the JSON object
	String ID = paymentObject.get("payID").getAsString();
	String name = paymentObject.get("cardHolderName").getAsString();
	String number = paymentObject.get("cardNumber").getAsString();
	String charge = paymentObject.get("charge").getAsString();
	String output = paymentObj.updatePayment(ID, name, number, charge);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String payID = doc.select("payID").text();
	String output = paymentObj.deletePayment(payID);
	return output;
	}
	
	
}

package com;

import model.Pharmacy;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Pharmacy")
public class PharmacyService
{
	Pharmacy obj = new Pharmacy();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String view()
	{
	return obj.view();
	}


	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String add_pharmacy(@FormParam("name") String name,
			@FormParam("owner") String owner,
	 @FormParam("address") String address,
	 @FormParam("email") String email,
	 @FormParam("phone") String phone)
	{
	 String output = obj.add_pharmacy(name, owner, address, email, phone);
	return output;
	}
	

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String update(String data)
	{
	//Convert the input string to a JSON object
	 JsonObject p_object = new JsonParser().parse(data).getAsJsonObject();
	//Read the values from the JSON object
	 String id = p_object.get("id").getAsString();
	 String name = p_object.get("name").getAsString();
	 String owner = p_object.get("owner").getAsString();
	 String address = p_object.get("address").getAsString();
	 String email = p_object.get("email").getAsString();
	 String phone = p_object.get("phone").getAsString();
	
	
	 String output = obj.update(id, name, owner, address, email, phone);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String remove(String data)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(data, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String id = doc.select("id").text();
	 String output = obj.remove(id);
	return output;
	}
}

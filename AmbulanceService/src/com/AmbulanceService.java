package com;

import model.Ambulance;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Ambulance")
public class AmbulanceService
{
	Ambulance obj = new Ambulance();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
	return obj.readItems();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addAmbulance(@FormParam("name") String name,
								 @FormParam("model") String model,
								 @FormParam("phone") String phone)
	{
	 String output = obj.addAmbulance(name, model, phone);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAmbulance(String data)
	{
	//Convert the input string to a JSON object
	 JsonObject a_object = new JsonParser().parse(data).getAsJsonObject();
	//Read the values from the JSON object
	 String id = a_object.get("id").getAsString();
	 String name = a_object.get("name").getAsString();
	 String model = a_object.get("model").getAsString();
	 String phone = a_object.get("phone").getAsString();
	
	 String output = obj.updateAmbulance(id, name, model, phone);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String removeAmbulance(String data)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(data, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String id = doc.select("id").text();
	 String output = obj.removeAmbulance(id);
	return output;
	}
}


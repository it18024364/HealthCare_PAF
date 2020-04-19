package com;

import model.Hospital;

import java.sql.SQLException;

///For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Hospital")

public class HospitalService 
{
	Hospital hospitalObj = new Hospital();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readHospital()
	{
	 return hospitalObj.readHospitals();
    } 
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertHospital(@FormParam("hospitalid") String hospitalid,
	@FormParam("hospitalname") String hospitalname,
	@FormParam("hospitalphone") int hospitalphone,
	@FormParam("hospitalemail") String hospitalemail,
	@FormParam("hospitallocation") String hospitallocation) throws SQLException
	{
		String output =hospitalObj.insertHospital(hospitalid, hospitalname, hospitalphone, hospitalemail,hospitallocation);
	   return output;
	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHospital(String hospitalData)
	{
	//Convert the input string to a JSON object
	JsonObject hospitalObject = new JsonParser().parse(hospitalData).getAsJsonObject();
	//Read the values from the JSON object
	String hospitalid = hospitalObject.get("hospitalid").getAsString();
	String hospitalname= hospitalObject.get("hospitalname").getAsString();
	int hospitalphone= hospitalObject.get("hospitalphone").getAsInt();
	String hospitalemail= hospitalObject.get("hospitalemail").getAsString();
	String hospitallocation= hospitalObject.get("hospitallocation").getAsString();
	String output = hospitalObj.updateHospital(hospitalid, hospitalname, hospitalphone, hospitalemail, hospitallocation);
	return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospital(String hospitalData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(hospitalData, "", Parser.xmlParser());
	//Read the value from the element <HospitalID>
	String hospitalid = doc.select("hospitalid").text();
	String output = hospitalObj.deleteHospital(hospitalid);
	return output;
	}
}	
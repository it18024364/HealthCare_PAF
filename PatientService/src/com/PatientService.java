package com;

import model.Patient;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Patients")


public class PatientService {
	
	Patient patientObj = new Patient();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPatient() {
		return patientObj.readPatient();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient (	@FormParam("first") String patfirst,
									@FormParam("lname") String patlname,
									@FormParam("email") String patemail,
									@FormParam("age") String patage,
									@FormParam("mobile") String patmobile,
									@FormParam("password") String patpassword)
	{
		String output = patientObj.insertPatient(patfirst, patlname, patemail, patage, patmobile, patpassword );
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatient(String patData) {
		// Convert the input string to a JSON object
		JsonObject patientObject = new JsonParser().parse(patData).getAsJsonObject();
		
		// Read the values from the JSON object
		String patUid = patientObject.get("Uid").getAsString();
		String patfirst = patientObject.get("first").getAsString();
		String patlname = patientObject.get("lname").getAsString();
		String patemail = patientObject.get("email").getAsString();
		String patage = patientObject.get("age").getAsString();
		String patmobile = patientObject.get("mobile").getAsString();
		String patpassword = patientObject.get("password").getAsString();
		
		String output = patientObj.updatePatient(patUid, patfirst,patlname,patemail,patage,patmobile,patpassword );
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(String patData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(patData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String patUid = doc.select("Uid").text();
		String output = patientObj.deletePatient(patUid);
		return output;
	}
	
	
	

}

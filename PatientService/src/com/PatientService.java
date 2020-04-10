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
	public String readPatients() {
		return patientObj.readPatients();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient(@FormParam("Pid") String Pid,@FormParam("first") String first, @FormParam("lname") String lname,
			@FormParam("email") String email, @FormParam("age") String age, @FormParam("mobile") String mobile,
			@FormParam("password") String password){
		String output = patientObj.insertPatient(Pid,first, lname, email, age, mobile, password );
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatient(String PatientData) {
		// Convert the input string to a JSON object
		JsonObject patientObject = new JsonParser().parse(PatientData).getAsJsonObject();
		// Read the values from the JSON object
		String first = patientObject.get("firt").getAsString();
		String lname = patientObject.get("lname").getAsString();
		String email =patientObject.get("email").getAsString();
		String age = patientObject.get("age").getAsString();
		String mobile = patientObject.get("mobile").getAsString();
		String password = patientObject.get("password").getAsString();
		String output = patientObj.updatePatient(first, lname, email, age, mobile,password);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(String PatientData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(PatientData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String email = doc.select("email").text();
		String output = patientObj.deletePatient(email);
		return output;
	}
	
	
	

}

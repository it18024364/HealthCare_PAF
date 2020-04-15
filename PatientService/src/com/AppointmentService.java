package com;

import model.Appointment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Appointments")



public class AppointmentService {
	
	Appointment appObj = new Appointment();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient (	@FormParam("Pid") String appPid,
									@FormParam("adocName") String appadocName,
									@FormParam("apatName") String appapatName,
									@FormParam("adate") String appadate
								)
									
	{
		String output = appObj.insertAppointment(appPid, appadocName, appapatName, appadate);
		return output;
	}
	

}

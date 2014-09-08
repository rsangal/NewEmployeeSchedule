package com.schedule;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

	import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
	@RequestMapping("/employee")
	@Controller
	 public class EmployeeScheduleWeb {

		@RequestMapping(value = "/schedule", method = RequestMethod.GET)
		public  @ResponseBody String getToken() {
			 String result="";
			 //JSONArray jArray = new JSONArray();
			try {
				  Class.forName("com.mysql.jdbc.Driver");
				  Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", 
				"root", "pass");
				  Statement st=con.createStatement();
				  ResultSet rs=st.executeQuery("select * from employeeschedule");
				 		    
				 while(rs.next()){
					 
					 result=result+rs.getString("EmployeeName")+" "+rs.getString("Date")+" "+rs.getString("ShiftFrom")+" "+" "+rs.getString("ShiftTo")+"\n";
					
				 }
				  
				  } catch (Exception e) {
				  System.out.println("Error Message"+e.getMessage());
				  }
		  
		  return result;
			
	}

	}

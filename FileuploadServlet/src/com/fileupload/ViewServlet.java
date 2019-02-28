package com.fileupload;

import com.fileupload.FileUploadServlet;  

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;   
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

/**
 * Servlet implementation class ViewServlet  
 */
@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;    
       
   
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub  
			
			 
			
			response.setContentType("text/html");             
			
			PrintWriter pw=response.getWriter();      
			
			
			
			try{ 
				  

		     Class.forName("com.mysql.jdbc.Driver");                            

			 Connection	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sathish", "root", "");   
				
			 	Statement stmt=con.createStatement();  
			 	
			 	
			 	ResultSet rs=stmt.executeQuery("select * from student");  
			 	
			 	
			     pw.println("<h1>Employees List</h1>");       
			        
				 
				 pw.print("<html>");                               
				  
				 pw.print("<body>");            
				 
				 pw.print("<form>");                 
				 
			     pw.print("<table border='1' width='100%'");                   
				  
   pw.print("<tr><th>ID</th><th>Name</th><th>Password</th><th>Email</th></tr>");  
				  
				 
				 while(rs.next())                   
				 {     
					 
					
					//System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));  
		   
 pw.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>");  
	                 
						
		 }           
				 
				                  
	   			 pw.print("</table>");                           
				 
				 pw.print("</form>");            
				 pw.print("</body>");   
				 pw.print("</html>");              
				 
				 
				
				con.close();  
				
				rs.close();
				
				
				pw.close();
				 
				
				
				
				
			}
			
			catch(Exception e)
			{
				
				
				System.out.println(e);    
			}
			
			
			
			
			
			
			
			
			
	}

}

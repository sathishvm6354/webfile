package com.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload; 



import org.apache.poi.xssf.usermodel.XSSFRow; 
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.*;

@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public static Connection con;   
	
	public static PreparedStatement ps;  
	
	
	public static FileInputStream input;    
	
	public static  PrintWriter pw;   
	
	
	public static int id;   
	
	public static String name;    
	 
	public static String password;
	
	public  static String email;
	
	public static String sql;
	
	
	public static int result;  
	
	
	
	

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");  
		
		 pw=response.getWriter();  
		
		
		if (ServletFileUpload.isMultipartContent(request)) {      

			try { 

				 List<FileItem> multipart = new ServletFileUpload(
						 
						new DiskFileItemFactory()).parseRequest(request);   
				  

				 for (FileItem item : multipart) {      

					if (!item.isFormField()) {      

						String name = new File(item.getName()).getName();        

						item.write(new File("D://guru" + File.separator + name));       

					}   
					
					

				}    
				 
				 pw.println("File successfull upload");     

			} catch (Exception Ex) {    

				System.out.println("output" + Ex); 
			}  
			
	 }     
		
	  else{      
	
		    pw.println("Please insert files ");   
		   
		}             
     
		
		readFileReader();        
	
		if(result>0)       
		{  
			
			
		    RequestDispatcher rd=request.getRequestDispatcher("ViewServlet"); 
		    
	        rd.forward(request, response);          
	} 
	
		
		else{      
			
			
			 RequestDispatcher rd=request.getRequestDispatcher("fileupload.html"); 
			    
		        rd.include(request, response);    
			
		}        
		
		
	 	
	}

	public void readFileReader() {      
		// TODO Auto-generated method stub 
		
		
		
		 try{              
			

	     	 Class.forName("com.mysql.jdbc.Driver");               

			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sathish", "root", "");     
			 
			 input=new FileInputStream("D://guru//project1.xlsx");             
			 
			 System.out.println("hello1");     
			 
			 XSSFWorkbook workbook=new XSSFWorkbook(input);              
		    
			 XSSFSheet sheet=workbook.getSheetAt(0);        
			 
			 XSSFRow row;  
			 
			 System.out.println("hello2");   
			 
			 
			 for(int i=1; i<=sheet.getLastRowNum(); i++)    
			 { 
				 
				  row=sheet.getRow(i);           
				 
				 
				 id=(int)row.getCell(0).getNumericCellValue();                 
				 
				name=row.getCell(1).getStringCellValue();   
				
				password=row.getCell(2).getStringCellValue();     
				
				email=row.getCell(3).getStringCellValue();     
				
				sql="insert into student(id,name,password,email) values(?,?,?,?)";           
				
				  ps=con.prepareStatement(sql);            
				   
				  ps.setInt(1, id);              
				  ps.setString(2, name);      
				  ps.setString(3, password);               
				  ps.setString(4, email);          
		    		  
				  
				 result =ps.executeUpdate();                
				  
		} 
			 
			 System.out.println("Query will be updateded");       
			 
			
		    if(result>0)          
			 { 
				 
			
				 System.out.println("Successfull register"+" "+result);      
				 
			}        
			 
			
			  
			 
			 
			 System.out.println("************************************************");    
		
			 
			 
		
		/*	ResultSet rs=ps.executeQuery("select * from student");              
			 
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
			 
			 */
			 
			
			 con.close();              
			 
			 input.close();    
			 
			// pw.close();  
			
			 
			  System.out.println("***************************************");   

				System.out.println("Success import excel to mysql table");      
			 
		  }      
		
		
		catch(Exception e)     
		{
			
		      System.out.println("out"+e);                  
			
	    }  
		
		
		
	 	
		
	}

}

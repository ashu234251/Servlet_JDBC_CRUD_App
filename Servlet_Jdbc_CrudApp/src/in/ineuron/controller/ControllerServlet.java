package in.ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.Student;
import in.ineuron.service.IStudentService;
import in.ineuron.servicefactory.StudentServiceFactory;


@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	static
	{
		System.out.println("ControllerServlet class loaded..");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doProcess(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
	{
		IStudentService stdService=StudentServiceFactory.getStudentService();
		
		System.out.println("Request URI :: "+request.getRequestURI());
		System.out.println("Path Info   :: "+request.getPathInfo());
		
		
		if(request.getRequestURI().endsWith("addform"))
		{
			String sAge=request.getParameter("sage");
			String sName=request.getParameter("sname");
			String sSalary=request.getParameter("ssal");

			Student student=new Student();
			student.setName(sName);
			student.setAge(Integer.parseInt(sAge));
			student.setSalary(Integer.parseInt(sSalary));
			
			String status= stdService.addStudent(student);
			
			PrintWriter output=response.getWriter();
			
			if(status.equalsIgnoreCase("success"))
			{
				output.println("<h1 style='color:green; text-align:center;'>Student Added Successfully..</h1>");
			}
			else
			{
				output.println("<h1 style='color:red; text-align:center;'> Student Registration Unsuccessfull. Please try again..</h1>");
			}
		}
		if(request.getRequestURI().endsWith("searchform"))
		{
			Integer sId=Integer.parseInt(request.getParameter("sid"));
			
			Student student= stdService.searchStudent(sId);
			PrintWriter output=response.getWriter();
			response.setContentType("text/html");
			if(student!=null)
			{
				output.println("<center><h1 style='color:blue; '>Student Data</h1></center><hr/>");
				output.println("<table border='1' align='center'>");
				output.println("<tr><td>Student I'd</td><td>"+student.getId()+"</td></tr>");
				output.println("<tr><td>Student Name</td><td>"+student.getName()+"</td></tr>");
				output.println("<tr><td>Student Age</td><td>"+student.getAge()+"</td></tr>");
				output.println("<tr><td>Student Salary</td><td>"+student.getSalary()+"</td></tr>");
			}
			else
			{
				output.println("<h1 style='color:red; text-align:center;'> No data found with I'd = "+sId+"</h1>");
			}
	     }
		
		if(request.getRequestURI().endsWith("editform"))
		{
			String sId = request.getParameter("sid");

			Student student = stdService.searchStudent(Integer.parseInt(sId));
			PrintWriter out = response.getWriter();
			if (student != null) 
			{
				// display student records as a form data so it is editable
				out.println("<body>");
				out.println("<center>");
				out.println("<form method='post' action='./updateRecord'>");
				out.println("<table>");
				out.println("<tr><th>ID</th><td>" + student.getId() + "</td></tr>");
				
				out.println("<input type='hidden' name='sid' value='" + student.getId() + "'/>");
				out.println("<tr><th>NAME</th><td><input type='text' name='sname' value='" + student.getName()+ "'/></td></tr>");
				out.println("<tr><th>AGE</th><td><input type='text' name='sage' value='" + student.getAge() + "'/></td></tr>");
				out.println("<tr><th>ADDRESS</th><td><input type='text' name='ssal' value='" + student.getSalary() + "'/></td></tr>");
				
				out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</center>");
				out.println("</body>");
			} else 
			{
				// display not found message
				out.println("<body>");
				out.println("<h1 style='color:red;text-align:center;'>Record not avaialable for the give id :: " + sId + "</h1>");
				out.println("</body>");
			}
			out.close();
		}
		if (request.getRequestURI().endsWith("updateRecord")) 
		{
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			String sage = request.getParameter("sage");
			String ssal = request.getParameter("ssal");

			Student student = new Student();
			student.setId(Integer.parseInt(sid));
			student.setSalary(Integer.parseInt(ssal));
			student.setName(sname);
			student.setAge(Integer.parseInt(sage));

			String status = stdService.updateStudent(student);
			PrintWriter out = response.getWriter();

			if (status.equalsIgnoreCase("success")) 
			{
				out.println("<h1 style='color:green; text-align:center;'>STUDENT RECORD UPDATED SUCCESSFULLY</h1>");
			} 
			else 
			{
				out.println("<h1 style='color:red; text-align:center;'>STUDENT RECORD UPDATION FAILED</h1>");
			}
			out.close();

		}
		
		if(request.getRequestURI().endsWith("deleteform"))
		{
			Integer sId=Integer.parseInt(request.getParameter("sid"));
			String status= stdService.deleteStudent(sId);
			PrintWriter output=response.getWriter();
			
			response.setContentType("text/html");
			if(status.equalsIgnoreCase("success"))
			{
				output.println("<h1 style='color:green; text-align:center;'>Student with I'd: "+sId+" deleted Successfully..</h1>");
			}
			else if(status.equalsIgnoreCase("Not Found"))
			{
				output.println("<h1 style='color:red; text-align:center;'> record not found with I'd = "+sId+"</h1>");
			}
			else
			{
				output.println("<h1 style='color:red; text-align:center;'> Record Deletion Failed..</h1>");
			}
			output.close();
		}
	}
}

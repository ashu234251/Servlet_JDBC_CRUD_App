package in.ineuron.servicefactory;

import in.ineuron.service.IStudentService;
import in.ineuron.service.StudentServiceImpl;

public class StudentServiceFactory 
{

	// Make Constructor private to avoid object creation.
	//Abstraction logic implementation
	private StudentServiceFactory()
	{
	}
	private static IStudentService studentService=null;
	
	public static IStudentService getStudentService()
	{
		// Singleton Pattern Code
		if(studentService==null)
		{
			studentService=new StudentServiceImpl();
		}
		return studentService;
	}
	
}

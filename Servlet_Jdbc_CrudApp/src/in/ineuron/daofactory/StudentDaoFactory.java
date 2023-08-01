package in.ineuron.daofactory;

import in.ineuron.dao.IStudentDao;
import in.ineuron.dao.StudentDaoImpl;

public class StudentDaoFactory 
{
	private StudentDaoFactory()
	{

	}
	
	private static IStudentDao studentdao=null;
	
	public static IStudentDao getStudentDao()
	{
		if(studentdao==null)
		{
			studentdao=new StudentDaoImpl();
		}
		return studentdao;
	}
}

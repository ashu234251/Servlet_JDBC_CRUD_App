package in.ineuron.service;

import in.ineuron.daofactory.StudentDaoFactory;
import in.ineuron.dto.Student;
import in.ineuron.dao.IStudentDao;


//Service Layer Logic.
public class StudentServiceImpl implements IStudentService {

	private IStudentDao stdDao;
	
	@Override
	public String addStudent(Student student) 
	{
		stdDao=StudentDaoFactory.getStudentDao();
		return stdDao.addStudent(student);
	}
	
	@Override
	public Student searchStudent(Integer id) 
	{
		stdDao=StudentDaoFactory.getStudentDao();
		return stdDao.searchStudent(id);
	}

	@Override
	public String updateStudent(Student student) 
	{
		stdDao=StudentDaoFactory.getStudentDao();
		return stdDao.updateStudent(student);
	}

	@Override
	public String deleteStudent(Integer id) 
	{
		stdDao=StudentDaoFactory.getStudentDao();
		return stdDao.deleteStudent(id);
	}
}

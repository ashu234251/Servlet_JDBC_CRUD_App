package in.ineuron.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.Student;
import in.ineuron.util.JdbcUtil;

public class StudentDaoImpl implements IStudentDao 
{
	Connection connection=null;
	PreparedStatement pstmt=null;
	ResultSet resultSet=null;
	// Write persistence logic using JDBC APIs
	@Override
	public String addStudent(Student student)
	{
		try 
		{
			String sqlInsertQuery="INSERT INTO student(name,age,salary) VALUES(?,?,?)";
			connection=JdbcUtil.getJdbcConnection();
			
			if(connection!=null)
				pstmt=connection.prepareStatement(sqlInsertQuery);
			
			if(pstmt!=null)
			{
				pstmt.setString(1, student.getName());
				pstmt.setInt(2, student.getAge());
				pstmt.setInt(3, student.getSalary());
				int rowAffected=pstmt.executeUpdate();
				if(rowAffected==1)
					return "Success";
			}
		} 
		catch (SQLException | IOException e) 
		{
			e.printStackTrace();
		}
		return "Failure";
	}

	@Override
	public Student searchStudent(Integer id) 
	{
		try 
		{
			Student student=null;
			String sqlSelectQuery="SELECT id,name,age,salary FROM student where id=?";
			connection=JdbcUtil.getJdbcConnection();
			
			if(connection!=null)
				pstmt=connection.prepareStatement(sqlSelectQuery);
			
			if(pstmt!=null)
			{
				pstmt.setInt(1, id);
				resultSet= pstmt.executeQuery();
			}
			if(resultSet!=null)
			{
				if(resultSet.next())
				{
					student=new Student();
					
					//Copying resultSet data to Student object
					student.setId(resultSet.getInt(1));
					student.setName(resultSet.getString(2));
					student.setAge(resultSet.getInt(3));
					student.setSalary(resultSet.getInt(4));
					
					return student;
				}
			}
		} 
		catch (SQLException | IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String updateStudent(Student student) 
	{
		try 
		{
			String sqlUpdateQuery="UPDATE student SET name=?,age=?,salary=? WHERE id=?";
			connection=JdbcUtil.getJdbcConnection();
			
			if(connection!=null)
				pstmt=connection.prepareStatement(sqlUpdateQuery);
			
			if(pstmt!=null)
			{
				pstmt.setString(1, student.getName());
				pstmt.setInt(2, student.getAge());
				pstmt.setInt(3, student.getSalary());
				pstmt.setInt(4, student.getId());
				
				int rowAffected=pstmt.executeUpdate();
				if(rowAffected==1)
					return "Success";
			}
		} 
		catch (SQLException | IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String deleteStudent(Integer id) 
	{
		try 
		{
			String sqlDeleteQuery="DELETE FROM student WHERE id=?";
			connection=JdbcUtil.getJdbcConnection();
			
			if(connection!=null)
				pstmt=connection.prepareStatement(sqlDeleteQuery);
			
			if(pstmt!=null)
			{
				pstmt.setInt(1, id);
				int rowAffected=pstmt.executeUpdate();
				if(rowAffected==1)
					return "Success";
				else
					return "Not Found";
			}
		} 
		catch (SQLException | IOException e) 
		{
			e.printStackTrace();
		}
		return "Failure";
	}
   
}

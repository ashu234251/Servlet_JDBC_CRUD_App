package in.ineuron.dto;

import java.io.Serializable;


public class Student implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer age;
	private Integer salary;
	
	public Integer getId() 
	{
		return id;
	}
	public void setId(Integer id) 
	{
		this.id = id;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public Integer getAge() 
	{
		return age;
	}
	public void setAge(Integer age) 
	{
		this.age = age;
	}
	public Integer getSalary() 
	{
		return salary;
	}
	public void setSalary(Integer salary) 
	{
		this.salary = salary;
	}
	
	@Override
	public String toString() 
	{
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + "]";
	}
}

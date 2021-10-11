package mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class mybatisInsert {

	public static void main(String args[]) throws IOException {

		Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();

		// Create a new student object
		Student student = new Student("Mohammad", "It", 80, 984803322, "Mohammad@gmail.com");
		//insertStudent(student , session);
		//updateStudent(session,1);
		deleteStudent(session , 1);
		session.commit();
		session.close();

	}
	public static void insertStudent(Student student, SqlSession session) {
		   session.insert("mybatis.Student.insert", student);
		   System.out.println("record inserted successfully");
	}

	public static void selectStudent(SqlSession session, int id) {
		Student student = session.selectOne("mybatis.Student.getById", id);
		if(student==null) {
			throw new RuntimeException("No Student Found of id "+id);
		}

		System.out.println(student.getId());
		System.out.println(student.getName());
		System.out.println(student.getBranch());
		System.out.println(student.getPercentage());
		System.out.println(student.getEmail());
		System.out.println(student.getPhone());

	}

	public static void selectAllStudent(SqlSession session) {
		List<Student> students = session.selectList("mybatis.Student.getAll");
		for (Student st : students) {
			System.out.println(st.getId());
			System.out.println(st.getName());
			System.out.println(st.getBranch());
			System.out.println(st.getPercentage());
			System.out.println(st.getEmail());
			System.out.println(st.getPhone());
		}
	}
	
	public static void updateStudent(SqlSession session, int id) {
	     Student student = (Student) session.selectOne("mybatis.Student.getById", id);
	      System.out.println("Current details of the student are" );
	      System.out.println(student.toString());  
	      
	      student.setEmail("mohamad123@yahoo.com");
	      student.setPhone(90000000);
	      
	      session.update("mybatis.Student.update",student);
	      System.out.println("Record updated successfully");  
	      
	      selectAllStudent(session);
	}
	
	public static void deleteStudent(SqlSession session, int id)
	{
		 session.delete("mybatis.Student.deleteById", id);
		 System.out.println("Record Deleted Sucessfully");
		 
		 selectAllStudent(session);
		 
	}

}
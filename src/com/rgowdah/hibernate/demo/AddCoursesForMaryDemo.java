package com.rgowdah.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rgowdah.hibernate.entity.Course;
import com.rgowdah.hibernate.entity.Instructor;
import com.rgowdah.hibernate.entity.Instructor_Detail;
import com.rgowdah.hibernate.entity.Review;
import com.rgowdah.hibernate.entity.Student;

public class AddCoursesForMaryDemo {
	public static void main(String[] args) {
		//create session factory
		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).addAnnotatedClass(Instructor_Detail.class).
				addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).addAnnotatedClass(Student.class)
				.buildSessionFactory();
		//create session
		Session session=sessionFactory.getCurrentSession();
		try{			
			//start transaction
			session.beginTransaction();
			//get student boss from database
			int id=2;
			Student student=session.get(Student.class, id);
			System.out.println("Student is : "+student);
			System.out.println("Courses is : "+student.getCourses());
			//create more courses
			Course course=new Course("solved rubics cube");
			Course course1=new Course("solved rubics cube1");
			//add student to courses
			course.addStudent(student);
			course1.addStudent(student);
			//save the courses
			System.out.println("saving the courses");
			session.save(course);
			session.save(course1);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}catch (Exception exc) {
			exc.printStackTrace();
		}finally{
			session.close();
			sessionFactory.close();
		}
	}
}

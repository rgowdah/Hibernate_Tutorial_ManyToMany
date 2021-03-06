package com.rgowdah.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rgowdah.hibernate.entity.Course;
import com.rgowdah.hibernate.entity.Instructor;
import com.rgowdah.hibernate.entity.Instructor_Detail;
import com.rgowdah.hibernate.entity.Review;
import com.rgowdah.hibernate.entity.Student;

public class CreateCourseandStudentsDemo {
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
			//create a course
			Course course=new Course("Pacman how to score million points");			
			//save the course
			session.save(course);
			//create the students
			Student student=new Student("John","Doe","John@gmail.com");
			Student student1=new Student("boss","bee","bee@gmail.com");
			Student student2=new Student("baby","bae","bae@gmail.com");
			course.addStudent(student);
			course.addStudent(student1);
			course.addStudent(student2);
			//save the students
			System.out.println("Saving students..");
			session.save(student);
			session.save(student1);
			session.save(student2);
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

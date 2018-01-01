package com.rgowdah.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rgowdah.hibernate.entity.Course;
import com.rgowdah.hibernate.entity.Instructor;
import com.rgowdah.hibernate.entity.Instructor_Detail;
import com.rgowdah.hibernate.entity.Review;
import com.rgowdah.hibernate.entity.Student;

public class DeletePacmanCourseDemo {
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
			//get pacman course from database
			int id=10;
			Course course=session.get(Course.class, id);
			//delete the course
			System.out.println("Deleting the course...");
			session.delete(course);
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

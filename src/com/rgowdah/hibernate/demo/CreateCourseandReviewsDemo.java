package com.rgowdah.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rgowdah.hibernate.entity.Course;
import com.rgowdah.hibernate.entity.Instructor;
import com.rgowdah.hibernate.entity.Instructor_Detail;
import com.rgowdah.hibernate.entity.Review;

public class CreateCourseandReviewsDemo {
	public static void main(String[] args) {
		//create session factory
		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).addAnnotatedClass(Instructor_Detail.class).
				addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).buildSessionFactory();
		//create session
		Session session=sessionFactory.getCurrentSession();
		try{			
			//start transaction
			session.beginTransaction();
			//create a course
			Course course=new Course("Pacman how to score million points");
			//add some reviews
			course.addReview(new Review("awesome"));
			course.addReview(new Review("excellent"));
			course.addReview(new Review("bad"));
			//save the course..and leverage cascade all
			System.out.println("Saving the course");
			System.out.println("Course: "+course);
			System.out.println("Reviews: "+course.getReviews());
			session.save(course);			
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

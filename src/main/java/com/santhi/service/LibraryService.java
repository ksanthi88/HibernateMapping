package com.santhi.service;

import com.santhi.model.Book;
import com.santhi.model.Library;
import com.santhi.model.Member;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class LibraryService {
    public static void insertLibraryAndMember() {
        //creating Session
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        //Starting transaction
        Transaction transaction = session.beginTransaction();
        //Adding Library
        Library city = new Library();
        city.setName("City Library");

        Library city1 = new Library();
        city1.setName("Down Library");
        //session persisit
        session.persist(city);
        session.persist(city1);
        // adding members
        Member member1 = new Member();
        member1.setName("John Doe");
        member1.setEmail("john.doe@gmail.com");
        member1.setLibrary(city);

        Member member2 = new Member();
        member2.setName("Jo Doe");
        member2.setEmail("jo.doe@gmail.com");
        member2.setLibrary(city);

        Member member3 = new Member();
        member3.setName("Lisa Doe");
        member3.setEmail("Lisa.doe@gmail.com");
        member3.setLibrary(city1);

        Member member4 = new Member();
        member4.setName("Varun Doe");
        member4.setEmail("Varu.doe@gmail.com");
        member4.setLibrary(city1);
        //member persists
        session.persist(member1);
        session.persist(member2);
        session.persist(member3);
        session.persist(member4);
System.out.println("Data created sucessfully");
transaction.commit();
session.close();
    }

//    public static void retriveMember() {
//        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        Library city = (Library) session.get(Library.class, 1);
//    }
    public static void insertBooksToLibrary() {
        //create session factory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        //start transaction
        Transaction transaction = session.beginTransaction();
        //fetch exisiting library
       Library city=session.createQuery("from Library  where name = :name",Library.class).setParameter
               ("name","City Library").uniqueResult();
       Library city1=session.createQuery("from Library where name=:name",Library.class).
               setParameter("name","Down Library").uniqueResult();
       if(city != null) {
           //add books to city
           Book book1 = new Book();
           book1.setTitle("Java 8");
           Book book2 = new Book();
           book2.setTitle("Java 10");
           Book book3 = new Book();
           book3.setTitle("Java 11");
           city.getBooks().add(book1);
           city.getBooks().add(book2);
           city.getBooks().add(book3);
           session.persist(city);
       }else{
           System.out.println("City library not found");
       }
        if(city1 != null) {
            //add books to city
            Book book1 = new Book();
            book1.setTitle("SQL");
            Book book2 = new Book();
            book2.setTitle("Java");
            Book book3 = new Book();
            book3.setTitle("Python");
            city1.getBooks().add(book1);
            city1.getBooks().add(book2);
            city1.getBooks().add(book3);
            session.persist(city1);
        }else{
            System.out.println("Down library not found");
        }
        System.out.println("Books added  sucessfully");
        transaction.commit();
        session.close();
    }
}

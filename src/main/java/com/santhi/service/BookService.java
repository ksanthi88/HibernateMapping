package com.santhi.service;

import com.santhi.model.Book;
import com.santhi.model.Library;
import com.santhi.model.Member;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.List;

public class BookService {
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
            book1.setTitle("Science Fiction");
            Book book2 = new Book();
            book2.setTitle("Modern Science Fiction");
            Book book3 = new Book();
            book3.setTitle("Mathematics");
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
            book1.setTitle("Tamil ");
            Book book2 = new Book();
            book2.setTitle("English");
            Book book3 = new Book();
            book3.setTitle("Social Media");
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
    public static void accessBook(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //Access book from library
        Library re=session.get(Library.class,1);
        re.getBooks().forEach(System.out::println);


    }
    public  static void insertMembersAndBooks(){
        // Create SessionFactory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Begin transaction
        Transaction transaction = session.beginTransaction();

        //Create Books
        Book book1 = new Book();
        book1.setTitle("Java Basics");

        Book book2 = new Book();
        book2.setTitle("Spring in Action");

        Book book3 = new Book();
        book3.setTitle("Hibernate for Beginners");

        //Create Members
        Member alice = new Member();
        alice.setName("Alice");
        alice.setEmail("alice@gmail.com");

        Member bob = new Member();
        bob.setName("Bob");
        bob.setEmail("bob@example.com");


        //Assign books to members
        alice.getBooks().add(book1);
        alice.getBooks().add(book2);

        bob.getBooks().add(book3);

        //Persist Members (cascading saves books)
        session.persist(alice);
        session.persist(bob);

        //Commit Transaction
        transaction.commit();

        //Close session and factory
        session.close();
        sessionFactory.close();

        System.out.println("Members and books inserted successfully.");


    }
}

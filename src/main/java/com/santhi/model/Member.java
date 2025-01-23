package com.santhi.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name="library_id")//this is the forign key for member table and PK for library
    private Library library;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id")//forginkey
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="member_book",//join table name
              joinColumns=@JoinColumn(name="member_id"),//forgin key for member
               inverseJoinColumns = @JoinColumn(name="book_id") //foreign key for book
     )
    private Set<Book> books=new HashSet<>();
    public Member() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", library=" + library +
                ", address=" + address +
                ", books=" + books +
                '}';
    }
}

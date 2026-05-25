package hr.algebra.vjezba.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Publisher {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String name;
 @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
 private Set<Book> books = new HashSet<>();

public Publisher(){

}
public Publisher(String name){
    this.name = name;
}

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Book> getBooks() {
        return books;
    }
    public void addBook(Book book){
    books.add(book);
    book.setPublisher(this);
    }
}

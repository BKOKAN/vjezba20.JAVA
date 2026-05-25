package hr.algebra.vjezba.model;

import jakarta.persistence.*;

@Entity
public class Book {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String title;

@ManyToOne
private Author author;

@ManyToOne
private Publisher publisher;

public Book(){

}
public Book(String title){
    this.title=title;
}

public void setTitle(String title) {
    this.title = title;
}
public String getTitle() {
    return title;
}

public Long getId() {
    return id;
}

public void setAuthor(Author author) {
    this.author = author;
}
public Author getAuthor() {
    return author;
}

public Publisher getPublisher() {
    return publisher;
}

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}

package hr.algebra.vjezba.model;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String name;

@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();

public Author(){

}
public Author(String name){
    this.name = name;
}

public void setId(Long id) {
    this.id = id;
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
    book.setAuthor(this);
}
}

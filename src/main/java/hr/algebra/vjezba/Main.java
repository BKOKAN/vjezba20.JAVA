package hr.algebra.vjezba;

import hr.algebra.vjezba.model.Author;
import hr.algebra.vjezba.model.Book;
import hr.algebra.vjezba.model.Publisher;
import hr.algebra.vjezba.util.JpaUtil;

import jakarta.persistence.EntityManager;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            Publisher p1 = new Publisher("Knjige1");
            Publisher p2 = new Publisher("Knjige2");

            Author a1 = new Author("Miroslav Krleža");
            Author a2 = new Author("Ivana Brlić-Mažuranić");

            Book b1 = new Book("Gospoda Glembajevi");
            Book b2 = new Book("Priče iz davnine");

            a1.addBook(b1);
            a2.addBook(b2);

            p1.addBook(b1);
            p2.addBook(b2);

            em.persist(p1);
            em.persist(p2);

            em.persist(a1);
            em.persist(a2);

            em.getTransaction().commit();

            List<Author> authors = em.createQuery("select a from Author a", Author.class).getResultList();

            for (Author author : authors) {

                System.out.println(author.getName());

                for (Book book : author.getBooks()) {
                    System.out.println(" - je napisao/la " + book.getTitle());
                }
            }

            em.getTransaction().begin();

            Book bookToUpdate = em.find(Book.class, 1L);

            if (bookToUpdate != null) {
                bookToUpdate.setTitle("Novi naslov knjige");
            }

            em.getTransaction().commit();

            em.getTransaction().begin();

            Book bookToDelete = em.find(Book.class, b2.getId());
            System.out.println("ID JE "+ bookToDelete.getId());
            if (bookToDelete != null) {
                em.remove(bookToDelete);
            }

            em.getTransaction().commit();

            List<Book> books = em.createQuery("select b from Book b", Book.class).getResultList();

            for (Book book : books) {
                System.out.println(book.getTitle());
            }

            System.out.println("b1 id = " + b1.getId());
            System.out.println("b2 id = " + b2.getId());

        } finally {
            em.close();
            JpaUtil.close();
        }
    }
}
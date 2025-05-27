package mk.ukim.finki.wp.labgra.model.domain;

import jakarta.persistence.*;
import mk.ukim.finki.wp.labgra.model.enums.Categories;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class BookHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Categories category;

    @ManyToOne
    private Author author;

    private Integer availableCopies;

    private LocalDateTime dateModified;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


    public BookHistory(Long id, String name, Categories category, Author author, Integer availableCopies, LocalDateTime dateModified) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.dateModified=dateModified;
    }

    public BookHistory() {

    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookHistory(String name, Categories category, Author author, Integer availableCopies, LocalDateTime dateModified) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }
}

package com.techvibes.lims.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cover_img", length = 50, nullable = true)
    private String cover;

    @Column(name = "isbn", length = 50, nullable = false, unique = true)
    private String isbn;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "serialName", length = 50, nullable = false)
    private String serialName;

    @Column(name = "revision_no", length = 50, nullable = false)
    private Integer revno;

    @Column(name = "description", length = 250, nullable = false)
    private String description;

    @Column(name = "published_date")
    private Date published;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    @JoinTable(name = "books_authors", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
            @JoinColumn(name = "author_id") })
    private Set<Author> authors = new HashSet<Author>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "books_categories", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
            @JoinColumn(name = "category_id") })
    private Set<Category> categories = new HashSet<Category>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "books_publishers", joinColumns = { @JoinColumn(name = "book_id") }, inverseJoinColumns = {
            @JoinColumn(name = "publisher_id") })
    private Set<Publisher> publishers = new HashSet<Publisher>();

    @Column(name = "added_date")
    private Date date_added;

    public Book(String isbn, String title, String serialName, String description, Date date_added) {
        this.isbn = isbn;
        this.title = title;
        this.serialName = serialName;
        this.description = description;
        this.date_added = date_added;
    }

    public void addAuthors(Author author) {
        this.authors.add(author);
        author.getBooks().add(this);
    }

    public void removeAuthors(Author author) {
        this.authors.remove(author);
        author.getBooks().remove(this);
    }

    public void addCategories(Category category) {
        this.categories.add(category);
        category.getBooks().add(this);
    }

    public void removeCategories(Category category) {
        this.categories.remove(category);
        category.getBooks().remove(this);
    }

    public void addPublishers(Publisher publisher) {
        this.publishers.add(publisher);
        publisher.getBooks().add(this);
    }

    public void removePublishers(Publisher publisher) {
        this.publishers.remove(publisher);
        publisher.getBooks().remove(this);
    }
}

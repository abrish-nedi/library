package com.example.library.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private boolean isRented;
    private LocalDate returnDate;

    // Getters and Setters
}
Create a Book Repository:
package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIdAndIsRentedFalse(Long id);
}
Create a Book Service:

java
Copy code
package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Optional<Book> rentBook(Long bookId, int daysToRent) {
        Optional<Book> bookOpt = bookRepository.findByIdAndIsRentedFalse(bookId);
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            book.setRented(true);
            book.setReturnDate(LocalDate.now().plusDays(daysToRent));
            bookRepository.save(book);
            return Optional.of(book);
        }
        return Optional.empty();
    }
}
Create a Book Controller:

java
Copy code
package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/{id}/rent")
    public ResponseEntity<String> rentBook(@PathVariable Long id, @RequestParam int days) {
        Optional<Book> rentedBook = bookService.rentBook(id, days);
        if (rentedBook.isPresent()) {
            return ResponseEntity.ok("Book rented successfully!");
        } else {
            return ResponseEntity.status(400).body("Book is not available for rent.");
        }
    }
}
Add necessary dependencies in pom.xml:

xml

<dependencies>
    <!-- Other dependencies -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
Add an HTML page with a "Rent Book" button:

html
Copy code
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Book Details</title>
</head>
<body>
    <div th:if="${book}">
        <h2 th:text="${book.title}">Book Title</h2>
        <p th:text="${book.isRented ? 'Rented' : 'Available'}"></p>
        <button th:if="${!book.isRented}" 
                th:onclick="'rentBook(' + ${book.id} + ');'">Rent Book</button>
    </div>

    <script>
        function rentBook(bookId) {
            fetch('/api/books/' + bookId + '/rent?days=7', {
                method: 'POST'
            })
            .then(response => response.text())
            .then(data => alert(data))
            .catch(error => console.error('Error:', error));
        }
    </script>
</body>
</html>

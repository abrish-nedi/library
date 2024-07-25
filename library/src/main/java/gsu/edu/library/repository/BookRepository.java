package gsu.edu.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import gsu.edu.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
	List<Book> findByTitleContaining(String title);
}

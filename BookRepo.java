package gsu.edu.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);
}

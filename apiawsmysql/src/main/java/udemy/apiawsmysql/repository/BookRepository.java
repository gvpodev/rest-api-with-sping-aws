package udemy.apiawsmysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udemy.apiawsmysql.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}

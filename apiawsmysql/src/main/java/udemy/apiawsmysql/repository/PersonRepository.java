package udemy.apiawsmysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udemy.apiawsmysql.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}

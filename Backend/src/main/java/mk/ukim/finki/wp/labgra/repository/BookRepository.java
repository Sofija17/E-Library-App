package mk.ukim.finki.wp.labgra.repository;

import mk.ukim.finki.wp.labgra.model.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}

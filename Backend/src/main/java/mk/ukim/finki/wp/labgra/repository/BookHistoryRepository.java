package mk.ukim.finki.wp.labgra.repository;

import mk.ukim.finki.wp.labgra.model.domain.BookHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookHistoryRepository extends JpaRepository<BookHistory, Long> {

    List<BookHistory> findAllByBookId(Long bookId);
}

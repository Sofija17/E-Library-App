package mk.ukim.finki.wp.labgra.repository;

import mk.ukim.finki.wp.labgra.model.domain.Author;
import mk.ukim.finki.wp.labgra.model.projections.AuthorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    //@Query("select  a.name, a.surname from Author a")
    List<AuthorProjection> findAllBy();
}

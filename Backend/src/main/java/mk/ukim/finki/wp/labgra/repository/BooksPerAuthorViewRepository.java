//package mk.ukim.finki.wp.labgra.repository;
//
//import mk.ukim.finki.wp.labgra.model.views.BooksPerAuthorView;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.transaction.annotation.Transactional;
//import java.util.List;
//
//public interface BooksPerAuthorViewRepository extends JpaRepository<BooksPerAuthorView, Long> {
//
//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query(value = "REFRESH MATERIALIZED VIEW books_per_author", nativeQuery = true)
//    void refreshMaterializedView();
//
//    List<BooksPerAuthorView> findAll();
//}

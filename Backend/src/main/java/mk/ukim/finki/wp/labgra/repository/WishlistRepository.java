package mk.ukim.finki.wp.labgra.repository;

import mk.ukim.finki.wp.labgra.model.domain.User;
import mk.ukim.finki.wp.labgra.model.domain.Wishlist;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist,Long> {

    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"books"}
    )
    List<Wishlist> findByUser(User user);
}

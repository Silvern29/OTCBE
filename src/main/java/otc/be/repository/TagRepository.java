package otc.be.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import otc.be.entity.Tag;

import java.util.Optional;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {

    @Query(value = "SELECT * FROM tag WHERE text=?1", nativeQuery = true)
    Optional<Tag> findTagByText(@Param("text") String text);
}

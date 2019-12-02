package otc.be.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import otc.be.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findById(int id);

    @Query(value = "SELECT * FROM user where last_name LIKE %:lastName%", nativeQuery = true)
    Iterable<User> findUserByLastName(@Param("lastName") String lastName);

    Optional<User> findByEmail(@Param("email") String email);

    }

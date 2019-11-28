package otc.be.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import otc.be.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

        @Query(value = "SELECT * FROM customer where last_name LIKE %:lastName%", nativeQuery = true)
        Iterable<User> findCustomerByLastName(@Param("lastName") String lastName);

        Optional<User> findByEmail(@Param("email") String email);

    }

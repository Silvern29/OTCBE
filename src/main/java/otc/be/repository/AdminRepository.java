package otc.be.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import otc.be.entity.Admin;

import java.util.LinkedList;
import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {

    Optional<Admin> findById(int id);

    @Query(value = "SELECT * FROM admin where last_name LIKE %:lastName%", nativeQuery = true)
    Iterable<Admin> findAdminByLastName(@Param("lastName") String lastName);

    Admin findByEmail(@Param("email") String email);

    LinkedList<Admin> findByOrderByIdAsc();
}

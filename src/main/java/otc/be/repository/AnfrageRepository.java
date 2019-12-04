package otc.be.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import otc.be.entity.Anfrage;

@Repository
public interface AnfrageRepository extends CrudRepository<Anfrage, Integer> {
}

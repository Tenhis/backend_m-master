package ThirdGear.Kyselypalvelu_backend.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface KysymysRepo extends CrudRepository<Kysymys, Long> {

	   List<Kysymys> findByKysymysteksti(String kysymysteksti);
	   List<Kysymys> findByKysely(Optional<Kysely> kysely);

}

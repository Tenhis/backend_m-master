package ThirdGear.Kyselypalvelu_backend.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;



public interface KyselyRepo extends CrudRepository<Kysely, Long>  {

    List<Kysely> findByNimi(String nimi);
     
 
}

package ThirdGear.Kyselypalvelu_backend.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ThirdGear.Kyselypalvelu_backend.domain.Kysely;
import ThirdGear.Kyselypalvelu_backend.domain.KyselyRepo;
import ThirdGear.Kyselypalvelu_backend.domain.Kysymys;
import ThirdGear.Kyselypalvelu_backend.domain.KysymysRepo;

@Controller
public class RestController {
	
	@Autowired
	private KyselyRepo kyselyrepo;
	
	@Autowired	
	private KysymysRepo kysymysrepo; 
	

	// REST kaikkien kyselyiden hakuun
		@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value = "/api/kyselyt")
		public @ResponseBody List<Kysely> kyselyListaRest() {
			return (List<Kysely>) kyselyrepo.findAll();
		}

	// REST hakee kyselyn id:n perusteella  th:href="@{kysely/{id}(id=${kysely.id})}"
		@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value = "/api/kysely/{id}", method = RequestMethod.GET)
		public @ResponseBody Optional<Kysely> findKyselyRest(@PathVariable("id") Long id) {
			return kyselyrepo.findById(id);
		}
  
	// REST kaikkien kysymysten hakuun
		@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value="/api/kysymykset", method = RequestMethod.GET)
		public @ResponseBody List<Kysymys> kysymyslistaRest() {	
			return (List<Kysymys>) kysymysrepo.findAll();
		} 
		   

	// REST --> hakee kysymykset id:n avulla
		@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value="/api/kysymykset/{id}", method = RequestMethod.GET)
		public @ResponseBody Optional<Kysymys> findKysymysRest(@PathVariable("id") Long kysymysId) {	
			return kysymysrepo.findById(kysymysId);
		}  
	
}

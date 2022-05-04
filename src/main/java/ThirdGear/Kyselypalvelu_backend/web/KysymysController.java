package ThirdGear.Kyselypalvelu_backend.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ThirdGear.Kyselypalvelu_backend.domain.Kysely;
import ThirdGear.Kyselypalvelu_backend.domain.KyselyRepo;
import ThirdGear.Kyselypalvelu_backend.domain.Kysymys;
import ThirdGear.Kyselypalvelu_backend.domain.KysymysRepo;




@Controller
public class KysymysController {
	
	@Autowired	
	private KyselyRepo kyselyrepo; 
	
	@Autowired	
	private KysymysRepo kysymysrepo; 
	
 
//   3. KYSELYN KAIKKIEN KYSYMYSTEN LISTAAMINEN JA KYSYMYSLOMAKKEEN LUONTI
//   4. KYSYMYKSEN TALLENNUS    
//   5. KYSYMYKSEN POISTAMINEN     
    
    
    
// 3. ----- KYSELYN KAIKKIEN KYSYMYSTEN LISTAAMINEN JA KYSYMYSLOMAKKEEN LUONTI--------------------------------------------------------------------
	
// Kyselyn id tulee thymeleafilla olevasta linkistä (PathVariable), jota klikataan
	
 	@RequestMapping(value = "/kysely/{id}", method =RequestMethod.GET)
 	
 	public String noudaKysely(@PathVariable("id") Long id, Model model){
 		
// Haetaan oikea kysely id:n perusteella
 		Kysely kysely = kyselyrepo.findById(id).get(); 
 	
 		model.addAttribute("kysely", kysely);
 		
// Uusi kysymys modeliin --> Lomake uuden kysymyksen lisäämiseen -------		
		model.addAttribute("kysymys", new Kysymys());
		
 		return "kysely";
 	}
 	
 	


 //4. ---------- KYSYMYKSEN TALLENNUS---------------------------------------------------------------------------------------	
 	
 	

 // Tallenna uusi kysymys kyselyyn ja ohjaa kyselyyn takaisin tallennuksen jälkeen
 	@PreAuthorize("hasAuthority('ADMIN')")
 	@RequestMapping(value = "/kysely/{id}/tallennakysymys", method = RequestMethod.POST)
 	public String tallennaKysymys(@PathVariable("id") Long id, @ModelAttribute Kysymys kysymys) {
 		kysymys.setKysymysteksti(kysymys.getKysymysteksti());
 		kysymys.setVastaustyyppi(kysymys.getVastaustyyppi());
 		kysymys.setKysely(kyselyrepo.findById(id).get());
 		kysymysrepo.save(kysymys);
 		return "redirect:/kysely/{id}";
 	}
     
 	
 	 // (tämä ei käytössä tällä hetkellä ollenkaan) 
 	 // /Tallenna kysymys ---yksinkertainen tallennus ilman id-muuttujaa (ei voi ohjata id:n sisältävään urliin).
 	     @RequestMapping(value = "/tallennakysymys", method = RequestMethod.POST)
 	     public String tallennaKysymys(Kysymys kysymys){        
 	         kysymysrepo.save(kysymys);     
 	         return "redirect:kyselyt";
 	     } 
 	
 	
 // 5. ---------------KYSYMYKSEN POISTAMINEN --------------------------------------------------------------------	
 	
	
 	// Pathvariable:ksi tarvitaan kyselyn id ("id") sekä kysymyksen id ("kysymysid")
 	     	@PreAuthorize("hasAuthority('ADMIN')")
 			@RequestMapping(value = "kysely/{id}/poista/{kysymysid}", method = RequestMethod.GET)
 			public String poistaKysymys(@PathVariable("id") Long id, @PathVariable("kysymysid") Long kysymysid) {
 	// kysymysreposta haetaan id:n(kysymysid) perusteella oikea kysymys ja poistetaan se
 				kysymysrepo.delete(kysymysrepo.findById(kysymysid).get());
 				return "redirect:/kysely/{id}";
 			}
 		
}
package ThirdGear.Kyselypalvelu_backend;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ThirdGear.Kyselypalvelu_backend.domain.Kysely;
import ThirdGear.Kyselypalvelu_backend.domain.KyselyRepo;
import ThirdGear.Kyselypalvelu_backend.domain.Kysymys;
import ThirdGear.Kyselypalvelu_backend.domain.KysymysRepo;
import ThirdGear.Kyselypalvelu_backend.domain.User;
import ThirdGear.Kyselypalvelu_backend.domain.UserRepo;



@SpringBootApplication
public class KyselypalveluBackendApplication {
	
	private static final Logger log = LoggerFactory.getLogger(KyselypalveluBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KyselypalveluBackendApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(KyselyRepo kyselyRepo, KysymysRepo kysymysRepo, UserRepo userRepo) {
		return  (args) -> {
				
				log.info("Kyselyn testidata tietokantaan"); 
				
				List<Kysymys> kysymykset = new ArrayList<Kysymys>();
				Kysely kysely1 = new Kysely("Opiskelijan uni", "Nukkumistottumusten kartoitus", kysymykset);
				kyselyRepo.save(kysely1);

				kysymysRepo.save(new Kysymys ("Kuinka paljon nukut öisin keskimäärin?", "teksti", kysely1));
				kysymysRepo.save(new Kysymys ("Heräiletkö usein kesken unien?", "teksti", kysely1));
				kysymysRepo.save(new Kysymys ("Nousetko virkeänä aamulla?", "teksti", kysely1));
				
				
				User user1 = new User("admin", "$2a$10$dJiJhhhrIyDXhOwHlePDyeKUs5M2hOkJ5xd3usX3yzoyA3XY2Wroq", "ADMIN");
				User user2 = new User("user", "$2a$10$z636VfOgBnnTUplQ50DuE.M3MB.tcymYusQ2H25SYR7odVpmiIOvO", "USER");
				userRepo.save(user1);
				userRepo.save(user2);
						
		};
	}	
}

package ThirdGear.Kyselypalvelu_backend.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Kysely {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
	private Long id;
	private String nimi;
	private String kuvaus;


	
//Kyselyllä on monta kysymystä mutta Kysymys kuuluu vain yhteen Kyselyyn	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysely")

	
// --- MUODOSTETAAN LISTA KYSYMYKSISTÄ	
	private List<Kysymys> kysymykset;
	
	
	
// ---------------------------------------------------------------------------------------------------------------
	
	public Kysely() {
		super();
		this.nimi = null;
		this.kuvaus = null;
		this.kysymykset = null;
	}
	
	
	
	public Kysely (String nimi, List<Kysymys> kysymykset) {
		super();
		this.nimi = nimi;
		this.kysymykset = kysymykset;
	}

	
	
	public Kysely (String nimi, String kuvaus, List<Kysymys> kysymykset) {
		super();
		this.nimi = nimi;
		this.kuvaus = kuvaus;
		this.kysymykset = kysymykset;
	}
	
	
	
//----- GET --------------------------------------------------------
	
	public Long getId() {
		return id;
	}
	
	public String getNimi() {
		return nimi;
	}
	
	public String getKuvaus() {
		return kuvaus;
	}
	
// -- hae kysymyslista  ----------
	public List<Kysymys> getKysymykset() {
		return kysymykset;
	}
	
// -- hae kysymysten määrä --------
	public int getKysymyksetSize() {
		return kysymykset.size();
	}
	
//----- SET ---------------------------------------------------------	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}
	
// -- aseta kysymyslista  ----------	
	public void setKysymykset(List<Kysymys> kysymykset) {
		this.kysymykset = kysymykset;
	}
	
	
// ---- STRING TO STRING ----------------------------------------------------
	
	@Override
	public String toString() {
		                                    
		return "Kysely [id=" +id + ", nimi=" + nimi   + "]";
		
	}
}

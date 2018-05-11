package jwd.wafepa.web.dto;

public class PivoDTO {

	private Long id;
	private String naziv;
	private Double alkohol;
	private Double ibu;
	private Integer kolicina;
	private Long pivaraId;
	private String pivaraNaziv;
	private Long vrstaPivaId;
	private String vrstaPivaNaziv;
	
	
	public Long getVrstaPivaId() {
		return vrstaPivaId;
	}
	public void setVrstaPivaId(Long vrstaPivaId) {
		this.vrstaPivaId = vrstaPivaId;
	}
	public String getVrstaPivaNaziv() {
		return vrstaPivaNaziv;
	}
	public void setVrstaPivaNaziv(String vrstaPivaNaziv) {
		this.vrstaPivaNaziv = vrstaPivaNaziv;
	}
	public Long getPivaraId() {
		return pivaraId;
	}
	public void setPivaraId(Long pivaraId) {
		this.pivaraId = pivaraId;
	}
	public String getPivaraNaziv() {
		return pivaraNaziv;
	}
	public void setPivaraNaziv(String pivaraNaziv) {
		this.pivaraNaziv = pivaraNaziv;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Double getAlkohol() {
		return alkohol;
	}
	public void setAlkohol(Double alkohol) {
		this.alkohol = alkohol;
	}
	public Double getIbu() {
		return ibu;
	}
	public void setIbu(Double ibu) {
		this.ibu = ibu;
	}
	public Integer getKolicina() {
		return kolicina;
	}
	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}
	
	
}

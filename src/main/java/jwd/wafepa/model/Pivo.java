package jwd.wafepa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Pivo {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column
	private String naziv;
	
	@Column
	private Double alkohol;
	
	@Column
	private Double ibu;
	
	@Column
	private Integer kolicina;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private VrstaPiva vrstaPiva;

	@ManyToOne(fetch=FetchType.EAGER)
	private Pivara pivara;
	
	@OneToMany(mappedBy="pivo",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<KupovinaPiva> kupovine = new ArrayList<>();

	public List<KupovinaPiva> getKupovine() {
		return kupovine;
	}

	public void setKupovine(List<KupovinaPiva> kupovine) {
		this.kupovine = kupovine;
	}

	public void addKupovina(KupovinaPiva kupovina){
		this.kupovine.add(kupovina);
		
		if(!this.equals(kupovina.getPivo())){
			kupovina.setPivo(this);
		}
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

	public VrstaPiva getVrstaPiva() {
		return vrstaPiva;
	}

	public void setVrstaPiva(VrstaPiva vrstaPiva) {
		this.vrstaPiva = vrstaPiva;
		if(vrstaPiva!=null && !vrstaPiva.getPiva().contains(this)){
			vrstaPiva.getPiva().add(this);
		}
	}

	public Pivara getPivara() {
		return pivara;
	}

	public void setPivara(Pivara pivara) {
		this.pivara = pivara;
		if(pivara!=null && !pivara.getPiva().contains(this)){
			pivara.getPiva().add(this);
		}
	}
	
	
}

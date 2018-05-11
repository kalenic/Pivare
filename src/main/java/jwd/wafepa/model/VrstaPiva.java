package jwd.wafepa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class VrstaPiva {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column
	private String naziv;
	
	@OneToMany(mappedBy = "vrstaPiva" ,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Pivo> piva = new ArrayList<>();

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

	public List<Pivo> getPiva() {
		return piva;
	}

	public void setPiva(List<Pivo> piva) {
		this.piva = piva;
	}
	
	
}

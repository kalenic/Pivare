package jwd.wafepa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.wafepa.model.Pivo;

public interface PivoService {

	Page<Pivo> findAll(int pageNum);
	Pivo findOne(Long id);
	void save(Pivo pivo);
	void remove(Long id);
	Page<Pivo> findByPivaraId(int pageNum, Long pivaraId);
	Page<Pivo> pretraga(
			@Param("naziv") String naziv, 
			@Param("minIbu") Double minIbu, 
			@Param("maxIbu") Double maxIbu,
			@Param("nazivPivare") String nazivPivare,
			int page);
}

package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.VrstaPiva;

public interface VrstaPivaService {

	List<VrstaPiva> findAll();
	VrstaPiva findOne(Long id);
	void save(VrstaPiva vrstaPiva);
}

package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Pivara;

public interface PivaraService {

	List<Pivara> findAll();
	Pivara findOne(Long id);
	void save(Pivara pivara);
	void remove(Long id);
}

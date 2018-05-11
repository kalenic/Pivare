package jwd.wafepa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Pivara;
import jwd.wafepa.repository.PivaraRepository;
import jwd.wafepa.service.PivaraService;

@Service
@Transactional
public class JpaPivaraService implements PivaraService {
	
	@Autowired
	private PivaraRepository pivaraRepository;

	@Override
	public List<Pivara> findAll() {
		return pivaraRepository.findAll();
	}

	@Override
	public Pivara findOne(Long id) {
		return pivaraRepository.findOne(id);
	}

	@Override
	public void save(Pivara pivara) {
		pivaraRepository.save(pivara);

	}

	@Override
	public void remove(Long id) {
		pivaraRepository.delete(id);

	}

}

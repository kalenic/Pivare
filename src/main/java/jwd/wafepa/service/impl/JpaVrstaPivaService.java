package jwd.wafepa.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Pivara;
import jwd.wafepa.model.VrstaPiva;
import jwd.wafepa.repository.VrstaPivaRepository;
import jwd.wafepa.service.VrstaPivaService;

@Service
@Transactional
public class JpaVrstaPivaService implements VrstaPivaService {

	@Autowired
	private VrstaPivaRepository vrstaPivaRepository;
	
	@Override
	public List<VrstaPiva> findAll() {
		return vrstaPivaRepository.findAll();
	}

	@Override
	public VrstaPiva findOne(Long id) {
		return vrstaPivaRepository.findOne(id);
	}
	
	@Override
	public void save(VrstaPiva vrstaPiva) {
		vrstaPivaRepository.save(vrstaPiva);

	}

}


package jwd.wafepa.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.KupovinaPiva;
import jwd.wafepa.model.Pivo;
import jwd.wafepa.repository.KupovinaPivaRepository;
import jwd.wafepa.repository.PivoRepository;
import jwd.wafepa.service.KupovinaPivaService;

@Service
@Transactional
public class JpaKupovinaPivaService implements KupovinaPivaService {

	@Autowired
	private KupovinaPivaRepository kupovinaRepository;
	@Autowired
	private PivoRepository pivoRepository;
	
	@Override
	public KupovinaPiva buyABeer(Long pivoId) {
		
		if(pivoId == null) {
			throw new IllegalArgumentException("Id of a beer cannot be null!");
		}
		
		Pivo pivo = pivoRepository.findOne(pivoId);
		if(pivo == null) {
			throw new IllegalArgumentException("There is no beer with given id!");
		}
		
		if(pivo.getKolicina() > 0) {
			
			KupovinaPiva kupovina = new KupovinaPiva();
			kupovina.setPivo(pivo);
			
			pivo.setKolicina(pivo.getKolicina() - 1);
			
			kupovinaRepository.save(kupovina);
			pivoRepository.save(pivo);
			
			return kupovina;
		}
		
		return null;
		
	}

}

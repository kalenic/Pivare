package jwd.wafepa.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Pivo;
import jwd.wafepa.service.PivaraService;
import jwd.wafepa.service.PivoService;
import jwd.wafepa.service.VrstaPivaService;
import jwd.wafepa.web.dto.PivoDTO;

@Component
public class PivoDTOToPivo implements Converter<PivoDTO, Pivo>{
	
	@Autowired
	private PivoService pivoService;
	
	@Autowired
	private PivaraService pivaraService;
	
	@Autowired
	private VrstaPivaService vrstaPivaService;
	
	
	@Override
	public Pivo convert(PivoDTO source) {
		Pivo pivo;
		if(source.getId()==null){
			pivo = new Pivo();
			//AKO NE MENJAMO PIVARU I VRSTU PIVA OVDE IH SETUJEMO
//			pivo.setPivara(pivaraService.findOne(source.getPivaraId()));
//			pivo.setVrstaPiva(vrstaPivaService.findOne(source.getVrstaPivaId()));
		}else{
			pivo = pivoService.findOne(source.getId());
		}
		//AKO MENJAMO PIVARU I VRSTU PIVA ONDA IH OVDE SETUJEMO
		pivo.setPivara(pivaraService.findOne(source.getPivaraId()));
		pivo.setVrstaPiva(vrstaPivaService.findOne(source.getVrstaPivaId()));
		
		pivo.setNaziv(source.getNaziv());
		pivo.setAlkohol(source.getAlkohol());
		pivo.setIbu(source.getIbu());
		pivo.setKolicina(source.getKolicina());
		
		return pivo;
	}
	
	

}

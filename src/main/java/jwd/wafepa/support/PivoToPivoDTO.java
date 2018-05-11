package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Pivo;
import jwd.wafepa.web.dto.PivoDTO;

@Component
public class PivoToPivoDTO implements Converter<Pivo, PivoDTO>{

	@Override
	public PivoDTO convert(Pivo source) {
		PivoDTO dto = new PivoDTO();
		dto.setId(source.getId());
		dto.setNaziv(source.getNaziv());
		dto.setAlkohol(source.getAlkohol());
		dto.setIbu(source.getIbu());
		dto.setKolicina(source.getKolicina());
		dto.setPivaraId(source.getPivara().getId());
		dto.setPivaraNaziv(source.getPivara().getNaziv());
		dto.setVrstaPivaId(source.getVrstaPiva().getId());
		dto.setVrstaPivaNaziv(source.getVrstaPiva().getNaziv());
		
		return dto;
	}

	public List<PivoDTO> convert(List<Pivo> piva){
		List<PivoDTO> ret = new ArrayList<>();
		
		for(Pivo p : piva){
			ret.add(convert(p));
		}
		
		return ret;
	}
	
}

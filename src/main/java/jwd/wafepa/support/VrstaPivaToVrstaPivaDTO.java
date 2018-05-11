package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.VrstaPiva;
import jwd.wafepa.web.dto.VrstaPivaDTO;

@Component
public class VrstaPivaToVrstaPivaDTO implements Converter<VrstaPiva, VrstaPivaDTO>{

	@Override
	public VrstaPivaDTO convert(VrstaPiva source) {
		VrstaPivaDTO vrstaPivaDTO = new VrstaPivaDTO();
		vrstaPivaDTO.setId(source.getId());
		vrstaPivaDTO.setNaziv(source.getNaziv());
		
		return vrstaPivaDTO;
	}
	
	public List<VrstaPivaDTO> convert(List<VrstaPiva> vrstePiva) {
		List<VrstaPivaDTO> ret = new ArrayList<>();
		
		for(VrstaPiva v: vrstePiva){
			ret.add(convert(v));
		}
		
		return ret;
	}

}

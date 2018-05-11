package jwd.wafepa.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.KupovinaPiva;
import jwd.wafepa.web.dto.KupovinaPivaDTO;

@Component
public class KupovinaPivaToKupovinaPivaDTO implements Converter<KupovinaPiva, KupovinaPivaDTO>{

	@Override
	public KupovinaPivaDTO convert(KupovinaPiva kupovina) {
		KupovinaPivaDTO dto = new KupovinaPivaDTO();
		dto.setId(kupovina.getId());
		
		return dto;
	}

}

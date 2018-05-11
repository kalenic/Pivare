package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.service.VrstaPivaService;
import jwd.wafepa.support.VrstaPivaToVrstaPivaDTO;
import jwd.wafepa.web.dto.VrstaPivaDTO;

@RestController
@RequestMapping(value="/api/vrste_piva")
public class ApiVrstaPivaController {

	@Autowired
	private VrstaPivaService vrstaPivaService;

//	@Autowired
//	private PivoService pivoService;

	@Autowired
	private VrstaPivaToVrstaPivaDTO toDTO;

//	@Autowired
//	private PivoToPivoDTO toPivoDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<VrstaPivaDTO>> get(){
		return new ResponseEntity<>(
				toDTO.convert(vrstaPivaService.findAll()),
				HttpStatus.OK);
	}
}

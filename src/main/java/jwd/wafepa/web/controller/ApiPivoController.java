package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.KupovinaPiva;
import jwd.wafepa.model.Pivo;
import jwd.wafepa.service.KupovinaPivaService;
import jwd.wafepa.service.PivoService;
import jwd.wafepa.support.KupovinaPivaToKupovinaPivaDTO;
import jwd.wafepa.support.PivoDTOToPivo;
import jwd.wafepa.support.PivoToPivoDTO;
import jwd.wafepa.web.dto.KupovinaPivaDTO;
import jwd.wafepa.web.dto.PivoDTO;

@RestController
@RequestMapping("/api/piva")
public class ApiPivoController {

	@Autowired
	private KupovinaPivaService kupovinaService;
	
	@Autowired
	private PivoService pivoService;
	@Autowired
	private PivoToPivoDTO toDTO;
	@Autowired
	private KupovinaPivaToKupovinaPivaDTO toKupovinaDTO;
	@Autowired
	private PivoDTOToPivo toPivo;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PivoDTO>> get(
			@RequestParam(required=false) String naziv,
			@RequestParam(required=false) Double minIbu,
			@RequestParam(required=false) Double maxIbu,
			@RequestParam(required=false) String nazivPivare,
			
			@RequestParam(defaultValue="0") int pageNum){
		
		Page<Pivo> piva = pivoService.findAll(pageNum);
		
		if(naziv != null || minIbu != null || maxIbu != null || nazivPivare != null) {
			piva = pivoService.pretraga(naziv, minIbu, maxIbu, nazivPivare, pageNum);
		}else{
			piva = pivoService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(piva.getTotalPages()) );
		return  new ResponseEntity<>(
				toDTO.convert(piva.getContent()),
				headers,
				HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<PivoDTO> get(@PathVariable Long id) {
		Pivo pivo = pivoService.findOne(id);

		if (pivo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(pivo), HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<PivoDTO> add(
			@RequestBody PivoDTO novoPivo){
		
		Pivo pivo= toPivo.convert(novoPivo); 
		pivoService.save(pivo);
		
		return new ResponseEntity<>(toDTO.convert(pivo),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{id}/kupovina")
	public ResponseEntity<KupovinaPivaDTO> buy(@PathVariable Long id){
		
		KupovinaPiva k = kupovinaService.buyABeer(id);
		
		if(k == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(toKupovinaDTO.convert(k), HttpStatus.CREATED);
		}
		
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}")
	public ResponseEntity<PivoDTO> edit(
			@PathVariable Long id,
			@RequestBody PivoDTO izmenjen){
		
		if(!id.equals(izmenjen.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Pivo pivo = toPivo.convert(izmenjen); 
		pivoService.save(pivo);
		
		return new ResponseEntity<>(toDTO.convert(pivo),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,
			value="/{id}")
	public ResponseEntity<PivoDTO> delete(@PathVariable Long id){
		pivoService.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

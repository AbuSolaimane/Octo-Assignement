package ma.octo.assignement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.octo.assignement.dto.VersementDto;
import ma.octo.assignement.entity.Versement;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.service.VersementService;

@RestController
@RequestMapping("/api/versements")
public class VersementController {
	
	@Autowired
	private VersementService versementService; 
	
	@GetMapping
	public List<Versement> getAllVersements() {
		return versementService.getAllVersements();
	}
	
	@GetMapping("{id}")
	public Versement getVersement(@PathVariable("id") Long id) {
		return versementService.getVersement(id);
	}
	
	@PostMapping
	public Versement createVersement(@RequestBody VersementDto versementDto) throws CompteNonExistantException {
		return versementService.createVersement(versementDto);
	}
	
	@PutMapping("{id}")
	public Versement updateVersement(@PathVariable("id") Long id, @RequestBody Versement versement) {
		return versementService.updateVersement(versement, id);
	}
	
	@DeleteMapping("{id}")
	public Versement updateVersement(@PathVariable("id") Long id) {
		return versementService.deleteVersement(id);
	}
	

}

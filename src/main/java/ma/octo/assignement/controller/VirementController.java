package ma.octo.assignement.controller;

import ma.octo.assignement.dto.VirementDto;
import ma.octo.assignement.entity.Virement;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.service.VirementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/virements")
class VirementController {

	@Autowired
	private VirementService virementService;

	@GetMapping
	public List<Virement> getAllVirements() {
		return virementService.getAllVirements();
	}
	
	@GetMapping("{id}")
	public Virement getVirement(@PathVariable("id") Long id) {
		return virementService.getVirement(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Virement createTransaction(@RequestBody VirementDto virementDto) throws 
												SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException {

		return virementService.createTransaction(virementDto);
	}

	@PutMapping("{id}")
	public Virement updateTransaction(@PathVariable("id") Long id, @RequestBody Virement virement) {

		return virementService.updateVirement(virement, id);
	}
	
	@DeleteMapping("{id}")
	public Virement deleteTransaction(@PathVariable("id") Long id) {

		return virementService.deleteVirement(id);
	}
}

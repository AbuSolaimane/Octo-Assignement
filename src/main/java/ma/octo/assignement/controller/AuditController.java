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

import ma.octo.assignement.entity.Audit;
import ma.octo.assignement.service.AuditService;

@RestController
@RequestMapping("/api/audits")
public class AuditController {
	
	@Autowired
	private AuditService auditService;
	
	@GetMapping
	public List<Audit> getAllAudits() {
		return auditService.getAllAudits();
	}
	
	@GetMapping("{id}")
	public Audit getAudit(@PathVariable("id") Long id) {
		return auditService.getAudit(id);
	}
	
	@PostMapping
	public Audit createAudit(@RequestBody Audit audit) {
		return auditService.createAudit(audit);
	}
	
	@PutMapping("{id}")
	public Audit updateAudit(@PathVariable("id") Long id, @RequestBody Audit audit) {
		return auditService.updateAudit(audit, id);
	}
	
	@DeleteMapping("{id}")
	public Audit deleteAudit(@PathVariable("id") Long id) {
		return auditService.deleteAudit(id);
	}

}

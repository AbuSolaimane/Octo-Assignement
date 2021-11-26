package ma.octo.assignement.service;

import java.util.List;

import ma.octo.assignement.entity.Audit;


public interface AuditService {
	List<Audit> getAllAudits();
	Audit getAudit(Long id);	
	Audit createAudit(Audit audit);
	Audit updateAudit(Audit audit, Long id);
	Audit deleteAudit(Long id);
}

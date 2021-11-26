package ma.octo.assignement.service.impl;

import ma.octo.assignement.entity.Audit;
import ma.octo.assignement.repository.AuditRepository;
import ma.octo.assignement.service.AuditService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuditServiceImpl implements AuditService{

    @Autowired
    private AuditRepository auditRepository;

	@Override
	public List<Audit> getAllAudits() {
		return auditRepository.findAll();
	}

	@Override
	public Audit getAudit(Long id) {
		return auditRepository.findById(id).get();
	}

	@Override
	public Audit createAudit(Audit audit) {
		return auditRepository.save(audit);
	}

	@Override
	public Audit updateAudit(Audit audit, Long id) {
		Audit oldAudit = auditRepository.findById(id).get();
		oldAudit.setEventType(audit.getEventType());
		oldAudit.setMessage(audit.getMessage());
		return oldAudit;
	}

	@Override
	public Audit deleteAudit(Long id) {
		Audit oldAudit = auditRepository.findById(id).get();
		auditRepository.deleteById(id);
		return oldAudit;
	}
}

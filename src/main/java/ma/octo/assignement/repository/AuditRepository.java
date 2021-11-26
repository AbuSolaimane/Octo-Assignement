package ma.octo.assignement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.octo.assignement.entity.Audit;

public interface AuditRepository extends JpaRepository<Audit, Long> {
	
}

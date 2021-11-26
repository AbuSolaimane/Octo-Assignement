package ma.octo.assignement.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.octo.assignement.entity.Virement;

public interface VirementRepository extends JpaRepository<Virement, Long> {
	
	List<Virement> findByDateExecution(Date dateExecution);
}

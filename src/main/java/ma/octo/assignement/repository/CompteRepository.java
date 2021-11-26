package ma.octo.assignement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.octo.assignement.entity.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {
  Compte findByNrCompte(String nrCompte);
  Compte findByRib(String rib);
}

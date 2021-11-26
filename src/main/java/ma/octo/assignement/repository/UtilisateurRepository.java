package ma.octo.assignement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.octo.assignement.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}

package ma.octo.assignement.dto;

import java.math.BigDecimal;

public class VersementDto {

	private BigDecimal montantVirement;
	private String rib;
	private String nom_prenom_emetteur;

	public VersementDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getMontantVirement() {
		return montantVirement;
	}

	public void setMontantVirement(BigDecimal montantVirement) {
		this.montantVirement = montantVirement;
	}

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

	public String getNom_prenom_emetteur() {
		return nom_prenom_emetteur;
	}

	public void setNom_prenom_emetteur(String nom_prenom_emetteur) {
		this.nom_prenom_emetteur = nom_prenom_emetteur;
	}

}

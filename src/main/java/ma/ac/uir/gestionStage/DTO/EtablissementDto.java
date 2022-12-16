package ma.ac.uir.gestionStage.DTO;

public class EtablissementDto {
    private int idEtablissement;
    private String nom;
    private String description;

    public EtablissementDto() {
    }

    public EtablissementDto(int idEtablissement, String nom, String description) {
        this.idEtablissement = idEtablissement;
        this.nom = nom;
        this.description = description;
    }

    public int getIdEtablissement() {
        return idEtablissement;
    }

    public void setIdEtablissement(int idEtablissement) {
        this.idEtablissement = idEtablissement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Etablissement{" +
                "idEtablissement=" + idEtablissement +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

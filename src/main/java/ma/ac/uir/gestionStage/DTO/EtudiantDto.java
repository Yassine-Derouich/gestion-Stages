package ma.ac.uir.gestionStage.DTO;

import lombok.Data;
@Data
public class EtudiantDto {

    //@JsonProperty("idE")
    private int id;
    private String nom, prenom, email, password;
    private String nomFiliere;
    private int NBniveau;

    //@JsonIgnore
    //private int professeurDto;
}

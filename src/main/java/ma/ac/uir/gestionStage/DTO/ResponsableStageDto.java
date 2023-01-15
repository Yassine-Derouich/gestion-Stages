package ma.ac.uir.gestionStage.DTO;

import lombok.Data;

@Data
public class ResponsableStageDto {
    private int id;
    private String nom, prenom, email, password;
    private int idRole;
}

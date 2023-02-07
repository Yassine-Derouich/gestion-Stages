package ma.ac.uir.gestionStage.DTO;

import lombok.Data;

@Data
public class ResponsableStageDto {

    private int idResponsable;
    private String nom, prenom, email, password;
}

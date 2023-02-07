package ma.ac.uir.gestionStage.DTO;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@Data
public class EtablissementDto {
    private int idEtablissement;
    private String nom;
    private String description;
    private int idResponsable;


}

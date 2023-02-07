package ma.ac.uir.gestionStage.DTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import ma.ac.uir.gestionStage.Entities.Etudiant;
import ma.ac.uir.gestionStage.Entities.Professeur;


import javax.persistence.Column;
import java.sql.Date;
import java.util.List;

@Data
public class ReunionDto {
    private int idReunion;
    private String libelle;
    private String date;
    private String salle;

}

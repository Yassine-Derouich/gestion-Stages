package ma.ac.uir.gestionStage.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ma.ac.uir.gestionStage.Entities.Etudiant;
import org.springframework.data.annotation.Reference;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
public class ProfesseurDto {

    private int id;
    private String nom, prenom, email, password;
    @JsonBackReference(value = "idE")
    //private List<Integer>idE;
    private List<Etudiant> idE;

}

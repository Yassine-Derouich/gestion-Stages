package ma.ac.uir.gestionStage.Entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "niveau")
@Entity
public class Niveau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name= "idNiveau")
    private Integer idNiveau;

    @Column( name= "nomFiliere")
    private String nomFiliere;

    @Column( name= "niveau")
    private String niveau;

    public Integer getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Integer idNiveau) {
        this.idNiveau = idNiveau;
    }

    public String getNomFiliere() {
        return nomFiliere;
    }

    public void setNomFiliere(String nomFiliere) {
        this.nomFiliere = nomFiliere;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
}

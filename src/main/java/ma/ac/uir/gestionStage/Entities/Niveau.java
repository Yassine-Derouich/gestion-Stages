package ma.ac.uir.gestionStage.Entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "niveau")
public class Niveau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name= "idNiveau")
    private Integer idNiveau;

    @Column( name= "nomFiliere", unique = true)
    private String nomFiliere;

    @Column( name= "NBniveau")
    private String NBniveau;

    public Niveau() {
    }
    public Niveau(Integer idNiveau, String nomFiliere, String NBniveau) {
        this.idNiveau = idNiveau;
        this.nomFiliere = nomFiliere;
        this.NBniveau = NBniveau;
    }
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
    public String getNBniveau() {
        return NBniveau;
    }
    public void setNBniveau(String NBniveau) {
        this.NBniveau = NBniveau;
    }

  /*  @ToString.Exclude
    @OneToOne(mappedBy = "niveau", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true)
    private Etudiant etudiant;*/
}

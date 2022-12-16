package ma.ac.uir.gestionStage.Entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "etablissment")
@Data
public class Etablissement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name= "idEtablissement")
    private Integer idEtablissement;

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;
}

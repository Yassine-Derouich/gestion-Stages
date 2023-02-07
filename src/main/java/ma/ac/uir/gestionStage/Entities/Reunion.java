package ma.ac.uir.gestionStage.Entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "reunion")
public class Reunion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReunion")
    private int idReunion;

    @Column(name = "libelle")
    private String libelle;

    @Column(name="salle")
    private String salle;

    @Column(name = "date")
    private String date;

    /////////////REUNION - ETD & PROF////////////////

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "etudiant_id",unique = true)
    private Etudiant etudiant;

    @OneToOne( fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval = true)
    @JoinColumn(name = "professeur_id")
    private Professeur professeur;
}

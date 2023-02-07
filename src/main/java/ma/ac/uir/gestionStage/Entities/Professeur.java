package ma.ac.uir.gestionStage.Entities;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.sql.ordering.antlr.ColumnReference;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table
@Data
public class Professeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name= "id")
    private int id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    /*@OneToMany//(mappedBy = "etudiant", fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @Nullable
    @JoinColumn(name = "professeur_id")
    private List<Etudiant> etudiantList;*/

   /* @ToString.Exclude
    @OneToMany(mappedBy = "professeur", cascade = {CascadeType.PERSIST,CascadeType.MERGE} )
    private List<Etudiant> etudiantList;
*/

///////////////////// affectation etudiant-prof/////////
    @OneToMany(cascade = { CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = false)
    @JoinTable(name = "professeur_etudiants",
            joinColumns = {@JoinColumn(name = "professeur_id",unique = false)},
            inverseJoinColumns = {@JoinColumn(name = "etudiant_id")})
            private List<Etudiant> etudiantList;

//////////////////::reunion///////////////////////:
    /*@OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Reunion reunion;*/
    ///////////////////////////////////

    ////////////////////document-prof///////////////////
    /*@OneToMany(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true)
    @JoinTable (name = "document_prof",
            joinColumns = {@JoinColumn(name = "document_id")},
            inverseJoinColumns = {@JoinColumn(name = "professeur_id")})
    private List<Document> documentList;*/
}

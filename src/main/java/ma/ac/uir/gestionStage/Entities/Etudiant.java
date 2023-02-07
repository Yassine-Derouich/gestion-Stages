package ma.ac.uir.gestionStage.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name ="Etudiant")
@DiscriminatorValue("Etudiant")
//@OnDelete(action = OnDeleteAction.CASCADE)
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name= "id")
    private int id;
    @Column(name = "nom",nullable = false)
    private String nom;
    @Column(name = "prenom",nullable = false)
    private String prenom;
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Column(name = "password",nullable = false,unique = true)
    private String password;
    @Column( name= "nomFiliere")
    private String nomFiliere;
    @Column( name= "NBniveau")
    private int NBniveau;




    /*@ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Professeur professeur;*/


   /* @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
            @JoinColumn(name="nomFiliere", referencedColumnName="nomFiliere",unique = true)
            @JoinColumn(name="NBniveau", referencedColumnName="NBniveau")
    private Niveau niveau;*/

    ////////////////:::::document - etd -////////////////////////////////
    @OneToMany(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable (name = "document_etudiant",
            joinColumns = {@JoinColumn(name = "document_id")},
            inverseJoinColumns = {@JoinColumn(name = "etudiant_id")})
    private List<Document> documentList;
}

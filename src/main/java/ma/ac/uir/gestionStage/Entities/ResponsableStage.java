package ma.ac.uir.gestionStage.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "responsableStage")
@Data
public class ResponsableStage implements Serializable {
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
/////////////etab + respo/////////////////////////
        @ToString.Exclude
        @OneToOne(mappedBy = "responsableStage", fetch = FetchType.LAZY,
                cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true)
        private Etablissement etablissement;

///////////////////////////////////////////////////////

////////////////Document + respo //////////////////
       /* @ToString.Exclude
        @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval = true)
        @JoinTable(name = "document_respo",
                joinColumns = {@JoinColumn(name = "document_id")},
                inverseJoinColumns = {@JoinColumn(name = "responsable_id")})
        private List<Document> documentList;*/
}
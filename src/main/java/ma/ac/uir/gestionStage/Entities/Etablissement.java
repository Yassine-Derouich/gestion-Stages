package ma.ac.uir.gestionStage.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "etablissement")
@Data
public class Etablissement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name= "idEtablissement")
    private int idEtablissement;

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsable_id", unique = true, nullable = true)
    //@JsonBackReference
    private ResponsableStage responsableStage;
}

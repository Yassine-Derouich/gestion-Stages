package ma.ac.uir.gestionStage.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "responsableStage")
@Data
public class ResponsableStage {
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

        @ManyToOne
        @JoinColumn(name = "role_id")
        private Role role;

        @OneToOne(mappedBy = "responsableStage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private Etablissement etablissement;

        }

package ma.ac.uir.gestionStage.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "compte")
public class Compte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name= "id")
    private int id;

    @Column(name = "nom",nullable=false)
    private String nom;

    @Column(name = "prenom",nullable=false)
    private String prenom;

    @Column(name = "email",unique=true,nullable=false)
    private String email;

    @Column(name = "password",nullable=false)
    private String password;
/*
    @Column(name = "typeCompte")
    private String typeCompte;
*/
@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
@JoinTable(
        name="users_roles",
        joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
        inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
private List<Role> roles = new ArrayList<>();




}

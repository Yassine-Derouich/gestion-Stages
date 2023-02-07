package ma.ac.uir.gestionStage.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email",unique=true,nullable=false)
    private String email;

    @Column(name = "password",nullable=false)
    private String password;



}

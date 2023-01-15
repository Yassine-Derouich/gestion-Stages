package ma.ac.uir.gestionStage.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    @OneToMany(mappedBy= "role",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Etudiant> listEtudiants;
    @OneToMany(mappedBy= "role",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ResponsableStage> responsableStageList;
}


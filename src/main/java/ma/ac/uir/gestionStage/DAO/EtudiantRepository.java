package ma.ac.uir.gestionStage.DAO;

import ma.ac.uir.gestionStage.Entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Integer> {
    Etudiant findById(int id);
    Etudiant findByEmailAndPassword(String email, String password);

}

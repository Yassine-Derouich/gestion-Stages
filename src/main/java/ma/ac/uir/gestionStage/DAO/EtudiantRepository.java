package ma.ac.uir.gestionStage.DAO;

import ma.ac.uir.gestionStage.Entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Integer> {
    Etudiant findById(int id);
    List<Etudiant> findAllById(Iterable<Integer>id);
    Etudiant findByEmailAndPassword(String email, String password);
    Etudiant findByEmail(String email);
    Etudiant findByPassword(String password);
    List<Etudiant> findByNBniveau(int nbniveau);



   // List<Etudiant> findETDSById(List<Integer> ide);
}

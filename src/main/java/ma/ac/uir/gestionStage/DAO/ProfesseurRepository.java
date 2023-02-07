package ma.ac.uir.gestionStage.DAO;

import ma.ac.uir.gestionStage.Entities.Etudiant;
import ma.ac.uir.gestionStage.Entities.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Integer> {
    Professeur findByEmailAndPassword(String email, String password);
    List<Professeur> getByIdAndEmail(int id, String email);

//    List<Professeur> findByProfByETD(Etudiant etudiant);
//    List<Professeur> findProfesseurByEtudiantList(List<Etudiant> etudiantList);
}

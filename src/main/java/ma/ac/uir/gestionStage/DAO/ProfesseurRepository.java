package ma.ac.uir.gestionStage.DAO;

import ma.ac.uir.gestionStage.Entities.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Integer> {
    Professeur findByEmailAndPassword(String email, String password);
}

package ma.ac.uir.gestionStage.DAO;

import ma.ac.uir.gestionStage.Entities.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReunionRepository extends JpaRepository<Reunion, Integer> {
    Reunion findByEtudiantId(int idE);
    List<Reunion> findByProfesseurId(int idP);
}

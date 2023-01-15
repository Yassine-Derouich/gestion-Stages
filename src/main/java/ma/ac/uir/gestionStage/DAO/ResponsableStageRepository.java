package ma.ac.uir.gestionStage.DAO;

import ma.ac.uir.gestionStage.Entities.ResponsableStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsableStageRepository extends JpaRepository<ResponsableStage,Integer> {
    ResponsableStage findResponsableStageById(int id);
    ResponsableStage findByEmailAndPassword(String email, String password);
}

package ma.ac.uir.gestionStage.DAO;

import ma.ac.uir.gestionStage.Entities.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtablissementRepository extends JpaRepository<Etablissement, Integer> {
}

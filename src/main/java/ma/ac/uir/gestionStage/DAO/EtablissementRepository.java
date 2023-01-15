package ma.ac.uir.gestionStage.DAO;

import ma.ac.uir.gestionStage.DTO.EtablissementDto;
import ma.ac.uir.gestionStage.Entities.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtablissementRepository extends JpaRepository<Etablissement, Integer> {
    Etablissement save(EtablissementDto etablissementDto);
    Etablissement findById(int id);
}

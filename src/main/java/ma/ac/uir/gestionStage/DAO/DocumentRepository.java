package ma.ac.uir.gestionStage.DAO;

import ma.ac.uir.gestionStage.Entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Integer> {
    List<Document> findDocumentsByEtudiant_Id(int idE);
    List<Document> findDocumentsByProfesseur_Id(int idP);
    List<Document> findDocumentsByResponsableStage_Id(int idP);

    @Query(value = "SELECT * FROM `document` WHERE etudiant_id", nativeQuery = true)
    List<Document> findAllByEtudiant();

    @Query(value = "SELECT * FROM `document` WHERE etudiant_id AND date_envoi", nativeQuery = true)
    List<Document> findDocumentByDateEnvoiAndEtudiant_Id();
}

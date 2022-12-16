package ma.ac.uir.gestionStage.DAO;

import ma.ac.uir.gestionStage.DTO.CompteDto;
import ma.ac.uir.gestionStage.Entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte,Integer>{

   /* @Query("SELECT email FROM Compte WHERE email = :email")
   Compte getCompteByEmail(@Param("email") String email);*/

   Compte findByEmail(String email);

}

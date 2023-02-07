package ma.ac.uir.gestionStage.DAO;

import ma.ac.uir.gestionStage.Entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByEmailAndAndPassword(String email, String password);
}

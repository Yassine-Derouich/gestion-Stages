package ma.ac.uir.gestionStage.DAO;

import ma.ac.uir.gestionStage.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

package ma.ac.uir.gestionStage.DAO;

import ma.ac.uir.gestionStage.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
   // @Query("SELECT r FROM Role r WHERE r.name = ?1")
    Role findRoleByName(String name);
    Role findRoleById(int id);
}

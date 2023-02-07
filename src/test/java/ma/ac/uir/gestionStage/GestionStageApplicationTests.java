package ma.ac.uir.gestionStage;

import ma.ac.uir.gestionStage.DAO.EtudiantRepository;
import ma.ac.uir.gestionStage.Entities.Etudiant;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class GestionStageApplicationTests {

	@Test
	void contextLoads() {
	}
	EtudiantRepository etudiantRepository ;

	@Test
	void getetdIds(){
		List<Etudiant> ids = new ArrayList<>();
		List<Etudiant> list = new ArrayList<>();
		for (int i =0; i< ids.size();i++)
			list = etudiantRepository.findAllById((Iterable<Integer>) ids.get(i));
		System.out.println("ids===================>" +list);
	}

}

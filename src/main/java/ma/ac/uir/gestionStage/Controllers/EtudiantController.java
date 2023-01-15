package ma.ac.uir.gestionStage.Controllers;

import lombok.Data;
import ma.ac.uir.gestionStage.DTO.EtudiantDto;
import ma.ac.uir.gestionStage.Exceptions.EntityNotFoundException;
import ma.ac.uir.gestionStage.Services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Data
@RequestMapping(value = "Etudiant")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;


    @PutMapping(value= "/update/{id}")
    public ResponseEntity<EtudiantDto> modifierCompte(@Valid @RequestBody() EtudiantDto etudiantDto, @PathVariable() int id) {
        etudiantDto = etudiantService.updateCompte(etudiantDto, id);
        return ResponseEntity.accepted().body(etudiantDto);
    }
    
    @GetMapping("/find/{id}")
    public ResponseEntity<EtudiantDto> FindEtudiantbyId(@PathVariable int id){
        return ResponseEntity.ok(etudiantService.findEtudiantById(id));
    }
    @CrossOrigin(origins="*")
    @GetMapping(value = "/find/All")
    public ResponseEntity<List<EtudiantDto>> recupererAllEntudiants() {
        return new ResponseEntity<>(etudiantService.findAllEtudiants(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/Login/{email}/{password}")
    public ResponseEntity<EtudiantDto> loginEtd(@PathVariable String email, @PathVariable String password){
           return new ResponseEntity<>(etudiantService.etudiantLogin(email,password),HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<EtudiantDto> supprimerETD(@PathVariable Integer id) {
        try {
            EtudiantDto etudiantDto = etudiantService.deleteEtudiant(id);
            return ResponseEntity.ok(etudiantDto);
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/All")
    public ResponseEntity<EtudiantDto> supprimerBudgets(){
        try {
            EtudiantDto etudiantDto = etudiantService.deleteAllEtudiants();
            return ResponseEntity.ok(etudiantDto);
        }catch(EntityNotFoundException en){
            en.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

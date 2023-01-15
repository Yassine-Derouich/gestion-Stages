package ma.ac.uir.gestionStage.Controllers;

import ma.ac.uir.gestionStage.DTO.ProfesseurDto;
import ma.ac.uir.gestionStage.Exceptions.EntityNotFoundException;
import ma.ac.uir.gestionStage.Services.ProfesseurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("Professeur")
public class ProfesseurController {

    private ProfesseurService professeurService;



    @PostMapping("/save")
    public ResponseEntity<ProfesseurDto> saveProf(@RequestBody ProfesseurDto professeurDto){
        return new ResponseEntity<>(professeurService.savePROF(professeurDto), HttpStatus.OK);
    }

    @PutMapping(value= "/update/{id}")
    public ResponseEntity<ProfesseurDto> modifierProf(@Valid @RequestBody() ProfesseurDto professeurDto, @PathVariable() int id) {
        professeurDto = professeurService.updateProf(professeurDto, id);
        return ResponseEntity.accepted().body(professeurDto);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProfesseurDto> FindProfById(@PathVariable int id){
        return ResponseEntity.ok(professeurService.findProfById(id));
    }
    @CrossOrigin(origins="*")
    @GetMapping(value = "/find/All")
    public ResponseEntity<List<ProfesseurDto>> recupererAllProfs() {
        return new ResponseEntity<>(professeurService.findAllProfs(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/Login/{email}/{password}")
    public ResponseEntity<ProfesseurDto> loginProf(@PathVariable String email, @PathVariable String password){
        return new ResponseEntity<>(professeurService.ProfesseurLogin(email,password),HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ProfesseurDto> supprimerProfesseur(@PathVariable Integer id) {
        try {
            ProfesseurDto professeurDto = professeurService.deleteProfesseur(id);
            return ResponseEntity.ok(professeurDto);
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/All")
    public ResponseEntity<ProfesseurDto> supprimerProfesseurs(){
        try {
            ProfesseurDto professeurDto = professeurService.deleteAllProfesseurs();
            return ResponseEntity.ok(professeurDto);
        }catch(EntityNotFoundException en){
            en.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

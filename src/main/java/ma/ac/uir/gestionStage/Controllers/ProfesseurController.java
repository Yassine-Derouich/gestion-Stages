package ma.ac.uir.gestionStage.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import ma.ac.uir.gestionStage.DTO.EtudiantDto;
import ma.ac.uir.gestionStage.DTO.ProfesseurDto;
import ma.ac.uir.gestionStage.Entities.Etudiant;
import ma.ac.uir.gestionStage.Exceptions.EntityNotFoundException;
import ma.ac.uir.gestionStage.Services.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("Professeur")
public class ProfesseurController {
   @Autowired private ProfesseurService professeurService;

    @PostMapping("/save")
    public ResponseEntity<ProfesseurDto> saveProf(@RequestBody ProfesseurDto professeurDto){
        return new ResponseEntity<>(professeurService.savePROF(professeurDto), HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/Login/{email}/{password}")
    public ResponseEntity<ProfesseurDto> LoginProf(@PathVariable String email, @PathVariable String password){
        return new ResponseEntity<>(professeurService.ProfesseurLogin(email,password),HttpStatus.ACCEPTED);
    }
    @PutMapping(value= "/update/{id}")
    public ResponseEntity<ProfesseurDto> modifierProf(@Valid @RequestBody() ProfesseurDto professeurDto, @PathVariable() int id) {
        try {
            professeurDto = professeurService.updateProf(professeurDto, id);
            return ResponseEntity.accepted().body(professeurDto);
        }catch (javax.persistence.EntityNotFoundException en){
            en.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProfesseurDto> FindProfById(@PathVariable int id){
        try {
            return ResponseEntity.ok(professeurService.findProfById(id));
        }catch (javax.persistence.EntityNotFoundException en){
            en.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin(origins="*")
    @GetMapping(value = "/find/All")
    public ResponseEntity<List<ProfesseurDto>> recupererAllProfs() {
        try {
            return new ResponseEntity<>(professeurService.findAllProfs(), HttpStatus.OK);
        }catch (javax.persistence.EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    /*@GetMapping(value = "/find/etudiants_afféctés")
    public ResponseEntity<List<ProfesseurDto>> profEtdsAffecté(String email){
        List<ProfesseurDto> lp = (List<ProfesseurDto>) professeurService.findProfbyETDs(email);
        return new ResponseEntity<>(lp,HttpStatus.OK);
    }*/


    @CrossOrigin("*")
    @PostMapping(value = "/affecter/prof_etd")
    public ResponseEntity<ProfesseurDto> affecterPROF_ETD(@RequestBody ProfesseurDto professeurDto,int idP){
        return new ResponseEntity<>(professeurService.affecterETD_PROF(professeurDto, idP),HttpStatus.OK);
    }
    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ProfesseurDto> supprimerProfesseur(@PathVariable int id) {
        try {
            ProfesseurDto professeurDto = professeurService.deleteProfesseur(id);
            return ResponseEntity.ok(professeurDto);
        }catch (javax.persistence.EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/All")
    public ResponseEntity<ProfesseurDto> supprimerAllProfesseurs(){
        try {
            ProfesseurDto professeurDto = professeurService.deleteAllProfesseurs();
            return ResponseEntity.ok(professeurDto);
        }catch(javax.persistence.EntityNotFoundException en){
            en.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

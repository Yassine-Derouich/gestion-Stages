package ma.ac.uir.gestionStage.Controllers;

import ma.ac.uir.gestionStage.DTO.EtablissementDto;
import ma.ac.uir.gestionStage.Services.EtablissementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("Etablissement")
public class Etablissement_controller {

   private final EtablissementService etablissementService;

    public Etablissement_controller(EtablissementService etablissementService){
        this.etablissementService = etablissementService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/save")
    public ResponseEntity<EtablissementDto> creerEtablissement(@RequestBody EtablissementDto etablissementDto) {
        try {
            return new ResponseEntity<>(etablissementService.saveEtablissement(etablissementDto), HttpStatus.OK);
        }catch (RuntimeException r){
            r.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin(origins="*")
    @GetMapping(value = "/find/All")
    public ResponseEntity<List<EtablissementDto>> recupererAllEtablissement() {
        try {
            return new ResponseEntity<>(etablissementService.findAllEtablissement(), HttpStatus.OK);
        }catch (javax.persistence.EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins="*")
    @GetMapping(value = "/find/{id}")
    public ResponseEntity<EtablissementDto> recupererEtablissementParId(@PathVariable int id) {
        try {
            EtablissementDto etablissementDto = etablissementService.findEtablissementById(id);
            return ResponseEntity.ok(etablissementDto);
        }catch (javax.persistence.EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<EtablissementDto> supprimerEtablissement(@PathVariable int id) {
        try {
            EtablissementDto etablissementDto = etablissementService.deleteEtablissement(id);
            return ResponseEntity.ok(etablissementDto);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/All")
    public ResponseEntity<EtablissementDto> supprimerEtablissements(){
        try {
            EtablissementDto etablissementDto = etablissementService.deleteAllEtablissement();
            return ResponseEntity.ok(etablissementDto);
        }catch (javax.persistence.EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin(origins="*")
    @PutMapping(value= "/update/{id}")
    public ResponseEntity<EtablissementDto> modifierEtablissement(@Valid @RequestBody() EtablissementDto etablissementDto, @PathVariable() int id) {
       try {
           etablissementDto = etablissementService.updateEtablissement(etablissementDto, id);
           return ResponseEntity.accepted().body(etablissementDto);
       }catch (javax.persistence.EntityNotFoundException e){
           e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }
}

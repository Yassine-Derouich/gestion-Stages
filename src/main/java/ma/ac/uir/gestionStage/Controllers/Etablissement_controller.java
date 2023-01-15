package ma.ac.uir.gestionStage.Controllers;

import ma.ac.uir.gestionStage.DTO.EtablissementDto;
import ma.ac.uir.gestionStage.Exceptions.EntityNotFoundException;
import ma.ac.uir.gestionStage.Services.EtablissementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("Etablissement")
public class Etablissement_controller {

   private EtablissementService etablissementService;

    public Etablissement_controller(EtablissementService etablissementService){
        this.etablissementService = etablissementService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/save")
    public ResponseEntity<EtablissementDto> creerEtablissement(@RequestBody EtablissementDto etablissementDto) {
        return new ResponseEntity<>(etablissementService.saveEtablissement(etablissementDto),HttpStatus.OK);
    }

    @CrossOrigin(origins="*")
    @GetMapping(value = "/find/AllEtablissements")
    public ResponseEntity<List<EtablissementDto>> recupererAllEtablissement() {
        return new ResponseEntity<>(etablissementService.findAllEtablissement(), HttpStatus.OK);
    }

    @CrossOrigin(origins="*")
    @GetMapping(value = "/find/Etablissement/{id}")
    public ResponseEntity<EtablissementDto> recupererEtablissementParId(@PathVariable int id) {
        EtablissementDto etablissementDto = etablissementService.findEtablissementById(id);
        return ResponseEntity.ok(etablissementDto);
    }
    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/Etablissement/{id}")
    public ResponseEntity<EtablissementDto> supprimerEtablissement(int id) {
        try {
            EtablissementDto etablissementDto = etablissementService.deleteEtablissement(id);
            return ResponseEntity.ok(etablissementDto);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/AllEtablissement")
    public ResponseEntity<EtablissementDto> supprimerEtablissements(){
        try {
            EtablissementDto etablissementDto = etablissementService.deleteAllEtablissement();
            return ResponseEntity.ok(etablissementDto);
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin(origins="*")
    @PutMapping(value= "/updateEtablissement/{id}")
    public ResponseEntity<EtablissementDto> modifierEtablissement(@Valid @RequestBody() EtablissementDto etablissementDto, @PathVariable() int id) {
        etablissementDto = etablissementService.updateEtablissement(etablissementDto, id);
        return ResponseEntity.accepted().body(etablissementDto);
    }
}

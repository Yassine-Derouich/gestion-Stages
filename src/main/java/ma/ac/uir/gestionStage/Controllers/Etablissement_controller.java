package ma.ac.uir.gestionStage.Controllers;

import ma.ac.uir.gestionStage.DTO.EtablissementDto;
import ma.ac.uir.gestionStage.DTO.EtablissementDto;
import ma.ac.uir.gestionStage.Services.service_impl.EtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping(value = "/save/Etablissement")
    public ResponseEntity<EtablissementDto> creerEtablissement(@RequestBody EtablissementDto etablissementDto) {
       etablissementDto = etablissementService.saveEtablissement(etablissementDto);
        return new ResponseEntity<>(etablissementDto, HttpStatus.CREATED);
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
    public String supprimerEtablissement(int id) {
        etablissementService.deleteEtablissement(id);
        return "Etablissement supprimé avec succes!";
    }
    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/AllEtablissement")
    public String supprimerEtablissementx(){
       etablissementService.deleteAllEtablissement();
        return "Etablissements supprimés avec succès !";
    }
    @CrossOrigin(origins="*")
    @PutMapping(value= "/updateEtablissement/{id}")
    public ResponseEntity<EtablissementDto> modifierEtablissement(@Valid @RequestBody() EtablissementDto etablissementDto, @PathVariable() int id) {
        etablissementDto = etablissementService.updateEtablissement(etablissementDto, id);
        return ResponseEntity.accepted().body(etablissementDto);
    }
}

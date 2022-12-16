package ma.ac.uir.gestionStage.Controllers;


import ma.ac.uir.gestionStage.DTO.EtablissementDto;
import ma.ac.uir.gestionStage.DTO.NiveauDto;
import ma.ac.uir.gestionStage.Services.service_impl.NiveauService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("niveau")
public class Niveau_controller {

    private NiveauService niveauService;

    public Niveau_controller(NiveauService niveauService){
        this.niveauService=niveauService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/saveNiveau")
    public ResponseEntity<NiveauDto> creerNiveau(@Valid @RequestBody NiveauDto niveauDto) {
        niveauDto = niveauService.saveNiveau(niveauDto);
        return new ResponseEntity<>(niveauDto, HttpStatus.CREATED);
    }

    @CrossOrigin(origins="*")
    @GetMapping(value = "/findNiveau/AllNiveaux")
    public ResponseEntity<List<NiveauDto>> recupererAllNiveaux() {
        return new ResponseEntity<>(niveauService.findAllNiveaux(), HttpStatus.OK);
    }

    @CrossOrigin(origins="*")
    @GetMapping(value = "/findNiveau/{id}")
    public ResponseEntity<NiveauDto> recupererNiveauParId(@PathVariable int id) {
        NiveauDto niveauDto = niveauService.findNiveauById(id);
        return ResponseEntity.ok(niveauDto);
    }
    ////////////////////////////
    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/deleteNiveau/{id}")
    public String supprimerNiveau(Integer id) {
        niveauService.deleteNiveau(id);
        return "Niveau supprimé avec succes!";
    }
    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/AllNiveaux")
    public String supprimerNiveaux(){
        niveauService.deleteAllNiveaux();
        return "Niveaux supprimés avec succès !";
    }
    @CrossOrigin(origins="*")
    @PutMapping(value= "/updateNiveau/{id}")
    public ResponseEntity<NiveauDto> modifierNiveau(@Valid @RequestBody() NiveauDto niveauDto, @PathVariable() int id) {
        niveauDto = niveauService.updateNiveau(niveauDto, id);
        return ResponseEntity.accepted().body(niveauDto);
    }
}

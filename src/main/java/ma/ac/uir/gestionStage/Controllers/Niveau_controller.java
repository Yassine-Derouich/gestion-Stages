package ma.ac.uir.gestionStage.Controllers;


import ma.ac.uir.gestionStage.DTO.NiveauDto;
import ma.ac.uir.gestionStage.Services.NiveauService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("Niveau")
public class Niveau_controller {

    private NiveauService niveauService;
    public Niveau_controller(NiveauService niveauService){
        this.niveauService=niveauService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/save")
    public ResponseEntity<NiveauDto> creerNiveau(@Valid @RequestBody() NiveauDto niveauDto) {
        return new ResponseEntity<>(niveauService.saveNiveau(niveauDto),HttpStatus.OK);
    }
    @CrossOrigin(origins="*")
    @PutMapping(value= "/update/{id}")
    public ResponseEntity<NiveauDto> modifierNiveau(@Valid @RequestBody() NiveauDto niveauDto, @PathVariable() int id) {
        niveauDto = niveauService.updateNiveau(niveauDto, id);
        return ResponseEntity.accepted().body(niveauDto);
    }
    @CrossOrigin(origins="*")
    @GetMapping(value = "/find/All")
    public ResponseEntity<List<NiveauDto>> recupererAllNiveaux() {
        try {
            return new ResponseEntity<>(niveauService.findAllNiveaux(), HttpStatus.OK);
        }catch(EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins="*")
    @GetMapping(value = "/find/{id}")
    public ResponseEntity<NiveauDto> recupererNiveauParId(@PathVariable int id) {
        NiveauDto niveauDto = niveauService.findNiveauById(id);
        return ResponseEntity.ok(niveauDto);
    }
    ////////////////////////////
    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<NiveauDto> supprimerNiveau(@PathVariable int id){
        try {
            NiveauDto niveauDto = (niveauService.deleteNiveau(id));
            return ResponseEntity.ok(niveauDto);
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<NiveauDto> supprimerNiveaux() {
        try {
            NiveauDto niveauDto = (niveauService.deleteAllNiveaux());
            return ResponseEntity.ok(niveauDto);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

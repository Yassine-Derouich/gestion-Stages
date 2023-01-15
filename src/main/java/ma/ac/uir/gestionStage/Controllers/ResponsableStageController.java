package ma.ac.uir.gestionStage.Controllers;

import ma.ac.uir.gestionStage.DTO.NiveauDto;
import ma.ac.uir.gestionStage.DTO.ResponsableStageDto;
import ma.ac.uir.gestionStage.DTO.ResponsableStageDto;
import ma.ac.uir.gestionStage.Entities.Professeur;
import ma.ac.uir.gestionStage.Exceptions.EntityNotFoundException;
import ma.ac.uir.gestionStage.Services.ResponsableStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("ResponsableStage")
public class ResponsableStageController {

    @Autowired
    private ResponsableStageService responsableStageService;

    @PostMapping("/save")
    public ResponseEntity<ResponsableStageDto> saveRespo(@RequestBody ResponsableStageDto responsableStageDto){
        return new ResponseEntity<>(responsableStageService.saveRESPO(responsableStageDto), HttpStatus.OK);
    }

    @PutMapping(value= "/update/{id}")
    public ResponseEntity<ResponsableStageDto> modifierRespo(@Valid @RequestBody() ResponsableStageDto responsableStageDto, @PathVariable() int id) {
        responsableStageDto = responsableStageService.updateResponsableStage(responsableStageDto, id);
        return ResponseEntity.accepted().body(responsableStageDto);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ResponsableStageDto> FindRespoById(@PathVariable int id){
        return ResponseEntity.ok(responsableStageService.findResponsableStageById(id));
    }
    @CrossOrigin(origins="*")
    @GetMapping(value = "/find/All")
    public ResponseEntity<List<ResponsableStageDto>> recupererAllRespo() {
        return new ResponseEntity<>(responsableStageService.findAllResponsableStages(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/Login/{email}/{password}")
    public ResponseEntity<ResponsableStageDto> loginProf(@PathVariable String email, @PathVariable String password){
        return new ResponseEntity<>(responsableStageService.RespoLogin(email,password),HttpStatus.ACCEPTED);
    }

    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<ResponsableStageDto> supprimerResponsableStage(@PathVariable Integer id) {
        try {
            ResponsableStageDto responsableStageDto = responsableStageService.deleteResponsableStage(id);
            return ResponseEntity.ok(responsableStageDto);
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/All")
    public ResponseEntity<ResponsableStageDto> supprimerAllResponsableStages(){
        try {
            ResponsableStageDto responsableStageDto = responsableStageService.deleteAllResponsableStages();
            return ResponseEntity.ok(responsableStageDto);
        }catch(EntityNotFoundException en){
            en.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}


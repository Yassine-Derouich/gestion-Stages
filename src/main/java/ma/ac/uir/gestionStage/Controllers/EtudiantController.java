package ma.ac.uir.gestionStage.Controllers;

import lombok.Data;
import ma.ac.uir.gestionStage.DTO.EtudiantDto;
import ma.ac.uir.gestionStage.Exceptions.EntityNotFoundException;
import ma.ac.uir.gestionStage.Exceptions.UserAlreadyExistAuthenticationException;
import ma.ac.uir.gestionStage.Services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Data
@RequestMapping(value = "Etudiant")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    @PostMapping(value = "save/uploadExcel",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveEtdFromExcel(@RequestParam("file") MultipartFile file){
        try {
            etudiantService.saveEtdsFromExcel(file);
            return ResponseEntity.ok(Map.of("Message", " Students data uploaded and saved to database successfully"));
        }catch (HttpClientErrorException.UnsupportedMediaType e){
            e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        } catch (UserAlreadyExistAuthenticationException e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping(value= "/update/{id}")
    public ResponseEntity<EtudiantDto> modifierEtudiant(@Valid @RequestBody() EtudiantDto etudiantDto, @PathVariable() int id) {
        try {
            etudiantDto = etudiantService.updateEtudiant(etudiantDto, id);
            return ResponseEntity.accepted().body(etudiantDto);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/Login/{email}/{password}")
    public ResponseEntity<EtudiantDto> LoginEtd(@PathVariable String email, @PathVariable String password){
        try {
            return new ResponseEntity<>(etudiantService.etudiantLogin(email,password),HttpStatus.ACCEPTED);
        }catch (EntityNotFoundException e ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<EtudiantDto> FindEtudiantbyId(@PathVariable int id){
        try {
            return ResponseEntity.ok(etudiantService.findEtudiantById(id));
        }catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin(origins="*")
    @GetMapping(value = "/find/All")
    public ResponseEntity<List<EtudiantDto>> recupererAllEntudiants() {
        try {
            return new ResponseEntity<>(etudiantService.findAllEtudiants(), HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<EtudiantDto> supprimerEtudiant(@PathVariable int id) {
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
    public ResponseEntity<EtudiantDto> supprimerAllEtudiants(){
        try {
            EtudiantDto etudiantDto = etudiantService.deleteAllEtudiants();
            return ResponseEntity.ok(etudiantDto);
        }catch(EntityNotFoundException en){
            en.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins ="*")
    @GetMapping(value = "/find/Niveau={nbNiveau}")
    public ResponseEntity<List<EtudiantDto>> recupererEtudiantByNiveau(@PathVariable int nbNiveau){
        try {
            List<EtudiantDto> etudiantDtoList = etudiantService.findEtudiantsByNiveau(nbNiveau);
            return ResponseEntity.ok(etudiantDtoList);
        }catch (EntityNotFoundException en){
            en.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

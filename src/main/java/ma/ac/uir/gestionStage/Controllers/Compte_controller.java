package ma.ac.uir.gestionStage.Controllers;

import ma.ac.uir.gestionStage.DTO.CompteDto;
import ma.ac.uir.gestionStage.Services.service_impl.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/compte")
public class Compte_controller {
    @Autowired
    private CompteService compteService;

    public Compte_controller (CompteService compteService){
        this.compteService=compteService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/save")
    public ResponseEntity<CompteDto> creerCompte(@Valid @RequestBody CompteDto compteDto) {
       compteDto = compteService.saveCompte(compteDto);
    return new ResponseEntity<>(compteDto, HttpStatus.CREATED);
    }

////////////////////
    @CrossOrigin(origins="*")
    @GetMapping(value = "/find/All")
    public ResponseEntity<List<CompteDto>> recupererAllComptes() {
        return new ResponseEntity<>(compteService.findAllCompte(), HttpStatus.OK);
    }
    @CrossOrigin(origins="*")
    @GetMapping(value = "/find/{id}")
    public ResponseEntity<CompteDto> recupererCompteParId(@PathVariable int id) {
        CompteDto compteDto = compteService.findCompteById(id);
        return ResponseEntity.ok(compteDto);
    }
////////////////////////////
    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/{id}")
    public String supprimerCompte(Integer id) {
        compteService.deleteCompte(id);
        return "Compte supprimé avec succes!";
    }
    @CrossOrigin(origins="*")
    @DeleteMapping(value = "/delete/All")
    public String supprimerBudgets(){
        compteService.deleteAllComptes();
        return "Comptes supprimées avec succès !";
    }
    @CrossOrigin(origins="*")
    @PutMapping(value= "/update/{id}")
    public ResponseEntity<CompteDto> modifierCompte(@Valid @RequestBody() CompteDto compteDto, @PathVariable() int id) {
        compteDto = compteService.updateCompte(compteDto, id);
        return ResponseEntity.accepted().body(compteDto);
    }





















/*
    @CrossOrigin(origins="*")
    @PostMapping(value= "/Login")
    public ResponseEntity<CompteDto> login(@RequestBody String email, @RequestBody String password) {
        CompteDto compteDto = compteService.login(email,password);

        if(compteDto.getTypeCompte()=="admin"){
            System.out.println("admin ok");
            //return new Admin_controller();
        }
        return ResponseEntity.ok(compteDto);

    }

    @CrossOrigin(origins="*")
    @PostMapping(value= "/Login")
    public Admin_controller loginADMIN(@PathVariable String email, @PathVariable String password) {
        CompteDto compteDto = compteService.login(email,password);
        Compte compte new ;
        if(compte.getTypeCompte()=="admin"){
            System.out.println("admin ok");
        }
        return new Admin_controller();
    }


    @CrossOrigin(origins="*")
    @GetMapping(value= "/GetTypeCompte")
    public String typeCompte(@PathVariable String email, @PathVariable String password) {
        String typeCompte = compteService.getTypeCompte(email,password);
        return typeCompte;
    }

    @CrossOrigin(origins="*")
    @GetMapping(value = "/find/type/{typeCompte}")
    public ResponseEntity<List<CompteDto>> getTYPECOMPTE(@PathVariable("typeCompte") String typeCompte) {
        List<CompteDto> listCompte = compteService.findCompteByType(typeCompte);
        return new ResponseEntity<>(listCompte, HttpStatus.OK);
    }
*/
}

package ma.ac.uir.gestionStage.Controllers;

import lombok.Data;
import ma.ac.uir.gestionStage.DTO.ReunionDto;
import ma.ac.uir.gestionStage.Services.ReunionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@Data
@RequestMapping("Reunion")
public class ReunionController {

    @Autowired
    private ReunionService reunionService;

    @PostMapping("/organiser/{idE}/{idP}")
    public ResponseEntity<ReunionDto> organiserReunion(@RequestBody ReunionDto reunionDto,@PathVariable int idE,@PathVariable int idP){
        return ResponseEntity.ok(reunionService.organiserReunion(reunionDto,idE,idP));
    }

    @GetMapping(value = "/findByETD/{idE}")
    public ResponseEntity<ReunionDto> recupererReunionByETD(@PathVariable("idE") int idE){
        try {
            return ResponseEntity.ok(reunionService.findReunionByETD(idE));
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/findByPROF/{idP}")
    public ResponseEntity<List<ReunionDto>> recupererReunionByPROF(@PathVariable("idP") int idP){
        try {
            return ResponseEntity.ok(reunionService.findReunionByPROF(idP));
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

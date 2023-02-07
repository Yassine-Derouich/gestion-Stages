package ma.ac.uir.gestionStage.Controllers;

import lombok.Data;
import ma.ac.uir.gestionStage.DTO.ResponseData;
import ma.ac.uir.gestionStage.Entities.Document;
import ma.ac.uir.gestionStage.Services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@RestController
@Data
@RequestMapping("Document")
public class DocumentController {
    @Autowired private DocumentService documentService;
    public static String fn, ft;

    @CrossOrigin(origins="*")
    @PostMapping(value = "/upload/file/by={ID}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Document> uploadDoc(@PathVariable int ID , @RequestPart("file")MultipartFile file){
        try{
            System.out.println("Filename = "+ file.getOriginalFilename()+ "filetype= "+file.getContentType());
            Document document = documentService.save(ID, file);
            String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")/*.path(document.getFileName())*/
                    .path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();

            new ResponseData(file.getOriginalFilename(), downloadURl, file.getContentType(), file.getSize());
            System.out.println("Uploaded the file successfully: " + file.getOriginalFilename());

            fn = file.getOriginalFilename();
            ft = file.getContentType();

            return new ResponseEntity<>(document,HttpStatus.OK);
        }catch (RuntimeException e ){
            String msg= "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            System.out.println(msg);
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins="*")
    @GetMapping("/download/{idF}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int idF) throws Exception {
        try {
            Document document = documentService.downloadFile(idF);
            return  ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(document.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + document.getFileName()
                                    + "\"")
                    .body(new ByteArrayResource(document.getFichier()));
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins="*")
    @GetMapping(value = "/find/{idF}")
    public ResponseEntity<Document> findDocumentById(@PathVariable int idF) {
        try {
            return new ResponseEntity<>(documentService.downloadFile(idF), HttpStatus.OK);
        }catch(EntityNotFoundException e ){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins="*")
    @GetMapping(value = "/find/By/ETD{idE}")
    public ResponseEntity<List<Document>> findDocumentByETD(@PathVariable("idE") int idE){
        try{
            return new ResponseEntity<>(documentService.findDocByETD(idE), HttpStatus.OK);
        }catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins="*")
    @GetMapping(value = "/find/By/PROF{idP}")
    public ResponseEntity<List<Document>> findDocumentByPROF(@PathVariable("idP") int idP){
        try{
            return new ResponseEntity<>(documentService.findDocByPROF(idP), HttpStatus.OK);
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @CrossOrigin(origins="*")
    @GetMapping(value = "/find/By/Respo{idP}")
    public ResponseEntity<List<Document>> findDocumentByRESPO(@PathVariable("idR") int idR){
        try{
            return new ResponseEntity<>(documentService.findDocByRESPO(idR), HttpStatus.OK);
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/find/all/by/etudiants")
    public ResponseEntity<List<Document>> findAllDocumentByETD(){
        try{
            return new ResponseEntity<>(documentService.findDocumentByEtudiant(), HttpStatus.OK);
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/find/all/by/etudians/dateEnvoi")
    public ResponseEntity<List<Document>> findAllDocumentByETDDateEnvoi(){
        try{
            return new ResponseEntity<>(documentService.findDocumentByEtdDateEnvoi(), HttpStatus.OK);
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

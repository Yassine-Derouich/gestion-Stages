package ma.ac.uir.gestionStage.Services;

import lombok.Data;
import ma.ac.uir.gestionStage.DAO.*;
import ma.ac.uir.gestionStage.Entities.Document;
import ma.ac.uir.gestionStage.Entities.Etudiant;
import ma.ac.uir.gestionStage.Entities.Professeur;
import ma.ac.uir.gestionStage.Entities.ResponsableStage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class DocumentService {
    @Autowired private DocumentRepository documentRepository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private EtudiantRepository etudiantRepository;
    @Autowired private ProfesseurRepository professeurRepository;
    @Autowired private ResponsableStageRepository responsableStageRepository;
    //public static String filename,filetype;

    @Transactional
    public Document save(int ID, MultipartFile file) {
        Document document = new Document();
        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            String fileType=file.getContentType();

            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
            document.setDateEnvoi(sqlDate);

                if (fileName.contains("..")) // Check if the file's name contains invalid characters
                    throw new FileSystemException("Sorry! Filename contains invalid path sequence " + fileName);
                document.setFileName(fileName);
                document.setFileType(fileType);
                document.setFichier(file.getBytes());

            Etudiant eID = etudiantRepository.findById(ID);
            document.setEtudiant(eID);

            Optional<Professeur> pID = professeurRepository.findById(ID);
            document.setProfesseur(pID.get());

            Optional<ResponsableStage> rID = responsableStageRepository.findById(ID);
            document.setResponsableStage(rID.get());

        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
        }
        document = documentRepository.save(document);
        return modelMapper.map((Object) document, (Type) Document.class);
    }

    @Transactional
    public Document downloadFile(int idF){
        Optional<Document> document = documentRepository.findById(idF);
        if(document.isPresent()){
            return modelMapper.map(document, Document.class);
        }else{
            throw new EntityNotFoundException("document not found with Id: " + idF);
        }
    }

    @Transactional
    public List<Document> findDocByETD(int idE){
        List<Document> lde = documentRepository.findDocumentsByEtudiant_Id(idE);
        if(!lde.isEmpty()){
        return lde.stream().map(element -> modelMapper.map(element, Document.class))
                    .collect(Collectors.toList());
        }else{
            throw new EntityNotFoundException("File not found with the given ID");
        }
    }
    @Transactional
    public List<Document> findDocByPROF(int idP){
        List<Document> ldp = documentRepository.findDocumentsByProfesseur_Id(idP);
        if(!ldp.isEmpty()) {
            return ldp.stream().map(element -> modelMapper.map(element, Document.class))
                        .collect(Collectors.toList());
            }else{
                throw new EntityNotFoundException("File not found with the given ID");
        }
    }

    @Transactional
    public List<Document> findDocByRESPO(int idR){
        List<Document> ldr = documentRepository.findDocumentsByResponsableStage_Id(idR);
        if(!ldr.isEmpty()) {
            return ldr.stream().map(element -> modelMapper.map(element, Document.class))
                    .collect(Collectors.toList());
        }else {
            throw new EntityNotFoundException("File not found with the given ID");
        }
    }


    @Transactional
    public List<Document> findDocumentByEtudiant(){
        return documentRepository.findAllByEtudiant()
                .stream().map(element -> modelMapper.map(element, Document.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Document> findDocumentByEtdDateEnvoi(){
        return documentRepository.findDocumentByDateEnvoiAndEtudiant_Id()
                .stream().map(element -> modelMapper.map(element, Document.class))
                .collect(Collectors.toList());
    }
}

package ma.ac.uir.gestionStage.Services;


import lombok.Data;
import ma.ac.uir.gestionStage.DAO.EtudiantRepository;
import ma.ac.uir.gestionStage.DTO.EtudiantDto;
import ma.ac.uir.gestionStage.DTO.NiveauDto;
import ma.ac.uir.gestionStage.Entities.Etudiant;
import ma.ac.uir.gestionStage.Exceptions.UserAlreadyExistAuthenticationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class EtudiantService {

   @Autowired private ModelMapper modelMapper;
    @Autowired private EtudiantRepository etudiantRepository;
    private Etudiant etudiant;
    @Transactional
    public void saveEtdsFromExcel(MultipartFile file) throws UserAlreadyExistAuthenticationException {
        if(ExcelUploadService.isValidExcelFile(file)){
            try {
                List<Etudiant> etudiantList = ExcelUploadService.getEtdsDataFromExcel(file.getInputStream());
                   etudiantRepository.saveAll(etudiantList);
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }catch (DuplicateKeyException d){
                throw new UserAlreadyExistAuthenticationException("user credentials already exists in DB");
            }
        }
    }
    @Transactional
    public EtudiantDto updateEtudiant(EtudiantDto etudiantDto, int id) throws EntityNotFoundException {//UPDATE
        Optional<Etudiant> etdOptional = Optional.ofNullable(etudiantRepository.findById(id));
        if (etdOptional.isPresent()) {
            Etudiant etudiant = modelMapper.map(etudiantDto, Etudiant.class);
            etudiant.setId(id);
            Etudiant updated = etudiantRepository.save(etudiant);
            return modelMapper.map(updated, EtudiantDto.class);
        } else {
            throw new EntityNotFoundException("introuvable");
        }
    }
    
    @Transactional
    public EtudiantDto findEtudiantById(int id) {
        etudiant = etudiantRepository.findById(id);
        System.out.println("etudiant: " + etudiant);
        return modelMapper.map(etudiant, EtudiantDto.class);
    }

    @Transactional
    public List<EtudiantDto> findAllEtudiants(){
        return etudiantRepository.findAll()
                .stream().map(element -> modelMapper.map(element, EtudiantDto.class))
                .collect(Collectors.toList());
    }
    @Transactional
    public EtudiantDto deleteEtudiant(int id) {          //DELETE ID
        Optional<Etudiant> etudiant = Optional.ofNullable(etudiantRepository.findById(id));
        if(!etudiant.isPresent()){
            throw new EntityNotFoundException("etudiant not found");
        }
        else {
            etudiantRepository.deleteById(id);
            return modelMapper.map(etudiant, EtudiantDto.class);

        }
    }

    @Transactional
    public EtudiantDto deleteAllEtudiants() {            //DELETE ALL
        List<Etudiant> etudiant = etudiantRepository.findAll();
        if(etudiant.isEmpty()){
            throw new EntityNotFoundException("liste esy vide");
        }
        else {
            etudiantRepository.deleteAll();
            return modelMapper.map((Object) etudiant, (Type) EtudiantDto.class);
        }
    }

    @Transactional
    public EtudiantDto etudiantLogin(String email, String password){
        if(email != null && password !=null)
            etudiant = etudiantRepository.findByEmailAndPassword(email,password);
        return modelMapper.map(etudiant,EtudiantDto.class);

    }

    @Transactional
    public List<EtudiantDto> findEtudiantsByNiveau(int nbNiveau){
       List<Etudiant> etudiantList = etudiantRepository.findByNBniveau(nbNiveau);
        if(!etudiantList.isEmpty()){
            return etudiantList.stream().map(element -> modelMapper.map(element, EtudiantDto.class))
                    .collect(Collectors.toList());
        }else {
            throw new EntityNotFoundException("introuvable");
        }
    }


    
}



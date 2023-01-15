package ma.ac.uir.gestionStage.Services;

import ma.ac.uir.gestionStage.DAO.ResponsableStageRepository;
import ma.ac.uir.gestionStage.DAO.RoleRepository;
import ma.ac.uir.gestionStage.DTO.EtudiantDto;
import ma.ac.uir.gestionStage.DTO.NiveauDto;
import ma.ac.uir.gestionStage.DTO.ProfesseurDto;
import ma.ac.uir.gestionStage.DTO.ResponsableStageDto;
import ma.ac.uir.gestionStage.Entities.Professeur;
import ma.ac.uir.gestionStage.Entities.ResponsableStage;
import ma.ac.uir.gestionStage.Entities.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResponsableStageService {

    private ResponsableStage responsableStage;
    private Role role;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired private ResponsableStageRepository responsableStageRepository;
    @Autowired private RoleRepository roleRepository;

    @Transactional
    public ResponsableStageDto saveRESPO(ResponsableStageDto responsableStageDto){
        ma.ac.uir.gestionStage.Entities.ResponsableStage responsableStage = new ResponsableStage();
        responsableStage.setNom(responsableStageDto.getNom());
        responsableStage.setPrenom(responsableStageDto.getPrenom());
        responsableStage.setEmail(responsableStageDto.getEmail());
        responsableStage.setPassword(responsableStageDto.getPassword());

        int roleID = responsableStageDto.getIdRole();
        Role rID = roleRepository.findRoleById(roleID);
        if(rID != null) {
            responsableStage.setRole(rID);
        }
        responsableStage = responsableStageRepository.save(responsableStage);
        return modelMapper.map(responsableStage,ResponsableStageDto.class);
    }

    @Transactional
    public ResponsableStageDto updateResponsableStage(ResponsableStageDto responsableStageDto, int id) throws EntityNotFoundException {            //UPDATE

        Optional<ResponsableStage> compteOptional = Optional.ofNullable(responsableStageRepository.findResponsableStageById(id));
        if (compteOptional != null) {
            ResponsableStage responsableStage = modelMapper.map(responsableStageDto, ResponsableStage.class);
            responsableStage.setId(id);
            ResponsableStage updated = responsableStageRepository.save(responsableStage);
            return modelMapper.map(updated, ResponsableStageDto.class);
        } else {
            System.out.println("introuvable");
            return null;
        }
    }

    @Transactional
    public ResponsableStageDto findResponsableStageById(int id) {
        ResponsableStage responsableStage = responsableStageRepository.findResponsableStageById(id);
        System.out.println("responsableStage: " + responsableStage);
        return modelMapper.map(responsableStage, ResponsableStageDto.class);
    }

    @Transactional
    public List<ResponsableStageDto> findAllResponsableStages(){
        return responsableStageRepository.findAll()
                .stream().map(element -> modelMapper.map(element, ResponsableStageDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponsableStageDto deleteResponsableStage (Integer id)  {
        Optional<ResponsableStage>  responsableStage = responsableStageRepository.findById(id);
        if (responsableStage.isEmpty()) {
            throw new EntityNotFoundException("Niveau not found");
        } else {
            responsableStageRepository.deleteById(id);
            return modelMapper.map(responsableStage, (Type) ResponsableStageDto.class);
        }
    }

    @Transactional
    public ResponsableStageDto deleteAllResponsableStages()  {
        List<ResponsableStage> responsableStage = responsableStageRepository.findAll();
        if (responsableStage.isEmpty()) {
            throw new EntityNotFoundException("Niveaux not found");
        } else {
            responsableStageRepository.deleteAll();
            return modelMapper.map((Object) responsableStage, (Type) ResponsableStageDto.class);
        }

    }

    @Transactional
    public ResponsableStageDto RespoLogin(String email, String password){
        ResponsableStageDto responsableStageDto = new ResponsableStageDto();
        int idR = responsableStageDto.getIdRole();
        if(email != null && password !=null)
            responsableStage = responsableStageRepository.findByEmailAndPassword(email,password);
        return modelMapper.map((Object) responsableStage, (Type) EtudiantDto.class);

    }


}

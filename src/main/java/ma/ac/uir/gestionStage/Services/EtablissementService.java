package ma.ac.uir.gestionStage.Services;

import lombok.Data;
import ma.ac.uir.gestionStage.DAO.EtablissementRepository;
import ma.ac.uir.gestionStage.DAO.ResponsableStageRepository;
import ma.ac.uir.gestionStage.DTO.EtablissementDto;
import ma.ac.uir.gestionStage.DTO.NiveauDto;
import ma.ac.uir.gestionStage.Entities.Etablissement;
import ma.ac.uir.gestionStage.Entities.Niveau;
import ma.ac.uir.gestionStage.Entities.ResponsableStage;
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
@Data
public class EtablissementService{
    @Autowired
    private  EtablissementRepository etablissementRepository;
    private static ModelMapper modelMapper;
    @Autowired
    private ResponsableStageRepository responsableStageRepository;

    public EtablissementService(EtablissementRepository etablissementRepository, ModelMapper modelMapper) {
        this.etablissementRepository = etablissementRepository;
        this.modelMapper = modelMapper;
    }

    
    public EtablissementDto saveEtablissement(EtablissementDto etablissementDto) {
        Etablissement etablissement = new Etablissement();
        etablissement.setIdEtablissement(etablissementDto.getIdEtablissement());
        etablissement.setNom(etablissementDto.getNom());
        etablissement.setDescription(etablissementDto.getDescription());
        int respoID = etablissementDto.getIdRespo();
        ResponsableStage rID = responsableStageRepository.findResponsableStageById(respoID);
        if(rID != null) {
            etablissement.setResponsableStage(rID);
        }
        etablissement = etablissementRepository.save(etablissement);
        return modelMapper.map(etablissement,EtablissementDto.class);
    }

    
    public EtablissementDto updateEtablissement(EtablissementDto etablissementDto, int id) {
        Optional<Etablissement> etablissementOptional = Optional.ofNullable(etablissementRepository.findById(id));
        if (etablissementOptional.isPresent()) {
            Etablissement etablissement = modelMapper.map(etablissementDto, Etablissement.class);
            etablissement.setIdEtablissement(id);
            Etablissement updated = etablissementRepository.save(etablissement);
            return modelMapper.map(updated, EtablissementDto.class);
        } else {
            System.out.println("introuvable");
            return null;
    }
    }

    
    public EtablissementDto findEtablissementById(int id)throws EntityNotFoundException{
            Etablissement etablissement = etablissementRepository.findById(id);
            return modelMapper.map(etablissement,EtablissementDto.class);
    }

   
    public List<EtablissementDto> findAllEtablissement() {
        return etablissementRepository.findAll()
                .stream().map(element -> modelMapper.map(element, EtablissementDto.class))
                .collect(Collectors.toList());
    }

    
    public EtablissementDto deleteEtablissement(int id) {
        Optional<Etablissement> etablissment = Optional.ofNullable(etablissementRepository.findById(id));
        if(!etablissment.isPresent()){
            throw new EntityNotFoundException("Niveau not found");
        }
        else {
            etablissementRepository.deleteById(id);
            return modelMapper.map((Object) etablissment, (Type) NiveauDto.class);
        }
    }
    
    public EtablissementDto deleteAllEtablissement() {
        List<Etablissement> etablissment = etablissementRepository.findAll();
        if(!etablissment.isEmpty()){
            throw new EntityNotFoundException("la liste des établissements est vide");
        }
        else {
            etablissementRepository.deleteAll();
            return modelMapper.map((Object) etablissment, (Type) NiveauDto.class);
        }
    }
    
}

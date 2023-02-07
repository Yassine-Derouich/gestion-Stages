package ma.ac.uir.gestionStage.Services;

import lombok.Data;
import ma.ac.uir.gestionStage.DAO.EtablissementRepository;
import ma.ac.uir.gestionStage.DAO.ResponsableStageRepository;
import ma.ac.uir.gestionStage.DTO.EtablissementDto;
import ma.ac.uir.gestionStage.DTO.EtablissementDto;
import ma.ac.uir.gestionStage.Entities.Etablissement;
import ma.ac.uir.gestionStage.Entities.ResponsableStage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
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

    @Transactional
    public EtablissementDto saveEtablissement(EtablissementDto etablissementDto) throws EntityNotFoundException, RuntimeException {
        Etablissement etablissement = new Etablissement();
        etablissement.setIdEtablissement(etablissementDto.getIdEtablissement());
        etablissement.setNom(etablissementDto.getNom());
        etablissement.setDescription(etablissementDto.getDescription());

        int respoID = etablissementDto.getIdResponsable();
        ResponsableStage rID = responsableStageRepository.findResponsableStageById(respoID);
        Optional<Etablissement> rsIDexist = etablissementRepository.findEtablissementByResponsableStageId(respoID);

       if(rID!=null && !rsIDexist.isPresent()) {
            etablissement.setResponsableStage(rID);
        }else if (rsIDexist.isPresent()){             // CHECK IF RESPO ID ALREADY EXIST
            throw new RuntimeException("this RESPO ID already exist! ");
        }else{
            throw new EntityNotFoundException("ID RESPO NOT FOUND !");
        }
        etablissement = etablissementRepository.save(etablissement);
        return modelMapper.map(etablissement,EtablissementDto.class);
    }

    @Transactional
    public EtablissementDto updateEtablissement(EtablissementDto etablissementDto, int id) {
        Optional<Etablissement> etablissementOptional = Optional.ofNullable(etablissementRepository.findById(id));
        if (etablissementOptional.isPresent()) {
            Etablissement etablissement = modelMapper.map(etablissementDto, Etablissement.class);
            etablissement.setIdEtablissement(id);

            int respoID = etablissementDto.getIdResponsable();
            ResponsableStage rID = responsableStageRepository.findResponsableStageById(respoID);
            Optional<Etablissement> rsIDexist = etablissementRepository.findEtablissementByResponsableStageId(respoID);

            if(rID!=null && !rsIDexist.isPresent()) {
                etablissement.setResponsableStage(rID);
            }else if (rsIDexist.isPresent()){             // CHECK IF RESPO ID ALREADY EXIST
                throw new RuntimeException("this RESPO ID already exist! ");
            }else{
                throw new EntityNotFoundException("ID RESPO NOT FOUND !");
            }
            Etablissement updated = etablissementRepository.save(etablissement);

            return modelMapper.map(updated, EtablissementDto.class);
        } else {
            throw new EntityNotFoundException("Etab not found !");
        }
    }

    @Transactional
    public EtablissementDto findEtablissementById(int id)throws EntityNotFoundException{
            Etablissement etablissement = etablissementRepository.findById(id);
            return modelMapper.map(etablissement,EtablissementDto.class);
    }

    @Transactional
    public List<EtablissementDto> findAllEtablissement() throws EntityNotFoundException{

        return etablissementRepository.findAll()
                .stream().map(element -> modelMapper.map(element, EtablissementDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public EtablissementDto deleteEtablissement(int id) {
        Optional<Etablissement> etablissement = Optional.ofNullable(etablissementRepository.findById(id));
        if(!etablissement.isPresent()){
            throw new EntityNotFoundException("Etab not found");
        } else {
            etablissementRepository.deleteById(id);
            return modelMapper.map(etablissement, EtablissementDto.class);
        }
    }
    @Transactional
    public EtablissementDto deleteAllEtablissement() {
        List<Etablissement> etablissement = etablissementRepository.findAll();
        if(!etablissement.isEmpty()){
            etablissementRepository.deleteAll();
            return modelMapper.map(etablissement, EtablissementDto.class);
        } else {
            throw new EntityNotFoundException("la liste des établissements est vide");
        }
    }
    
}

package ma.ac.uir.gestionStage.Services;

import lombok.Data;
import ma.ac.uir.gestionStage.DAO.EtudiantRepository;
import ma.ac.uir.gestionStage.DAO.ProfesseurRepository;
import ma.ac.uir.gestionStage.DAO.ResponsableStageRepository;
import ma.ac.uir.gestionStage.DAO.ReunionRepository;
import ma.ac.uir.gestionStage.DTO.EtudiantDto;
import ma.ac.uir.gestionStage.DTO.ReunionDto;
import ma.ac.uir.gestionStage.Entities.Etudiant;
import ma.ac.uir.gestionStage.Entities.Professeur;
import ma.ac.uir.gestionStage.Entities.Reunion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Table;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Service
public class ReunionService {
    @Autowired private ReunionRepository reunionRepository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private EtudiantRepository etudiantRepository;
    @Autowired private ProfesseurRepository professeurRepository;

    @Transactional
    public ReunionDto organiserReunion(ReunionDto reunionDto, int idE, int idP){
         Reunion reunion = new Reunion();
        reunion.setIdReunion(reunionDto.getIdReunion());
        reunion.setLibelle(reunionDto.getLibelle());
        reunion.setSalle(reunionDto.getSalle());
        reunion.setDate(reunionDto.getDate());

        int etdID = idE;
        Etudiant eID = etudiantRepository.findById(etdID);

        int profID = idP;
        Optional<Professeur> pID = professeurRepository.findById(profID);

        if((etdID!=0 && eID==null)|| (profID!=0 && pID.isPresent())) {
            reunion.setEtudiant(eID);
            reunion.setProfesseur(pID.get());
        }else {
            throw new RuntimeException("ERROR");
        }
        reunion = reunionRepository.save(reunion);
        return modelMapper.map(reunion, ReunionDto.class);
    }

    @Transactional
    public ReunionDto findReunionByETD(int idE){
        Reunion reunion = reunionRepository.findByEtudiantId(idE);
        return modelMapper.map((Object) reunion, (Type) ReunionDto.class);
    }
    @Transactional
    public List<ReunionDto> findReunionByPROF(int idP){
        List<Reunion> reunionList = reunionRepository.findByProfesseurId(idP);
        if(!reunionList.isEmpty()){
            return reunionList.stream().map(element -> modelMapper.map(element, ReunionDto.class))
                    .collect(Collectors.toList());
        }else {
            throw new EntityNotFoundException("introuvable");
        }
    }

}

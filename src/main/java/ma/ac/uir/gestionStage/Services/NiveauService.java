package ma.ac.uir.gestionStage.Services;

import lombok.Data;
import ma.ac.uir.gestionStage.DAO.NiveauRepository;
import ma.ac.uir.gestionStage.DTO.NiveauDto;
import ma.ac.uir.gestionStage.Entities.Niveau;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NiveauService {

    @Autowired private NiveauRepository niveauRepository;
    @Autowired private ModelMapper modelMapper;

    @Transactional
    public NiveauDto saveNiveau(NiveauDto niveauDto){            //CREATE
        Niveau niveau = new Niveau();
        niveau.setIdNiveau(niveauDto.getIdNiveau());
        niveau.setNomFiliere(niveauDto.getNomFiliere());
        niveau.setNBniveau(niveauDto.getNBniveau());
        niveau = niveauRepository.save(niveau);
        return modelMapper.map(niveau, NiveauDto.class);
    }

    @Transactional
    public NiveauDto updateNiveau(NiveauDto niveauDto, int id) {            //UPDATE
        Optional<Niveau> niveauOptional = niveauRepository.findById(id);
        if (niveauOptional.isPresent()) {
            Niveau niveau = modelMapper.map(niveauDto, Niveau.class);
            niveau.setIdNiveau(id);
            Niveau updated = niveauRepository.save(niveau);
            return modelMapper.map(updated, NiveauDto.class);
        } else {
            System.out.println("introuvable");
            return null;
        }
    }

    @Transactional
    public NiveauDto findNiveauById(int id) {//GET ID
        Niveau niveau= niveauRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Niveau introuvable !!"));
        return modelMapper.map(niveau,NiveauDto.class);    }

    @Transactional(readOnly = true)
    public List<NiveauDto> findAllNiveaux() {            //GET ALL
        List<Niveau> niveau = niveauRepository.findAll();
        if(niveau.isEmpty()){
            throw new EntityNotFoundException("Liste des niveaux est vide!");
        }else {
            return niveauRepository.findAll()
                    .stream().map(element -> modelMapper.map(element, NiveauDto.class))
                    .collect(Collectors.toList());
        }
    }

    @Transactional
    public NiveauDto deleteNiveau(int id){
        Optional<Niveau> niveau = niveauRepository.findById(id);
        if(!niveau.isPresent()){
            throw new EntityNotFoundException("Niveau not found");
        }
        else {
            niveauRepository.deleteById(id);
            return modelMapper.map(niveau, NiveauDto.class);
        }
    }
    @Transactional
    public NiveauDto deleteAllNiveaux(){
        List<Niveau> niveau = niveauRepository.findAll();
        if(niveau.isEmpty()){
            throw new EntityNotFoundException("Niveaux not found");
        }
        else {
            niveauRepository.deleteAll();
            return modelMapper.map(niveau, NiveauDto.class);
        }
    }




}

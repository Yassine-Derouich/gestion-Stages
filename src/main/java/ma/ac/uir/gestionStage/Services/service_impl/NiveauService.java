package ma.ac.uir.gestionStage.Services.service_impl;

import ma.ac.uir.gestionStage.DAO.NiveauRepository;
import ma.ac.uir.gestionStage.DTO.NiveauDto;
import ma.ac.uir.gestionStage.Entities.Niveau;
import ma.ac.uir.gestionStage.Services.service_interfaces.Niveau_interface;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NiveauService implements Niveau_interface {

    private static NiveauRepository niveauRepository;
    private static ModelMapper modelMapper;

    public NiveauService(NiveauRepository niveauRepository, ModelMapper modelMapper) {
        this.niveauRepository = niveauRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public NiveauDto saveNiveau(NiveauDto niveauDto){            //CREATE
        Niveau niveau = new Niveau();
        niveau.setIdNiveau(niveauDto.getIdNiveau());
        niveau.setNomFiliere(niveauDto.getNomFiliere());
        niveau.setNiveau(niveauDto.getNiveau());
        Niveau saved = niveauRepository.save(niveau);
        return modelMapper.map(saved, NiveauDto.class);
    }

    @Transactional
    @Override
    public NiveauDto updateNiveau(NiveauDto niveauDto, int id) {            //UPDATE

        Optional<Niveau> niveauOptional = niveauRepository.findById(id);
        if (niveauOptional != null) {
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
    @Override
    public NiveauDto findNiveauById(int id) {       //GET ID
        Niveau niveau= niveauRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Niveau introuvable !!"));
        System.out.println("niveau: "+niveau);
        return modelMapper.map(niveau,NiveauDto.class);    }

    @Transactional(readOnly = true)
    @Override
    public List<NiveauDto> findAllNiveaux() {            //GET ALL
        return niveauRepository.findAll()
                .stream().map(element -> modelMapper.map(element, NiveauDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteNiveau(int id) {          //DELETE ID
        niveauRepository.deleteById(id);
    }
    @Transactional
    @Override
    public void deleteAllNiveaux() {            //DELETE ALL
        niveauRepository.deleteAll();
    }

}

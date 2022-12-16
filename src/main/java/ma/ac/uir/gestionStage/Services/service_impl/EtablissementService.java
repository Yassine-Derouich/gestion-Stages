package ma.ac.uir.gestionStage.Services.service_impl;

import ma.ac.uir.gestionStage.DAO.EtablissementRepository;
import ma.ac.uir.gestionStage.DTO.EtablissementDto;
import ma.ac.uir.gestionStage.DTO.NiveauDto;
import ma.ac.uir.gestionStage.Entities.Etablissement;
import ma.ac.uir.gestionStage.Entities.Niveau;
import ma.ac.uir.gestionStage.Services.service_interfaces.Etablissement_interface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class EtablissementService implements Etablissement_interface {
@Autowired
    private static EtablissementRepository etablissementRepository;
    private static ModelMapper modelMapper;

    public EtablissementService(EtablissementRepository etablissementRepository, ModelMapper modelMapper) {
        this.etablissementRepository = etablissementRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EtablissementDto saveEtablissement(EtablissementDto etablissementDto) {
        Etablissement etablissement = new Etablissement();
        etablissement.setIdEtablissement(etablissementDto.getIdEtablissement());
        etablissement.setNom(etablissementDto.getNom());
        etablissement.setDescription(etablissementDto.getDescription());
        Etablissement saved = etablissementRepository.save(etablissement);
        return modelMapper.map(saved,EtablissementDto.class);
    }

    @Override
    public EtablissementDto updateEtablissement(EtablissementDto etablissementDto, int id) {
        Optional<Etablissement> etablissementOptional = etablissementRepository.findById(id);
        if (etablissementOptional != null) {
            Etablissement etablissement = modelMapper.map(etablissementDto, Etablissement.class);
            etablissement.setIdEtablissement(id);
            Etablissement updated = etablissementRepository.save(etablissement);
            return modelMapper.map(updated, EtablissementDto.class);
        } else {
            System.out.println("introuvable");
            return null;
    }
    }

    @Override
    public EtablissementDto findEtablissementById(int id){
            Etablissement etablissement = etablissementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("etablissement introuvable !!"));
            return modelMapper.map(etablissement,EtablissementDto.class);
    }

    @Override
    public List<EtablissementDto> findAllEtablissement() {
        return etablissementRepository.findAll()
                .stream().map(element -> modelMapper.map(element, EtablissementDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEtablissement(int id) {
    etablissementRepository.deleteById(id);
    }

    @Override
    public void deleteAllEtablissement() {
        etablissementRepository.deleteAll();
    }
}

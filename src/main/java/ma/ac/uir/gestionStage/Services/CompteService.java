package ma.ac.uir.gestionStage.Services;

import lombok.AllArgsConstructor;
import ma.ac.uir.gestionStage.DAO.CompteRepository;
import ma.ac.uir.gestionStage.DAO.RoleRepository;
import ma.ac.uir.gestionStage.DTO.CompteDto;
import ma.ac.uir.gestionStage.Entities.Compte;
import ma.ac.uir.gestionStage.Entities.Role;
import ma.ac.uir.gestionStage.Services.service_interfaces.Compte_interface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompteService implements Compte_interface {

    private CompteRepository compteRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    //private PasswordEncoder passwordEncoder;
    @Transactional
    public CompteDto saveCompte(CompteDto compteDto) {            //CREATE
        Compte compte = new Compte();
        compte.setId(compteDto.getId());
        compte.setNom(compteDto.getNom());
        compte.setPrenom(compteDto.getPrenom());
        compte.setEmail(compteDto.getEmail());
       // compte.setPassword(passwordEncoder.encode(compteDto.getPassword()));
        compte.setPassword(compteDto.getPassword());
        Compte saved = compteRepository.save(compte);
        return modelMapper.map(saved, CompteDto.class);
    }
    @Transactional
    @Override
    public CompteDto updateCompte(CompteDto compteDto, int id) {            //UPDATE

        Optional<Compte> compteOptional = compteRepository.findById(id);
        if (compteOptional != null) {
            Compte compte = modelMapper.map(compteDto, Compte.class);
            compte.setId(id);
            Compte updated = compteRepository.save(compte);
            return modelMapper.map(updated, CompteDto.class);
        } else {
            System.out.println("introuvable");
            return null;
        }
    }

    @Transactional
    @Override
    public CompteDto findCompteById(int id) {       //GET ID
        Compte compte = compteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Compte introuvable !!"));
        return modelMapper.map(compte, CompteDto.class);
    }
    @Transactional
    public CompteDto findByEmail(String email) {
        Compte compte = compteRepository.findByEmail(email);
        return modelMapper.map(compte, CompteDto.class);
    }
    @Transactional(readOnly = true)
    @Override
    public List<CompteDto> findAllCompte() {            //GET ALL

        return compteRepository.findAll()
                .stream().map(element -> modelMapper.map(element, CompteDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteCompte(int id) {          //DELETE ID
        compteRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAllComptes() {            //DELETE ALL
        compteRepository.deleteAll();
    }















   /* public CompteDto login(String email, String password) {
        Compte compte = compteRepository.findCompteByEmailAndPassword(email, password);
        return modelMapper.map(compte, CompteDto.class);
    }
    public String getTypeCompte(String email, String password) {
        Compte compte = compteRepository.findCompteByEmailAndPassword(email, password);
        modelMapper.map(compte, CompteDto.class);
        return compte.getTypeCompte();
    }


    public List<CompteDto> findCompteByType(String typeCompte) {
        List<Compte> listCompte = compteRepository.findCompteByTypeCompte(typeCompte);
        if(listCompte == null) throw new EntityNotFoundException("type introuvable !!");
        return listCompte.stream().map(element -> modelMapper.map(element,CompteDto.class)).collect(Collectors.toList());
    }*/
}


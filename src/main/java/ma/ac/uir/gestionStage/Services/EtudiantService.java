package ma.ac.uir.gestionStage.Services;


import ma.ac.uir.gestionStage.DAO.EtudiantRepository;
import ma.ac.uir.gestionStage.DAO.RoleRepository;
import ma.ac.uir.gestionStage.DTO.EtudiantDto;
import ma.ac.uir.gestionStage.DTO.NiveauDto;
import ma.ac.uir.gestionStage.Entities.Etudiant;
import ma.ac.uir.gestionStage.Entities.Niveau;
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
public class EtudiantService {
    private Role role;
   @Autowired private ModelMapper modelMapper;
    @Autowired private EtudiantRepository etudiantRepository;
    @Autowired private RoleRepository roleRepository;
    private Etudiant etudiant;

    @Transactional
    public EtudiantDto saveETD(EtudiantDto etudiantDto){
            Etudiant etudiant = new Etudiant();
            etudiant.setNom(etudiantDto.getNom());
            etudiant.setPrenom(etudiantDto.getPrenom());
            etudiant.setEmail(etudiantDto.getEmail());
            etudiant.setPassword(etudiantDto.getPassword());

        int roleID = etudiantDto.getIdRole();
        Role rID = roleRepository.findRoleById(roleID);
        if(rID != null) {
            etudiant.setRole(rID);
        }
        etudiant = etudiantRepository.save(etudiant);
        return modelMapper.map(etudiant,EtudiantDto.class);
    }

    @Transactional
    public EtudiantDto updateCompte(EtudiantDto etudiantDto, int id) throws EntityNotFoundException {            //UPDATE

        Optional<Etudiant> compteOptional = Optional.ofNullable(etudiantRepository.findById(id));
        if (compteOptional != null) {
            Etudiant etudiant = modelMapper.map(etudiantDto, Etudiant.class);
            etudiant.setId(id);
            Etudiant updated = etudiantRepository.save(etudiant);
            return modelMapper.map(updated, EtudiantDto.class);
        } else {
            System.out.println("introuvable");
            return null;
        }
    }
    
    @Transactional
    public EtudiantDto findEtudiantById(int id) {
        Etudiant etudiant = new Etudiant();
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
            throw new EntityNotFoundException("Niveau not found");
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
            throw new EntityNotFoundException("Niveaux not found");
        }
        else {
            etudiantRepository.deleteAll();
            return modelMapper.map((Object) etudiant, (Type) NiveauDto.class);
        }
    }

    @Transactional
    public EtudiantDto etudiantLogin(String email, String password){
        EtudiantDto etudiantDto = new EtudiantDto();
        int idR = etudiantDto.getIdRole();
        if(email != null && password !=null)
            etudiant = etudiantRepository.findByEmailAndPassword(email,password);
        System.out.println("login etd ok "+ idR);
        return modelMapper.map(etudiant,EtudiantDto.class);

    }
    
}



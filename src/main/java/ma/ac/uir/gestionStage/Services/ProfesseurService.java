package ma.ac.uir.gestionStage.Services;

import lombok.Data;
import ma.ac.uir.gestionStage.DAO.EtudiantRepository;
import ma.ac.uir.gestionStage.DAO.ProfesseurRepository;
import ma.ac.uir.gestionStage.DTO.ProfesseurDto;
import ma.ac.uir.gestionStage.Entities.Etudiant;
import ma.ac.uir.gestionStage.Entities.Professeur;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Data
public class ProfesseurService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProfesseurRepository professeurRepository;
    @Autowired private EtudiantRepository etudiantRepository;

    @Transactional
    public ProfesseurDto savePROF(ProfesseurDto professeurDto) {
        Professeur professeur = new Professeur();
        professeur.setNom(professeurDto.getNom());
        professeur.setPrenom(professeurDto.getPrenom());
        professeur.setEmail(professeurDto.getEmail());
        professeur.setPassword(professeurDto.getPassword());
/////////////// had block khasou ikoun f method d'affectation bo7dha /////////////
       /* Iterable<Integer> id = professeurDto.getIdE();                  System.out.println("id="+  id);
        assert id != null;
        List<Etudiant> le = etudiantRepository.findAllById(id);        System.out.println("le:"+  le);
            if (!le.isEmpty())
                professeur.setEtudiantList(le);*/
        /////////////////////////////////////////////////////////
        professeur = professeurRepository.save(professeur);
        return modelMapper.map(professeur, ProfesseurDto.class);
    }

    @Transactional
    public ProfesseurDto affecterETD_PROF(ProfesseurDto professeurDto, int idP){
        Professeur professeur;
        Optional<Professeur> professeurOptional = (professeurRepository.findById(idP));
        if (professeurOptional.isPresent()) {
            professeur = modelMapper.map(professeurDto, Professeur.class);
            professeur.setId(idP);
            professeur.setEtudiantList(professeurDto.getIdE());  //passed whole obj
            Professeur updated = professeurRepository.save(professeur);
            return modelMapper.map(updated, ProfesseurDto.class);
        }else {
            System.out.println("introuvable");
            throw new RuntimeException();
        }
    }
    @Transactional
    public ProfesseurDto updateProf(ProfesseurDto professeurDto, int id) throws EntityNotFoundException {            //UPDATE
        Optional<Professeur> professeurOptional = (professeurRepository.findById(id));
        if (professeurOptional.isPresent()) {
            Professeur professeur = modelMapper.map(professeurDto, Professeur.class);
            professeur.setId(id);
            Professeur updated = professeurRepository.save(professeur);
            return modelMapper.map(updated, ProfesseurDto.class);
        } else {
            System.out.println("introuvable");
            return null;
        }
    }

    @Transactional
    public ProfesseurDto findProfById(int id) throws EntityNotFoundException {
        professeur = professeurRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Professeur introuvable !!"));
        return modelMapper.map(professeur, ProfesseurDto.class);
    }

    @Transactional
    public List<ProfesseurDto> findAllProfs() throws EntityNotFoundException {
        List<Professeur> professeurs = professeurRepository.findAll();
        if(professeurs.isEmpty()){
            throw new EntityNotFoundException("Liste des profs est vide");
        }else {
            return professeurRepository.findAll()
                    .stream().map(element -> modelMapper.map(element, ProfesseurDto.class))
                    .collect(Collectors.toList());
        }
    }

    @Transactional
    public ProfesseurDto deleteProfesseur(int id) throws EntityNotFoundException {
        Optional<Professeur> professeur = professeurRepository.findById(id);
        if (professeur.isEmpty()) {
            throw new EntityNotFoundException("Prof introuvable");
        } else {
            professeurRepository.deleteById(id);
            return modelMapper.map( professeur, (Type) ProfesseurDto.class);
        }
    }

    @Transactional
    public ProfesseurDto deleteAllProfesseurs() throws EntityNotFoundException {
        List<Professeur> professeur = professeurRepository.findAll();
        if (!professeur.isEmpty()) {
            professeurRepository.deleteAll();
            return modelMapper.map(professeur, ProfesseurDto.class);
        } else {
            throw new EntityNotFoundException("liste des profs est vide");
        }

    }

    private Professeur professeur;
    @Transactional
    public ProfesseurDto ProfesseurLogin(String email, String password) throws EntityNotFoundException{
        if(email != null && password !=null)
            professeur = professeurRepository.findByEmailAndPassword(email,password);
        return modelMapper.map( professeur, ProfesseurDto.class);

    }
}

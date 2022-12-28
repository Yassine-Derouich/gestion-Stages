package ma.ac.uir.gestionStage.Services.service_interfaces;

import ma.ac.uir.gestionStage.DTO.CompteDto;

import java.util.List;

public interface Compte_interface {

     CompteDto saveCompte(CompteDto compteDto);

    CompteDto updateCompte(CompteDto compteDto, int id);

    CompteDto findCompteById(int id);

    List<CompteDto> findAllCompte();

    void deleteCompte(int id);

    void deleteAllComptes();


}

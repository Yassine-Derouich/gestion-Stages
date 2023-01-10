package ma.ac.uir.gestionStage.Services.service_interfaces;

import ma.ac.uir.gestionStage.DTO.NiveauDto;

import java.util.List;

public interface Niveau_interface {

    NiveauDto saveNiveau(NiveauDto niveauDto);

    NiveauDto updateNiveau(NiveauDto niveauDto, int id);

    NiveauDto findNiveauById(int id);

    List<NiveauDto> findAllNiveaux();

    //void deleteNiveau(int id);
    NiveauDto deleteNiveau(int id);
    NiveauDto deleteAllNiveaux();
}

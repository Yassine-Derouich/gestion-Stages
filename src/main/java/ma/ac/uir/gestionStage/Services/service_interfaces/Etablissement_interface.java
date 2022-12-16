package ma.ac.uir.gestionStage.Services.service_interfaces;

import ma.ac.uir.gestionStage.DTO.EtablissementDto;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface Etablissement_interface {

    EtablissementDto saveEtablissement(EtablissementDto etablissementDto);

    EtablissementDto updateEtablissement(EtablissementDto etablissementDto, int id);

    EtablissementDto findEtablissementById(int id);

    List<EtablissementDto> findAllEtablissement();

    void deleteEtablissement(int id);

    void deleteAllEtablissement();
}

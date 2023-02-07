package ma.ac.uir.gestionStage.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.sql.Date;

@Service
@Data
@Entity
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDocument")
    private int idDocument;

    @Column(name = "fichier", columnDefinition = "BLOB")
    @JsonIgnore
    private byte[] fichier;

    @Column(name = "dateEnvoi")
    @CreatedDate
    private java.sql.Date dateEnvoi;


    private String fileName, fileType;

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private Professeur professeur;

    @ManyToOne
    private ResponsableStage responsableStage;


}

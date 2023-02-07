package ma.ac.uir.gestionStage.DTO;

import lombok.Data;

import java.sql.Date;

@Data
public class DocumentDto {
    private int idDocument;
    private java.sql.Date dateEnvoi;
    private byte[] file;

    public java.sql.Date getDateEnvoi(Date sqlDate) {
        return dateEnvoi;
    }
    public void setDateEnvoi(Date dateEnvoi) {
        //Calendar calendar = Calendar.getInstance();
        //dateEnvoi = (Date) calendar.getTime();
        this.dateEnvoi = dateEnvoi;
    }
}

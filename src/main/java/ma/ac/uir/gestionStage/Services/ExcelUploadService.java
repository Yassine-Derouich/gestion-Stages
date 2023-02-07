package ma.ac.uir.gestionStage.Services;


import lombok.Data;
import ma.ac.uir.gestionStage.DTO.EtudiantDto;
import ma.ac.uir.gestionStage.Entities.Etudiant;
import ma.ac.uir.gestionStage.Entities.Niveau;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
@Data
public class ExcelUploadService {

    private EtudiantDto etudiantDto;

    public static boolean isValidExcelFile(MultipartFile file) {
            return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }
    public static List<Etudiant> getEtdsDataFromExcel(InputStream inputStream){
        List<Etudiant> etudiantList = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("etd");
            int rowIndex =0;
            for (Row row : sheet){
                if (rowIndex ==0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Etudiant etudiant = new Etudiant();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 0 -> etudiant.setId((int) cell.getNumericCellValue());
                        case 1 -> etudiant.setNom(cell.getStringCellValue());
                        case 2 -> etudiant.setPrenom(cell.getStringCellValue());
                        case 3 -> etudiant.setEmail(cell.getStringCellValue());
                        case 4 -> etudiant.setPassword(cell.getStringCellValue());
                        case 5 -> etudiant.setNomFiliere(cell.getStringCellValue());
                        case 6 -> etudiant.setNBniveau((int) cell.getNumericCellValue());
                        default -> {
                        }
                    }
                    cellIndex++;
                }
                etudiantList.add(etudiant);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return etudiantList;
    }

}

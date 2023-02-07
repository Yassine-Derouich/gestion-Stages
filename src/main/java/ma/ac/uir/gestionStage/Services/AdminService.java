package ma.ac.uir.gestionStage.Services;

import lombok.Data;
import ma.ac.uir.gestionStage.DAO.AdminRepository;
import ma.ac.uir.gestionStage.DTO.AdminDto;
import ma.ac.uir.gestionStage.Entities.Admin;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

@Service
@Data
public class AdminService {
   private Admin admin;
   @Autowired private AdminRepository adminRepository;
  @Autowired private ModelMapper modelMapper;

    @Transactional
    public AdminDto AdminLogin(String email, String password){
        try {
            if (email != null && password != null)
                admin = adminRepository.findByEmailAndAndPassword(email, password);
            return modelMapper.map(admin, AdminDto.class);
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            throw new EntityNotFoundException("credentials not found !");
        }
    }

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail() {
        String from = "vohido9689@brandoza.com";
        String to = "vohido9689@brandoza.com";

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Spring boot email test");
        message.setText("votre email & pass");

        mailSender.send(message);
    }
}

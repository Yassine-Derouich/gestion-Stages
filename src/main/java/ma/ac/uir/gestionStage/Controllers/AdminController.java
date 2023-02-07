package ma.ac.uir.gestionStage.Controllers;

import ma.ac.uir.gestionStage.DAO.AdminRepository;
import ma.ac.uir.gestionStage.DTO.AdminDto;
import ma.ac.uir.gestionStage.Entities.Admin;
import ma.ac.uir.gestionStage.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("Admin")
public class AdminController {

    @Autowired private AdminRepository adminRepository;
    @Autowired private AdminService adminService;

    @PostMapping(value = "Login/{email}/{password}")
    public ResponseEntity<AdminDto> LoginAdmin(@PathVariable String email, @PathVariable String password){
        try {
            return new ResponseEntity<>(adminService.AdminLogin(email, password), HttpStatus.ACCEPTED);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Autowired
    private JavaMailSender mailSender;
    @PostMapping("/sendEmail")
    public String sendEmail() {
        adminService.sendEmail();
        return "ok";
    }

}

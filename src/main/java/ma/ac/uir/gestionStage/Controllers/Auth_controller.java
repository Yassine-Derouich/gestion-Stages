package ma.ac.uir.gestionStage.Controllers;

import ma.ac.uir.gestionStage.DTO.CompteDto;
import ma.ac.uir.gestionStage.Entities.Compte;
import ma.ac.uir.gestionStage.Services.service_impl.CompteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Controller
public class Auth_controller {

    private CompteService compteService;

    public Auth_controller(CompteService compteService) {
        this.compteService = compteService;
    }

    @GetMapping("index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        CompteDto compteDto = new CompteDto();
        model.addAttribute("compte", compteDto);
        return "register";
    }

    /*// handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("compteDto") CompteDto compteDto,
                               BindingResult result,
                               Model model){
        Compte existing = compteService.findByEmail(compteDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("compte", compteDto);
            return "register";
        }
        compteService.saveCompte(compteDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<CompteDto> comptes = compteService.findAllCompte();
        model.addAttribute("comptes", comptes);
        return "comptes";
    }*/
}

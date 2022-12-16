package ma.ac.uir.gestionStage.DTO;


import javax.validation.constraints.NotEmpty;

public class CompteDto {

    private int id;
    @NotEmpty(message = "Ce champ est requis")
    private String nom;
    @NotEmpty(message = "Ce champ est requis")
    private String prenom;
    @NotEmpty(message = "Ce champ est requis")
    private String password;
    @NotEmpty(message = "Ce champ est requis")
    private String email;
    // private String typeCompte;
    public CompteDto() {
    }

    public CompteDto(int id,String nom, String prenom, String email, String password/*,String typeCompte*/) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        //this.typeCompte = typeCompte;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() { return prenom; }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "CompteDto{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                /*", typeCompte='" + typeCompte + '\''*/ +
                '}';
    }
}

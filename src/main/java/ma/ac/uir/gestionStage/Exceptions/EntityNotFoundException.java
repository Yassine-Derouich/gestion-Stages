package ma.ac.uir.gestionStage.Exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super();
    }
    public EntityNotFoundException(String message) {
        super(message);
    }
}

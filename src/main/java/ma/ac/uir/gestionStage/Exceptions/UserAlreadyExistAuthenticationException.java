package ma.ac.uir.gestionStage.Exceptions;

import javax.naming.AuthenticationException;

public class UserAlreadyExistAuthenticationException extends AuthenticationException {
    public UserAlreadyExistAuthenticationException(final String msg) {
        super(msg);
    }
}

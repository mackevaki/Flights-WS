package exceptions;

import jakarta.xml.ws.WebFault;

@WebFault
public class ArgumentException extends Exception {
    public ArgumentException(String message) {
        super(message);
    }
}

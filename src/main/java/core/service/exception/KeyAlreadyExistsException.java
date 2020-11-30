package core.service.exception;

public class KeyAlreadyExistsException extends Exception{
    public KeyAlreadyExistsException(String s){
        super("Key "+s+" already exists");
    }
}

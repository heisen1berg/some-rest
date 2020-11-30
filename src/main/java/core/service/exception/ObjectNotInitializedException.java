package core.service.exception;

public class ObjectNotInitializedException extends Exception{
    public ObjectNotInitializedException(){
        super("Object has not been initialized");
    }
}

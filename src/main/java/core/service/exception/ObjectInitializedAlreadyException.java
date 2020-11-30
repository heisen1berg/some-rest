package core.service.exception;

public class ObjectInitializedAlreadyException extends Exception{
    public ObjectInitializedAlreadyException(){
        super("Object has already been initialized");
    }
}

package core.service;

import core.data.SavedFields;
import core.service.exception.KeyAlreadyExistsException;
import core.service.exception.ObjectInitializedAlreadyException;
import core.service.exception.ObjectNotInitializedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class SavedFieldsAdapter {
    private enum SavedFieldsAdapterOperations{POST,PUT,PATCH,GET};
    private final SavedFields savedFields;
    private final ResponseWriter responseWriter;

    @Autowired
    public SavedFieldsAdapter(SavedFields savedFields, ResponseWriter responseWriter){
        this.savedFields=savedFields;
        this.responseWriter=responseWriter;
    }

    public ResponseEntity post(Map<String,String> pairs){
        return doItSynchronized(SavedFieldsAdapterOperations.POST,pairs);
    }
    public ResponseEntity put(Map<String,String> pairs){
        return doItSynchronized(SavedFieldsAdapterOperations.PUT,pairs);
    }
    public ResponseEntity patch(Map<String,String> pairs){
        return doItSynchronized(SavedFieldsAdapterOperations.PATCH,pairs);
    }
    public ResponseEntity get(){
        return doItSynchronized(SavedFieldsAdapterOperations.GET,null);
    }
    private synchronized ResponseEntity doItSynchronized(SavedFieldsAdapterOperations sfao, Map<String,String> pairs){

        switch(sfao){
            case POST:
                try{
                    savedFields.init();
                    savedFields.add(pairs);
                }
                catch (ObjectInitializedAlreadyException|KeyAlreadyExistsException e) {
                    return responseWriter.badRequest(e);
                }
                catch (ObjectNotInitializedException ignore) { }
                return responseWriter.ok();


            case PUT:
                try {
                    savedFields.rewrite(pairs);
                } catch (ObjectNotInitializedException e) {
                    return responseWriter.badRequest(e);
                }
                return responseWriter.ok();

            case PATCH:
                try {
                    savedFields.add(pairs);
                } catch (ObjectNotInitializedException | KeyAlreadyExistsException e) {
                    return responseWriter.badRequest(e);
                }
                return responseWriter.ok();

            case GET:
                return responseWriter.jsonResponse(Optional.ofNullable(savedFields.getFields()));

        }

        throw new RuntimeException();
    }
}

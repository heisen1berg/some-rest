package core.data;


import core.service.exception.KeyAlreadyExistsException;
import core.service.exception.ObjectInitializedAlreadyException;
import core.service.exception.ObjectNotInitializedException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SavedFields {
    private HashMap<String,String> fields;

    public void rewrite(Map<String,String> pairs) throws ObjectNotInitializedException {
        if(fields==null)throw new ObjectNotInitializedException();
        fields.clear();
        pairs.forEach(fields::put);
    }
    public void add(Map<String,String> pairs) throws ObjectNotInitializedException, KeyAlreadyExistsException {
        if(fields==null)throw new ObjectNotInitializedException();
        for (Map.Entry<String, String> entry : pairs.entrySet()) {
            if(fields.containsKey(entry.getKey()))throw new KeyAlreadyExistsException(entry.getKey());
        }
        pairs.forEach(fields::put);
    }
    public void init() throws ObjectInitializedAlreadyException {
        if(fields!=null)throw new ObjectInitializedAlreadyException();
        fields=new HashMap<>();
    }
    public Map<String,String> getFields(){
        return fields;
    }
}

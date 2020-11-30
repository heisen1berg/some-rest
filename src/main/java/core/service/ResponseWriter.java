package core.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ResponseWriter {
    public ResponseEntity badRequest(Exception exception){
        final HashMap<String,String> response=new HashMap<String,String>(){{
            put("status","400");
            put("error","Bad Request");
            put("message",exception.getMessage());
        }};
        return ResponseEntity.badRequest().body(response);
    }
    public ResponseEntity ok(){
        final HashMap<String,String> response=new HashMap<String,String>(){{
            put("status","200");
            put("message","OK");
        }};
        return ResponseEntity.ok(response);
    }
    public ResponseEntity jsonResponse(Optional<Map<String,String>> pairs){
        return ResponseEntity.ok(pairs.orElse(new HashMap<>()));
    }
}

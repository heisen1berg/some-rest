package core.controller;

import core.service.SavedFieldsAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class MainController {
    private final SavedFieldsAdapter savedFieldsAdapter;
    @Autowired
    public MainController(SavedFieldsAdapter savedFieldsAdapter){
        this.savedFieldsAdapter=savedFieldsAdapter;
    }

    @PostMapping
    public @ResponseBody
    ResponseEntity post(@RequestBody Map<String,String> pairs){
        return savedFieldsAdapter.post(pairs);
    }
    @PutMapping
    public @ResponseBody
    ResponseEntity put(@RequestBody Map<String,String> pairs){
        return savedFieldsAdapter.put(pairs);
    }
    @PatchMapping
    public @ResponseBody
    ResponseEntity patch(@RequestBody Map<String,String> pairs){
        return savedFieldsAdapter.patch(pairs);
    }
    @GetMapping
    public @ResponseBody
    ResponseEntity get(){
        return savedFieldsAdapter.get();
    }
}

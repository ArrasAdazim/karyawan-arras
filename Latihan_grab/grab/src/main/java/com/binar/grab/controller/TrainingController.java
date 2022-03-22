package com.binar.grab.controller;


import com.binar.grab.model.Training;
import com.binar.grab.repository.TrainingRepository;
import com.binar.grab.service.TrainingService;
import com.binar.grab.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/binar/training")
public class TrainingController {

    @Autowired
    public TemplateResponse templateResponse;

    @Autowired
    public TrainingRepository trainingRepository;

    @Autowired
    public TrainingService trainingService;


    @PostMapping("")
    public ResponseEntity<Map> save(@RequestBody Training obj){
        Map save = trainingService.simpan(obj);
        return new ResponseEntity<Map>(save, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Map> update(@RequestBody Training obj){
        Map update = trainingService.update(obj);
        return new ResponseEntity<Map>(update, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
        Map map = trainingService.delete(id);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

}

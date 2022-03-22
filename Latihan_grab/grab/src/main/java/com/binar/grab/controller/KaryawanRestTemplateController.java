package com.binar.grab.controller;


import com.binar.grab.model.Karyawan;
import com.binar.grab.repository.KaryawanRepository;
import com.binar.grab.service.KaryawanRestTemplateService;
import com.binar.grab.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v2/binar/karyawan")

public class KaryawanRestTemplateController {

    @Autowired
    public KaryawanRestTemplateService karyawanRestTemplateService;

    @Autowired
    public KaryawanRepository karyawanRepository;

    @Autowired
    public TemplateResponse templateResponse;

    @PostMapping("")
    public ResponseEntity<Map> save(
            @RequestBody Karyawan obj){
        Map save = karyawanRestTemplateService.insert(obj);
        return new ResponseEntity<Map>(save, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map> update(@RequestBody Karyawan objModel){
        Map update = karyawanRestTemplateService.update(objModel);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idKaryawan}")
    public ResponseEntity<Map> delete(@PathVariable(value = "idKaryawan") Long idKaryawan) {
        Map map = karyawanRestTemplateService.delete(idKaryawan);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }


    @GetMapping("/list")
    public ResponseEntity<Map> list(
            @RequestParam() Integer page,
            @RequestParam() Integer size){
        Map map = karyawanRestTemplateService.getAll(size, page);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}

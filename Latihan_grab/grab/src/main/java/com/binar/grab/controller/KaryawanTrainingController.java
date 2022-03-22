package com.binar.grab.controller;

import com.binar.grab.dao.request.KaryawanTrainingRequest;
import com.binar.grab.model.KaryawanTraining;
import com.binar.grab.repository.KaryawanTrainingRepository;
import com.binar.grab.service.KaryawanTrainingService;
import com.binar.grab.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/binar/karyawan-training")
public class KaryawanTrainingController {

    @Autowired
    public TemplateResponse templateResponse;

    @Autowired
    public KaryawanTrainingService karyawanTrainingService;

    @Autowired
    public KaryawanTrainingRepository karyawanTrainingRepository;

    @PostMapping("")
    public ResponseEntity<Map> save(@RequestBody KaryawanTrainingRequest objModel) {
        Map obj = karyawanTrainingService.simpan(objModel);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @PutMapping("/update/")
    public ResponseEntity<Map> update(  @RequestBody KaryawanTrainingRequest objModel ) {
        Map map = karyawanTrainingService.update(objModel);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
        Map map = karyawanTrainingService.delete(id);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

//    @GetMapping("/list")
//    public ResponseEntity<Map> getlist(){
//        Map map = new HashMap();
//        map.put("data", karyawanTrainingRepository.findAll());
//        map.put("code","200");
//        map.put("status","succes");
//        return new ResponseEntity<Map>(map, HttpStatus.OK);
//    }

    @GetMapping("/list")
    public ResponseEntity<Map> listByBama(
            @RequestParam(required = false) String tanggalTraining) {
        Map map = new HashMap();

        if(tanggalTraining != null && !tanggalTraining.isEmpty()){
            map.put("data", karyawanTrainingRepository.findKaryawanTrainingByTanggalTrainingLike( "%"+tanggalTraining+"%"));

        } else{
            map.put("data", karyawanTrainingRepository.findAll());
        }
        return new ResponseEntity<Map>(templateResponse.templateSukses(map), new HttpHeaders(), HttpStatus.OK);
    }

}

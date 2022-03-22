package com.binar.grab.controller;


import com.binar.grab.model.Rekening;
import com.binar.grab.repository.RekeningRepository;
import com.binar.grab.service.RekeningService;
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
@RequestMapping("/v1/binar/rekening")

public class RekeningController {

    @Autowired
    public RekeningService rekeningService;

    @Autowired
    public RekeningRepository rekeningRepository;

    @Autowired
    public TemplateResponse templateResponse;



    @PostMapping("/simpan/{idkaryawan}")
    public ResponseEntity<Map> save(@PathVariable(value = "idkaryawan") Long idkaryawan,
                                    @RequestBody Rekening objModel) {
        Map obj = rekeningService.insert(objModel, idkaryawan);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @PutMapping("/update/{idkaryawan}")
    public ResponseEntity<Map> update(@PathVariable(value = "idkaryawan") Long idkaryawan,
                                      @RequestBody Rekening objModel ) {
        Map map = rekeningService.update(objModel, idkaryawan);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
        Map map = rekeningService.delete(id);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }


//    @GetMapping("/list")
//    public ResponseEntity<Map> listByNama(
//            @RequestParam() Integer page,
//            @RequestParam() Integer size) {
//        Map list = rekeningService.getAll(size, page);
//        return new ResponseEntity<Map>(list, new HttpHeaders(), HttpStatus.OK);
//    }


    @GetMapping("/list")
    public ResponseEntity<Map> listByNama(
            @RequestParam() Integer page,
            @RequestParam() Integer size,
            @RequestParam(required = false) String  namaLike,// ga mandatory : default mandatory
            @RequestParam(required = false) String namaRekening) {
        Map map = new HashMap();
        Page<Rekening> list = null;
        Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());//batasin roq

        if(namaRekening != null && !namaRekening.isEmpty()){
            list = rekeningRepository.findByNama("%"+namaRekening+"%",show_data);
        } if(namaLike != null && !namaLike.isEmpty()){
            list = rekeningRepository.findByNamaLike("%"+namaLike+"%",show_data);
        } else{
            list = rekeningRepository.getAllData(show_data);
        }
        return new ResponseEntity<Map>(templateResponse.templateSukses(list), new HttpHeaders(), HttpStatus.OK);
    }


}

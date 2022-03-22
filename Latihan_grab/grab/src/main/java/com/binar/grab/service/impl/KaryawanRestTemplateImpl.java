package com.binar.grab.service.impl;


import com.binar.grab.model.Karyawan;
import com.binar.grab.repository.DetailKaryawanRepository;
import com.binar.grab.repository.KaryawanRepository;
import com.binar.grab.service.KaryawanRestTemplateService;
import com.binar.grab.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KaryawanRestTemplateImpl implements KaryawanRestTemplateService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public KaryawanRepository karyawanRepository;

    @Autowired
    public TemplateResponse templateResponse;

    @Autowired
    public DetailKaryawanRepository detailKaryawanRepository;


    @Override
    public Map insert(Karyawan obj) {
        try {

            //1. Validasi//
            if(templateResponse.chekNull(obj.getNama())){
                return   templateResponse.templateEror("Nama Wajib diisi");
            }
            if(templateResponse.chekNull(obj.getJk())){
                return   templateResponse.templateEror("Jenis Kelamin Wajib diisi");
            }
            if(templateResponse.chekNull(obj.getDob())){
                return   templateResponse.templateEror("Dob Wajib diisi");
            }

            if(templateResponse.chekNull(obj.getAlamat())){
                return   templateResponse.templateEror("Alamat Wajib diisi");
            }
            if(templateResponse.chekNull(obj.getStatus())){
                return   templateResponse.templateEror("Status Wajib diisi");
            }

            //2. Check DetailKaryawan

            if (templateResponse.chekNull(obj.getDetailKaryawan())) {
                return templateResponse.templateEror("Detail Karyawan Wajib ada");
            }

            String url = "http://localhost:9091/api/v1/binar/karyawan";

            ResponseEntity<Map> result = restTemplateBuilder.build().postForEntity(url, obj, Map.class);
            return templateResponse.templateSukses(result.getBody());


        }catch (Exception e){
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map update(Karyawan obj) {
        try {
            if (templateResponse.chekNull(obj.getId())) {
                return templateResponse.templateEror("Id karyawan is Requiered");
            }
            Karyawan karyawanUpdate = karyawanRepository.getbyId(obj.getId());
            if (templateResponse.chekNull(karyawanUpdate)) {
                return templateResponse.templateEror("Id Karyawan is not found");
            }

            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
            String url = "http://localhost:9091/api/v1/binar/karyawan/update";
            HttpHeaders headers = new HttpHeaders();
            // set `content-type` header
            headers.setContentType(MediaType.APPLICATION_JSON);
            // set `accept` header
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            // build the request
            HttpEntity<Karyawan> entity = new HttpEntity<>(obj, headers);

            // send PUT request to update post with `id` 10
            ResponseEntity<Map> result = restTemplateBuilder.build().exchange(url, HttpMethod.PUT, entity, Map.class);

            if (result.getStatusCode() == HttpStatus.OK) {
                return templateResponse.templateSukses(result.getBody());
            } else {
                System.out.println("error");
                return templateResponse.templateEror(result.getBody());
            }
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map delete(Long idKaryawan) {
        try {
            if (templateResponse.chekNull(idKaryawan)) {
                return templateResponse.templateEror("Id Karyawan is required");
            }
            Karyawan chekIdKaryawan = karyawanRepository.getbyId(idKaryawan);
            if (templateResponse.chekNull(chekIdKaryawan)) {
                return templateResponse.templateEror("Id Karyawan Not found");
            }
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "*/*");
            headers.set("Content-Type", "application/json");
            HttpEntity<String> entity = new HttpEntity<String>(null, headers);

            String url = "http://localhost:9091/api/v1/binar/karyawan/delete/" + idKaryawan;
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
            ResponseEntity<Map> result = restTemplateBuilder.build().exchange(url, HttpMethod.DELETE, entity, Map.class);

            return templateResponse.templateSukses(result.getBody());
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }

    }

    @Override
    public Map getAll(int size, int page) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
            final  String url = "http://localhost:9091/api/v1/binar/karyawan/list?size={size}&page={page}";
            Map<String, Object> params = new HashMap<>();
            params.put("size", 10);
            params.put("page", 0);
            HttpEntity<?> httpEntity  = new HttpEntity<>(httpHeaders);
            ResponseEntity<Map> result = restTemplateBuilder.build().exchange(url, HttpMethod.GET, httpEntity,Map.class, params);
            return templateResponse.templateSukses(result.getBody());

        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }

    }

}

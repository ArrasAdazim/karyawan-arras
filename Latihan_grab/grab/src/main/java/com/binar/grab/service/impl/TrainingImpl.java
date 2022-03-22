package com.binar.grab.service.impl;

import com.binar.grab.model.Training;
import com.binar.grab.repository.TrainingRepository;
import com.binar.grab.service.TrainingService;
import com.binar.grab.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Date;
import java.util.Map;

@Service
public class TrainingImpl implements TrainingService {

    public static final Logger log = LoggerFactory.getLogger(TrainingImpl.class);

    @Autowired
    public TrainingRepository trainingRepository;

    @Autowired
    public TemplateResponse templateResponse;


    @Override
    public Map simpan(Training obj) {
        try{
            // Cek apakah request dari klien kosong atau tidak
            if(templateResponse.chekNull(obj.getTema())){
                return templateResponse.templateEror("Tema tidak boleh kosong");
            }
            if(templateResponse.chekNull(obj.getNamaPengajar())){
                return templateResponse.templateEror("Nama Pengajar tidak boleh kosong");
            }
            // Save data
            Training save = trainingRepository.save(obj);
            return templateResponse.templateSukses(save);
        }catch (Exception e){
            log.error("Error pada method insert Karyawan");
            return templateResponse.templateEror(e);
        }

    }

    @Override
    public Map update(Training obj) {
        try{
            // Cek apakah request dari klien kosong atau tidak
            if(templateResponse.chekNull(obj.getId())){
                return templateResponse.templateEror("Id tidak boleh kosong");
            }
            Training update = trainingRepository.getbyID(obj.getId());
            // Cek apakah id dari database dengan request klien ada atau tidak
            if (templateResponse.chekNull(update)){
                return templateResponse.templateEror("Id tidak ditemukan");
            }
            // Update data
            update.setTema(obj.getTema());
            update.setNamaPengajar(obj.getNamaPengajar());

            // Save data
            Training save = trainingRepository.save(update);
            return templateResponse.templateSukses(save);
        }catch (Exception e){
            log.error("Error pada method update Karyawan");
            return templateResponse.templateEror(e);
        }

    }

    @Override
    public Map delete(Long idTraining) {
       /*
        soft delete , bukan delete permanent
        1. chek id idPembeli
        2. update , tanggal deleted saja
         */
        try {
            if (templateResponse.chekNull(idTraining)) {
                return templateResponse.templateEror("Id Training Tidak Boleh Kosong");
            }
            //            1. chek id idPembeli
            Training chekIdTraining = trainingRepository.getbyID(idTraining);
            if (templateResponse.chekNull(chekIdTraining)) {
                return templateResponse.templateEror("Id Pembeli Not found");
            }

//            2. update , tanggal deleted saja
            chekIdTraining.setDeleted_date(new Date());//
            trainingRepository.save(chekIdTraining);

            return templateResponse.templateSukses("Berhasil Menghapus");

        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }
}

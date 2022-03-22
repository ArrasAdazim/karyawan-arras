package com.binar.grab.service.impl;

import com.binar.grab.dao.request.KaryawanTrainingRequest;
import com.binar.grab.model.Karyawan;
import com.binar.grab.model.Training;
import com.binar.grab.model.KaryawanTraining;
import com.binar.grab.repository.KaryawanRepository;
import com.binar.grab.repository.KaryawanTrainingRepository;
import com.binar.grab.repository.TrainingRepository;
import com.binar.grab.service.KaryawanTrainingService;
import com.binar.grab.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class KaryawanTrainingImpl implements KaryawanTrainingService {

    @Autowired
    public TemplateResponse templateResponse;

    @Autowired
    public KaryawanTrainingRepository karyawanTrainingRepository;

    @Autowired
    public KaryawanRepository karyawanRepository;

    @Autowired
    public TrainingRepository trainingRepository;


    @Override
    public Map simpan(KaryawanTrainingRequest obj) {
        try {
            if (templateResponse.chekNull(obj.getIdKaryawan())) {
                return templateResponse.templateEror("Id Karyawan Tidak boleh Kosong");
            }
            if (templateResponse.chekNull(obj.getIdTraining())) {
                return templateResponse.templateEror("Id Training Tidak boleh Kosong");
            }
            Karyawan chekIdKaryawan = karyawanRepository.getbyId(obj.getIdKaryawan());
            if (templateResponse.chekNull(chekIdKaryawan)) {
                return templateResponse.templateEror("Id Barang Tidak ada di database");
            }
            Training chekIdTraining = trainingRepository.getbyID(obj.getIdTraining());
            if (templateResponse.chekNull(chekIdTraining)) {
                return templateResponse.templateEror("Id Training Tidak ada di database");
            }

            KaryawanTraining save = new KaryawanTraining();
            save.setKaryawan(chekIdKaryawan);
            save.setTraining(chekIdTraining);
            save.setTanggalTraining(obj.getTanggalTraining());

            KaryawanTraining doSave = karyawanTrainingRepository.save(save);
            return templateResponse.templateSukses(doSave);
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map update(KaryawanTrainingRequest obj) {
        try {

            if (templateResponse.chekNull(obj.getIdKaryawan())) {
                return templateResponse.templateEror("Id Karyawan Tidak boleh Kosong");
            }

            if (templateResponse.chekNull(obj.getIdTraining())) {
                return templateResponse.templateEror("Id Training Tidak boleh Kosong");
            }
            if (templateResponse.chekNull(obj.getId())) {
                return templateResponse.templateEror("Id Karyawan Training Tidak boleh Kosong");
            }
            Karyawan chekIdKaryawan = karyawanRepository.getbyId(obj.getIdKaryawan());
            if (templateResponse.chekNull(chekIdKaryawan)) {
                return templateResponse.templateEror("Id Barang Tidak ada di database");
            }

            Training chekIdTraining = trainingRepository.getbyID(obj.getIdTraining());
            if (templateResponse.chekNull(chekIdTraining)) {
                return templateResponse.templateEror("Id Pembeli Tidak ada di database");
            }

            KaryawanTraining chekIdKaryawanTraining = karyawanTrainingRepository.getbyID(obj.getId());
            if (templateResponse.chekNull(chekIdKaryawanTraining)) {
                return templateResponse.templateEror("Id Karyawan Traning Tidak ada di database");
            }
            //update disini
            chekIdKaryawanTraining.setKaryawan(chekIdKaryawan);
            chekIdKaryawanTraining.setTraining(chekIdTraining);
            chekIdKaryawanTraining.setTanggalTraining(obj.getTanggalTraining());
            KaryawanTraining doSave = karyawanTrainingRepository.save(chekIdKaryawanTraining);
            return templateResponse.templateSukses(doSave);
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map delete(Long id) {
        try {
            if (templateResponse.chekNull(id)) {
                return templateResponse.templateEror("Id Karyawan Training is required");
            }
            KaryawanTraining chekIdKaryawanTraining = karyawanTrainingRepository.getbyID(id);
            if (templateResponse.chekNull(chekIdKaryawanTraining)) {
                return templateResponse.templateEror("Id Karyawan Training Not found");
            }

//           update , tanggal deleted saja
            chekIdKaryawanTraining.setDeleted_date(new Date());//
            karyawanTrainingRepository.save(chekIdKaryawanTraining);

            return templateResponse.templateSukses("Berhasil Menghapus");

        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }

//    @Override
//    public Map getAll(int size, int page) {
//        try {
//            Pageable show_data = PageRequest.of(page, size);
//            Page<KaryawanTraining> list = karyawanTrainingRepository.getAllData(show_data);
//            return templateResponse.templateSukses(list);
//        } catch (Exception e) {
//            return templateResponse.templateEror(e);
//        }
//
//    }
}
package com.binar.grab.service.impl;

import com.binar.grab.model.Karyawan;
import com.binar.grab.model.Rekening;
import com.binar.grab.repository.RekeningRepository;
import com.binar.grab.repository.KaryawanRepository;
import com.binar.grab.service.RekeningService;
import com.binar.grab.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

@Service
public class RekeningImpl implements RekeningService {

    @Autowired
    public RekeningRepository rekeningRepository;
    @Autowired
    public TemplateResponse templateResponse;
    @Autowired
    public KaryawanRepository karyawanRepository;




    public static final Logger log = LoggerFactory.getLogger(RekeningImpl.class);


    @Override
    public Map insert(Rekening obj, Long idkaryawan) {
        try{
            if (templateResponse.chekNull(obj.getNama())) {
                return templateResponse.templateEror("Nama tidak boleh kosong");
            }
            if (templateResponse.chekNull(obj.getJenis())) {
                return templateResponse.templateEror("Jenis Rekening tidak boleh kosong");
            }
            if (templateResponse.chekNull(obj.getNomor())) {
                return templateResponse.templateEror("Nomor Rekening tidak boleh kosong");
            }
            Karyawan checkId = karyawanRepository.getbyId(idkaryawan);
            if (templateResponse.chekNull(checkId)) {
                return templateResponse.templateEror("ID karyawan tidak ditemukan");
            }

//           Save kedatabase ada disini
            obj.setKaryawan(checkId);
            Rekening rekeningSave = rekeningRepository.save(obj);
            return templateResponse.templateSukses(rekeningSave);
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }


    @Override
    public Map update(Rekening obj, Long idkaryawan) {
        try {
/*
1. chek id supplirt - =takutnyanya suppliar tidak ada
2. chek id barang - apakah ada atau ga di db
3. simpan ke database
 */
            if (templateResponse.chekNull(idkaryawan)) {
                return templateResponse.templateEror("Id Karyawan is requiered");
            }
//            1. che id supplirt - =takutnyanya suppliar
            Karyawan chekId = karyawanRepository.getbyId(idkaryawan);
            if (templateResponse.chekNull(chekId)) {
                return templateResponse.templateEror("Id Karyawan Not found");
            }
            if (templateResponse.chekNull(obj.getId())) {
                return templateResponse.templateEror("Id Rekening is required");
            }
//            2. chek id rekening - apakah ada atau ga
            Rekening chekIdRekening = rekeningRepository.getbyID(obj.getId());
            if (templateResponse.chekNull(chekIdRekening)) {
                return templateResponse.templateEror("Id Rekening Not found");
            }
//            3. simpan database : update
            chekIdRekening.setNama(obj.getNama());
            chekIdRekening.setJenis(obj.getJenis());
            chekIdRekening.setNomor(obj.getNomor());
            Rekening dosave = rekeningRepository.save(chekIdRekening);

            return templateResponse.templateSukses(dosave);
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }

    }

    @Override
    public Map delete(Long idRekening) {
         /*
        soft delete , bukan delete permanent
        1. chek id barang
        2. update , tanggal deleted saja
         */
        try {
            if (templateResponse.chekNull(idRekening)) {
                return templateResponse.templateEror("Id Rekening is required");
            }
            //            1. chek id rekening
            Rekening chekIdRekening = rekeningRepository.getbyID(idRekening);
            if (templateResponse.chekNull(chekIdRekening)) {
                return templateResponse.templateEror("Id Rekening Not found");
            }

//            2. update , tanggal deleted saja
            chekIdRekening.setDeleted_date(new Date());//
            rekeningRepository.save(chekIdRekening);

            return templateResponse.templateSukses("Berhasil Menghapus");

        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }

    }

//    @Override
//    public Map getAll(int size, int page) {
//        try {
//            Pageable show_data = PageRequest.of(page, size);
//            Page<Rekening> list = rekeningRepository.getAllData(show_data);
//            return templateResponse.templateSukses(list);
//        } catch (Exception e) {
//            log.error("ada eror di method getAll:" + e);
//            return templateResponse.templateEror(e);
//        }
//
//    }

//    @Override
//    public Map findByNama(String nama, Integer page, Integer size) {
//        try {
//           /*
//           1. buat query dulu where nama rekening
//            */
//            Pageable show_data = PageRequest.of(page, size);
//            Page<Rekening> list = rekeningRepository.findByNama(nama, show_data);
//            return templateResponse.templateSukses(list);
//        } catch (Exception e) {
//
//            log.error("eror disini findByNama : " + e);
//            //menampilkan responose
//            return templateResponse.templateEror(e);
//        }
//
//    }
//
//    @Override
//    public Page<Rekening> findByNamaLike(String nama, Pageable pageable) {
//        try {
//           /*
//           1. buat query dulu where nama barang = like
//            */
//            Page<Rekening> list = rekeningRepository.findByNamaLike("'%" + nama + "%'", pageable);
////             public Page<Rekening> findByNamaLike(String nama , Pageable pageable);
//            return list;
//        } catch (Exception e) {
//            // manampilkan di terminal saja
//            log.error("ada eror di method findByNamaLike:" + e);
//            return null;
//        }
//
//    }
}

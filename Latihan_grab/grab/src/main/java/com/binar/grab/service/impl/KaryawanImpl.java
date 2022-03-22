package com.binar.grab.service.impl;

import com.binar.grab.model.DetailKaryawan;
import com.binar.grab.model.Karyawan;
import com.binar.grab.repository.DetailKaryawanRepository;
import com.binar.grab.repository.KaryawanRepository;
import com.binar.grab.service.KaryawanService;
import com.binar.grab.util.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class KaryawanImpl implements KaryawanService {
    public static final Logger log = LoggerFactory.getLogger(KaryawanImpl.class);

    @Autowired
    public TemplateResponse templateResponse;
    @Autowired
    public KaryawanRepository karyawanRepository;

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

            if (templateResponse.chekNull(obj.getDetailKaryawan().getNik())) {
                return templateResponse.templateEror("Nik is Requiered");
            }

            //3. Insert Data Karyawan
            Karyawan karyawanData = new Karyawan();
            karyawanData.setNama(obj.getNama());
            karyawanData.setJk(obj.getJk());
            karyawanData.setDob(obj.getDob());
            karyawanData.setAlamat(obj.getAlamat());
            karyawanData.setStatus(obj.getStatus());
            Karyawan saveKaryawan = karyawanRepository.save(karyawanData);

            //4. insert Detail Karyawan: set FK dari Karyawan
            DetailKaryawan detailKaryawanData = new DetailKaryawan();
            detailKaryawanData.setNik(obj.getDetailKaryawan().getNik());
            detailKaryawanData.setNpwp(obj.getDetailKaryawan().getNpwp());
            detailKaryawanData.setKaryawan(saveKaryawan);

            DetailKaryawan saveDetailKaryawan = detailKaryawanRepository.save(detailKaryawanData);

            return templateResponse.templateSukses(saveDetailKaryawan);
        }catch (Exception e){
            return templateResponse.templateEror(e);
        }

    }

    @Override
    public Map update(Karyawan obj) {
        try{

            //1. Validasi
            // Cek apakah request dari klien kosong atau tidak
            if(templateResponse.chekNull(obj.getId())){
                return templateResponse.templateEror("Id tidak boleh kosong");
            }
            if (templateResponse.chekNull(obj.getDetailKaryawan())) {
                return templateResponse.templateEror("Detail Karyawan Wajib ada");
            }
            if (templateResponse.chekNull(obj.getDetailKaryawan().getNik())) {
                return templateResponse.templateEror("Nik is Requiered");
            }

            Karyawan update = karyawanRepository.getbyId(obj.getId());
            // Cek apakah id dari database dengan request klien ada atau tidak
            if (templateResponse.chekNull(update)){
                return templateResponse.templateEror("Id tidak ditemukan");
            }
            // Update data
            update.setNama(obj.getNama());
            update.setJk(obj.getJk());
            update.setDob(obj.getDob());
            update.setAlamat(obj.getAlamat());
            update.setStatus(obj.getStatus());

            update.getDetailKaryawan().setNik(obj.getDetailKaryawan().getNik());
            update.getDetailKaryawan().setNpwp(obj.getDetailKaryawan().getNpwp());

            // Save data
            Karyawan save = karyawanRepository.save(update);
            return templateResponse.templateSukses(save);
        }catch (Exception e){
            log.error("Error pada method update Karyawan");
            return templateResponse.templateEror(e);
        }

    }

    @Override
    public Map delete(Long idkaryawan) {
        try {
            if (templateResponse.chekNull(idkaryawan)) {
                return templateResponse.templateEror("Id Karyawan is required");
            }
            // 1. chek id Karyawan
            Karyawan chekIdKaryawwan = karyawanRepository.getbyId(idkaryawan);
            if (templateResponse.chekNull(chekIdKaryawwan)) {
                return templateResponse.templateEror("Id Karyawan Not found");
            }
            // 2. update , tanggal deleted saja
            chekIdKaryawwan.setDeleted_date(new Date());//
            karyawanRepository.save(chekIdKaryawwan);

            return templateResponse.templateSukses("Berhasil Menghapus");

        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }



        }
}

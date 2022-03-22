package com.binar.grab.service.impl;


import com.binar.grab.model.Karyawan;
import com.binar.grab.repository.KaryawanRepository;
import com.binar.grab.service.KaryawanTymeleafService;
import com.binar.grab.util.BadResourceException;
import com.binar.grab.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class KaryawanTymeleafImpl implements KaryawanTymeleafService {

    @Autowired
    public KaryawanRepository karyawanRepository;

    @Override
    public List<Karyawan> listKaryawanTymeleaf(int pageNumber, int ROW_PER_PAGE) {
        List<Karyawan> karyawans = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, ROW_PER_PAGE,
                Sort.by("id").ascending());
        karyawanRepository.findAll(sortedByIdAsc).forEach(karyawans::add);
        return  karyawans;
    }

    @Override
    public boolean existsByIdTymeleaf(Long id) {
        return karyawanRepository.existsById(id);
    }

    @Override
    public Karyawan findByIdTymeleaf(Long id) throws ResourceNotFoundException {
        Karyawan karyawan = karyawanRepository.findById(id).orElse(null);
        if (karyawan==null) {
            throw new ResourceNotFoundException("Tidak dapat menemukan id karyawan: " + id);
        }
        else return karyawan;
    }

    @Override
    public Karyawan saveTymeleaf(Karyawan karyawan) throws BadResourceException {
        //        if (!StringUtils.isEmpty(barang.getNama())) {
        return karyawanRepository.save(karyawan);
//        }
//        else {
//            BadResourceException exc = new BadResourceException("Failed to save barang");
//            exc.addErrorMessage("barang is null or empty");
//            throw exc;
//        }
    }

    @Override
    public void updateTymeleaf(Karyawan karyawan) throws ResourceNotFoundException, BadResourceException {
        //        if (!StringUtils.isEmpty(barang.getNama())) {
        if (!existsByIdTymeleaf(karyawan.getId())) {
            throw new ResourceNotFoundException("Tidak dapat menemukan id Karyawan : " + karyawan.getId());
        }
        karyawanRepository.save(karyawan);
//        }
//        else {
//            BadResourceException exc = new BadResourceException("Failed to save Barang");
//            exc.addErrorMessage("Barang is null or empty");
//            throw exc;
//        }

    }

    @Override
    public void deleteByIdTymeleaf(Long id) throws ResourceNotFoundException {
        if (!existsByIdTymeleaf(id)) {
            throw new ResourceNotFoundException("Tidak dapat menemukan id Karyawan :" + id);
        }
        else {
            Karyawan a = karyawanRepository.getbyId(id);
            a.setDeleted_date(new Date());
            karyawanRepository.save(a);
        }

    }
}

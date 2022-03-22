package com.binar.grab.service;

import com.binar.grab.model.Rekening;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface RekeningService {
    public Map insert(Rekening obj, Long idkaryawan);

    public Map update(Rekening obj, Long idkaryawan);

    public Map delete(Long idRekening);

//    public Map getAll(int size, int page);
//
//    public Map findByNama(String nama, Integer page, Integer size);
//
//    Page<Rekening> findByNamaLike(String nama, Pageable pageable);

}

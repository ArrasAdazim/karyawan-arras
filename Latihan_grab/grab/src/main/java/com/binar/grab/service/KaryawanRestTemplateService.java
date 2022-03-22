package com.binar.grab.service;

import com.binar.grab.model.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface KaryawanRestTemplateService {
    public Map insert(Karyawan obj);

    public Map update(Karyawan obj);

    public Map delete(Long idKaryawan);

    public Map getAll(int size, int page);

}

package com.binar.grab.service;

import com.binar.grab.model.Karyawan;

import java.util.Map;

public interface KaryawanService {

    public Map insert(Karyawan obj);
    public Map update(Karyawan obj);
    public Map delete(Long idKaryawan);

}

package com.binar.grab.service;

import com.binar.grab.dao.request.KaryawanTrainingRequest;

import java.util.Map;

public interface KaryawanTrainingService {
    public Map simpan(KaryawanTrainingRequest obj);

    public Map update(KaryawanTrainingRequest obj);

    public Map delete(Long id);

//    Map getAll(int size, int page);
}

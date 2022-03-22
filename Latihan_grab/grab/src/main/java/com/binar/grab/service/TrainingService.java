package com.binar.grab.service;

import com.binar.grab.model.Training;

import java.util.Map;

public interface TrainingService {
    public Map simpan (Training obj);
    public Map update (Training obj);
    public Map delete (Long idTraining);
}

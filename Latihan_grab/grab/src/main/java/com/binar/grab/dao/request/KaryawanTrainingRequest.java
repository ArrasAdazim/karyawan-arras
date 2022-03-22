package com.binar.grab.dao.request;

import lombok.Data;

import java.util.Date;

@Data
public class KaryawanTrainingRequest {
    public Long idKaryawan;
    public Long idTraining;
    public Date tanggalTraining;
    public Long id;//id KaryawanTraining
}

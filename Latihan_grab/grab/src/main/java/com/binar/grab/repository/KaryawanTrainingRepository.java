package com.binar.grab.repository;

import com.binar.grab.model.KaryawanTraining;
import com.binar.grab.model.Rekening;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface KaryawanTrainingRepository extends
        PagingAndSortingRepository<KaryawanTraining, Long> {

    @Query("select c from KaryawanTraining c WHERE c.id = :id")
    public KaryawanTraining getbyID(@Param("id") Long id);

//    public Page<KaryawanTraining> findByNamaKaryawan(String nama , Pageable pageable);
//
//    public Page<KaryawanTraining> findByTraining(String nama , Pageable pageable);

//    @Query("select c from KaryawanTraining c where c.tanggalTraining= :tanggalTraining")// nama class
//    public Page<Rekening> findByTanggal(Date tanggal , Pageable pageable);

    public KaryawanTraining findKaryawanTrainingByTanggalTrainingLike(String nama);

    @Query("select c from KaryawanTraining c")// nama class
    public Page<KaryawanTraining> getAllData(Pageable pageable);

//    @Query("select k3 from karyawan k3  full join karyawantraining k on k3.id =k.id where k3.nama like %:nama% ")// nama class
//    public Page<Rekening> findByNamaKaryawan(String nama , Pageable pageable);


}


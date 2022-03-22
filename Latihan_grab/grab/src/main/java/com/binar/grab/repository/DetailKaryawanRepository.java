package com.binar.grab.repository;

import com.binar.grab.model.DetailKaryawan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface DetailKaryawanRepository extends
        PagingAndSortingRepository<DetailKaryawan, Long> {

    @Query("select c from DetailKaryawan c WHERE c.id = :id")
    public DetailKaryawan getbyID(@Param("id") Long id);
}


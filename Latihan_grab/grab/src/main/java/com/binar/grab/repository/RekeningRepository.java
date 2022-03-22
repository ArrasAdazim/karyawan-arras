package com.binar.grab.repository;

import com.binar.grab.model.Rekening;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface RekeningRepository extends PagingAndSortingRepository<Rekening, Long> {

    @Query("select c from Rekening c")// nama class
    public Page<Rekening> getAllData(Pageable pageable);

    @Query("select c from Rekening c WHERE c.id = :idrekening")// titik dua menunjukkan parameter
    public Rekening getbyID(@Param("idrekening") Long idbebas);

    @Query("select c from Rekening c where c.nama= :nama")// nama class
    public Page<Rekening> findByNama(String nama , Pageable pageable);

    @Query("select c from Rekening c where c.nama like %:nama%")// nama class
    public Page<Rekening> findByNamaLike(String nama , Pageable pageable);

}

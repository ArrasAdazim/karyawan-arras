package com.binar.grab.repository;

import com.binar.grab.model.Training;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends
        PagingAndSortingRepository<Training, Long> {

    @Query("select c from Training c WHERE c.id = :id")
    public Training getbyID(@Param("id") Long id);

}

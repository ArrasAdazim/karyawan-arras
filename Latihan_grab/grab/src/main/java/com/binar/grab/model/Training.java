package com.binar.grab.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "training")
@Where(clause = "deleted_date is null")
public class Training extends AbstractDate implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tema", nullable = false)
    private String tema;

    @Column(name = "namaPengajar", nullable = false)
    private String namaPengajar;

    @OneToMany(mappedBy = "training")
    List<KaryawanTraining> karyawanTraining;
}


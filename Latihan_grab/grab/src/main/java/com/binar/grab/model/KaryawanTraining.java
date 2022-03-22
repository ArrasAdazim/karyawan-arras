package com.binar.grab.model;


import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "karyawantraining")
@Where(clause = "deleted_date is null")
public class KaryawanTraining extends AbstractDate implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_training")
    Training training;

    @ManyToOne
    @JoinColumn(name = "id_karyawan")
    Karyawan karyawan;

    private Date tanggalTraining;
}


package com.binar.grab.model;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "karyawan")
@Where(clause = "deleted_date is null")
public class Karyawan extends AbstractDate implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama", nullable = false, length = 45)
    private String nama;

    @Column(name = "jk", nullable = false)
    private String jk;

    @Column(name = "dob",nullable = true)
    private Date dob;

    @Column(name = "alamat", columnDefinition="TEXT")
    private String alamat;

    @Column(name = "status")
    private String status;

//    private String jk;
//    private Date dob;
//    private String alamat;
//    private String status;

    //RELASI ONE-TO-ONE//
    @OneToOne(mappedBy = "karyawan")
    private DetailKaryawan detailKaryawan;


    //RELASI ONE-TO-MANY//
    @OneToMany(mappedBy = "karyawan")
    List<Rekening> rekening;

    //RELASI ONE-TO-MANY//
    @OneToMany(mappedBy = "karyawan")
    List<KaryawanTraining> karyawanTraining;
}

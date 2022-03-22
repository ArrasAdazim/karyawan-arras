package com.binar.grab.junit;


import com.binar.grab.model.DetailKaryawan;
import com.binar.grab.model.Karyawan;
import com.binar.grab.service.KaryawanRestTemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTemplateUnitTestController {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    public KaryawanRestTemplateService karyawanRestTemplateService;


    @Test
    public void simpanKaryawan() throws ParseException {

        Karyawan karyawan = new Karyawan();
        DetailKaryawan detailKaryawan = new DetailKaryawan();

        karyawan.setNama("Arras Unit Test");
        karyawan.setJk("Laki-Laki");
        karyawan.setDob(new SimpleDateFormat("dd-MM-yyyy").parse("22-03-2001"));
        karyawan.setAlamat("Jl.Premix RT 52 Blok A No 15 Kota Jambi,Jambi");
        karyawan.setStatus("Aktif");

        detailKaryawan.setNik("22032001");
        detailKaryawan.setNpwp("8020190130");

        karyawan.setDetailKaryawan(detailKaryawan);

        Map map = karyawanRestTemplateService.insert(karyawan);
        assertEquals("200", map.get("status"));
        if (map.get("status").equals("200")) {
            System.out.println(map.get("data"));
            System.out.println(map.get("status"));
            System.out.println(map.get("message"));
        } else {
            System.out.println("terjadi eror");
        }
    }

    @Test
    public void updateKaryawan() throws ParseException {
        Karyawan updateKaryawan = new Karyawan();
        DetailKaryawan detailKaryawan = new DetailKaryawan();

        updateKaryawan.setId(41L);
        updateKaryawan.setNama("Arras Unit Test Update");
        updateKaryawan.setJk("Laki-Laki");
        updateKaryawan.setDob(new SimpleDateFormat("dd-MM-yyyy").parse("22-03-2001"));
        updateKaryawan.setAlamat("Update Jl.Premix RT 52 Blok A No 15 Kota Jambi,Jambi");
        updateKaryawan.setStatus("Aktif Selesai Update");

        detailKaryawan.setNik("22032001");
        detailKaryawan.setNpwp("8020190130");

        updateKaryawan.setDetailKaryawan(detailKaryawan);

        Map map = karyawanRestTemplateService.update(updateKaryawan);
        assertEquals("200", map.get("status"));
        if (map.get("status").equals("200")) {
            System.out.println(map.get("data"));
            System.out.println(map.get("status"));
            System.out.println(map.get("message"));
        } else {
            System.out.println("terjadi eror");
        }

    }

    @Test
    public void deleteKaryawan() {
        Map map = karyawanRestTemplateService.delete(40L);

        if (map.get("status").equals("200")) {
            System.out.println(map.get("data"));
            System.out.println(map.get("status"));
            System.out.println(map.get("message"));
        } else {
            System.out.println("terjadi eror");
        }
    }

    @Test
    public void getKaryawan() {
        Map map = karyawanRestTemplateService.getAll(10,0);

        if (map.get("status").equals("200")) {
            System.out.println(map.get("data"));
            System.out.println(map.get("status"));
            System.out.println(map.get("message"));
        } else {
            System.out.println("terjadi eror");
        }


    }
}

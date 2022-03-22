package com.binar.grab.controller;


import com.binar.grab.model.Karyawan;
import com.binar.grab.repository.KaryawanRepository;
import com.binar.grab.service.KaryawanTymeleafService;
import com.binar.grab.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/v1/view/karyawan")

public class KaryawanTymeleafController {

    @Autowired
    public KaryawanRepository karyawanRepository;

    @Autowired
    public KaryawanTymeleafService karyawanTymeleafService;

    private final int ROW_PER_PAGE = 5;

    //    Index Page
    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", "Title Saya");
        return "index";
    }

    @GetMapping(value = "/list")
    public String getKaryawan(Model model,
                            @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        List<Karyawan> karyawans = karyawanTymeleafService.listKaryawanTymeleaf(pageNumber, ROW_PER_PAGE);

        long count = karyawanRepository.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("karyawans", karyawans);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        return "karyawan-list";
    }

    @GetMapping(value = {"/add"})
    public String showAddKaryawan(Model model) {
        Karyawan karyawan = new Karyawan();
        model.addAttribute("add", true);
        model.addAttribute("karyawan", karyawan);

        return "karyawan-edit";
    }

    @PostMapping(value = "/add")
    public String addKaryawan(Model model,
                            @ModelAttribute("karyawan") Karyawan karyawan) {
        try {
            karyawan.setDob(new Date());
            System.out.println("Nama Karyawan="+karyawan.getNama());
            Karyawan newKayawan = karyawanTymeleafService.saveTymeleaf(karyawan);
            return "redirect:/v1/view/karyawan/" + String.valueOf(newKayawan.getId());
        } catch (Exception ex) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            //model.addAttribute("barang", barang);
            model.addAttribute("add", true);
            return "karyawan-edit";
        }
    }

    @GetMapping(value = {"/{karyawanId}/edit"})
    public String showEditKaryawan(Model model, @PathVariable long karyawanId) {
        Karyawan karyawan = null;
        try {
            karyawan = karyawanTymeleafService.findByIdTymeleaf(karyawanId);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Karyawan Tidak ada");
        }
        model.addAttribute("add", false);
        model.addAttribute("karyawan", karyawan);
        return "karyawan-edit";
    }

    @PostMapping(value = {"/{karyawanId}/edit"})
    public String updateKaryawan(Model model,
                               @PathVariable long karyawanId,
                               @ModelAttribute("karyawan") Karyawan karyawan) {
        try {
            karyawan.setDob(new Date());
            karyawan.setId(karyawanId);
            karyawanTymeleafService.updateTymeleaf(karyawan);
            return "redirect:/v1/view/karyawan/" + String.valueOf(karyawan.getId());
        } catch (Exception ex) {
            // log exception first,
            // then show error
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);

            model.addAttribute("add", false);
            return "karyawan-edit";
        }
    }

    @GetMapping(value = "/{karyawanId}")
    public String getKaryawanById(Model model, @PathVariable long karyawanId) {
        Karyawan karyawan = null;
        try {
            karyawan = karyawanTymeleafService.findByIdTymeleaf(karyawanId);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Karyawan Tidak ada");
        }
        model.addAttribute("karyawan", karyawan);
        return "karyawan";
    }

    //delete
    @GetMapping(value = {"/{karyawanId}/delete"})
    public String showDeleteKaryawanById(
            Model model, @PathVariable long karyawanId) {
        Karyawan karyawan = null;
        try {
            karyawan = karyawanTymeleafService.findByIdTymeleaf(karyawanId);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Karyawan Tidak ada");
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("karyawan", karyawan);
        return "karyawan";
    }

    @PostMapping(value = {"/{karyawanId}/delete"})
    public String deleteKaryawanById(
            Model model, @PathVariable long karyawanId) {
        try {
            karyawanTymeleafService.deleteByIdTymeleaf(karyawanId);
            return "redirect:/v1/view/karyawan/list";
        } catch (ResourceNotFoundException ex) {
            String errorMessage = ex.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            return "karyawan";
        }
    }
}

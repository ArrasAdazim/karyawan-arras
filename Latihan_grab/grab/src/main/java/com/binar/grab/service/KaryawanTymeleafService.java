package com.binar.grab.service;

import com.binar.grab.model.Karyawan;
import com.binar.grab.util.BadResourceException;
import com.binar.grab.util.ResourceNotFoundException;

import java.util.List;

public interface KaryawanTymeleafService {

    public List<Karyawan> listKaryawanTymeleaf(int pageNumber, int ROW_PER_PAGE );

    public boolean existsByIdTymeleaf(Long id);

    public Karyawan findByIdTymeleaf(Long id) throws ResourceNotFoundException;

    public Karyawan saveTymeleaf(Karyawan karyawan) throws BadResourceException;

    public void updateTymeleaf(Karyawan karyawan) throws ResourceNotFoundException, BadResourceException;

    public void deleteByIdTymeleaf(Long id) throws ResourceNotFoundException;
}

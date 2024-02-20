package com.institute.ServiceImpl;

import java.rmi.ServerException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.institute.Model.Institute;
import com.institute.Repository.InstituteRepository;
import com.institute.Service.InstituteService;

@Service
public class InstituteServiceImpl implements InstituteService {

	@Autowired
	InstituteRepository instituteRepo;

	private static final Logger logger=LoggerFactory.getLogger(InstituteServiceImpl.class);
	@Override
	public void saveInstitute(Institute institute) {
		try
		{
		instituteRepo.save(institute);
		}
		catch (DataAccessException ex) {

			logger.error("Error occured while saving in db",ex);
		}
	}

	@Override
	public Optional<Institute> checkIfInstituteAlreadyExists(long i) {
		Optional<Institute> findById = instituteRepo.findById(i);
		return findById;
	}

	@Override
	public Optional<Institute> findByInstituteName(String instituteName) {
		Optional<Institute> findByInstituteName = instituteRepo.findByInstituteName(instituteName);
		return findByInstituteName;
	}

}

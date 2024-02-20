package com.institute.Controller;

import java.rmi.ServerException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.institute.Constants.Constants;
import com.institute.Model.Institute;
import com.institute.Model.ResponseModel;
import com.institute.Service.InstituteService;
import com.institute.ServiceImpl.InstituteServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/institute")
public class MainInstituteController {
	private static final Logger logger = LoggerFactory.getLogger(MainInstituteController.class);

	@Autowired
	InstituteService instituteService;

	@PostMapping("/register")
	public ResponseEntity<?> registerInstitute(@RequestBody @Valid Institute institute) {
		logger.info("Entering registerInstitute with" + institute.toString());
		try {
			Optional<Institute> checkIfInstituteAlreadyExists = instituteService
					.findByInstituteName(institute.getInstituteName());
			if (checkIfInstituteAlreadyExists.isEmpty()) {
				instituteService.saveInstitute(institute);
				ResponseModel<Institute> response = new ResponseModel<Institute>(institute,
						Constants.INSTITUTE.REGISTERED);
				logger.info("Exiting registerInstitute with" + response.toString());

				return ResponseEntity.ok(response);
			} else {
				logger.info("Exiting registerInstitute without saving because institute already exists"
						+ checkIfInstituteAlreadyExists.get().toString());

				return ResponseEntity.badRequest().body(Constants.INSTITUTE.ALREADY_REGISTERED + institute);
			}
		} catch (DataAccessException ex) {
			logger.error("Error occured while saving", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occured while saving");

		}

	}

	@PatchMapping("/updateInstitute")
	public ResponseEntity<?> updateInstitute(@RequestBody  Institute institute) {

		Optional<Institute> doesExist = instituteService.checkIfInstituteAlreadyExists(institute.getInstituteId());

		if (doesExist.isPresent()) {
			Institute existingInstitute = doesExist.get();
			existingInstitute.setInstituteName(institute.getInstituteName());
			existingInstitute.setLocation(institute.getLocation());
			existingInstitute.setPhoneNumber(institute.getPhoneNumber());
			instituteService.saveInstitute(existingInstitute);
			ResponseModel<Institute> response = new ResponseModel<Institute>(existingInstitute,
					Constants.INSTITUTE.UPDATED_INSTITUE);
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().body(Constants.INSTITUTE.UNREGISTERED);

	}

	@GetMapping("/getInstitute/{instituteId}")
	public ResponseEntity<?> getInstituteById(@PathVariable long instituteId) {
		Optional<Institute> checkIfInstituteAlreadyExists = instituteService.checkIfInstituteAlreadyExists(instituteId);
		if (checkIfInstituteAlreadyExists.isPresent()) {
			ResponseModel<Institute> response = new ResponseModel<Institute>(checkIfInstituteAlreadyExists.get(),
					Constants.INSTITUTE.EXISTING_INSTITTUTE);

			return ResponseEntity.ok(response);
		}
		return ResponseEntity.badRequest().body(Constants.INSTITUTE.UNREGISTERED);

	}
}

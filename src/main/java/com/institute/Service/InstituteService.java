package com.institute.Service;

import java.util.Optional;

import com.institute.Model.Institute;

public interface InstituteService {

	void saveInstitute(Institute institute);

	Optional<Institute> checkIfInstituteAlreadyExists(long instituteId);


	Optional<Institute> findByInstituteName(String instituteName);


}

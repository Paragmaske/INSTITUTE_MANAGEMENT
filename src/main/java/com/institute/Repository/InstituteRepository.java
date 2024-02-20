package com.institute.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.institute.Model.Institute;

@Repository 
public interface InstituteRepository extends JpaRepository<Institute, Long>  {

	//@Query(value="SELECT * from Institute WHERE instituteName=:instituteName",nativeQuery = true)
	Optional<Institute> findByInstituteName(String instituteName);


}

package com.institute;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.institute.Model.Institute;
import com.institute.Repository.InstituteRepository;
import com.institute.Service.InstituteService;
import com.institute.ServiceImpl.InstituteServiceImpl;

public class InstituteServiceTests {

	@InjectMocks
	InstituteServiceImpl instituteServiceImpl;

	@Mock
	InstituteRepository instituteRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void checkIfInstituteAlreadyExistsTest() {
		Optional<Institute> instituteWithId100 = Optional.of(new Institute(100, "Bhavans", "Nagpur", new Long(987654321)));
		when(instituteRepository.findById(new Long(100))).thenReturn(instituteWithId100);
		Optional<Institute> checkIfInstituteAlreadyExists = instituteServiceImpl.checkIfInstituteAlreadyExists(100);
		assertThat(instituteWithId100).isEqualTo(checkIfInstituteAlreadyExists);
	}
}

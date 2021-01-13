package se.sylwan.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.PathVariable;

import se.sylwan.model.Plane;
import se.sylwan.repository.PlaneRepository;

//@WebMvcTest
class TravelControllerTest {
	
//	@Autowired
//	MockMvc mockMvc;
	
//	@MockBean
//	private PlaneRepository planeRepository;
	
	@Test
	void getPlaneByName() {
		
		TravelController travelController = new TravelController();
		assertThrows(NullPointerException.class,
	            ()->{ travelController.getPlaneByName("Not a plane");});		
	}
	
//	@Test
//	void getPlaneByName2() throws Exception {
//		
//		mockMvc.perform(
//				MockMvcRequestBuilders.get("/api/v1/airports/all")
//				.accept(MediaType.APPLICATION_JSON))
//				.andReturn();
//		
//		Mockito.when(planeRepository.findAll()).thenReturn(Collections.emptyList());
//		
//		TravelController travelController = new TravelController();
//		
//		verify(planeRepository).findAll();
//
//	}

}

package com.br.knowledge.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.br.knowledge.model.WorkedTime;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkedTimeServiceTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private WorkedTimeService workedTimeService;

	@Test
	void postWorkedTime() throws Exception {
		WorkedTime workedTime = new WorkedTime(1, LocalDate.of(2022, 04, 05), LocalTime.of(8, 30), LocalTime.of(17, 30));
		mockMvc.perform(post("/workedTime")
		        .contentType("application/json")
		        .content(mapper.writeValueAsString(workedTime)))
		        .andExpect(status().isCreated());
	}
	
	@Test
	void arrivalTimeCanNotBeAfterDepartureTime () {
		WorkedTime workedTime = new WorkedTime(1, LocalDate.of(2022, 3, 30), LocalTime.now().plusHours(1), LocalTime.now());
		
		RuntimeException exception = assertThrows(RuntimeException.class,
					() -> workedTimeService.validIfArrivalTimeIsGreaterThanDepartureTime(workedTime));
		
		assertThat(exception.getMessage().contains("Erro"));
	}
	
	@Test
	void validLuchTime() {
		WorkedTime workedTime = new WorkedTime(1, LocalDate.of(2022, 3, 30), LocalTime.now(), LocalTime.now().plusHours(8));
		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> workedTimeService.validLunchTime(workedTime));
		
		assertThat(exception.getMessage().contains("Erro"));
	}
	
	@Test
	void validWeekendTimeSaturday() {
		WorkedTime workedTime = new WorkedTime(1, LocalDate.of(2022, 3, 26), LocalTime.now(), LocalTime.now());
		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> workedTimeService.validWeekendTime(workedTime));
		
		assertThat(exception.getMessage().contains("Erro"));
	}
	
	
	@Test
	void validWeekendTimeSunday() {
		WorkedTime workedTime = new WorkedTime(1, LocalDate.of(2022, 3, 27), LocalTime.now(), LocalTime.now());
		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> workedTimeService.validWeekendTime(workedTime));
		
		assertThat(exception.getMessage().contains("Erro"));
	}
}

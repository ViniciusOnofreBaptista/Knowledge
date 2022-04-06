package com.br.knowledge.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.br.knowledge.model.WorkedTime;
import com.br.knowledge.model.Worker;
import com.br.knowledge.repository.Periodo;
import com.br.knowledge.repository.WorkedTimeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkedTimeServiceTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private WorkedTimeService workedTimeService;

	@MockBean
	private WorkedTimeRepository workedTimeRepo;

	@Test
	void postWorkedTime() throws Exception {
		WorkedTime workedTime = new WorkedTime(1, LocalDate.of(2022, 04, 05), LocalTime.of(8, 30), LocalTime.of(17, 30),
				Periodo.MANHA, new Worker(1, "Batman"));
		mockMvc.perform(
				post("/workedTime").contentType("application/json").content(mapper.writeValueAsString(workedTime)))
				.andExpect(status().isCreated());
	}

	@Test
	void arrivalTimeCanNotBeAfterDepartureTime() {
		WorkedTime workedTime = new WorkedTime(1, LocalDate.of(2022, 3, 30), LocalTime.now().plusHours(1),
				LocalTime.now(), Periodo.MANHA, new Worker(1, "Batman"));

		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> workedTimeService.validIfArrivalTimeIsGreaterThanDepartureTime(workedTime));

		assertThat(exception.getMessage().contains("Erro"));
	}

	@Test
	void validLuchTime() {
		WorkedTime workedTime = new WorkedTime(1, LocalDate.of(2022, 3, 30), LocalTime.now(),
				LocalTime.now().plusHours(8), Periodo.MANHA, new Worker(1, "Batman"));
		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> workedTimeService.validLunchTime(workedTime));

		assertThat(exception.getMessage().contains("Erro"));
	}

	@Test
	void validWeekendTimeSaturday() {
		WorkedTime workedTime = new WorkedTime(1, LocalDate.of(2022, 3, 26), LocalTime.now(), LocalTime.now(),
				Periodo.MANHA, new Worker(1, "Batman"));
		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> workedTimeService.validWeekendTime(workedTime));

		assertThat(exception.getMessage().contains("Erro"));
	}

	@Test
	void validWeekendTimeSunday() {
		WorkedTime workedTime = new WorkedTime(1, LocalDate.of(2022, 3, 27), LocalTime.now(), LocalTime.now(),
				Periodo.MANHA, new Worker(1, "Batman"));
		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> workedTimeService.validWeekendTime(workedTime));

		assertThat(exception.getMessage().contains("Erro"));
	}

	@Test
	void updateWorkedTimeFailed() throws Exception {
		Optional<WorkedTime> op = Optional.of(new WorkedTime(1, LocalDate.now(), LocalTime.now().plusMinutes(10),
				LocalTime.now().plusHours(9), Periodo.MANHA, new Worker(1, "Batman")));
		Mockito.when(workedTimeRepo.findByWorkerIdAndDay(1, LocalDate.now())).thenReturn(op);
		WorkedTime workedTime = new WorkedTime(1, LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(9),
				Periodo.MANHA, new Worker(1, "Batman"));
		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> workedTimeService.updateWorkedTime(workedTime));

		assertThat(exception.getMessage().contains("A hora"));
	}

	@Test
	void updateWorkedTimeSuccess() throws Exception {
		Optional<WorkedTime> mock = Optional.of(new WorkedTime(1, LocalDate.now(), LocalTime.now(),
				LocalTime.now().plusHours(9).plusMinutes(15), Periodo.MANHA, new Worker(1, "Batman")));
		Mockito.when(workedTimeRepo.findByWorkerIdAndDay(1, LocalDate.now())).thenReturn(mock);

		WorkedTime workedTime = new WorkedTime(1, LocalDate.now(), LocalTime.now().plusMinutes(15),
				LocalTime.now().plusHours(9).plusMinutes(15), Periodo.MANHA, new Worker(1, "Batman"));
		mockMvc.perform(
				put("/workedTime").contentType("application/json").content(mapper.writeValueAsString(workedTime)))
				.andExpect(status().isAccepted());
	}

}

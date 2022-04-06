package com.br.knowledge.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.br.knowledge.model.ProjectTimeWorked;
import com.br.knowledge.model.WorkedTime;
import com.br.knowledge.model.Worker;
import com.br.knowledge.repository.Periodo;
import com.br.knowledge.repository.ProjectTimeWorkedRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectTimeWorkedServiceTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private ProjectTimeWorkedService projectTimeWorkedService;
	
	@MockBean
	private WorkedTimeService serviceWorkedTime;
	
	@MockBean
	private ProjectTimeWorkedRepository projectTimeWorkedRepository;
	
	@Test
	void postProjectWorkedTime() throws Exception {
		ProjectTimeWorked projectTimeWorked = new ProjectTimeWorked("fabrica de memes", 5, LocalDate.now(), new Worker(1, "Batman"));
		WorkedTime workedTime = new WorkedTime(1, LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(9), Periodo.MANHA, new Worker(1, "Batman"));
		Mockito.when(serviceWorkedTime.findWorkedTimeByDayAndIdWorker(projectTimeWorked.getWorker().getId(), projectTimeWorked.getWorkedDay())).thenReturn(workedTime);
		mockMvc.perform(post("/project")
		        .contentType("application/json")
		        .content(mapper.writeValueAsString(projectTimeWorked)))
		        .andExpect(status().isCreated());
	}
	
	
	@Test
	void saveProjectTimeWorkerCantBeGreaterThanTimeWorked() throws Exception {
		List<ProjectTimeWorked> list= new ArrayList<ProjectTimeWorked>();
		Mockito.when(projectTimeWorkedRepository.findByWorkedDayAndWorkerId(LocalDate.now(), 1)).thenReturn(list);
		WorkedTime workedTime = new WorkedTime(1, LocalDate.now(), LocalTime.now(), LocalTime.now().plusHours(8), Periodo.MANHA, new Worker(1, "Batman"));
		ProjectTimeWorked projectTimeWorked = new ProjectTimeWorked("Fabricas de meme", 10, LocalDate.now(), new Worker(1, "Vinicius"));
		
		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> projectTimeWorkedService.ValidWorkedTimeIsNotLessThanProjectWorkedTime(workedTime, projectTimeWorked));
		
		assertThat(exception.getMessage().contains("Horas"));
	}
	
}

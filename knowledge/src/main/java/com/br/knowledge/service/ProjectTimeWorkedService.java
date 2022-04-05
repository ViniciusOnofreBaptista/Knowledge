package com.br.knowledge.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.knowledge.exception.BadArgumentsException;
import com.br.knowledge.model.ProjectTimeWorked;
import com.br.knowledge.model.WorkedTime;
import com.br.knowledge.repository.ProjectTimeWorkedRepository;

@Service
public class ProjectTimeWorkedService {

	@Autowired
	private ProjectTimeWorkedRepository projectTimeWorkedRepository;
	
	@Autowired WorkedTimeService workedTimeService;
	
	public ProjectTimeWorked saveProjectTimeWorked(ProjectTimeWorked projectTimeWorked) {
		WorkedTime workedTimeDb = workedTimeService.findWorkedTimeByDayAndIdWorker(projectTimeWorked.getWorker().getId(), projectTimeWorked.getWorkedDay());
		ValidWorkedTimeIsNotLessThanProjectWorkedTime(workedTimeDb, projectTimeWorked);
		return projectTimeWorkedRepository.save(projectTimeWorked);
	}
	
	public void ValidWorkedTimeIsNotLessThanProjectWorkedTime(WorkedTime workedTimeDb, ProjectTimeWorked projectTimeWorked) {
		Integer sumOfWorkedTimeInProjectsPlusWorkedTime = validIfExistsSomeWorkedProjectTime(projectTimeWorked.getWorker().getId(), projectTimeWorked.getWorkedDay(), projectTimeWorked.getTimeSpent());
		Integer timeWorked = getWorkedTime(workedTimeDb);
		if(timeWorked < sumOfWorkedTimeInProjectsPlusWorkedTime) {
			throw new BadArgumentsException("Horas trabalhadas não pode ser menor que as horas gastas no projeto");
		}
	}

	private Integer validIfExistsSomeWorkedProjectTime(Integer workedId, LocalDate workedDay, Integer timeSpent) {
		List<ProjectTimeWorked> projectsTimeWorked = projectTimeWorkedRepository.findByWorkedDayAndWorkerId(workedDay, workedId);
		for (ProjectTimeWorked projects : projectsTimeWorked) {
			timeSpent += projects.getTimeSpent();
		}
		return timeSpent;
	}
	
	private Integer getWorkedTime(WorkedTime workedTimeDb) {
		Integer timeWorked = workedTimeDb.getDepartureTime().getHour() - workedTimeDb.getArrivalTime().getHour();
		return timeWorked;
	}


}

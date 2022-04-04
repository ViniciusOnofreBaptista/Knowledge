package com.br.knowledge.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ProjectTimeWorked {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String projectName;
	
	private Integer timeSpent;
	
	private LocalDate workedDay;
	
	@OneToOne()
	@JoinColumn(name = "worker_id", referencedColumnName = "id")
	private Worker worker;

	
	
	public ProjectTimeWorked() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(Integer timeSpent) {
		this.timeSpent = timeSpent;
	}

	public LocalDate getWorkedDay() {
		return workedDay;
	}

	public void setWorkedDay(LocalDate workedDay) {
		this.workedDay = workedDay;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	
	
}

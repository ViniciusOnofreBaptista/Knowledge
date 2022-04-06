package com.br.knowledge.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.br.knowledge.repository.Periodo;

@Entity
@Table(name = "worked_time")
public class WorkedTime {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDate day;

	private LocalTime arrivalTime;

	private LocalTime departureTime;

	private Periodo period;

	@OneToOne()
	@JoinColumn(name = "worker_id", referencedColumnName = "id")
	private Worker worker;

	public WorkedTime() {
		super();
	}


	public WorkedTime(Integer id, LocalDate day, LocalTime arrivalTime, LocalTime departureTime, Periodo period,
			Worker worker) {
		super();
		this.id = id;
		this.day = day;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.period = period;
		this.worker = worker;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public Periodo getPeriod() {
		return period;
	}

	public void setPeriod(Periodo period) {
		this.period = period;
	}
}

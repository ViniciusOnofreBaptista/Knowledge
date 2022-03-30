package com.br.knowledge.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "worked_time")
public class WorkedTime {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDate day;
	
	private LocalTime arrivalTime;
	
	private LocalTime departureTime;

	
	
	public WorkedTime() {
		super();
	}

	public WorkedTime(Integer id, LocalDate day, LocalTime arrivalTime, LocalTime departureTime) {
		super();
		this.id = id;
		this.day = day;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
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
	
	
}

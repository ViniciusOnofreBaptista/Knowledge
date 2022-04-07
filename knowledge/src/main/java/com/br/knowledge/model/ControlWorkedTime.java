package com.br.knowledge.model;

public class ControlWorkedTime {

	private Integer workedTimeGoal;
	private Integer workedTimeConcluded;
	private Integer workedTimeRemaining;
	
	
	public ControlWorkedTime(Integer workedTimeGoal, Integer workedTimeConcluded, Integer workedTimeRemaining) {
		super();
		this.workedTimeGoal = workedTimeGoal;
		this.workedTimeConcluded = workedTimeConcluded;
		this.workedTimeRemaining = workedTimeRemaining;
	}
	public Integer getWorkedTimeGoal() {
		return workedTimeGoal;
	}
	public void setWorkedTimeGoal(Integer workedTimeGoal) {
		this.workedTimeGoal = workedTimeGoal;
	}
	public Integer getWorkedTimeConcluded() {
		return workedTimeConcluded;
	}
	public void setWorkedTimeConcluded(Integer workedTimeConcluded) {
		this.workedTimeConcluded = workedTimeConcluded;
	}
	public Integer getWorkedTimeRemaining() {
		return workedTimeRemaining;
	}
	public void setWorkedTimeRemaining(Integer workedTimeRemaining) {
		this.workedTimeRemaining = workedTimeRemaining;
	}
	
	
	
	
	
	
}

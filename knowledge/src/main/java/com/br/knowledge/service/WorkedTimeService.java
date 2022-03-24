package com.br.knowledge.service;

import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.knowledge.model.WorkedTime;
import com.br.knowledge.repository.WorkedTimeRepository;

@Service
public class WorkedTimeService {

	@Autowired
	private WorkedTimeRepository workedTimeRepo;
	
	public WorkedTime saveWorkedtTime(WorkedTime workedTime) {
		validWorkedTime(workedTime);
		return workedTimeRepo.save(workedTime);
	}
	
	public void validWorkedTime(WorkedTime workedTime) {
		validIfArrivalTimeIsGreaterThanDepartureTime(workedTime);
		validLunchTime(workedTime);
		validWeekendTime(workedTime);
	}


	private void validIfArrivalTimeIsGreaterThanDepartureTime(WorkedTime workedTime) {
		if(workedTime.getArrivalTime().isAfter(workedTime.getDepartureTime())){
			throw new RuntimeException("Erro ao salvar horas trabalhadas hora de chegada não pode ser maior que a hora de saida");
		}
	}

	private void validLunchTime(WorkedTime workedTime) {
	 if(workedTime.getDepartureTime().getHour() - workedTime.getArrivalTime().getHour() < 9) {
		throw new RuntimeException("Erro ao salvar horas trabalhadas hora de almoço precisa ser maior que uma hora");
	}
	}
	
	private void validWeekendTime(WorkedTime workedTime) {
		if(workedTime.getDay().getDayOfWeek().getValue() == 7 || workedTime.getDay().getDayOfWeek().getValue() == 6) {
			throw new RuntimeException("Erro ao salvar horas trabalhadas finais de semana não permitido");
		}
	}
}

 
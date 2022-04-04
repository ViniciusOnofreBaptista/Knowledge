package com.br.knowledge.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.knowledge.exception.BadArgumentsException;
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


	public void validIfArrivalTimeIsGreaterThanDepartureTime(WorkedTime workedTime) {
		if(workedTime.getArrivalTime().isAfter(workedTime.getDepartureTime())){
			throw new BadArgumentsException("Erro ao salvar horas trabalhadas hora de chegada não pode ser maior que a hora de saida");
		}
	}

	public void validLunchTime(WorkedTime workedTime) {
	 if(workedTime.getDepartureTime().getHour() - workedTime.getArrivalTime().getHour() < 9) {
		 throw new BadArgumentsException("Erro ao salvar horas trabalhadas hora de almoço precisa ser maior que uma hora");
	}
	}
	
	public void validWeekendTime(WorkedTime workedTime) {
		if(workedTime.getDay().getDayOfWeek().getValue() == DayOfWeek.SUNDAY.getValue() || workedTime.getDay().getDayOfWeek().getValue() == DayOfWeek.SATURDAY.getValue()) {
			throw new BadArgumentsException("Erro ao salvar horas trabalhadas finais de semana não permitido");
		}
	}
	
	public List<WorkedTime> getAllRegister(){
		return workedTimeRepo.findAll();
	}

	public WorkedTime findWorkedTimeByDayAndIdWorker(Integer id, LocalDate dayWorked) {
		Optional<WorkedTime> workedTimeDb = workedTimeRepo.findByWorkerIdAndDay(id, dayWorked);
		return workedTimeDb.get();
	}
	
}

 
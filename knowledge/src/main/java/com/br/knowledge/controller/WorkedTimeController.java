package com.br.knowledge.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.knowledge.model.WorkedTime;
import com.br.knowledge.service.WorkedTimeService;

@RestController()
@RequestMapping("/workedTime")
public class WorkedTimeController {

	@Autowired
	private WorkedTimeService workedTimeService;

	@PostMapping()
	public ResponseEntity<?> saveWorkedTime(@RequestBody WorkedTime workedTime) {
		 WorkedTime savedWorkedTime = workedTimeService.saveWorkedtTime(workedTime);
		return new ResponseEntity<>(savedWorkedTime, HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<?> getAllRegisters(){
		return new ResponseEntity<>(workedTimeService.getAllRegister(), HttpStatus.OK);
	}
	
	@GetMapping("/workedTimeByDayAndId")
	public ResponseEntity<?> addFoo(@RequestParam(name = "id") Integer id, @RequestParam LocalDate dayWorked) { 
	    WorkedTime findWorkedTimeByDayAndId = workedTimeService.findWorkedTimeByDayAndIdWorker(id, dayWorked);
		return new ResponseEntity<>(findWorkedTimeByDayAndId, HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<?> updateWorkedTime(@RequestBody WorkedTime workedTime){
		WorkedTime updateWorkedTime = workedTimeService.updateWorkedTime(workedTime);
		return new ResponseEntity<>(updateWorkedTime, HttpStatus.ACCEPTED);
	}
}

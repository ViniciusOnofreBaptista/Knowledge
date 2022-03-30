package com.br.knowledge.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
}

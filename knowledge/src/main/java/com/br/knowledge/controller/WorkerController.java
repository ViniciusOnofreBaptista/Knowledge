package com.br.knowledge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.knowledge.model.Worker;
import com.br.knowledge.repository.WorkerRepository;

@RestController
@RequestMapping("/worker")
public class WorkerController {

	@Autowired
	private WorkerRepository workerRepository;
	
	@PostMapping
	public ResponseEntity<?> createrWorker(@RequestBody Worker worker){
		Worker workerSaved = workerRepository.save(worker);
		return new ResponseEntity<>(workerSaved, HttpStatus.CREATED);
	}
	
}

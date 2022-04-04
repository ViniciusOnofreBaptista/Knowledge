package com.br.knowledge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.knowledge.model.ProjectTimeWorked;
import com.br.knowledge.service.ProjectTimeWorkedService;

@RestController
@RequestMapping("/project")
public class ProjectTimeWorkedController {

	@Autowired
	private ProjectTimeWorkedService projectTimeWorkedService;
	
	@PostMapping
	public ResponseEntity<?> saveProjectTimeWorked(@RequestBody ProjectTimeWorked projectTimeWorked){
		ProjectTimeWorked savedProjectTimeWorked = projectTimeWorkedService.saveProjectTimeWorked(projectTimeWorked);
		return new ResponseEntity<>(savedProjectTimeWorked, HttpStatus.CREATED);
	}
	
}

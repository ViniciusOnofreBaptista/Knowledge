package com.br.knowledge.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.knowledge.model.ProjectTimeWorked;

@Repository
public interface ProjectTimeWorkedRepository extends JpaRepository<ProjectTimeWorked, Integer>{

	List<ProjectTimeWorked> findByWorkedDayAndWorkerId(LocalDate workedDay, Integer Id);
	
}

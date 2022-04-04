package com.br.knowledge.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.knowledge.model.WorkedTime;

@Repository
public interface WorkedTimeRepository extends JpaRepository<WorkedTime, Integer>{

	
	Optional<WorkedTime> findByWorkerIdAndDay(Integer id, LocalDate workedDay);
}

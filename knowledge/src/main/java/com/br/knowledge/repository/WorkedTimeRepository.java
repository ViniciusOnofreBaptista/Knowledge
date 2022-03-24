package com.br.knowledge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.knowledge.model.WorkedTime;

@Repository
public interface WorkedTimeRepository extends JpaRepository<WorkedTime, Integer>{

}

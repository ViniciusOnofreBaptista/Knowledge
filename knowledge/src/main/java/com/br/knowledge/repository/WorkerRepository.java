package com.br.knowledge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.knowledge.model.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer>{

}

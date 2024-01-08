package com.abdo.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abdo.shop.model.entity.WorkerEntity;

@Repository
public interface WorkerRepository extends JpaRepository<WorkerEntity, Long> {

}

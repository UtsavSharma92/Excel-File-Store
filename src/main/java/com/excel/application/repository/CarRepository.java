package com.excel.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excel.application.domain.Car;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {

}

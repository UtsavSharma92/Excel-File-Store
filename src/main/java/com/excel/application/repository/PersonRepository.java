package com.excel.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excel.application.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

}

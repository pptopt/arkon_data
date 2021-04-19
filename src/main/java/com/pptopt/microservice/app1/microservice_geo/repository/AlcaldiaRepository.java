package com.pptopt.microservice.app1.microservice_geo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pptopt.microservice.app1.microservice_geo.entity.Alcaldia;


@Repository
public interface AlcaldiaRepository extends JpaRepository<Alcaldia, Integer> {
}

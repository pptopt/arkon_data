package com.pptopt.microservice.app1.microservice_geo.repository;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.pptopt.microservice.app1.microservice_geo.entity.PuntoDeRecorrido;

@Repository
public interface PuntoDeRecorridoRepository extends JpaRepository<PuntoDeRecorrido, Long> {

	/*
	 * Obtener lista completa de puntos de recorrido buscando por id de alcaldia
	 * */
	public List<PuntoDeRecorrido> findByIdAlcaldia(Integer idAlcaldia);
	
	/*
	 * verificar si este timestamp de la fuente de origen ya se encuentra cargado
	 * servirá para no cargar informacion duplicada
	 * */
	@Query(value = "select count(*) as num_regs from bt_recorrido r where r.date_updated=?1", nativeQuery = true)
	public Integer existeTimeStamp(Timestamp timestamp);
	
}

package com.pptopt.microservice.app1.microservice_geo.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vw_recorrido")
public class PuntoDeRecorridoView implements Serializable {
	@Id	
	Long idRecorrido;
	Timestamp dateUpdated;
	Integer idVehiculo;
	Integer vehicleLabel;
	Integer vehicleCurrentStatus;
	String puntoGeografico;
	Integer speed;
	Integer idAlcaldia;
	String alcaldia;
	
	
	public PuntoDeRecorridoView() {}
	
	

	public PuntoDeRecorridoView(Long idRecorrido, Timestamp dateUpdated, Integer idVehiculo, Integer vehicleLabel,
			Integer vehicleCurrentStatus, String puntoGeografico, Integer speed, Integer idAlcaldia, String alcaldia) {
		super();
		this.idRecorrido = idRecorrido;
		this.dateUpdated = dateUpdated;
		this.idVehiculo = idVehiculo;
		this.vehicleLabel = vehicleLabel;
		this.vehicleCurrentStatus = vehicleCurrentStatus;
		this.puntoGeografico = puntoGeografico;
		this.speed = speed;
		this.idAlcaldia = idAlcaldia;
		this.alcaldia = alcaldia;
	}





	public Long getIdRecorrido() {
		return idRecorrido;
	}





	public void setIdRecorrido(Long idRecorrido) {
		this.idRecorrido = idRecorrido;
	}





	public Timestamp getDateUpdated() {
		return dateUpdated;
	}





	public void setDateUpdated(Timestamp dateUpdated) {
		this.dateUpdated = dateUpdated;
	}





	public Integer getIdVehiculo() {
		return idVehiculo;
	}





	public void setIdVehiculo(Integer idVehiculo) {
		this.idVehiculo = idVehiculo;
	}





	public Integer getVehicleLabel() {
		return vehicleLabel;
	}





	public void setVehicleLabel(Integer vehicleLabel) {
		this.vehicleLabel = vehicleLabel;
	}





	public Integer getVehicleCurrentStatus() {
		return vehicleCurrentStatus;
	}





	public void setVehicleCurrentStatus(Integer vehicleCurrentStatus) {
		this.vehicleCurrentStatus = vehicleCurrentStatus;
	}





	public String getPuntoGeografico() {
		return puntoGeografico;
	}





	public void setPuntoGeografico(String puntoGeografico) {
		this.puntoGeografico = puntoGeografico;
	}





	public Integer getSpeed() {
		return speed;
	}





	public void setSpeed(Integer speed) {
		this.speed = speed;
	}





	public Integer getIdAlcaldia() {
		return idAlcaldia;
	}





	public void setIdAlcaldia(Integer idAlcaldia) {
		this.idAlcaldia = idAlcaldia;
	}





	public String getAlcaldia() {
		return alcaldia;
	}





	public void setAlcaldia(String alcaldia) {
		this.alcaldia = alcaldia;
	}





	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5531576439796126060L;
	
	
}

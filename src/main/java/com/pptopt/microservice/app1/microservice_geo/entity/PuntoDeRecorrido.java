package com.pptopt.microservice.app1.microservice_geo.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bt_recorrido")
public class PuntoDeRecorrido implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idRecorrido;
	Timestamp dateUpdated;
	Integer idVehiculo;
	Integer vehicleLabel;
	Integer vehicleCurrentStatus;
	Double positionLatitude;
	Double positionLongitude;
	String geographicPoint;
	Integer positionSpeed;
	Integer positionOdometer;
	Integer tripScheduleRelationship;
	Integer tripId;
	Integer tripStartDate;
	Integer tripRouteId;
	Integer idAlcaldia;

	public PuntoDeRecorrido() {
		super();
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

	public Double getPositionLatitude() {
		return positionLatitude;
	}

	public void setPositionLatitude(Double positionLatitude) {
		this.positionLatitude = positionLatitude;
	}

	public Double getPositionLongitude() {
		return positionLongitude;
	}

	public void setPositionLongitude(Double positionLongitude) {
		this.positionLongitude = positionLongitude;
	}

	public String getGeographicPoint() {
		return geographicPoint;
	}

	public void setGeographicPoint(String geographicPoint) {
		this.geographicPoint = geographicPoint;
	}

	public Integer getPositionSpeed() {
		return positionSpeed;
	}

	public void setPositionSpeed(Integer positionSpeed) {
		this.positionSpeed = positionSpeed;
	}

	public Integer getPositionOdometer() {
		return positionOdometer;
	}

	public void setPositionOdometer(Integer positionOdometer) {
		this.positionOdometer = positionOdometer;
	}

	public Integer getTripScheduleRelationship() {
		return tripScheduleRelationship;
	}

	public void setTripScheduleRelationship(Integer tripScheduleRelationship) {
		this.tripScheduleRelationship = tripScheduleRelationship;
	}

	public Integer getTripId() {
		return tripId;
	}

	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}

	public Integer getTripStartDate() {
		return tripStartDate;
	}

	public void setTripStartDate(Integer tripStartDate) {
		this.tripStartDate = tripStartDate;
	}

	public Integer getTripRouteId() {
		return tripRouteId;
	}

	public void setTripRouteId(Integer tripRouteId) {
		this.tripRouteId = tripRouteId;
	}

	public Integer getIdAlcaldia() {
		return idAlcaldia;
	}

	public void setIdAlcaldia(Integer idAlcaldia) {
		this.idAlcaldia = idAlcaldia;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6179419255282747934L;

}

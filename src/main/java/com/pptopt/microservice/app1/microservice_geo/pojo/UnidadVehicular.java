package com.pptopt.microservice.app1.microservice_geo.pojo;

import java.io.Serializable;

public class UnidadVehicular implements Serializable {
	int idVehicle;
	int vehicleLabel;
	
	public UnidadVehicular() {}
	
	public UnidadVehicular(int idVehicle, int vehicleLabel) {
		super();
		this.idVehicle = idVehicle;
		this.vehicleLabel = vehicleLabel;
	}
	public int getIdVehicle() {
		return idVehicle;
	}
	public void setIdVehicle(int idVehicle) {
		this.idVehicle = idVehicle;
	}
	public int getVehicleLabel() {
		return vehicleLabel;
	}
	public void setVehicleLabel(int vehicleLabel) {
		this.vehicleLabel = vehicleLabel;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6350317797297207121L;
	
	
}

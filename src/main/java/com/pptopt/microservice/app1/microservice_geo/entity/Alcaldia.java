package com.pptopt.microservice.app1.microservice_geo.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bt_alcaldia")
public class Alcaldia implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id_alcaldia;
	String des_alcaldia;
	String des_geo_shape;
	String des_geo_point;
	
	public Alcaldia() {
	};
	
	

	public Alcaldia(Integer id_alcaldia, String des_alcaldia, String des_geo_shape, String des_geo_point) {
		super();
		this.id_alcaldia = id_alcaldia;
		this.des_alcaldia = des_alcaldia;
		this.des_geo_shape = des_geo_shape;
		this.des_geo_point = des_geo_point;
	}

	

	public Integer getId_alcaldia() {
		return id_alcaldia;
	}



	public void setId_alcaldia(Integer id_alcaldia) {
		this.id_alcaldia = id_alcaldia;
	}



	public String getDes_alcaldia() {
		return des_alcaldia;
	}



	public void setDes_alcaldia(String des_alcaldia) {
		this.des_alcaldia = des_alcaldia;
	}



	public String getDes_geo_shape() {
		return des_geo_shape;
	}



	public void setDes_geo_shape(String des_geo_shape) {
		this.des_geo_shape = des_geo_shape;
	}



	public String getDes_geo_point() {
		return des_geo_point;
	}



	public void setDes_geo_point(String des_geo_point) {
		this.des_geo_point = des_geo_point;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 4246854919323583898L;


}

package com.pptopt.microservice.app1.microservice_geo.service;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import com.google.transit.realtime.GtfsRealtime;
import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.pptopt.microservice.app1.microservice_geo.controller.PuntoDeRecorridoController;
import com.pptopt.microservice.app1.microservice_geo.entity.Alcaldia;
import com.pptopt.microservice.app1.microservice_geo.entity.PuntoDeRecorrido;
import com.pptopt.microservice.app1.microservice_geo.pojo.Coordenada;
import com.pptopt.microservice.app1.microservice_geo.pojo.CustomResponse;

public class Procesos {

	private IPuntoDeRecorridoService puntoDeRecorridoService;
	private IAlcaldiaService alcaldiaService;

	private Logger log = LoggerFactory.getLogger(PuntoDeRecorridoController.class);

	public Procesos(IPuntoDeRecorridoService puntoDeRecorridoService, IAlcaldiaService alcaldiaService) {
		this.puntoDeRecorridoService = puntoDeRecorridoService;
		this.alcaldiaService = alcaldiaService;
	}
	
	
	
	/*/*
	 * 
	 *  PUNTOS DE RECORRIDO 
	 *  
	 */	

	
	/*
	 * Proceso de carga de la fuente a la BD   
	 * */
	public CustomResponse loadData(String origen) throws Exception {
		FeedMessage contenido = null;
		CustomResponse respuesta = null;
		
		try {
		
			log.debug("ruta origen " + origen);
			
			URLConnection conn = new URL(origen).openConnection();
			conn.connect();

			contenido = GtfsRealtime.FeedMessage.parseFrom(conn.getInputStream());
			
			conn.getInputStream().close();
			
			respuesta = updateDataBase(contenido);
			
		} catch (MalformedURLException e) {
			respuesta = new CustomResponse("La url: " + origen + " no es valida!", "Error", HttpStatus.BAD_REQUEST);
		}
		
		return respuesta;
	}

	/*
	 * Actualizacion en la BD
	 * */
	private CustomResponse updateDataBase(FeedMessage feedMessage) throws Exception {

		CustomResponse respuesta = null;
		
		long timestamp = feedMessage.getHeader().getTimestamp();
		Timestamp dateUpdated = new Timestamp(timestamp);
		int existeTimeStamp = puntoDeRecorridoService.existeTimeStamp(dateUpdated);
		log.debug("existeTimeStamp : " + existeTimeStamp);

		if (existeTimeStamp > 0) {
			respuesta = new CustomResponse("Ya se ha cargado este archivo previamente", "Se leyo el archivo pero no se realizo la carga", HttpStatus.ALREADY_REPORTED);
		} else {
			List<FeedEntity> lista = feedMessage.getEntityList();
			int numeroDeRegistro = 0;
			int registrosCargados = 0;
			int registrosNoCargados = 0;
			for (FeedEntity entity : lista) {
				++numeroDeRegistro;
				Integer idVehiculo = 0;
				try {
					idVehiculo = Integer.parseInt(entity.getVehicle().getVehicle().getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				Integer vehicleLabel = 0;
				try {
					vehicleLabel = Integer.parseInt(entity.getVehicle().getVehicle().getLabel());
				} catch (Exception e) {
				}
				Integer vehicleCurrentStatus = entity.getVehicle().getCurrentStatus().getNumber();
				double positionLatitude = entity.getVehicle().getPosition().getLatitude();
				double positionLongitude = entity.getVehicle().getPosition().getLongitude();
				String geographicPoint = new String(entity.getVehicle().getPosition().getLatitude() + "," + entity.getVehicle().getPosition().getLongitude());
				Integer positionSpeed = Double.valueOf(entity.getVehicle().getPosition().getSpeed()).intValue();
				Integer positionOdometer = Double.valueOf(entity.getVehicle().getPosition().getOdometer()).intValue();
				Integer tripScheduleRelationship = entity.getVehicle().getTrip().getScheduleRelationship().getNumber();
				Integer tripId = 0;
				try {
					tripId = Integer.parseInt(entity.getVehicle().getTrip().getTripId());
				} catch (Exception e) {
				}
				Integer tripStartDate = 0;
				try {
					tripStartDate = Integer.parseInt(entity.getVehicle().getTrip().getStartDate());
				} catch (Exception e) {
				}
				Integer tripRouteId = 0;
				try {
					tripRouteId = Integer.parseInt(entity.getVehicle().getTrip().getRouteId());
				} catch (Exception e) {
				}
				Integer idAlcaldia = 0;

				PuntoDeRecorrido puntoDeRecorrido = new PuntoDeRecorrido();
				puntoDeRecorrido.setDateUpdated(dateUpdated);
				puntoDeRecorrido.setIdVehiculo(idVehiculo);
				puntoDeRecorrido.setVehicleLabel(vehicleLabel);
				puntoDeRecorrido.setVehicleCurrentStatus(vehicleCurrentStatus);
				puntoDeRecorrido.setPositionLatitude(positionLatitude);
				puntoDeRecorrido.setPositionLongitude(positionLongitude);
				puntoDeRecorrido.setGeographicPoint(geographicPoint);
				puntoDeRecorrido.setPositionSpeed(positionSpeed);
				puntoDeRecorrido.setPositionOdometer(positionOdometer);
				puntoDeRecorrido.setTripScheduleRelationship(tripScheduleRelationship);
				puntoDeRecorrido.setTripId(tripId);
				puntoDeRecorrido.setTripStartDate(tripStartDate);
				puntoDeRecorrido.setTripRouteId(tripRouteId);
				puntoDeRecorrido.setIdAlcaldia(idAlcaldia);

				if(puntoDeRecorrido.getIdVehiculo()==0) {
					log.error("Numero de registro " + numeroDeRegistro + " no cuenta con id de vehiculo y no se agrego a la BD");
					++registrosNoCargados;
				}
				else {
					if(puntoDeRecorridoService.update(puntoDeRecorrido)!=null) {
						++registrosCargados;
					}
					else {
						log.error("Numero de registro " + numeroDeRegistro + " falló al agregarse a la BD");
						++registrosNoCargados;
					}
				}
			}
			
			respuesta = new CustomResponse("Se cargaron correctamente " + registrosCargados + " registros. No se cargaron " + registrosNoCargados + " registros", "Todo OK", HttpStatus.OK);
		}
		
		return respuesta;

	}
	
	
	
	/*/*
	 * 
	 *  ALCALDIAS
	 *  
	 */
	
	/*
	 * Actualiza el id de alcaldia en la tabla de bt_recorrido 
	 * */
	public CustomResponse actualizarIdAlcaldia() throws Exception {
		return evaluarPuntosGeograficos();
	}
	

	private CustomResponse evaluarPuntosGeograficos() throws Exception {
		CustomResponse respuesta = null; 
		int registrosCargados = 0;
		int registrosNoCargados = 0;
		
		List<Alcaldia> lstAlcaldias = alcaldiaService.findAll();
		
		if(lstAlcaldias.size()==0) {
			respuesta = new CustomResponse("La tabla de alcaldias está vacia", "Tabla sin datos", HttpStatus.NOT_FOUND);
		}
		else {
			for (int contAlcaldia = 0; contAlcaldia < lstAlcaldias.size(); contAlcaldia++) {
	
				Alcaldia alcaldiaActual = lstAlcaldias.get(contAlcaldia);
				ArrayList<Coordenada> limitesAlcaldia = new ArrayList<>();
	
				String strLimitesAlcaldia = alcaldiaActual.getDes_geo_shape();
	
				String[] arrayLimitesAlcaldia = strLimitesAlcaldia.split("],");
	
				// Contruir el poligono con los puntos delimitantes de la alcaldia
				for (int i = 0; i < arrayLimitesAlcaldia.length; i++) {
					String[] arrPuntoGeografico = arrayLimitesAlcaldia[i].replace(" ", "").replace("[", "").replace("]", "")
							.replace("{", "").replace("}", "").split(",");
					if (arrPuntoGeografico.length > 1) {
						if (!arrPuntoGeografico[0].isEmpty() && !arrPuntoGeografico[1].isEmpty()) {
							Double latitud = Double.valueOf(arrPuntoGeografico[1]);
							Double longitud = Double.valueOf(arrPuntoGeografico[0]);
							Coordenada puntoDelimitate = new Coordenada(Long.valueOf(""+i), latitud, longitud);
							limitesAlcaldia.add(puntoDelimitate);
						}
					}
				}
	
				// Obtener solamente los recorridos que no tienen seteada una alcaldia
				List<PuntoDeRecorrido> lstRecorridos = puntoDeRecorridoService.findByIdAlcaldia(0);
	
				// Evaluar cada punto del recorrido para verificar si esta dentro de la
				// delegacion Analizada
				for (int i = 0; i < lstRecorridos.size(); i++) {
					PuntoDeRecorrido recorridoActual = lstRecorridos.get(i);
					String[] arrPuntoGeografico = recorridoActual.getGeographicPoint().split(",");
					Coordenada puntoGeografico = null;
					if (arrPuntoGeografico.length > 1) {
						if (!arrPuntoGeografico[0].isEmpty() && !arrPuntoGeografico[1].isEmpty()) {
							Double latitud = Double.valueOf(arrPuntoGeografico[0]);
							Double longitud = Double.valueOf(arrPuntoGeografico[1]);
							puntoGeografico = new Coordenada(Long.valueOf(""+i), latitud, longitud);
						}
					}
	
					Boolean isInside = isLocationInsideTheArea(puntoGeografico, limitesAlcaldia);
	
					// Si la coordenada del punto del recorrido esta dentro de los limites
					// de la delegacion evaluada entonces actualizar el id de la delegacion
					// en el campo id_delegacion del recorrido
					if (isInside) {
						recorridoActual.setIdAlcaldia(alcaldiaActual.getId_alcaldia());
						if(puntoDeRecorridoService.update(recorridoActual)!=null) {
							++registrosCargados;
						}
						else {
							log.error("Punto de recorrido con id " + recorridoActual.getIdRecorrido() + " falló al actualizarse en la BD");
							++registrosNoCargados;
						}
					}
	
				}
	
			}
		}
		respuesta = new CustomResponse("Se actualizaron correctamente " + registrosCargados + " registros. No se actualizaron " + registrosNoCargados + " registros", "Todo OK", HttpStatus.OK);
		return respuesta;
		
	}

	private boolean isLocationInsideTheArea(Coordenada location, List<Coordenada> areaAlcaldia) {

		boolean blnIsinside = false;

		List<Coordenada> lstCoordinatesDTO = areaAlcaldia;

		Path2D area = new Path2D.Double();
		area.moveTo(lstCoordinatesDTO.get(0).getLatitud(), lstCoordinatesDTO.get(0).getLongitud()); 
																											
		for (int i = 1; i < lstCoordinatesDTO.size(); i++) {
			area.lineTo(lstCoordinatesDTO.get(i).getLatitud(), lstCoordinatesDTO.get(i).getLongitud()); 
		}
		area.closePath(); 

		Point2D puntoGeogradico = new Point2D.Double();
		puntoGeogradico.setLocation(location.getLatitud(), location.getLongitud());

		if (area.contains(puntoGeogradico)) {
			blnIsinside = true;
		} else {
			blnIsinside = false;
		}

		return blnIsinside;
	}	

}
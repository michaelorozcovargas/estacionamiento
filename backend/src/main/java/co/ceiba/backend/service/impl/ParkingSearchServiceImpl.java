package co.ceiba.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.backend.converter.ParkingRegistryConverter;
import co.ceiba.backend.entity.ParkingRegistry;
import co.ceiba.backend.model.ParkingRegistryModel;
import co.ceiba.backend.repository.ParkingRegistryRepository;
import co.ceiba.backend.service.ParkingSearchService;

/**
 * Implementacion de los servicios de consulta en el estacionamiento
 * 
 * @author michael.orozco
 */
@Service
public class ParkingSearchServiceImpl implements ParkingSearchService {

	/**
	 * Repositorio para la entidad {@link ParkingRegistry}
	 */
	@Autowired
	private ParkingRegistryRepository parkingRegistryRepository;
	/**
	 * Conversor para el tipo de dato {@link ParkingRegistry}
	 */
	@Autowired
	private ParkingRegistryConverter parkingRegistryConverter;

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param parkingRegistryRepository
	 *            Repositorio para la entidad {@link ParkingRegistry}
	 * @param parkingRegistryConverter
	 *            Conversor para el tipo de dato {@link ParkingRegistry}
	 */
	public ParkingSearchServiceImpl(ParkingRegistryRepository parkingRegistryRepository,
			ParkingRegistryConverter parkingRegistryConverter) {
		super();
		this.parkingRegistryRepository = parkingRegistryRepository;
		this.parkingRegistryConverter = parkingRegistryConverter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.ceiba.backend.service.ParkingSearchService#searchParkedVehicles()
	 */
	public List<ParkingRegistryModel> searchParkedVehicles() {
		return parkingRegistryConverter.convertList(parkingRegistryRepository.findParkedVehicles());
	}

}

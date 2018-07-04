package co.ceiba.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.ceiba.backend.converter.ParkingRegistryConverter;
import co.ceiba.backend.model.ParkingRegistryModel;
import co.ceiba.backend.repository.ParkingRegistryRepository;

public class ParkingSearchService {

	@Autowired
	private ParkingRegistryRepository parkingRegistryRepository;

	@Autowired
	private ParkingRegistryConverter parkingRegistryConverter;

	public List<ParkingRegistryModel> searchParkedVehicles() {
		return parkingRegistryConverter.convert(parkingRegistryRepository.findParkedVehicles());
	}

}

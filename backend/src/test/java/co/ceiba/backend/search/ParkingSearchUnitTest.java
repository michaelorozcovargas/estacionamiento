package co.ceiba.backend.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.backend.converter.ParkingRegistryConverter;
import co.ceiba.backend.converter.VehicleConverter;
import co.ceiba.backend.entity.ParkingRegistry;
import co.ceiba.backend.model.ParkingRegistryModel;
import co.ceiba.backend.repository.ParkingRegistryRepository;
import co.ceiba.backend.service.ParkingSearchService;
import co.ceiba.backend.service.impl.ParkingSearchServiceImpl;

/**
 * Contiene las pruebas unitarias para las funcionalidades de busqueda de
 * vehiculos en el estacionamiento
 * 
 * @author michael.orozco
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ParkingSearchUnitTest {

	/**
	 * Mock para las consultas de registros de estacionamiento
	 */
	@Mock
	private ParkingRegistryRepository parkingRegistryRepository;
	/**
	 * Mock de la clase VehicleConverter
	 */
	@Mock
	private VehicleConverter vehicleConverter;
	/**
	 * Inyeccion de mocks
	 */
	@InjectMocks
	private ParkingRegistryConverter parkingRegistryConverter = new ParkingRegistryConverter(vehicleConverter);
	/**
	 * Inyeccion de mocks
	 */
	@InjectMocks
	private ParkingSearchService parkingSearchService = new ParkingSearchServiceImpl(parkingRegistryRepository,
			parkingRegistryConverter);

	/**
	 * Prueba unitaria para un resultado sin registros
	 */
	@Test
	public void successEmptyResult() {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		when(parkingRegistryRepository.findParkedVehicles()).thenReturn(new ArrayList<>());

		// ------------------------------------------
		// Act
		// ------------------------------------------
		List<ParkingRegistryModel> resultList = parkingSearchService.searchParkedVehicles();

		// ------------------------------------------
		// Assert
		// ------------------------------------------
		assertEquals(0, resultList.size());
	}

	/**
	 * Prueba unitaria para un resultado sin registros
	 */
	@Test
	public void successNotEmptyResult() {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		List<ParkingRegistry> registryList = new ArrayList<>();

		ParkingRegistry registry = new ParkingRegistry();
		registryList.add(registry);

		when(parkingRegistryRepository.findParkedVehicles()).thenReturn(registryList);

		// ------------------------------------------
		// Act
		// ------------------------------------------
		List<ParkingRegistryModel> resultList = parkingSearchService.searchParkedVehicles();

		// ------------------------------------------
		// Assert
		// ------------------------------------------
		assertNotEquals(0, resultList.size());
	}
}

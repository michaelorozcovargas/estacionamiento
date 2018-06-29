package co.ceiba.backend.register;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.backend.builder.VehicleModelBuilder;
import co.ceiba.backend.constants.ApplicationConstants;
import co.ceiba.backend.converter.VehicleConverter;
import co.ceiba.backend.entity.ParkingRegistry;
import co.ceiba.backend.entity.Vehicle;
import co.ceiba.backend.entity.VehicleTypeEnum;
import co.ceiba.backend.error.ApplicationException;
import co.ceiba.backend.error.ErrorEnum;
import co.ceiba.backend.model.VehicleModel;
import co.ceiba.backend.repository.ParkingRegistryRepository;
import co.ceiba.backend.repository.VehicleRepository;
import co.ceiba.backend.service.ParkingRegistryService;
import co.ceiba.backend.service.VehicleService;
import co.ceiba.backend.service.impl.ParkingRegistryServiceImpl;
import co.ceiba.backend.service.impl.VehicleServiceImpl;

/**
 * Contiene las pruebas unitarias para las funcionalidades de registro de
 * vehiculos en el estacionamiento
 * 
 * @author michael.orozco
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingRegistryTest {

	/**
	 * Mock del repositorio del estacionamiento
	 */
	@Mock
	private ParkingRegistryRepository parkingRegistryRepository;
	/**
	 * Mock del repositorio de vehiculos
	 */
	@Mock
	private VehicleRepository vehicleRepository;
	/**
	 * Inyeccion de mocks
	 */
	@InjectMocks
	private VehicleService vehicleService = new VehicleServiceImpl(vehicleRepository);
	/**
	 * Inyeccion de mocks
	 */
	@InjectMocks
	private ParkingRegistryService parkingRegistryService = new ParkingRegistryServiceImpl(parkingRegistryRepository,
			vehicleService);

	/**
	 * Prueba de fallo ante espacio para carros
	 * 
	 * @throws ApplicationException
	 */
	@Test(expected = ApplicationException.class)
	public void failedRegisterVehicleByCarSpace() throws ApplicationException {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		String plate = "PLACA12345";
		Integer cc = 123;
		VehicleTypeEnum vehicleType = VehicleTypeEnum.CAR;

		VehicleModel vehicleModel = new VehicleModelBuilder().withPlate(plate).withCubicCentimeters(cc)
				.withVehicleType(vehicleType).build();

		Vehicle vehicle = VehicleConverter.getInstance().getVehicle(vehicleModel);

		// ParkingRegistryRepository parkingRegistryRepository =
		// Mockito.mock(ParkingRegistryRepository.class);
		when(parkingRegistryRepository.countParkedVehiclesByType(vehicleType))
				.thenReturn(ApplicationConstants.MAX_PARKED_CARS);
		
		// when(parkingRegistryRepository.save(ParkingRegistry.class));

		when(vehicleService.getVehicleByPlate(plate)).thenReturn(null);

		when(vehicleService.registerVehicle(vehicleModel)).thenReturn(vehicle);

		// parkingRegistryService = new
		// ParkingRegistryServiceImpl(parkingRegistryRepository, vehicleService);

		// ------------------------------------------
		// Act
		// ------------------------------------------
		try {
			parkingRegistryService.registerEntry(vehicleModel);

		} catch (ApplicationException e) {

			// ------------------------------------------
			// Assert
			// ------------------------------------------
			org.junit.Assert.assertEquals(ErrorEnum.UNAVAILABLE_SPACE.toString(), e.getMessage());
			throw e;
		}
	}

	/**
	 * Prueba de fallo ante espacio para motos
	 * 
	 * @throws ApplicationException
	 */
	// @Test(expected = ApplicationException.class)
	public void failedRegisterVehicleByMotorcycleSpace() throws ApplicationException {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		String plate = "PLACA12345";
		Integer cc = 123;
		VehicleTypeEnum vehicleType = VehicleTypeEnum.MOTORCYCLE;

		VehicleModel vehicle = new VehicleModelBuilder().withPlate(plate).withCubicCentimeters(cc)
				.withVehicleType(vehicleType).build();

		// ParkingRegistryRepository parkingRegistryRepository =
		// Mockito.mock(ParkingRegistryRepository.class);
		when(parkingRegistryRepository.countParkedVehiclesByType(vehicleType))
				.thenReturn(ApplicationConstants.MAX_PARKED_MOTORCYCLE);

		// ParkingRegistryService parkingRegistryService = new
		// ParkingRegistryServiceImpl(parkingRegistryRepository,
		// vehicleService);

		// ------------------------------------------
		// Act
		// ------------------------------------------
		try {
			parkingRegistryService.registerEntry(vehicle);

		} catch (ApplicationException e) {

			// ------------------------------------------
			// Assert
			// ------------------------------------------
			org.junit.Assert.assertEquals(ErrorEnum.UNAVAILABLE_SPACE.toString(), e.getMessage());
			throw e;
		}
	}

	// TODO refactorizar para tener en cuenta las circunstancias de exito
	// @Test
	public void registerVehicleUnitTestSuccessful() {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		boolean registered = false;
		String plate = "PLACA1234";
		Integer cc = 123;
		VehicleTypeEnum vehicleType = VehicleTypeEnum.CAR;

		VehicleModel vehicle = new VehicleModelBuilder().withPlate(plate).withCubicCentimeters(cc)
				.withVehicleType(vehicleType).build();

		// ParkingRegistryService parkingRegistryService =
		// Mockito.mock(ParkingRegistryService.class);
		// when(parkingRegistryService.registerEntry(vehicle)).thenReturn(Boolean.TRUE);

		/*
		 * VehicleRepository vehicleRepositoryMock =
		 * Mockito.mock(VehicleRepository.class);
		 * when(vehicleRepositoryMock.findByPlate(plate)).thenReturn(new Vehicle());
		 * 
		 * VehicleRegistryService vehicleRegistryService = new
		 * VehicleRegistryServiceImpl(vehicleRepositoryMock);
		 * 
		 */
		// ------------------------------------------
		// Act
		// ------------------------------------------
		// TODO registered = parkingRegistryService.registerEntry(vehicle);

		// ------------------------------------------
		// Assert
		// ------------------------------------------
		org.junit.Assert.assertTrue(registered);
	}
}
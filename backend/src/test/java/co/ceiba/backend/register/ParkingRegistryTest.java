package co.ceiba.backend.register;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.backend.builder.VehicleModelBuilder;
import co.ceiba.backend.entity.VehicleTypeEnum;
import co.ceiba.backend.model.VehicleModel;
import co.ceiba.backend.service.ParkingRegistryService;

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
	 * Servicios para el registro en el estacionamiento
	 */
	@Autowired
	@Qualifier("parkingRegistryService")
	ParkingRegistryService parkingRegistryService;

	/**
	 * Prueba de fallo ante espacio para carros
	 */
	@Test
	public void failedRegisterVehicleByCarSpace() {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		boolean registered = false;
		String plate = "PLACA1234";
		Integer cc = 123;
		VehicleTypeEnum vehicleType = VehicleTypeEnum.CAR;

		VehicleModel vehicle = new VehicleModelBuilder().withPlate(plate).withCubicCentimeters(cc)
				.withVehicleType(vehicleType).build();
	}

	// TODO refactorizar para tener en cuenta las circunstancias de exito
	@Test
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
		registered = parkingRegistryService.registerEntry(vehicle);

		// ------------------------------------------
		// Assert
		// ------------------------------------------
		org.junit.Assert.assertTrue(registered);
	}
}
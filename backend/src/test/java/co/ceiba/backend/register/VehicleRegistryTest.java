package co.ceiba.backend.register;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.backend.builder.VehicleModelBuilder;
import co.ceiba.backend.entity.Vehicle;
import co.ceiba.backend.entity.VehicleTypeEnum;
import co.ceiba.backend.model.VehicleModel;
import co.ceiba.backend.repository.VehicleRepository;
import co.ceiba.backend.service.VehicleRegistryService;
import co.ceiba.backend.service.impl.VehicleRegistryServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleRegistryTest {

	@Test
	public void registerVehicleUnitTestSuccessful() {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		boolean registered = false;
		String plate = "PLACA123";
		Integer cc = 123;
		VehicleTypeEnum vehicleType = VehicleTypeEnum.MOTORCYCLE;

		VehicleRepository vehicleRepositoryMock = Mockito.mock(VehicleRepository.class);
		when(vehicleRepositoryMock.findByPlate(plate)).thenReturn(new Vehicle());

		VehicleRegistryService vehicleRegistryService = new VehicleRegistryServiceImpl(vehicleRepositoryMock);

//		VehicleModel vehicleModel = new VehicleModelBuilder().withPlate(plate).withCubicCentimeters(123)
//				.withVehicleType().build();

		// ------------------------------------------
		// Act
		// ------------------------------------------
		// registered = vehicleRegistryService.registryVehicle(vehicleModel);

		// ------------------------------------------
		// Assert
		// ------------------------------------------
		org.junit.Assert.assertTrue(registered);
	}
}
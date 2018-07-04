package co.ceiba.backend.register;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.backend.builder.VehicleModelBuilder;
import co.ceiba.backend.constants.ApplicationConstants;
import co.ceiba.backend.constants.ResponseCodeEnum;
import co.ceiba.backend.entity.ParkingRegistry;
import co.ceiba.backend.entity.VehicleTypeEnum;
import co.ceiba.backend.error.ApplicationException;
import co.ceiba.backend.model.VehicleModel;
import co.ceiba.backend.repository.ParkingRegistryRepository;
import co.ceiba.backend.repository.VehicleRepository;
import co.ceiba.backend.service.ParkingRegistryService;
import co.ceiba.backend.service.VehicleService;
import co.ceiba.backend.service.impl.ParkingRegistryServiceImpl;
import co.ceiba.backend.service.impl.VehicleServiceImpl;
import co.ceiba.backend.utilities.CalendarUtil;

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
	 * Mock de las utilidades de fechas
	 */
	@Mock
	private CalendarUtil calendarUtil;
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
			vehicleService, calendarUtil);

	/**
	 * Permite generar un objeto vehiculo del tipo definido para las pruebas
	 * 
	 * @param vehicleType
	 *            tipo de vehiculo a crear
	 * 
	 * @return vehiculo creado
	 */
	private VehicleModel generateVehicleModel(VehicleTypeEnum vehicleType) {
		String plate = "PLACA12345";
		Integer cc = 123;
		VehicleModel vehicleModel = new VehicleModelBuilder().withPlate(plate).withCubicCentimeters(cc)
				.withVehicleType(vehicleType).build();
		return vehicleModel;
	}

	/**
	 * Prueba unitaria de fallo ante espacio para carros
	 */
	@Test
	public void failedRegisterCarBySpaceUT() {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		VehicleTypeEnum vehicleType = VehicleTypeEnum.CAR;

		VehicleModel vehicleModel = generateVehicleModel(vehicleType);

		when(parkingRegistryRepository.countParkedVehiclesByType(vehicleType))
				.thenReturn(ApplicationConstants.MAX_PARKED_CARS);

		// ------------------------------------------
		// Act
		// ------------------------------------------
		try {
			parkingRegistryService.registerEntry(vehicleModel);

			// Si no lanza excepcion falla
			fail();

		} catch (ApplicationException e) {

			// ------------------------------------------
			// Assert
			// ------------------------------------------
			assertEquals(ResponseCodeEnum.UNAVAILABLE_SPACE, e.getError());
		}
	}

	/**
	 * Prueba unitaria de fallo ante espacio para motos
	 */
	@Test
	public void failedRegisterMotorcycleBySpaceUT() {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		VehicleTypeEnum vehicleType = VehicleTypeEnum.MOTORCYCLE;

		VehicleModel vehicleModel = generateVehicleModel(vehicleType);

		when(parkingRegistryRepository.countParkedVehiclesByType(vehicleType))
				.thenReturn(ApplicationConstants.MAX_PARKED_MOTORCYCLE);

		// ------------------------------------------
		// Act
		// ------------------------------------------
		try {
			parkingRegistryService.registerEntry(vehicleModel);

			// Si no lanza excepcion falla
			fail();

		} catch (ApplicationException e) {

			// ------------------------------------------
			// Assert
			// ------------------------------------------
			assertEquals(ResponseCodeEnum.UNAVAILABLE_SPACE, e.getError());
		}
	}

	/**
	 * Prueba unitaria de fallo ante ingreso con placa que inicia con letra "A" en
	 * un dia diferente a Domingo o Lunes
	 */
	@Test
	public void failedRegisterVehicleByAPlateLetterUT() {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		VehicleTypeEnum vehicleType = VehicleTypeEnum.CAR;

		VehicleModel vehicleModel = generateVehicleModel(vehicleType);

		vehicleModel.setPlate("A_PLACA123");

		// Viernes 29 de Junio de 2018
		Calendar friday = Calendar.getInstance();
		friday.set(2018, 06, 29);

		when(calendarUtil.getCurrentDate()).thenReturn(friday);

		when(parkingRegistryRepository.countParkedVehiclesByType(vehicleType)).thenReturn(0);

		// ------------------------------------------
		// Act
		// ------------------------------------------
		try {
			parkingRegistryService.registerEntry(vehicleModel);

			// Si no lanza excepcion falla
			fail();

		} catch (ApplicationException e) {

			// ------------------------------------------
			// Assert
			// ------------------------------------------
			assertEquals(ResponseCodeEnum.ACCESS_DENIED_BY_DATE_AND_PLATE, e.getError());
		}
	}

	/**
	 * Prueba unitaria de exito ante ingreso de vehiculo
	 */
	@Test
	public void successRegisterVehicleUT() {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		boolean registered = false;

		VehicleTypeEnum vehicleType = VehicleTypeEnum.CAR;

		VehicleModel vehicleModel = generateVehicleModel(vehicleType);

		when(vehicleRepository.findByPlate(vehicleModel.getPlate())).thenReturn(null);

		when(vehicleRepository.save(any())).thenReturn(any());

		when(parkingRegistryRepository.countParkedVehiclesByType(vehicleType)).thenReturn(0);

		when(parkingRegistryRepository.save(any())).thenReturn(new ParkingRegistry());

		when(calendarUtil.getCurrentDate()).thenReturn(Calendar.getInstance());

		// ------------------------------------------
		// Act
		// ------------------------------------------
		try {
			registered = parkingRegistryService.registerEntry(vehicleModel);
		} catch (ApplicationException e) {
			fail();
		}

		// ------------------------------------------
		// Assert
		// ------------------------------------------
		assertTrue(registered);
	}

}
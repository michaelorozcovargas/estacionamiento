package co.ceiba.backend.departure;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.backend.builder.VehicleModelBuilder;
import co.ceiba.backend.constants.ApplicationConstants;
import co.ceiba.backend.converter.ParkingInvoiceConverter;
import co.ceiba.backend.converter.ParkingRegistryConverter;
import co.ceiba.backend.converter.VehicleConverter;
import co.ceiba.backend.entity.ParkingRegistry;
import co.ceiba.backend.entity.VehicleTypeEnum;
import co.ceiba.backend.model.ParkingInvoiceModel;
import co.ceiba.backend.model.VehicleModel;
import co.ceiba.backend.repository.ParkingInvoiceRepository;
import co.ceiba.backend.repository.ParkingRegistryRepository;
import co.ceiba.backend.repository.VehicleRepository;
import co.ceiba.backend.service.ParkingDepartureService;
import co.ceiba.backend.service.impl.ParkingDepartureServiceImpl;
import co.ceiba.backend.utilities.CalendarUtil;

/**
 * Contiene las pruebas unitarias para las funcionalidades de salida de
 * vehiculos en el estacionamiento
 * 
 * @author michael.orozco
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingDepartureUnitTests {

	@Mock
	private VehicleConverter vehicleConverter;

	@Mock
	private VehicleRepository vehicleRepository;

	@Mock
	private ParkingRegistryRepository parkingRegistryRepository;

	@Mock
	private ParkingInvoiceRepository parkingInvoiceRepository;

	@Mock
	private CalendarUtil calendarUtil;

	@InjectMocks
	private ParkingRegistryConverter parkingRegistryConverter = new ParkingRegistryConverter(vehicleConverter);

	@InjectMocks
	private ParkingInvoiceConverter parkingInvoiceConverter = new ParkingInvoiceConverter(parkingRegistryConverter);

	@InjectMocks
	private ParkingDepartureService parkingDepartureService = new ParkingDepartureServiceImpl(parkingRegistryRepository,
			vehicleRepository, calendarUtil, parkingInvoiceRepository, parkingInvoiceConverter);

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
	 * Prueba unitaria que verifica el costo de un carro por una hora
	 */
	@Test
	public void parkingPriceCarHour() {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		Integer price;
		ParkingInvoiceModel parkingInvoiceModel;
		VehicleModel vehicle = generateVehicleModel(VehicleTypeEnum.CAR);

		Date entryDate = calendarUtil.formatString(ApplicationConstants.DATE_FORMAT, "2018-06-05'T'13:00:00");
		Date outDate = calendarUtil.formatString(ApplicationConstants.DATE_FORMAT, "2018-06-05'T'13:59:59");

		Calendar calendarOut = Calendar.getInstance();
		calendarOut.setTime(outDate);

		ParkingRegistry registry = new ParkingRegistry();
		registry.setEntryDate(entryDate);

		when(vehicleRepository.findByPlate(any())).thenReturn(any());

		when(parkingRegistryRepository.findActiveRegistryByVehicle(any())).thenReturn(new ParkingRegistry());
		when(parkingRegistryRepository.save(any())).thenReturn(any());

		when(parkingInvoiceRepository.save(any())).thenReturn(any());

		when(calendarUtil.getCurrentDate()).thenReturn(calendarOut);

		// ------------------------------------------
		// Act
		// ------------------------------------------
		parkingInvoiceModel = parkingDepartureService.departVehicle(vehicle);
		price = parkingInvoiceModel.getPrice();

		// ------------------------------------------
		// Assert
		// ------------------------------------------
		assertEquals(1000, price.intValue());
	}

}

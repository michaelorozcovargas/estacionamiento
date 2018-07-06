package co.ceiba.backend.departure;

import static co.ceiba.backend.constants.ApplicationConstants.DATE_FORMAT;
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
import co.ceiba.backend.converter.ParkingInvoiceConverter;
import co.ceiba.backend.converter.ParkingRegistryConverter;
import co.ceiba.backend.converter.VehicleConverter;
import co.ceiba.backend.converter.VehicleModelConverter;
import co.ceiba.backend.entity.ParkingInvoice;
import co.ceiba.backend.entity.ParkingRegistry;
import co.ceiba.backend.entity.Vehicle;
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

	/**
	 * Conversores a ser utilizados
	 */
	private VehicleConverter vehicleConverter = new VehicleConverter();
	private ParkingRegistryConverter parkingRegistryConverter = new ParkingRegistryConverter(vehicleConverter);
	private ParkingInvoiceConverter parkingInvoiceConverter = new ParkingInvoiceConverter(parkingRegistryConverter);
	/**
	 * Mock para el repositorio de la clase {@link Vehicle}
	 */
	@Mock
	private VehicleRepository vehicleRepository;
	/**
	 * Mock para el repositorio de la clase {@link ParkingRegistry}
	 */
	@Mock
	private ParkingRegistryRepository parkingRegistryRepository;
	/**
	 * Mock para el repositorio de la clase {@link ParkingInvoice}
	 */
	@Mock
	private ParkingInvoiceRepository parkingInvoiceRepository;
	/**
	 * Mock para las funcionalidades de calendario
	 */
	@Mock
	private CalendarUtil calendarUtil;
	/**
	 * Inyeccion de mocks
	 */
	@InjectMocks
	private ParkingDepartureService parkingDepartureService = new ParkingDepartureServiceImpl(parkingRegistryRepository,
			vehicleRepository, calendarUtil, parkingInvoiceRepository, parkingInvoiceConverter);

	/**
	 * Prueba unitaria que verifica el costo de un carro por una hora
	 */
	@Test
	public void parkingPriceCarHour() {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		VehicleModel vehicleModel = new VehicleModelBuilder().withPlate("PLACA_123").withCubicCentimeters(123)
				.withVehicleType(VehicleTypeEnum.CAR).build();

		Vehicle vehicle = new VehicleModelConverter().convert(vehicleModel);

		when(vehicleRepository.findByPlate(any())).thenReturn(vehicle);

		when(calendarUtil.formatString(any(), any())).thenCallRealMethod();
		when(calendarUtil.formatDate(any(), any())).thenCallRealMethod();

		Date entryDate = calendarUtil.formatString(DATE_FORMAT, "2018-06-05T13:00:00");
		Date outDate = calendarUtil.formatString(DATE_FORMAT, "2018-06-05T13:59:59");

		Calendar calendarOut = Calendar.getInstance();
		calendarOut.setTime(outDate);

		when(calendarUtil.getCurrentDate()).thenReturn(calendarOut);

		when(parkingRegistryRepository.save(any())).thenReturn(new ParkingRegistry());
		when(parkingInvoiceRepository.save(any())).thenReturn(new ParkingInvoice());

		ParkingRegistry registry = new ParkingRegistry();
		registry.setVehicle(vehicle);
		registry.setEntryDate(entryDate);

		when(parkingRegistryRepository.findActiveRegistryByVehicle(any())).thenReturn(registry);

		// ------------------------------------------
		// Act
		// ------------------------------------------
		ParkingInvoiceModel parkingInvoiceModel = parkingDepartureService.departVehicle(vehicleModel);
		int price = parkingInvoiceModel.getPrice();

		// ------------------------------------------
		// Assert
		// ------------------------------------------
		assertEquals(1000, price);
	}

	/**
	 * Prueba unitaria que verifica el costo de una moto por una hora
	 */
	@Test
	public void parkingPriceMotorcycleHour() {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		VehicleModel vehicleModel = new VehicleModelBuilder().withPlate("PLACA_123").withCubicCentimeters(123)
				.withVehicleType(VehicleTypeEnum.MOTORCYCLE).build();

		Vehicle vehicle = new VehicleModelConverter().convert(vehicleModel);

		when(vehicleRepository.findByPlate(any())).thenReturn(vehicle);

		when(calendarUtil.formatString(any(), any())).thenCallRealMethod();
		when(calendarUtil.formatDate(any(), any())).thenCallRealMethod();

		Date entryDate = calendarUtil.formatString(DATE_FORMAT, "2018-06-05T13:00:00");
		Date outDate = calendarUtil.formatString(DATE_FORMAT, "2018-06-05T13:59:59");

		Calendar calendarOut = Calendar.getInstance();
		calendarOut.setTime(outDate);

		when(calendarUtil.getCurrentDate()).thenReturn(calendarOut);

		when(parkingRegistryRepository.save(any())).thenReturn(new ParkingRegistry());
		when(parkingInvoiceRepository.save(any())).thenReturn(new ParkingInvoice());

		ParkingRegistry registry = new ParkingRegistry();
		registry.setVehicle(vehicle);
		registry.setEntryDate(entryDate);

		when(parkingRegistryRepository.findActiveRegistryByVehicle(any())).thenReturn(registry);

		// ------------------------------------------
		// Act
		// ------------------------------------------
		ParkingInvoiceModel parkingInvoiceModel = parkingDepartureService.departVehicle(vehicleModel);
		int price = parkingInvoiceModel.getPrice();

		// ------------------------------------------
		// Assert
		// ------------------------------------------
		assertEquals(500, price);
	}

	/**
	 * Prueba unitaria que verifica el costo de una moto por una hora con pago
	 * adicional por CC
	 */
	@Test
	public void parkingPriceMotorcycleHourWithAditionalPaymentByCC() {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		VehicleModel vehicleModel = new VehicleModelBuilder().withPlate("PLACA_123").withCubicCentimeters(600)
				.withVehicleType(VehicleTypeEnum.MOTORCYCLE).build();

		Vehicle vehicle = new VehicleModelConverter().convert(vehicleModel);

		when(vehicleRepository.findByPlate(any())).thenReturn(vehicle);

		when(calendarUtil.formatString(any(), any())).thenCallRealMethod();
		when(calendarUtil.formatDate(any(), any())).thenCallRealMethod();

		Date entryDate = calendarUtil.formatString(DATE_FORMAT, "2018-06-05T13:00:00");
		Date outDate = calendarUtil.formatString(DATE_FORMAT, "2018-06-05T13:59:59");

		Calendar calendarOut = Calendar.getInstance();
		calendarOut.setTime(outDate);

		when(calendarUtil.getCurrentDate()).thenReturn(calendarOut);

		when(parkingRegistryRepository.save(any())).thenReturn(new ParkingRegistry());
		when(parkingInvoiceRepository.save(any())).thenReturn(new ParkingInvoice());

		ParkingRegistry registry = new ParkingRegistry();
		registry.setVehicle(vehicle);
		registry.setEntryDate(entryDate);

		when(parkingRegistryRepository.findActiveRegistryByVehicle(any())).thenReturn(registry);

		// ------------------------------------------
		// Act
		// ------------------------------------------
		ParkingInvoiceModel parkingInvoiceModel = parkingDepartureService.departVehicle(vehicleModel);
		int price = parkingInvoiceModel.getPrice();

		// ------------------------------------------
		// Assert
		// ------------------------------------------
		assertEquals(2500, price);
	}

	/**
	 * Prueba unitaria que verifica el costo de un carro por un dia
	 */
	@Test
	public void parkingPriceDayHour() {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		VehicleModel vehicleModel = new VehicleModelBuilder().withPlate("PLACA_123").withCubicCentimeters(123)
				.withVehicleType(VehicleTypeEnum.CAR).build();

		Vehicle vehicle = new VehicleModelConverter().convert(vehicleModel);

		when(vehicleRepository.findByPlate(any())).thenReturn(vehicle);

		when(calendarUtil.formatString(any(), any())).thenCallRealMethod();
		when(calendarUtil.formatDate(any(), any())).thenCallRealMethod();

		Date entryDate = calendarUtil.formatString(DATE_FORMAT, "2018-06-05T13:00:00");
		Date outDate = calendarUtil.formatString(DATE_FORMAT, "2018-06-05T23:59:59");

		Calendar calendarOut = Calendar.getInstance();
		calendarOut.setTime(outDate);

		when(calendarUtil.getCurrentDate()).thenReturn(calendarOut);

		when(parkingRegistryRepository.save(any())).thenReturn(new ParkingRegistry());
		when(parkingInvoiceRepository.save(any())).thenReturn(new ParkingInvoice());

		ParkingRegistry registry = new ParkingRegistry();
		registry.setVehicle(vehicle);
		registry.setEntryDate(entryDate);

		when(parkingRegistryRepository.findActiveRegistryByVehicle(any())).thenReturn(registry);

		// ------------------------------------------
		// Act
		// ------------------------------------------
		ParkingInvoiceModel parkingInvoiceModel = parkingDepartureService.departVehicle(vehicleModel);
		int price = parkingInvoiceModel.getPrice();

		// ------------------------------------------
		// Assert
		// ------------------------------------------
		assertEquals(8000, price);
	}

	/**
	 * Prueba unitaria que verifica el costo de una moto por un dia
	 */
	@Test
	public void parkingPriceMotorcycleDay() {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		VehicleModel vehicleModel = new VehicleModelBuilder().withPlate("PLACA_123").withCubicCentimeters(123)
				.withVehicleType(VehicleTypeEnum.MOTORCYCLE).build();

		Vehicle vehicle = new VehicleModelConverter().convert(vehicleModel);

		when(vehicleRepository.findByPlate(any())).thenReturn(vehicle);

		when(calendarUtil.formatString(any(), any())).thenCallRealMethod();
		when(calendarUtil.formatDate(any(), any())).thenCallRealMethod();

		Date entryDate = calendarUtil.formatString(DATE_FORMAT, "2018-06-05T13:00:00");
		Date outDate = calendarUtil.formatString(DATE_FORMAT, "2018-06-05T23:59:59");

		Calendar calendarOut = Calendar.getInstance();
		calendarOut.setTime(outDate);

		when(calendarUtil.getCurrentDate()).thenReturn(calendarOut);

		when(parkingRegistryRepository.save(any())).thenReturn(new ParkingRegistry());
		when(parkingInvoiceRepository.save(any())).thenReturn(new ParkingInvoice());

		ParkingRegistry registry = new ParkingRegistry();
		registry.setVehicle(vehicle);
		registry.setEntryDate(entryDate);

		when(parkingRegistryRepository.findActiveRegistryByVehicle(any())).thenReturn(registry);

		// ------------------------------------------
		// Act
		// ------------------------------------------
		ParkingInvoiceModel parkingInvoiceModel = parkingDepartureService.departVehicle(vehicleModel);
		int price = parkingInvoiceModel.getPrice();

		// ------------------------------------------
		// Assert
		// ------------------------------------------
		assertEquals(4000, price);
	}

	/**
	 * Prueba unitaria que verifica el costo de un motociclea por dias, horas y
	 * adicional por CC
	 */
	@Test
	public void parkingPriceMotorcycleHourAndHoursWithAditionalPaymentByCC() {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		VehicleModel vehicleModel = new VehicleModelBuilder().withPlate("PLACA_123").withCubicCentimeters(600)
				.withVehicleType(VehicleTypeEnum.MOTORCYCLE).build();

		Vehicle vehicle = new VehicleModelConverter().convert(vehicleModel);

		when(vehicleRepository.findByPlate(any())).thenReturn(vehicle);

		when(calendarUtil.formatString(any(), any())).thenCallRealMethod();
		when(calendarUtil.formatDate(any(), any())).thenCallRealMethod();

		Date entryDate = calendarUtil.formatString(DATE_FORMAT, "2018-06-02T11:00:00");
		Date outDate = calendarUtil.formatString(DATE_FORMAT, "2018-06-05T12:00:00");

		Calendar calendarOut = Calendar.getInstance();
		calendarOut.setTime(outDate);

		when(calendarUtil.getCurrentDate()).thenReturn(calendarOut);

		when(parkingRegistryRepository.save(any())).thenReturn(new ParkingRegistry());
		when(parkingInvoiceRepository.save(any())).thenReturn(new ParkingInvoice());

		ParkingRegistry registry = new ParkingRegistry();
		registry.setVehicle(vehicle);
		registry.setEntryDate(entryDate);

		when(parkingRegistryRepository.findActiveRegistryByVehicle(any())).thenReturn(registry);

		// ------------------------------------------
		// Act
		// ------------------------------------------
		ParkingInvoiceModel parkingInvoiceModel = parkingDepartureService.departVehicle(vehicleModel);
		int price = parkingInvoiceModel.getPrice();

		// ------------------------------------------
		// Assert
		// ------------------------------------------
		// 3 dias + 1 hora + adicional por CC
		assertEquals(14500, price);
	}
}
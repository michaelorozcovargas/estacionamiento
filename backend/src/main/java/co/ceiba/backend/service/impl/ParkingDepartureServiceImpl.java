package co.ceiba.backend.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.ceiba.backend.converter.ParkingInvoiceConverter;
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
import co.ceiba.backend.utilities.CalendarUtil;

import static co.ceiba.backend.constants.ApplicationConstants.DATE_FORMAT;

@Service
public class ParkingDepartureServiceImpl implements ParkingDepartureService {

	@Autowired
	private ParkingRegistryRepository parkingRegistryRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private CalendarUtil calendarUtil;

	@Autowired
	private ParkingInvoiceRepository parkingInvoiceRepository;

	@Autowired
	private ParkingInvoiceConverter parkingInvoiceConverter;

	/**
	 * Metodo constructor de la clase
	 * 
	 * @param parkingRegistryRepository
	 * @param vehicleRepository
	 * @param calendarUtil
	 * @param parkingInvoiceRepository
	 * @param parkingInvoiceConverter
	 */
	public ParkingDepartureServiceImpl(ParkingRegistryRepository parkingRegistryRepository,
			VehicleRepository vehicleRepository, CalendarUtil calendarUtil,
			ParkingInvoiceRepository parkingInvoiceRepository, ParkingInvoiceConverter parkingInvoiceConverter) {
		super();
		this.parkingRegistryRepository = parkingRegistryRepository;
		this.vehicleRepository = vehicleRepository;
		this.calendarUtil = calendarUtil;
		this.parkingInvoiceRepository = parkingInvoiceRepository;
		this.parkingInvoiceConverter = parkingInvoiceConverter;
	}

	public ParkingInvoiceModel departVehicle(VehicleModel vehicleModel) {

		Vehicle vehicle = vehicleRepository.findByPlate(vehicleModel.getPlate());

		ParkingRegistry parkingRegistry = parkingRegistryRepository.findActiveRegistryByVehicle(vehicle);
		parkingRegistry.setOutDate(calendarUtil.getCurrentDate().getTime());

		ParkingInvoice parkingInvoice = generateParkingInvoice(parkingRegistry);
		parkingInvoice.setRegistry(parkingRegistry);

		parkingRegistryRepository.save(parkingRegistry);
		parkingInvoiceRepository.save(parkingInvoice);

		return parkingInvoiceConverter.convert(parkingInvoice);
	}

	private ParkingInvoice generateParkingInvoice(ParkingRegistry parkingRegistry) {

		ParkingInvoice parkingInvoice = new ParkingInvoice();

		parkingInvoice.setRegistry(parkingRegistry);

		parkingInvoice.setPrice(calculatePrice(parkingRegistry));

		return parkingInvoice;
	}

	private Integer calculatePrice(ParkingRegistry parkingRegistry) {

		Integer price = 0;

		LocalDateTime entryDate = calendarUtil.formatDate(DATE_FORMAT, parkingRegistry.getEntryDate());
		LocalDateTime outDate = calendarUtil.formatDate(DATE_FORMAT, parkingRegistry.getOutDate());

		Duration duration = Duration.between(entryDate, outDate);

		Long hours = duration.toHours();

		VehicleTypeEnum vehicleType = parkingRegistry.getVehicle().getVehicleType();

		switch (vehicleType) {
		case CAR:

			if (hours <= 1) {
				price = 1000;
			}

			break;

		default:
			break;
		}

		return price;
	}

}

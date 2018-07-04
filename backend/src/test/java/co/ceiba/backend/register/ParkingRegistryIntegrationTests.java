package co.ceiba.backend.register;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.ceiba.backend.builder.VehicleModelBuilder;
import co.ceiba.backend.constants.ResponseCodeEnum;
import co.ceiba.backend.entity.VehicleTypeEnum;
import co.ceiba.backend.model.VehicleModel;
import co.ceiba.backend.util.ServiceCaller;

/**
 * Contiene las pruebas de integracion para las funcionalidades de registro de
 * vehiculos en el estacionamiento
 * 
 * @author michael.orozco
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@SqlGroup(@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:static/integration_test/truncate.sql"))
public class ParkingRegistryIntegrationTests {

	/**
	 * Contexto de aplicacion web
	 */
	@Autowired
	private MockMvc mvc;

	/**
	 * Invocador de servicios
	 */
	private ServiceCaller serviceCaller = new ServiceCaller();

	/**
	 * URL del servicio de registro de ingreso
	 */
	private static final String REGISTRY_SERVICE_URL = "http://192.168.18.196:8080/parkingService/registerEntry";

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
	 * Prueba de integracion de fallo ante espacio para carros
	 * 
	 * @throws JsonProcessingException
	 */
	@Test
	@SqlGroup({
			@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:static/integration_test/insertMaxCars.sql"),
			@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:static/integration_test/truncate.sql") })
	public void failedRegisterCarBySpace() throws JsonProcessingException {

		VehicleModel vehicleModel = generateVehicleModel(VehicleTypeEnum.CAR);
		String request = new ObjectMapper().writeValueAsString(vehicleModel);

		serviceCaller.performCallService(mvc, REGISTRY_SERVICE_URL, request, ResponseCodeEnum.UNAVAILABLE_SPACE);
	}

	/**
	 * Prueba de integracion de fallo ante espacio para motos
	 * 
	 * @throws JsonProcessingException
	 */
	@Test
	@SqlGroup({
			@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:static/integration_test/insertMaxMotorcycles.sql"),
			@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:static/integration_test/truncate.sql") })
	public void failedRegisterMotorcycleBySpace() throws JsonProcessingException {

		VehicleModel vehicleModel = generateVehicleModel(VehicleTypeEnum.MOTORCYCLE);
		String request = new ObjectMapper().writeValueAsString(vehicleModel);

		serviceCaller.performCallService(mvc, REGISTRY_SERVICE_URL, request, ResponseCodeEnum.UNAVAILABLE_SPACE);
	}

	/**
	 * Prueba de integracion de exito ante ingreso de vehiculo
	 * 
	 * @throws JsonProcessingException
	 */
	@Test
	@SqlGroup(@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:static/integration_test/truncate.sql"))
	public void successRegisterVehicle() throws JsonProcessingException {

		VehicleModel vehicleModel = generateVehicleModel(VehicleTypeEnum.MOTORCYCLE);
		String request = new ObjectMapper().writeValueAsString(vehicleModel);

		serviceCaller.performCallService(mvc, REGISTRY_SERVICE_URL, request, ResponseCodeEnum.SUCCESSFULL);
	}

}

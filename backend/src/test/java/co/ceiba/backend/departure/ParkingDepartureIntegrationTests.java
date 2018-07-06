package co.ceiba.backend.departure;

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
import co.ceiba.backend.util.ServiceCaller;

/**
 * Contiene las pruebas de integracion para las funcionalidades de salida de
 * vehiculos en el estacionamiento
 * 
 * @author michael.orozco
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@SqlGroup(@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:static/integration_test/truncate.sql"))
public class ParkingDepartureIntegrationTests {

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
	 * URL del servicio de registro de salida
	 */
	private static final String REGISTRY_SERVICE_URL = "http://192.168.18.196:8080/parkingService/departVehicle";

	/**
	 * Prueba de integracion de exito para la salida de un carro
	 * 
	 * @throws JsonProcessingException
	 */
	@Test
	@SqlGroup({
			@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:static/integration_test/insertMaxCars.sql"),
			@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:static/integration_test/truncate.sql") })
	public void successDepartCar() throws JsonProcessingException {

		String request = new ObjectMapper().writeValueAsString(
				new VehicleModelBuilder().withPlate("Placa_9").withVehicleType(VehicleTypeEnum.CAR).build());

		serviceCaller.performPostCallService(mvc, REGISTRY_SERVICE_URL, request, ResponseCodeEnum.SUCCESSFULL);
	}

	/**
	 * Prueba de integracion de exito para la salida de una moto
	 * 
	 * @throws JsonProcessingException
	 */
	@Test
	@SqlGroup({
			@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:static/integration_test/insertMaxMotorcycles.sql"),
			@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:static/integration_test/truncate.sql") })
	public void successDepartMotorcycle() throws JsonProcessingException {

		String request = new ObjectMapper().writeValueAsString(
				new VehicleModelBuilder().withPlate("Placa_9").withVehicleType(VehicleTypeEnum.MOTORCYCLE).build());

		serviceCaller.performPostCallService(mvc, REGISTRY_SERVICE_URL, request, ResponseCodeEnum.SUCCESSFULL);
	}
}

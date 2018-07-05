package co.ceiba.backend.search;

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

import co.ceiba.backend.constants.ResponseCodeEnum;
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
public class ParkingSearchIntegrationTests {

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
	private static final String REGISTRY_SERVICE_URL = "http://192.168.18.196:8080/parkingService/searchVehicles";

	/**
	 * Prueba de integracion de fallo ante espacio para carros
	 * 
	 * @throws JsonProcessingException
	 */
	@Test
	@SqlGroup({
			@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:static/integration_test/insertMaxCars.sql"),
			@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:static/integration_test/truncate.sql") })
	public void successResult() throws JsonProcessingException {
		serviceCaller.performGetCallService(mvc, REGISTRY_SERVICE_URL, ResponseCodeEnum.SUCCESSFULL);
	}

}

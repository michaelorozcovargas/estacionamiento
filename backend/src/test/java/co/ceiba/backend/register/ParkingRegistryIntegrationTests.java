package co.ceiba.backend.register;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.ceiba.backend.builder.VehicleModelBuilder;
import co.ceiba.backend.constants.ResponseCodeEnum;
import co.ceiba.backend.entity.VehicleTypeEnum;
import co.ceiba.backend.model.VehicleModel;

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

	// @Test
	// @SqlGroup(@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts =
	// "classpath:static/integration_test/insertMaxCars.sql"))
	public void test() throws Exception {

		mvc.perform(post("http://192.168.18.196:8080/parkingService/test").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mvc.perform(post("http://192.168.18.196:8080/parkingService/test") // servicio a invocar
				.contentType(MediaType.APPLICATION_JSON)) // tipo de mensaje
				.andExpect(status().isOk()) // Codigo de respuesta 200
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) // Respuesta en JSON
				.andExpect(jsonPath("$.code").value("00"));

		/*
		 * mvc.perform(
		 * get("/api/employees").contentType(MediaType.APPLICATION_JSON)).andExpect(
		 * status().isOk())
		 * .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		 * .andExpect(jsonPath("$[0].name", is("bob")));
		 */
	}

	/**
	 * Prueba de integracion de fallo ante espacio para carros
	 */
	@Test
	@SqlGroup({
			@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:static/integration_test/insertMaxCars.sql"),
			@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:static/integration_test/truncate.sql") })
	public void failedRegisterCarBySpace() {

		try {

			VehicleModel vehicleModel = generateVehicleModel(VehicleTypeEnum.CAR);
			String request = new ObjectMapper().writeValueAsString(vehicleModel);

			mvc.perform(post(REGISTRY_SERVICE_URL) // servicio a invocar
					.contentType(MediaType.APPLICATION_JSON) // formato de la peticion
					.content(request)) // objeto de la peticion
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) // Respuesta en JSON
					.andExpect(jsonPath("$.code").value(ResponseCodeEnum.UNAVAILABLE_SPACE.getResponseCode()));

		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Prueba de integracion de fallo ante espacio para motos
	 */
	@Test
	@SqlGroup({
			@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:static/integration_test/insertMaxMotorcycles.sql"),
			@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:static/integration_test/truncate.sql") })
	public void failedRegisterMotorcycleBySpace() {

		try {

			VehicleModel vehicleModel = generateVehicleModel(VehicleTypeEnum.MOTORCYCLE);
			String request = new ObjectMapper().writeValueAsString(vehicleModel);

			mvc.perform(post(REGISTRY_SERVICE_URL) // servicio a invocar
					.contentType(MediaType.APPLICATION_JSON) // formato de la peticion
					.content(request)) // objeto de la peticion
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) // Respuesta en JSON
					.andExpect(jsonPath("$.code").value(ResponseCodeEnum.UNAVAILABLE_SPACE.getResponseCode()));

		} catch (Exception e) {
			fail();
		}
	}

	/**
	 * Prueba de integracion de exito ante ingreso de vehiculo
	 */
	@Test
	@SqlGroup(@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:static/integration_test/truncate.sql"))
	public void successRegisterVehicle() {
		try {

			VehicleModel vehicleModel = generateVehicleModel(VehicleTypeEnum.MOTORCYCLE);
			String request = new ObjectMapper().writeValueAsString(vehicleModel);

			mvc.perform(post(REGISTRY_SERVICE_URL) // servicio a invocar
					.contentType(MediaType.APPLICATION_JSON) // formato de la peticion
					.content(request)) // objeto de la peticion
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) // Respuesta en JSON
					.andExpect(jsonPath("$.code").value(ResponseCodeEnum.SUCCESSFULL.getResponseCode()));

		} catch (Exception e) {
			fail();
		}
	}

}

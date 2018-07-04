package co.ceiba.backend.search;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.backend.service.ParkingSearchService;

/**
 * Contiene las pruebas unitarias para las funcionalidades de busqueda de
 * vehiculos en el estacionamiento
 * 
 * @author michael.orozco
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ParkingSearchUnitTest {

	@Test
	public void emptyResult() {

		// ------------------------------------------
		// Arrange
		// ------------------------------------------
		ParkingSearchService parkingSearchService = new ParkingSearchService();

		// ------------------------------------------
		// Act
		// ------------------------------------------
		List<Object> resultList = parkingSearchService.searchParkedVehicles();

		// ------------------------------------------
		// Assert
		// ------------------------------------------
		assertEquals(0, resultList.size());

	}

}

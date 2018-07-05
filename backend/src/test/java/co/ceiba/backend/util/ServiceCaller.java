package co.ceiba.backend.util;

import static org.assertj.core.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import co.ceiba.backend.constants.ResponseCodeEnum;
import co.ceiba.backend.constants.StringConstants;

/**
 * Clase encargada de realizar las invocaciones de los servicios para las
 * pruebas de integracion
 * 
 * @author michael.orozco
 */
public class ServiceCaller {

	/**
	 * Tipo de contenido a manejar en las invocaciones
	 */
	private MediaType mediaType = MediaType.APPLICATION_JSON;

	/**
	 * Metodo encargado de realizar la invocacion a un servicio POST validando
	 * ciertos parametros
	 * 
	 * @param mvc
	 *            contexto de la aplicacion web
	 * @param serviceURL
	 *            servicio a invocar
	 * @param request
	 *            peticion a enviar
	 * @param expectedResponse
	 *            respuesta esperada
	 */
	public void performPostCallService(MockMvc mvc, String serviceURL, String request,
			ResponseCodeEnum expectedResponse) {

		try {

			mvc.perform(post(serviceURL) // servicio a invocar
					.contentType(mediaType) // formato de la peticion
					.content(request)) // objeto de la peticion
					.andExpect(content().contentTypeCompatibleWith(mediaType)) // Formato de la respuesta
					.andExpect(jsonPath(StringConstants.JSON_CODE).value(expectedResponse.getResponseCode())); // Asercion

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de realizar la invocacion a un servicio GET validando
	 * ciertos parametros
	 * 
	 * @param mvc
	 *            contexto de la aplicacion web
	 * @param serviceURL
	 *            servicio a invocar
	 * @param expectedResponse
	 *            respuesta esperada
	 */
	public void performGetCallService(MockMvc mvc, String serviceURL, ResponseCodeEnum expectedResponse) {

		try {

			mvc.perform(get(serviceURL)) // servicio a invocar
					.andExpect(content().contentTypeCompatibleWith(mediaType)) // Formato de la respuesta
					.andExpect(jsonPath(StringConstants.JSON_CODE).value(expectedResponse.getResponseCode())); // Asercion

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}

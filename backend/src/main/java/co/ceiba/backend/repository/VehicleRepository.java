package co.ceiba.backend.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.ceiba.backend.entity.Vehicle;

/**
 * Define las funciones de persistencia para la entidad {@link Vehicle}
 * 
 * @author michael.orozco
 */
@Repository("vehicleRepository")
public interface VehicleRepository extends JpaRepository<Vehicle, Serializable> {

	/**
	 * Permite buscar un vehiculo por su placa
	 * 
	 * @param plate
	 *            placa del vehiculo
	 * 
	 * @return vehiculo asociado a la placa
	 */
	Vehicle findByPlate(String plate);

}

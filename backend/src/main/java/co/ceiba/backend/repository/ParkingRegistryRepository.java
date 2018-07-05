package co.ceiba.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.ceiba.backend.entity.ParkingRegistry;
import co.ceiba.backend.entity.VehicleTypeEnum;

/**
 * Define las funciones de persistencia para la entidad {@link ParkingRegistry}
 * 
 * @author michael.orozco
 */
@Repository
public interface ParkingRegistryRepository extends JpaRepository<ParkingRegistry, Integer> {

	/**
	 * Consulta la cantidad de vehiculos parqueados segun un tipo
	 * 
	 * @param vehicleType
	 *            tipo de vehiculo
	 * 
	 * @return cantidad de vehiculos parqueados por tipo
	 */
	@Query(value = "SELECT COUNT(1) FROM ParkingRegistry p WHERE (p.vehicle.vehicleType = :vehicleType) AND (p.outDate IS NULL)")
	Integer countParkedVehiclesByType(@Param("vehicleType") VehicleTypeEnum vehicleType);

	/**
	 * Consulta los vehiculos que se encuentran en el estacionamiento
	 * 
	 * @return Lista de vehiculos parqueados
	 */
	@Query(value = "SELECT p FROM ParkingRegistry p JOIN p.vehicle v WHERE (p.outDate IS NULL)")
	List<ParkingRegistry> findParkedVehicles();
}
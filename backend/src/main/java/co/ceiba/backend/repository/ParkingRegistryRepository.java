package co.ceiba.backend.repository;

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
@Repository("parkingRegistryRepository")
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
	Integer countParkedVehiclesByVehicleType(@Param("vehicleType") VehicleTypeEnum vehicleType);

}
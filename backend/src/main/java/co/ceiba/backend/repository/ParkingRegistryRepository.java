package co.ceiba.backend.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.ceiba.backend.entity.ParkingRegistry;

/**
 * Define las funciones de persistencia para la entidad {@link ParkingRegistry}
 * 
 * @author michael.orozco
 */
@Repository("parkingRegistryRepository")
public interface ParkingRegistryRepository extends JpaRepository<ParkingRegistry, Serializable> {

}

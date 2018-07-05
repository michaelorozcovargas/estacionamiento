package co.ceiba.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.ceiba.backend.entity.ParkingInvoice;

/**
 * Define las funciones de persistencia para la entidad {@link ParkingInvoice}
 * 
 * @author michael.orozco
 */
public interface ParkingInvoiceRepository extends JpaRepository<ParkingInvoice, Integer> {

}

package com.visitor.management.system.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.visitor.management.system.entities.Visitor;


public interface VisitorRepo extends JpaRepository<Visitor, Long>{
	
//	@Query("SELECT  * FROM visitors v WHERE" +
//	"v.full_name LIKE CONCAT('%',:query,'%')" +
//	"v.address LIKE CONCAT('%',:query,''%)")
	
	@Query(value="SELECT * FROM visitors WHERE id LIKE (%:query%) or full_name LIKE (%:query%) or address LIKE (%:query%) or gender LIKE (%:query%) or mobile_number LIKE (%:query%) or purpose LIKE (%:query%) or visiting_date LIKE (%:query%) or email_id LIKE (%:query%)", nativeQuery = true)
	List<Visitor> searchVisitors(String query);

	//@Query(value = "SELECT * FROM visitors WHERE date(visiting_date) >:startingDate AND date(visiting_date) <:endingDate",nativeQuery = true)
	@Query(value = "SELECT * FROM visitors WHERE visiting_date between :startingDate AND :endingDate",nativeQuery = true)
	List<Visitor> searchByDateRange(String startingDate,String endingDate); 
	
	@Query(value = "SELECT * FROM visitors WHERE full_name LIKE (%:full_name%) AND email_id LIKE (%:email_id%) AND purpose LIKE (%:purpose%) AND visiting_date LIKE (%:visiting_date%)",nativeQuery = true)
	List<Visitor> advanceSearch(String full_name,String email_id,String purpose,String visiting_date);

}

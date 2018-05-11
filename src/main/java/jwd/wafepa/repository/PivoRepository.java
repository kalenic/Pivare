package jwd.wafepa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Pivo;

@Repository
public interface PivoRepository extends JpaRepository<Pivo, Long>{

	Page<Pivo> findByPivaraId(Long pivaraId, Pageable pageRequest);
	

	@Query("SELECT p FROM Pivo p JOIN p.pivara z WHERE "
			+ "(:naziv IS NULL or p.naziv like :naziv ) AND "
			+ "(:minIbu IS NULL OR p.ibu >= :minIbu  ) AND "
			+ "(:maxIbu IS NULL OR p.ibu <= :maxIbu) AND"
			+ "(:nazivPivare IS NULL OR z.naziv like :nazivPivare)"
			)
	Page<Pivo> pretraga(
			@Param("naziv") String naziv, 
			@Param("minIbu") Double minIbu, 
			@Param("maxIbu") Double maxIbu,
			@Param("nazivPivare") String nazivPivare,
			Pageable pageRequest);
}

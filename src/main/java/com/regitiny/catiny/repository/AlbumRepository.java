package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.Album;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Album entity.
 */
@Repository
@GeneratedByJHipster
public interface AlbumRepository extends JpaRepository<Album, Long>, JpaSpecificationExecutor<Album> {
  @Query(
    value = "select distinct album from Album album left join fetch album.images",
    countQuery = "select count(distinct album) from Album album"
  )
  Page<Album> findAllWithEagerRelationships(Pageable pageable);

  @Query("select distinct album from Album album left join fetch album.images")
  List<Album> findAllWithEagerRelationships();

  @Query("select album from Album album left join fetch album.images where album.id =:id")
  Optional<Album> findOneWithEagerRelationships(@Param("id") Long id);
}

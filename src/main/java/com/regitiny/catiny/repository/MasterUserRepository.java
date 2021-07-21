package com.regitiny.catiny.repository;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.MasterUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MasterUser entity.
 */
@Repository
@GeneratedByJHipster
public interface MasterUserRepository extends JpaRepository<MasterUser, Long>, JpaSpecificationExecutor<MasterUser> {
  @Query(
    value = "select distinct masterUser from MasterUser masterUser left join fetch masterUser.topicInterests",
    countQuery = "select count(distinct masterUser) from MasterUser masterUser"
  )
  Page<MasterUser> findAllWithEagerRelationships(Pageable pageable);

  @Query("select distinct masterUser from MasterUser masterUser left join fetch masterUser.topicInterests")
  List<MasterUser> findAllWithEagerRelationships();

  @Query("select masterUser from MasterUser masterUser left join fetch masterUser.topicInterests where masterUser.id =:id")
  Optional<MasterUser> findOneWithEagerRelationships(@Param("id") Long id);
}

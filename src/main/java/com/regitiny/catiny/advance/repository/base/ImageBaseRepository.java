package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.Image;
import com.regitiny.catiny.repository.ImageRepository;

/**
 * Spring Data SQL repository for the {@link Image} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.ImageAdvanceRepository}
 */
public interface ImageBaseRepository extends BaseRepository<Image>, CommonRepository<Image>, ImageRepository
{
}

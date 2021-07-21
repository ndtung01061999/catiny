package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.Album;
import com.regitiny.catiny.repository.AlbumRepository;

/**
 * Spring Data SQL repository for the {@link Album} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.AlbumAdvanceRepository}
 */
public interface AlbumBaseRepository extends BaseRepository<Album>, CommonRepository<Album>, AlbumRepository
{
}

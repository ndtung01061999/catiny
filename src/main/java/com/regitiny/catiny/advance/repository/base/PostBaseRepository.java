package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.Post;
import com.regitiny.catiny.repository.PostRepository;

/**
 * Spring Data SQL repository for the {@link Post} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.PostAdvanceRepository}
 */
public interface PostBaseRepository extends BaseRepository<Post>, CommonRepository<Post>, PostRepository
{
}

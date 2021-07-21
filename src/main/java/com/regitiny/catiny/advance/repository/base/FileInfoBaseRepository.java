package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.FileInfo;
import com.regitiny.catiny.repository.FileInfoRepository;

/**
 * Spring Data SQL repository for the {@link FileInfo} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.FileInfoAdvanceRepository}
 */
public interface FileInfoBaseRepository extends BaseRepository<FileInfo>, CommonRepository<FileInfo>, FileInfoRepository
{
}

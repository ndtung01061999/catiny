package com.regitiny.catiny.advance.repository.search;

import com.regitiny.catiny.advance.repository.search.base.FileInfoBaseSearch;

/**
 * Spring Data Elasticsearch repository for the {@link com.regitiny.catiny.domain.FileInfo} entity.
 * <p>
 * here contains complex queries with pure Elasticsearch syntax.
 * if you want to write simple query then you should write to {@link FileInfoBaseSearch}
 */
public interface FileInfoAdvanceSearch extends FileInfoBaseSearch
{
}

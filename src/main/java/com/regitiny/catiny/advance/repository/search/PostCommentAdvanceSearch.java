package com.regitiny.catiny.advance.repository.search;

import com.regitiny.catiny.advance.repository.search.base.PostCommentBaseSearch;

/**
 * Spring Data Elasticsearch repository for the {@link com.regitiny.catiny.domain.PostComment} entity.
 * <p>
 * here contains complex queries with pure Elasticsearch syntax.
 * if you want to write simple query then you should write to {@link PostCommentBaseSearch}
 */
public interface PostCommentAdvanceSearch extends PostCommentBaseSearch
{
}

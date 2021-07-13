package com.regitiny.catiny.repository.search;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.PostComment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PostComment} entity.
 */
@GeneratedByJHipster
public interface PostCommentSearchRepository extends ElasticsearchRepository<PostComment, Long> {}

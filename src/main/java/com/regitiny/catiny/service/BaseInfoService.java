package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.BaseInfoDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.BaseInfo}.
 */
@GeneratedByJHipster
public interface BaseInfoService {
  /**
   * Save a baseInfo.
   *
   * @param baseInfoDTO the entity to save.
   * @return the persisted entity.
   */
  BaseInfoDTO save(BaseInfoDTO baseInfoDTO);

  /**
   * Partially updates a baseInfo.
   *
   * @param baseInfoDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<BaseInfoDTO> partialUpdate(BaseInfoDTO baseInfoDTO);

  /**
   * Get all the baseInfos.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<BaseInfoDTO> findAll(Pageable pageable);
  /**
   * Get all the BaseInfoDTO where UserProfile is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereUserProfileIsNull();
  /**
   * Get all the BaseInfoDTO where AccountStatus is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereAccountStatusIsNull();
  /**
   * Get all the BaseInfoDTO where DeviceStatus is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereDeviceStatusIsNull();
  /**
   * Get all the BaseInfoDTO where Friend is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereFriendIsNull();
  /**
   * Get all the BaseInfoDTO where FollowUser is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereFollowUserIsNull();
  /**
   * Get all the BaseInfoDTO where FollowGroup is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereFollowGroupIsNull();
  /**
   * Get all the BaseInfoDTO where FollowPage is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereFollowPageIsNull();
  /**
   * Get all the BaseInfoDTO where FileInfo is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereFileInfoIsNull();
  /**
   * Get all the BaseInfoDTO where PagePost is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWherePagePostIsNull();
  /**
   * Get all the BaseInfoDTO where PageProfile is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWherePageProfileIsNull();
  /**
   * Get all the BaseInfoDTO where GroupPost is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereGroupPostIsNull();
  /**
   * Get all the BaseInfoDTO where Post is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWherePostIsNull();
  /**
   * Get all the BaseInfoDTO where PostComment is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWherePostCommentIsNull();
  /**
   * Get all the BaseInfoDTO where PostLike is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWherePostLikeIsNull();
  /**
   * Get all the BaseInfoDTO where GroupProfile is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereGroupProfileIsNull();
  /**
   * Get all the BaseInfoDTO where NewsFeed is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereNewsFeedIsNull();
  /**
   * Get all the BaseInfoDTO where MessageGroup is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereMessageGroupIsNull();
  /**
   * Get all the BaseInfoDTO where MessageContent is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereMessageContentIsNull();
  /**
   * Get all the BaseInfoDTO where RankUser is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereRankUserIsNull();
  /**
   * Get all the BaseInfoDTO where RankGroup is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereRankGroupIsNull();
  /**
   * Get all the BaseInfoDTO where Notification is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereNotificationIsNull();
  /**
   * Get all the BaseInfoDTO where Album is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereAlbumIsNull();
  /**
   * Get all the BaseInfoDTO where Video is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereVideoIsNull();
  /**
   * Get all the BaseInfoDTO where Image is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereImageIsNull();
  /**
   * Get all the BaseInfoDTO where VideoStream is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereVideoStreamIsNull();
  /**
   * Get all the BaseInfoDTO where VideoLiveStreamBuffer is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereVideoLiveStreamBufferIsNull();
  /**
   * Get all the BaseInfoDTO where TopicInterest is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereTopicInterestIsNull();
  /**
   * Get all the BaseInfoDTO where TodoList is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereTodoListIsNull();
  /**
   * Get all the BaseInfoDTO where Event is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<BaseInfoDTO> findAllWhereEventIsNull();

  /**
   * Get the "id" baseInfo.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<BaseInfoDTO> findOne(Long id);

  /**
   * Delete the "id" baseInfo.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the baseInfo corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<BaseInfoDTO> search(String query, Pageable pageable);
}

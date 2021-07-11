package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.repository.BaseInfoRepository;
import com.regitiny.catiny.repository.search.BaseInfoSearchRepository;
import com.regitiny.catiny.service.BaseInfoService;
import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.mapper.BaseInfoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BaseInfo}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class BaseInfoServiceImpl implements BaseInfoService {

  private final Logger log = LoggerFactory.getLogger(BaseInfoServiceImpl.class);

  private final BaseInfoRepository baseInfoRepository;

  private final BaseInfoMapper baseInfoMapper;

  private final BaseInfoSearchRepository baseInfoSearchRepository;

  public BaseInfoServiceImpl(
    BaseInfoRepository baseInfoRepository,
    BaseInfoMapper baseInfoMapper,
    BaseInfoSearchRepository baseInfoSearchRepository
  ) {
    this.baseInfoRepository = baseInfoRepository;
    this.baseInfoMapper = baseInfoMapper;
    this.baseInfoSearchRepository = baseInfoSearchRepository;
  }

  @Override
  public BaseInfoDTO save(BaseInfoDTO baseInfoDTO) {
    log.debug("Request to save BaseInfo : {}", baseInfoDTO);
    BaseInfo baseInfo = baseInfoMapper.toEntity(baseInfoDTO);
    baseInfo = baseInfoRepository.save(baseInfo);
    BaseInfoDTO result = baseInfoMapper.toDto(baseInfo);
    baseInfoSearchRepository.save(baseInfo);
    return result;
  }

  @Override
  public Optional<BaseInfoDTO> partialUpdate(BaseInfoDTO baseInfoDTO) {
    log.debug("Request to partially update BaseInfo : {}", baseInfoDTO);

    return baseInfoRepository
      .findById(baseInfoDTO.getId())
      .map(
        existingBaseInfo -> {
          baseInfoMapper.partialUpdate(existingBaseInfo, baseInfoDTO);

          return existingBaseInfo;
        }
      )
      .map(baseInfoRepository::save)
      .map(
        savedBaseInfo -> {
          baseInfoSearchRepository.save(savedBaseInfo);

          return savedBaseInfo;
        }
      )
      .map(baseInfoMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<BaseInfoDTO> findAll(Pageable pageable) {
    log.debug("Request to get all BaseInfos");
    return baseInfoRepository.findAll(pageable).map(baseInfoMapper::toDto);
  }

  /**
   *  Get all the baseInfos where UserProfile is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereUserProfileIsNull() {
    log.debug("Request to get all baseInfos where UserProfile is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getUserProfile() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where AccountStatus is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereAccountStatusIsNull() {
    log.debug("Request to get all baseInfos where AccountStatus is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getAccountStatus() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where DeviceStatus is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereDeviceStatusIsNull() {
    log.debug("Request to get all baseInfos where DeviceStatus is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getDeviceStatus() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where Friend is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereFriendIsNull() {
    log.debug("Request to get all baseInfos where Friend is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getFriend() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where FollowUser is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereFollowUserIsNull() {
    log.debug("Request to get all baseInfos where FollowUser is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getFollowUser() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where FollowGroup is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereFollowGroupIsNull() {
    log.debug("Request to get all baseInfos where FollowGroup is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getFollowGroup() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where FollowPage is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereFollowPageIsNull() {
    log.debug("Request to get all baseInfos where FollowPage is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getFollowPage() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where FileInfo is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereFileInfoIsNull() {
    log.debug("Request to get all baseInfos where FileInfo is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getFileInfo() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where PagePost is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWherePagePostIsNull() {
    log.debug("Request to get all baseInfos where PagePost is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getPagePost() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where PageProfile is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWherePageProfileIsNull() {
    log.debug("Request to get all baseInfos where PageProfile is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getPageProfile() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where GroupPost is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereGroupPostIsNull() {
    log.debug("Request to get all baseInfos where GroupPost is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getGroupPost() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where Post is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWherePostIsNull() {
    log.debug("Request to get all baseInfos where Post is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getPost() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where PostComment is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWherePostCommentIsNull() {
    log.debug("Request to get all baseInfos where PostComment is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getPostComment() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where PostLike is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWherePostLikeIsNull() {
    log.debug("Request to get all baseInfos where PostLike is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getPostLike() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where GroupProfile is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereGroupProfileIsNull() {
    log.debug("Request to get all baseInfos where GroupProfile is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getGroupProfile() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where NewsFeed is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereNewsFeedIsNull() {
    log.debug("Request to get all baseInfos where NewsFeed is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getNewsFeed() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where MessageGroup is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereMessageGroupIsNull() {
    log.debug("Request to get all baseInfos where MessageGroup is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getMessageGroup() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where MessageContent is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereMessageContentIsNull() {
    log.debug("Request to get all baseInfos where MessageContent is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getMessageContent() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where RankUser is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereRankUserIsNull() {
    log.debug("Request to get all baseInfos where RankUser is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getRankUser() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where RankGroup is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereRankGroupIsNull() {
    log.debug("Request to get all baseInfos where RankGroup is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getRankGroup() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where Notification is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereNotificationIsNull() {
    log.debug("Request to get all baseInfos where Notification is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getNotification() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where Album is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereAlbumIsNull() {
    log.debug("Request to get all baseInfos where Album is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getAlbum() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where Video is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereVideoIsNull() {
    log.debug("Request to get all baseInfos where Video is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getVideo() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where Image is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereImageIsNull() {
    log.debug("Request to get all baseInfos where Image is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getImage() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where VideoStream is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereVideoStreamIsNull() {
    log.debug("Request to get all baseInfos where VideoStream is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getVideoStream() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where VideoLiveStreamBuffer is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereVideoLiveStreamBufferIsNull() {
    log.debug("Request to get all baseInfos where VideoLiveStreamBuffer is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getVideoLiveStreamBuffer() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where TopicInterest is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereTopicInterestIsNull() {
    log.debug("Request to get all baseInfos where TopicInterest is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getTopicInterest() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where TodoList is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereTodoListIsNull() {
    log.debug("Request to get all baseInfos where TodoList is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getTodoList() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   *  Get all the baseInfos where Event is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findAllWhereEventIsNull() {
    log.debug("Request to get all baseInfos where Event is null");
    return StreamSupport
      .stream(baseInfoRepository.findAll().spliterator(), false)
      .filter(baseInfo -> baseInfo.getEvent() == null)
      .map(baseInfoMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<BaseInfoDTO> findOne(Long id) {
    log.debug("Request to get BaseInfo : {}", id);
    return baseInfoRepository.findById(id).map(baseInfoMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete BaseInfo : {}", id);
    baseInfoRepository.deleteById(id);
    baseInfoSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<BaseInfoDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of BaseInfos for query {}", query);
    return baseInfoSearchRepository.search(queryStringQuery(query), pageable).map(baseInfoMapper::toDto);
  }
}

package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.repository.MessageGroupRepository;
import com.regitiny.catiny.repository.search.MessageGroupSearchRepository;
import com.regitiny.catiny.service.MessageGroupService;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import com.regitiny.catiny.service.mapper.MessageGroupMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link MessageGroup}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class MessageGroupServiceImpl implements MessageGroupService {

    private final Logger log = LoggerFactory.getLogger(MessageGroupServiceImpl.class);

    private final MessageGroupRepository messageGroupRepository;

    private final MessageGroupMapper messageGroupMapper;

    private final MessageGroupSearchRepository messageGroupSearchRepository;

    public MessageGroupServiceImpl(
        MessageGroupRepository messageGroupRepository,
        MessageGroupMapper messageGroupMapper,
        MessageGroupSearchRepository messageGroupSearchRepository
    ) {
        this.messageGroupRepository = messageGroupRepository;
        this.messageGroupMapper = messageGroupMapper;
        this.messageGroupSearchRepository = messageGroupSearchRepository;
    }

    @Override
    public MessageGroupDTO save(MessageGroupDTO messageGroupDTO) {
        log.debug("Request to save MessageGroup : {}", messageGroupDTO);
        MessageGroup messageGroup = messageGroupMapper.toEntity(messageGroupDTO);
        messageGroup = messageGroupRepository.save(messageGroup);
        MessageGroupDTO result = messageGroupMapper.toDto(messageGroup);
        messageGroupSearchRepository.save(messageGroup);
        return result;
    }

    @Override
    public Optional<MessageGroupDTO> partialUpdate(MessageGroupDTO messageGroupDTO) {
        log.debug("Request to partially update MessageGroup : {}", messageGroupDTO);

        return messageGroupRepository
            .findById(messageGroupDTO.getId())
            .map(
                existingMessageGroup -> {
                    messageGroupMapper.partialUpdate(existingMessageGroup, messageGroupDTO);
                    return existingMessageGroup;
                }
            )
            .map(messageGroupRepository::save)
            .map(
                savedMessageGroup -> {
                    messageGroupSearchRepository.save(savedMessageGroup);

                    return savedMessageGroup;
                }
            )
            .map(messageGroupMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MessageGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MessageGroups");
        return messageGroupRepository.findAll(pageable).map(messageGroupMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MessageGroupDTO> findOne(Long id) {
        log.debug("Request to get MessageGroup : {}", id);
        return messageGroupRepository.findById(id).map(messageGroupMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MessageGroup : {}", id);
        messageGroupRepository.deleteById(id);
        messageGroupSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MessageGroupDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of MessageGroups for query {}", query);
        return messageGroupSearchRepository.search(queryStringQuery(query), pageable).map(messageGroupMapper::toDto);
    }
}

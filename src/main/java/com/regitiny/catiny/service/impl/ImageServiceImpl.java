package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.Image;
import com.regitiny.catiny.repository.ImageRepository;
import com.regitiny.catiny.repository.search.ImageSearchRepository;
import com.regitiny.catiny.service.ImageService;
import com.regitiny.catiny.service.dto.ImageDTO;
import com.regitiny.catiny.service.mapper.ImageMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Image}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class ImageServiceImpl implements ImageService {

  private final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

  private final ImageRepository imageRepository;

  private final ImageMapper imageMapper;

  private final ImageSearchRepository imageSearchRepository;

  public ImageServiceImpl(ImageRepository imageRepository, ImageMapper imageMapper, ImageSearchRepository imageSearchRepository) {
    this.imageRepository = imageRepository;
    this.imageMapper = imageMapper;
    this.imageSearchRepository = imageSearchRepository;
  }

  @Override
  public ImageDTO save(ImageDTO imageDTO) {
    log.debug("Request to save Image : {}", imageDTO);
    Image image = imageMapper.toEntity(imageDTO);
    image = imageRepository.save(image);
    ImageDTO result = imageMapper.toDto(image);
    imageSearchRepository.save(image);
    return result;
  }

  @Override
  public Optional<ImageDTO> partialUpdate(ImageDTO imageDTO) {
    log.debug("Request to partially update Image : {}", imageDTO);

    return imageRepository
      .findById(imageDTO.getId())
      .map(
        existingImage -> {
          imageMapper.partialUpdate(existingImage, imageDTO);

          return existingImage;
        }
      )
      .map(imageRepository::save)
      .map(
        savedImage -> {
          imageSearchRepository.save(savedImage);

          return savedImage;
        }
      )
      .map(imageMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<ImageDTO> findAll(Pageable pageable) {
    log.debug("Request to get all Images");
    return imageRepository.findAll(pageable).map(imageMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<ImageDTO> findOne(Long id) {
    log.debug("Request to get Image : {}", id);
    return imageRepository.findById(id).map(imageMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete Image : {}", id);
    imageRepository.deleteById(id);
    imageSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<ImageDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of Images for query {}", query);
    return imageSearchRepository.search(queryStringQuery(query), pageable).map(imageMapper::toDto);
  }
}

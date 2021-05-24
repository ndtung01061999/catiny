package com.regitiny.catiny.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageContentMapperTest {

  private MessageContentMapper messageContentMapper;

  @BeforeEach
  public void setUp() {
    messageContentMapper = new MessageContentMapperImpl();
  }
}

package com.regitiny.catiny.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageGroupMapperTest {

  private MessageGroupMapper messageGroupMapper;

  @BeforeEach
  public void setUp() {
    messageGroupMapper = new MessageGroupMapperImpl();
  }
}

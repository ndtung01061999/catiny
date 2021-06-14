package com.regitiny.catiny.advance.controller.rest;

import com.regitiny.catiny.service.dto.MessageGroupDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("/api/o/users")
public interface UserManagement
{
  /**
   * {@code POST  /message-groups} : Create a new messageGroup.
   * <p>
   * //@param userIds danh sách những user được thêm vào trong lúc tạo group
   *
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new messageGroupDTO, or with status {@code 400 (Bad Request)} if the messageGroup has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/message-groups/new-group")
  ResponseEntity<List<MessageGroupDTO>> searchUser() throws URISyntaxException;
}

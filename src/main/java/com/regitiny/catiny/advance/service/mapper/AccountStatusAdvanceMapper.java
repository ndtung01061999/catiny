package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.AccountStatusModel;
import com.regitiny.catiny.domain.AccountStatus;
import com.regitiny.catiny.service.dto.AccountStatusDTO;
import com.regitiny.catiny.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface AccountStatusAdvanceMapper extends EntityAdvanceMapper<AccountStatusModel, AccountStatusDTO>, EntityMapper<AccountStatusDTO, AccountStatus>
{
  AccountStatusDTO requestToDto(AccountStatusModel.Request request);


  List<AccountStatusDTO> requestToDto(List<AccountStatusModel.Request> request);


  AccountStatusModel.Response dtoToResponse(AccountStatusDTO dto);


  List<AccountStatusModel.Response> dtoToResponse(List<AccountStatusDTO> dto);
}

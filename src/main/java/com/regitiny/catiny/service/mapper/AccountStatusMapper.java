package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.AccountStatusDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountStatus} and its DTO {@link AccountStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class })
@GeneratedByJHipster
public interface AccountStatusMapper extends EntityMapper<AccountStatusDTO, AccountStatus> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  AccountStatusDTO toDto(AccountStatus s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  AccountStatusDTO toDtoId(AccountStatus accountStatus);
}

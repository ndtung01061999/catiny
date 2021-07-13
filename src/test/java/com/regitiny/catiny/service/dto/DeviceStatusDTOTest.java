package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class DeviceStatusDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(DeviceStatusDTO.class);
    DeviceStatusDTO deviceStatusDTO1 = new DeviceStatusDTO();
    deviceStatusDTO1.setId(1L);
    DeviceStatusDTO deviceStatusDTO2 = new DeviceStatusDTO();
    assertThat(deviceStatusDTO1).isNotEqualTo(deviceStatusDTO2);
    deviceStatusDTO2.setId(deviceStatusDTO1.getId());
    assertThat(deviceStatusDTO1).isEqualTo(deviceStatusDTO2);
    deviceStatusDTO2.setId(2L);
    assertThat(deviceStatusDTO1).isNotEqualTo(deviceStatusDTO2);
    deviceStatusDTO1.setId(null);
    assertThat(deviceStatusDTO1).isNotEqualTo(deviceStatusDTO2);
  }
}

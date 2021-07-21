package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class ClassInfoTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(ClassInfo.class);
    ClassInfo classInfo1 = new ClassInfo();
    classInfo1.setId(1L);
    ClassInfo classInfo2 = new ClassInfo();
    classInfo2.setId(classInfo1.getId());
    assertThat(classInfo1).isEqualTo(classInfo2);
    classInfo2.setId(2L);
    assertThat(classInfo1).isNotEqualTo(classInfo2);
    classInfo1.setId(null);
    assertThat(classInfo1).isNotEqualTo(classInfo2);
  }
}

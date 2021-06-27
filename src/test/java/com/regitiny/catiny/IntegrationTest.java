package com.regitiny.catiny;

import com.regitiny.catiny.CatinyApp;
import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.RedisTestContainerExtension;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = CatinyApp.class)
@ExtendWith(RedisTestContainerExtension.class)
public @interface IntegrationTest {
}

package com.regitiny.catiny.cucumber;

import com.regitiny.catiny.CatinyApp;
import com.regitiny.catiny.GeneratedByJHipster;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = CatinyApp.class)
@WebAppConfiguration
@GeneratedByJHipster
public class CucumberTestContextConfiguration {}

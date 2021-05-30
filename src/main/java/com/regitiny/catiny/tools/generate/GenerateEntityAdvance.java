package com.regitiny.catiny.tools.generate;

import com.regitiny.catiny.advance.service.LocalService;
import com.regitiny.catiny.advance.service.impl.LocalServiceImpl;
import com.squareup.javapoet.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.lang.model.element.Modifier;

@Log4j2
public class GenerateEntityAdvance
{
  private static final String BASE_PACKAGE = "com.regitiny.catiny";

  public static void Generate(String entityName)
  {
    genRepoAdvance(entityName);
    genSearchRepoAdvance(entityName);
    genService(entityName);
  }

  public static void genRepoAdvance(String entityName)
  {
    String packageAdvanceRepository = BASE_PACKAGE + ".advance.repository";
    String packageRepository = BASE_PACKAGE + ".repository";
    String entityRepository = entityName + "Repository";
    String entityAdvanceRepository = entityName + "AdvanceRepository";

    String javadoc = "Spring Data SQL advance-repository extends jhipster-repository for the Entity.\n" +
      "@see ${entityRepository} is base repository generate by jhipster";
    javadoc = javadoc.replace("${entityRepository}", entityRepository);

    TypeSpec interfaceAdvanceRepository = TypeSpec.interfaceBuilder(entityAdvanceRepository)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addAnnotation(Repository.class)
      .addAnnotation(AnnotationSpec.builder(SuppressWarnings.class).addMember("value", "$S", "unused").build())
      .addSuperinterface(ClassName.get(packageRepository, entityRepository))
      .build();

    var javaFile = JavaFile.builder(packageAdvanceRepository, interfaceAdvanceRepository)
      .build();
    log.info("...AdvanceRepository after generated : \n {}", javaFile.toString());
  }

  public static void genSearchRepoAdvance(String entityName)
  {
    String packageAdvanceRepository = BASE_PACKAGE + ".advance.repository.search";
    String packageRepository = BASE_PACKAGE + ".repository.search";
    String entityRepository = entityName + "SearchRepository";
    String entityAdvanceRepository = entityName + "AdvanceSearch";
    String entity = BASE_PACKAGE + ".domain." + entityName;

    String javadoc = " Spring Data Elasticsearch advance-repository extends jhipster-search-repository for the {@link ${entity}} entity.\n" +
      "@see ${searchRepository} is base repository generate by jhipster";
    javadoc = javadoc.replace("${entity}", entity)
      .replace("${searchRepository}", entityRepository);

    TypeSpec interfaceAdvanceRepository = TypeSpec.interfaceBuilder(entityAdvanceRepository)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addSuperinterface(ClassName.get(packageRepository, entityRepository))
      .build();

    var javaFile = JavaFile.builder(packageAdvanceRepository, interfaceAdvanceRepository)
      .build();
    log.info("...AdvanceRepository after generated : \n {}", javaFile.toString());
  }

  public static void genService(String entityName)
  {
    String packageInput = BASE_PACKAGE + ".service";
    String entityInput = entityName + "Service";
    String packageAdvanceOutput = BASE_PACKAGE + ".advance.service";
    String entityAdvanceOutput = entityName + "AdvanceService";
    String entityDomain = BASE_PACKAGE + ".domain." + entityName;

    String javadoc = " Spring Data Elasticsearch advance-repository extends jhipster-search-repository for the {@link ${entityDomain}} entityDomain.\n" +
      "@see ${searchRepository} is base repository generate by jhipster";
    javadoc = javadoc.replace("${entityDomain}", entityDomain)
      .replace("${searchRepository}", entityInput);

    TypeSpec interfaceAdvanceService = TypeSpec.interfaceBuilder(entityAdvanceOutput)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addSuperinterface(ParameterizedTypeName.get(ClassName.get(LocalService.class), TypeVariableName.get(entityInput), TypeVariableName.get(entityName + "QueryService")))
      .build();


    var javaFile = JavaFile.builder(packageAdvanceOutput, interfaceAdvanceService)
      .build();

    log.info("...AdvanceRepository after generated : \n {}", javaFile.toString());

    var packageImplInput = packageInput + ".impl";
    var entityImplInput = entityInput + "Impl";
    var packageAdvanceImplOutput = packageAdvanceOutput + ".impl";
    var entityAdvanceImplOutput = entityAdvanceOutput + "Impl";


    TypeSpec advanceServiceImpl = TypeSpec.classBuilder(entityAdvanceImplOutput)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .superclass(ParameterizedTypeName.get(ClassName.get(LocalServiceImpl.class), ClassName.get(packageInput, entityInput), ClassName.get(packageInput, entityName + "QueryService")))
      .addSuperinterface(TypeVariableName.get(entityAdvanceOutput))
      .build();


    var javaFileImpl = JavaFile.builder(packageAdvanceImplOutput, advanceServiceImpl)
      .build();


    log.info("...AdvanceRepository after generated : \n {}", javaFileImpl.toString());

  }

}

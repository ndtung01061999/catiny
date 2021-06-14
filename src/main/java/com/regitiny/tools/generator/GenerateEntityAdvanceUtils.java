package com.regitiny.tools.generator;

import com.regitiny.catiny.advance.service.LocalService;
import com.regitiny.catiny.advance.service.impl.LocalServiceImpl;
import com.regitiny.catiny.advance.service.mapper.EntityAdvanceMapper;
import com.regitiny.catiny.service.mapper.EntityMapper;
import com.regitiny.catiny.tools.utils.StringPool;
import com.squareup.javapoet.*;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.WordUtils;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2(topic = "warn")
public class GenerateEntityAdvanceUtils
{
  private static final String BASE_PACKAGE = "com.regitiny.catiny";
  private static final String ENTITY_NAME = "${entityName}";

  private static String entityName(String entityName)
  {
    return BASE_PACKAGE + ".domain." + entityName;
  }

  private GenerateEntityAdvanceUtils()
  {
    throw new IllegalStateException("GenerateEntityAdvanceUtils Class");
  }

  public static Tuple2<List<JavaFile>, List<JavaFile>> Generate(String entityName, String projectPath)
  {
    final var javaPath = projectPath + "/src/main/java";
    var file = new File(javaPath);
    if (!file.exists() || !file.isDirectory())
    {
      log.warn("rood folder not exist or isn't a folder ");
      log.info("create folder with path = {}", file.getPath());
      if (!file.mkdirs())
      {
        log.warn("can not create folder");
        return Tuple.of(null, null);
      }
    }
    var listFileErrors = new ArrayList<JavaFile>();
    var listFileGenerated = new ArrayList<JavaFile>();

    final var javaFiles = List.of(
      genBaseRepo(entityName),
      genRepoAdvance(entityName),
      genBaseSearch(entityName),
      genSearchRepoAdvance(entityName),
      genMapper(entityName),
      genService(entityName),
      genServiceImpl(entityName),
      genModel(entityName));
//    kiểm tra nếu đã tồn tại thì bỏ qua nếu chưa tồn tại thì bắt đầu generate.
    var result = javaFiles.stream()
      .filter(javaFile ->
      {
        var fileGenerate = new File(javaPath, javaFile.toJavaFileObject().toUri().toString());
        if (!fileGenerate.exists() && !fileGenerate.isDirectory())
          return true;
        log.info("file exist , {}", javaFile.toJavaFileObject().getName());
        listFileGenerated.add(javaFile);
        return false;
      })
      .map(javaFile -> Try.of(() -> javaFile.writeToFile(new File(javaPath)))
        .onSuccess(file1 -> listFileGenerated.add(javaFile))
        .onFailure(throwable -> listFileErrors.add(javaFile))
        .getOrElse(() -> null))
      .collect(Collectors.toList());
    log.info("Generated {}/{}", result.size(), javaFiles.size());
    return Tuple.of(listFileGenerated, listFileErrors);
  }

  public static JavaFile genBaseRepo(String entityName)
  {
    String packageAdvanceRepository = BASE_PACKAGE + ".advance.repository";
    String packageRepository = BASE_PACKAGE + ".repository";
    String entityRepository = entityName + "Repository";
    String entityAdvanceRepository = entityName + "AdvanceRepository";

    var javadoc = "Spring Data SQL repository for the {@link ${entityName}} entity.\n\n" +
      "here contains simple query JPA syntax.\n" +
      "if you want to write complex query pure (SQL or HQL) then you should write to :\n" +
      "{@link ${advanceRepository}}";
    javadoc = javadoc.replace(ENTITY_NAME, entityName(entityName))
      .replace("${advanceRepository}", packageAdvanceRepository + StringPool.PERIOD + entityAdvanceRepository);

    var interfaceBaseRepository = TypeSpec.interfaceBuilder(entityName + "BaseRepository")
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addSuperinterface(ClassName.get(packageRepository, entityRepository))
      .build();

    var javaFileBaseRepository = JavaFile.builder(packageAdvanceRepository + ".base", interfaceBaseRepository).build();
    log.debug("...BaseRepository after generated : {}", javaFileBaseRepository.toString());
    return javaFileBaseRepository;
  }

  public static JavaFile genRepoAdvance(String entityName)
  {
//    AdvanceRepository
    String packageAdvanceRepository = BASE_PACKAGE + ".advance.repository";
    String packageRepository = BASE_PACKAGE + ".repository";
    String entityRepository = entityName + "Repository";
    String entityAdvanceRepository = entityName + "AdvanceRepository";

    String javadoc =
      " Spring Data SQL repository for the {@link ${entityName}} entity.\n\n" +
        " here contains complex queries with pure SQL or HQL syntax.\n" +
        " if you want to write simple query then you should write to :\n" +
        " {@link ${baseRepository}}";
    javadoc = javadoc.replace("${baseRepository}", entityName + "BaseRepository")
      .replace(ENTITY_NAME, entityName(entityName));

    TypeSpec interfaceAdvanceRepository = TypeSpec.interfaceBuilder(entityAdvanceRepository)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addAnnotation(Repository.class)
      .addAnnotation(AnnotationSpec.builder(SuppressWarnings.class).addMember("value", "$S", "unused").build())
      .addSuperinterface(ClassName.get(packageAdvanceRepository + ".base", entityName + "BaseRepository"))
      .build();

    var javaFile = JavaFile.builder(packageAdvanceRepository, interfaceAdvanceRepository).build();
    log.debug("...AdvanceRepository after generated :  {}", javaFile.toString());
    return javaFile;
  }

  public static JavaFile genBaseSearch(String entityName)
  {
    String packageAdvanceSearch = BASE_PACKAGE + ".advance.repository.search";
    String entityAdvanceSearch = entityName + "AdvanceSearch";
    String packageSearch = BASE_PACKAGE + ".repository.search";
    String entitySearch = entityName + "SearchRepository";
    String entityBaseSearch = entityName + "BaseSearch";

    var javadoc = "Spring Data Elasticsearch repository for the {@link ${entityName}} entity.\n\n" +
      "here contains simple queries same as JPA syntax.\n" +
      "if you want to write simple query then you should write to {@link ${advanceSearch}}";
    javadoc = javadoc.replace(ENTITY_NAME, entityName(entityName))
      .replace("${advanceSearch}", packageAdvanceSearch + StringPool.PERIOD + entityAdvanceSearch);
    var interfaceBaseSearch = TypeSpec.interfaceBuilder(entityBaseSearch)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addSuperinterface(ClassName.get(packageSearch, entitySearch))
      .build();
    var javaFileBaseSearch = JavaFile.builder(packageAdvanceSearch + ".base", interfaceBaseSearch).build();
    log.debug("...BaseSearch after generated : {}", javaFileBaseSearch.toString());

    return javaFileBaseSearch;
  }

  public static JavaFile genSearchRepoAdvance(String entityName)
  {
    String packageAdvanceRepository = BASE_PACKAGE + ".advance.repository.search";
    String entityAdvanceRepository = entityName + "AdvanceSearch";
    String packageRepository = BASE_PACKAGE + ".repository.search";
    String entityRepository = entityName + "SearchRepository";
    String entityBaseSearch = entityName + "BaseSearch";

    String javadoc =
      " Spring Data Elasticsearch repository for the {@link ${entityName}} entity.\n\n" +
        " here contains complex queries with pure Elasticsearch syntax.\n" +
        "if you want to write simple query then you should write to {@link ${baseSearch}}";
    javadoc = javadoc.replace(ENTITY_NAME, entityName(entityName))
      .replace("${baseSearch}", packageAdvanceRepository + ".base." + entityBaseSearch);

    TypeSpec interfaceAdvanceRepository = TypeSpec.interfaceBuilder(entityAdvanceRepository)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addSuperinterface(ClassName.get(packageAdvanceRepository + ".base", entityBaseSearch))
      .build();

    var javaFile = JavaFile.builder(packageAdvanceRepository, interfaceAdvanceRepository).build();
    log.debug("...AdvanceSearch after generated : {}", javaFile.toString());

    return javaFile;
  }

  public static JavaFile genService(String entityName)
  {
    final String packageInput = BASE_PACKAGE + ".service";
    final String entityInput = entityName + "Service";
    final String packageAdvanceOutput = BASE_PACKAGE + ".advance.service";
    final String entityAdvanceOutput = entityName + "AdvanceService";

    String javadoc = " Spring Data Elasticsearch advance-repository extends jhipster-search-repository for the {@link ${entityName}} entityDomain.\n" +
      "@see ${searchRepository} is base repository generate by jhipster";
    javadoc = javadoc.replace(ENTITY_NAME, entityName(entityName))
      .replace("${searchRepository}", packageInput + StringPool.PERIOD + entityInput);

    TypeSpec interfaceAdvanceService = TypeSpec.interfaceBuilder(entityAdvanceOutput)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addSuperinterface(ParameterizedTypeName.get(ClassName.get(LocalService.class), ClassName.get(packageInput, entityInput), ClassName.get(packageInput, entityName + "QueryService")))
      .build();

    var javaFile = JavaFile.builder(packageAdvanceOutput, interfaceAdvanceService).build();
    log.debug("...AdvanceRepository after generated : \n {}", javaFile.toString());
    return javaFile;
  }

  public static JavaFile genServiceImpl(String entityName)
  {
    final String packageInput = BASE_PACKAGE + ".service";
    final String entityInput = entityName + "Service";
    final String packageAdvanceOutput = BASE_PACKAGE + ".advance.service";
    final String entityAdvanceOutput = entityName + "AdvanceService";

    final var packageImplInput = packageInput + ".impl";
    final var entityImplInput = entityInput + "Impl";
    final var packageAdvanceImplOutput = packageAdvanceOutput + ".impl";
    final var entityAdvanceImplOutput = entityAdvanceOutput + "Impl";


    var javadoc = "";

//    ClassImplement

    var advanceServiceImpl = TypeSpec.classBuilder(entityAdvanceImplOutput)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addAnnotation(Log4j2.class)
      .addAnnotation(Service.class)
      .addAnnotation(Transactional.class)
      .superclass(ParameterizedTypeName.get(ClassName.get(LocalServiceImpl.class), ClassName.get(packageInput, entityInput), ClassName.get(packageInput, entityName + "QueryService")))
      .addSuperinterface(ClassName.get(packageAdvanceOutput, entityAdvanceOutput));

    var constructor = MethodSpec.constructorBuilder()
      .addModifiers(Modifier.PUBLIC);

    Function2<String, String, Void> addBaseField = (pName, eName) ->
    {
      advanceServiceImpl.addField(
        ClassName.get(BASE_PACKAGE + pName, entityName + eName), WordUtils.uncapitalize(entityName + eName), Modifier.PRIVATE, Modifier.FINAL);
      constructor.addParameter(ClassName.get(BASE_PACKAGE + pName, entityName + eName), WordUtils.uncapitalize(entityName + eName))
        .addStatement("this.$N = $N", WordUtils.uncapitalize(entityName + eName), WordUtils.uncapitalize(entityName + eName));
      return null;
    };

    addBaseField.apply(".advance.repository", "AdvanceRepository");
    addBaseField.apply(".advance.repository.search", "AdvanceSearch");
    addBaseField.apply(".advance.service.mapper", "AdvanceMapper");

    advanceServiceImpl.addMethod(constructor.build());

    var javaFileImpl = JavaFile.builder(packageAdvanceImplOutput, advanceServiceImpl.build()).build();
    log.debug("...AdvanceRepository after generated : \n {}", javaFileImpl.toString());
    return javaFileImpl;
  }

  public static JavaFile genMapper(String entityName)
  {
    final String packageInput = BASE_PACKAGE + ".service.mapper";
    final String entityInput = entityName + "ServiceMapper";
    final String packageAdvanceOutput = BASE_PACKAGE + ".advance.service.mapper";
    final String entityAdvanceOutput = entityName + "AdvanceMapper";

    var javadoc = "";

    var advanceMapper = TypeSpec.interfaceBuilder(entityAdvanceOutput)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addAnnotation(AnnotationSpec.builder(Mapper.class)
        .addMember("componentModel", "$S", "spring")
        .addMember("uses", "$L", "{}").build())
      .addSuperinterface(
        ParameterizedTypeName.get(
          ClassName.get(EntityAdvanceMapper.class),
          ClassName.get(BASE_PACKAGE + ".advance.controller.model", entityName + "Model"),
          ClassName.get(BASE_PACKAGE + ".service.dto", entityName + "DTO")))
      .addSuperinterface(
        ParameterizedTypeName.get(
          ClassName.get(EntityMapper.class),
          ClassName.get(BASE_PACKAGE + ".service.dto", entityName + "DTO"),
          ClassName.get(BASE_PACKAGE + ".domain", entityName)))
//    EntityDTO requestToDto(EntityModel.Request request)
      .addMethod(MethodSpec.methodBuilder("requestToDto").addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
        .addParameter(ParameterSpec.builder(ClassName.get(BASE_PACKAGE + ".advance.controller.model", entityName + "Model").nestedClass("Request"), "request").build())
        .returns(ClassName.get(BASE_PACKAGE + ".service.dto", entityName + "DTO")).build())
//    List<EntityDTO> requestToDto(List<EntityModel.Request> request)
      .addMethod(MethodSpec.methodBuilder("requestToDto").addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
        .addParameter(ParameterSpec.builder(
          ParameterizedTypeName.get(ClassName.get(List.class), ClassName.get(BASE_PACKAGE + ".advance.controller.model", entityName + "Model").nestedClass("Request")), "request").build())
        .returns(ParameterizedTypeName.get(ClassName.get(List.class), ClassName.get(BASE_PACKAGE + ".service.dto", entityName + "DTO"))).build())
//    EntityModel.Response requestToDto(EntityDTO dto)
      .addMethod(MethodSpec.methodBuilder("dtoToResponse").addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
        .addParameter(ParameterSpec.builder(ClassName.get(BASE_PACKAGE + ".service.dto", entityName + "DTO"), "dto").build())
        .returns(ClassName.get(BASE_PACKAGE + ".advance.controller.model", entityName + "Model").nestedClass("Response")).build())
//    List<EntityModel.Response> requestToDto(List<EntityDTO> dto)
      .addMethod(MethodSpec.methodBuilder("dtoToResponse").addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
        .addParameter(ParameterSpec.builder(
          ParameterizedTypeName.get(ClassName.get(List.class), ClassName.get(BASE_PACKAGE + ".service.dto", entityName + "DTO")), "dto").build())
        .returns(ParameterizedTypeName.get(ClassName.get(List.class), ClassName.get(BASE_PACKAGE + ".advance.controller.model", entityName + "Model").nestedClass("Response"))).build());

    var javaFile = JavaFile.builder(packageAdvanceOutput, advanceMapper.build()).build();
    log.debug("...Mapper after generated : \n {}", javaFile.toString());
    return javaFile;
  }

  public static JavaFile genModel(String entityName)
  {
    final String packageInput = BASE_PACKAGE + ".service.dto";
    final String entityInput = entityName + "DTO";
    final String packageAdvanceOutput = BASE_PACKAGE + ".advance.controller.model";
    final String entityAdvanceOutput = entityName + "Model";

    var javadoc = "";

    Function1<TypeSpec.Builder, Void> addAnnotationLombok = typeSpec ->
    {
      typeSpec.addAnnotation(Data.class)
        .addAnnotation(Builder.class)
        .addAnnotation(NoArgsConstructor.class)
        .addAnnotation(AllArgsConstructor.class)
        .addSuperinterface(Serializable.class);
      return null;
    };

    var entityModel = TypeSpec.classBuilder(entityAdvanceOutput)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc);
    var entityRequest = TypeSpec.classBuilder("Request")
      .addModifiers(Modifier.PUBLIC, Modifier.STATIC);
    var entityResponse = TypeSpec.classBuilder("Response")
      .addModifiers(Modifier.PUBLIC, Modifier.STATIC);

    addAnnotationLombok.apply(entityModel);
    addAnnotationLombok.apply(entityRequest);
    addAnnotationLombok.apply(entityResponse);

    try
    {
      var fields = Class.forName(packageInput + StringPool.PERIOD + entityInput).getDeclaredFields();
      var privateFields = Arrays.stream(fields)
        .filter(field -> java.lang.reflect.Modifier.isPrivate(field.getModifiers()))
        .collect(Collectors.toList());

      privateFields.forEach(field ->
      {
        var annotations = Arrays.stream(field.getAnnotations())
          .map(AnnotationSpec::get).collect(Collectors.toList());
        var fieldBuilder = FieldSpec
          .builder(field.getType(), field.getName(), Modifier.PRIVATE)
          .addAnnotations(annotations)
          .build();

        entityModel.addField(fieldBuilder);
        entityRequest.addField(fieldBuilder);
        entityResponse.addField(fieldBuilder);
      });
    }
    catch (ClassNotFoundException e)
    {
      log.debug("ClassNotFoundException when generate Model:", e);
    }

    entityModel.addType(entityRequest.build())
      .addType(entityResponse.build());

    var javaFile = JavaFile.builder(packageAdvanceOutput, entityModel.build()).build();
    log.debug(javaFile.toString());
    return javaFile;
  }

}

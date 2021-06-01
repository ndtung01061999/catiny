package com.regitiny.catiny.tools.generate;

import com.regitiny.catiny.advance.service.LocalService;
import com.regitiny.catiny.advance.service.impl.LocalServiceImpl;
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
import org.springframework.stereotype.Repository;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2()
public class GenerateEntityAdvanceUtils
{
  private static final String BASE_PACKAGE = "com.regitiny.catiny";
  private static final String BASE_PATH_GENERATED_OUTPUT = "C:/Users/yuvytung/IdeaProjects/catiny";
  private static final String CODE_JAVA_OUTPUT_PATH = BASE_PATH_GENERATED_OUTPUT + "/src/main/java/";

  private GenerateEntityAdvanceUtils()
  {
    throw new IllegalStateException("GenerateEntityAdvanceUtils Class");
  }

  public static Tuple2<List<File>, String> Generate(String entityName)
  {
    var file = new File(CODE_JAVA_OUTPUT_PATH);
    if (!file.exists() || !file.isDirectory())
    {
      log.warn("rood folder not exist or isn't a folder ");
      log.info("create folder with path = {}", file.getPath());
      if (file.mkdirs())
      {
        log.warn("can not create folder");
        return null;
      }
    }

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
      .filter(javaFile -> Try.of(() ->
      {
        var fileGenerate = new File(CODE_JAVA_OUTPUT_PATH, javaFile.toJavaFileObject().toUri().toString());
        return !fileGenerate.exists() && fileGenerate.canWrite() && !fileGenerate.isDirectory();
      }).getOrElse(false))
      .map(javaFile -> Try.of(() -> javaFile.writeToFile(new File(CODE_JAVA_OUTPUT_PATH)))
        .onSuccess(file1 -> log.info("generated a file , isExist = {}, path : {}", file1.exists(), file1.getPath())).getOrElse(() -> null))
      .collect(Collectors.toList());
    log.debug("Generated {}/{}", result.size(), javaFiles.size());
    return Tuple.of(result, entityName);
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
    javadoc = javadoc.replace("${entityName}", BASE_PACKAGE + ".domain." + entityName)
      .replace("${advanceRepository}", packageAdvanceRepository + StringPool.PERIOD + entityAdvanceRepository);

    var interfaceBaseRepository = TypeSpec.interfaceBuilder(entityName + "BaseRepository")
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addSuperinterface(ClassName.get(packageRepository, entityRepository))
      .build();

    var javaFileBaseRepository = JavaFile.builder(packageAdvanceRepository + ".base", interfaceBaseRepository).build();
//    log.info("...BaseRepository after generated : {}", javaFileBaseRepository.toString());
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
      .replace("${entityName}", BASE_PACKAGE + ".domain." + entityName);

    TypeSpec interfaceAdvanceRepository = TypeSpec.interfaceBuilder(entityAdvanceRepository)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addAnnotation(Repository.class)
      .addAnnotation(AnnotationSpec.builder(SuppressWarnings.class).addMember("value", "$S", "unused").build())
      .addSuperinterface(ClassName.get(packageAdvanceRepository + ".base", entityName + "BaseRepository"))
      .build();

    var javaFile = JavaFile.builder(packageAdvanceRepository, interfaceAdvanceRepository)
      .build();
//    log.info("...AdvanceRepository after generated :  {}", javaFile.toString());
    return javaFile;
  }

  public static JavaFile genBaseSearch(String entityName)
  {
    String packageAdvanceRepository = BASE_PACKAGE + ".advance.repository.search";
    String entityAdvanceRepository = entityName + "AdvanceSearch";
    String packageRepository = BASE_PACKAGE + ".repository.search";
    String entityRepository = entityName + "SearchRepository";
    String entity = BASE_PACKAGE + ".domain." + entityName;

    var javadoc = "";

    var interfaceBaseSearch = TypeSpec.interfaceBuilder(entityName + "BaseSearch")
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addSuperinterface(ClassName.get(packageRepository, entityRepository))
      .build();
    var javaFileBaseSearch = JavaFile.builder(packageAdvanceRepository + ".base", interfaceBaseSearch)
      .build();
//    log.info("...BaseSearch after generated : {}", javaFileBaseSearch.toString());

    return javaFileBaseSearch;
  }

  public static JavaFile genSearchRepoAdvance(String entityName)
  {
    String packageAdvanceRepository = BASE_PACKAGE + ".advance.repository.search";
    String entityAdvanceRepository = entityName + "AdvanceSearch";
    String packageRepository = BASE_PACKAGE + ".repository.search";
    String entityRepository = entityName + "SearchRepository";
    String entity = BASE_PACKAGE + ".domain." + entityName;

    String javadoc =
      " Spring Data Elasticsearch repository for the {@link ${entityName}} entity.\n\n" +
        " here contains complex queries with pure Elasticsearch syntax.\n" +
        "if you want to write simple query then you should write to {@link ${baseSearch}}";
    javadoc = javadoc.replace("${entityName}", entity)
      .replace("${baseSearch}", packageAdvanceRepository + ".base." + entityName + "BaseSearch");

    TypeSpec interfaceAdvanceRepository = TypeSpec.interfaceBuilder(entityAdvanceRepository)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addSuperinterface(ClassName.get(packageAdvanceRepository + ".base", entityName + "BaseSearch"))
      .build();

    var javaFile = JavaFile.builder(packageAdvanceRepository, interfaceAdvanceRepository)
      .build();
//    log.info("...AdvanceSearch after generated : {}", javaFile.toString());

    return javaFile;
  }

  public static JavaFile genService(String entityName)
  {
    final String packageInput = BASE_PACKAGE + ".service";
    final String entityInput = entityName + "Service";
    final String packageAdvanceOutput = BASE_PACKAGE + ".advance.service";
    final String entityAdvanceOutput = entityName + "AdvanceService";
    final String entityDomain = BASE_PACKAGE + ".domain." + entityName;

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

//    log.info("...AdvanceRepository after generated : \n {}", javaFile.toString());

    return javaFile;
  }

  public static JavaFile genServiceImpl(String entityName)
  {
    final String packageInput = BASE_PACKAGE + ".service";
    final String entityInput = entityName + "Service";
    final String packageAdvanceOutput = BASE_PACKAGE + ".advance.service";
    final String entityAdvanceOutput = entityName + "AdvanceService";
    final String entityDomain = BASE_PACKAGE + ".domain." + entityName;

    final var packageImplInput = packageInput + ".impl";
    final var entityImplInput = entityInput + "Impl";
    final var packageAdvanceImplOutput = packageAdvanceOutput + ".impl";
    final var entityAdvanceImplOutput = entityAdvanceOutput + "Impl";


    var javadoc = "";

//    ClassImplement

    var advanceServiceImpl = TypeSpec.classBuilder(entityAdvanceImplOutput)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .superclass(ParameterizedTypeName.get(ClassName.get(LocalServiceImpl.class), ClassName.get(packageInput, entityInput), ClassName.get(packageInput, entityName + "QueryService")))
      .addSuperinterface(TypeVariableName.get(entityAdvanceOutput));

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

//    log.info("...AdvanceRepository after generated : \n {}", javaFileImpl.toString());

    return javaFileImpl;
  }

  public static JavaFile genMapper(String entityName)
  {
    final String packageInput = BASE_PACKAGE + ".service.mapper";
    final String entityInput = entityName + "ServiceMapper";
    final String packageAdvanceOutput = BASE_PACKAGE + ".advance.service.mapper";
    final String entityAdvanceOutput = entityName + "AdvanceServiceMapper";
    final String entityDomain = BASE_PACKAGE + ".domain." + entityName;

    var javadoc = "";

    var advanceMapper = TypeSpec.interfaceBuilder(entityAdvanceOutput)
      .addModifiers(Modifier.PUBLIC)
      .addJavadoc(javadoc)
      .addSuperinterface(
        ParameterizedTypeName.get(
          ClassName.get(EntityMapper.class),
          ClassName.get(BASE_PACKAGE + ".advance.controller.model", entityName + "Model"),
          ClassName.get(BASE_PACKAGE + ".service.dto", entityName + "DTO")));

    var javaFile = JavaFile.builder(packageAdvanceOutput, advanceMapper.build()).build();
//    log.info("...Mapper after generated : \n {}", javaFile.toString());

    return javaFile;
  }


  public static JavaFile genModel(String entityName)
  {
    final String packageInput = BASE_PACKAGE + ".service.dto";
    final String entityInput = entityName + "DTO";
    final String packageAdvanceOutput = BASE_PACKAGE + ".advance.controller.model";
    final String entityAdvanceOutput = entityName + "Model";
    final String entityDomain = BASE_PACKAGE + ".domain." + entityName;

    var javadoc = "";

    Function1<TypeSpec.Builder, Void> addAnnotationLombok = (typeSpec) ->
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
    var entityInputModel = TypeSpec.classBuilder("InputModel")
      .addModifiers(Modifier.PUBLIC, Modifier.STATIC);
    var entityOutputModel = TypeSpec.classBuilder("OutputModel")
      .addModifiers(Modifier.PUBLIC, Modifier.STATIC);

    addAnnotationLombok.apply(entityModel);
    addAnnotationLombok.apply(entityInputModel);
    addAnnotationLombok.apply(entityOutputModel);

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
        entityInputModel.addField(fieldBuilder);
        entityOutputModel.addField(fieldBuilder);
      });
    }
    catch (ClassNotFoundException e)
    {
      log.debug("ClassNotFoundException when generate Model:", e);
    }

    entityModel.addType(entityInputModel.build())
      .addType(entityOutputModel.build());

    var javaFile = JavaFile.builder(packageAdvanceOutput, entityModel.build()).build();
//    log.info(javaFile.toString());

    return javaFile;
  }

}

package com.regitiny.catiny.advance.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.MessageGroup} entity.
 */
@ApiModel(description = "The PostDetails entity.\n@author A true hipster")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageGroupModel implements Serializable
{

  private Long id;

  /**
   * uuid
   */
  @NotNull
  @ApiModelProperty(value = "uuid", required = true)
  private UUID uuid;

  @NotNull
  private Long userId;

  @NotNull
  private String groupId;

  private String groupName;

  private String addBy;

  @Lob
  private String lastContent;

  /**
   * createdDate
   */
  @ApiModelProperty(value = "createdDate")
  private Instant createdDate;

  /**
   * modifiedDate
   */
  @ApiModelProperty(value = "modifiedDate")
  private Instant modifiedDate;

  /**
   * createdBy
   */
  @ApiModelProperty(value = "createdBy")
  private String createdBy;

  /**
   * modifiedBy
   */
  @ApiModelProperty(value = "modifiedBy")
  private String modifiedBy;

  @Data
  public static class MessageContentModelIn implements Serializable
  {

    private Long id;

    /**
     * uuid
     */
    @NotNull
    @ApiModelProperty(value = "uuid", required = true)
    private UUID uuid;

    @NotNull
    private String groupId;

    @Lob
    private String content;

    private String sender;

    private String status;

    /**
     * searchField
     */
    @ApiModelProperty(value = "searchField")
    @Lob
    private String searchField;

    /**
     * role
     */
    @Size(max = 511)
    @ApiModelProperty(value = "role")
    private String role;

    /**
     * createdDate
     */
    @ApiModelProperty(value = "createdDate")
    private Instant createdDate;

    /**
     * modifiedDate
     */
    @ApiModelProperty(value = "modifiedDate")
    private Instant modifiedDate;

    /**
     * createdBy
     */
    @ApiModelProperty(value = "createdBy")
    private String createdBy;

    /**
     * modifiedBy
     */
    @ApiModelProperty(value = "modifiedBy")
    private String modifiedBy;

    /**
     * comment
     */
    @Size(max = 511)
    @ApiModelProperty(value = "comment")
    private String comment;

  }

}

package com.upgrad.technical.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * UpdateImageRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-02-17T10:45:14.006+05:30")

public class UpdateImageRequest   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("status")
  private String status = null;

  public UpdateImageRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the image
   * @return name
  **/
  @ApiModelProperty(value = "Name of the image")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UpdateImageRequest description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Description of the image
   * @return description
  **/
  @ApiModelProperty(value = "Description of the image")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public UpdateImageRequest status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Status of the image
   * @return status
  **/
  @ApiModelProperty(value = "Status of the image")


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateImageRequest updateImageRequest = (UpdateImageRequest) o;
    return Objects.equals(this.name, updateImageRequest.name) &&
        Objects.equals(this.description, updateImageRequest.description) &&
        Objects.equals(this.status, updateImageRequest.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateImageRequest {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


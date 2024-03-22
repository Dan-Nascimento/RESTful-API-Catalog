package mb.dabm.servcatapi.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiErrorResponse {
  //http status code
  private HttpStatus status;
  // in case we want to provide API based custom error code
  private int error_code;
  // customer error message to the client API
  private String message;
  // Any furthur details which can help client API
  private String detail;
  private String path;
  private String version;

  // Time of the error.make sure to define a standard time zone to avoid any confusion.
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy hh:mm:ss")
  private LocalDateTime timeStamp;

  // getter and setters
  //Builder
  public static final class ApiErrorResponseBuilder {
    private HttpStatus status;
    private int error_code;
    private String message;
    private String detail;
    private String path;
    private String version;
    private LocalDateTime timeStamp;

    public ApiErrorResponseBuilder() {
    }

    public static ApiErrorResponseBuilder anApiErrorResponse() {
      return new ApiErrorResponseBuilder();
    }

    public ApiErrorResponseBuilder withStatus(HttpStatus status) {
      this.status = status;
      return this;
    }

    public ApiErrorResponseBuilder withError_code(int error_code) {
      this.error_code = error_code;
      return this;
    }

    public ApiErrorResponseBuilder withMessage(String message) {
      this.message = message;
      return this;
    }

    public ApiErrorResponseBuilder withDetail(String detail) {
      this.detail = detail;
      return this;
    }

    public ApiErrorResponseBuilder withPath(String path) {
      this.path = path;
      return this;
    }

    public ApiErrorResponseBuilder withVersion(String version) {
      this.version = version;
      return this;
    }

    public ApiErrorResponseBuilder atTime(LocalDateTime timeStamp) {
      this.timeStamp = timeStamp;
      return this;
    }

    public ApiErrorResponse build() {
      ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
      apiErrorResponse.status = this.status;
      apiErrorResponse.error_code = this.error_code;
      apiErrorResponse.message = this.message;
      apiErrorResponse.detail = this.detail;
      apiErrorResponse.path = this.path;
      apiErrorResponse.version = this.version;
      apiErrorResponse.timeStamp = this.timeStamp;
      return apiErrorResponse;
    }
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public int getError_code() {
    return error_code;
  }

  public void setError_code(int error_code) {
    this.error_code = error_code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public LocalDateTime getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(LocalDateTime timeStamp) {
    this.timeStamp = timeStamp;
  }

  public String getPath() { return path; }

  public void setPath(String path) { this.path = path; }

  public String getVersion() { return version;}

  public void setVersion(String version) { this.version = version; }

}

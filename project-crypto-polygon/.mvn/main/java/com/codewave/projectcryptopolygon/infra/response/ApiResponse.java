package com.codewave.projectcryptopolygon.infra.response;



import com.codewave.projectcryptopolygon.infra.exception.BizCode;
import com.codewave.projectcryptopolygon.infra.exception.BusinessException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
  boolean success;
  int code;
  String message;
  T data;

  public ApiResponse(Builder<T> builder) {
    this.success = builder.success;
    this.code = builder.code;
    this.message = builder.message;
    this.data = builder.data;
  }

  public static <T> Builder<T> builder() {
    return new Builder<>();
  }

  public static class Builder<T> {
    boolean success;
    int code;
    String message;
    T data;

    // success()
    public Builder<T> ok() {
      this.success = BizCode.SUCCESS.isSuccess();
      this.code = BizCode.SUCCESS.getCode();
      this.message = BizCode.SUCCESS.getMessage();
      return this;
    }

    public Builder<T> error(BusinessException e) {
      this.success = false;
      this.code = e.getCode();
      this.message = e.getMessage();
      return this;
    }

    public Builder<T> bizCode(BizCode bCode) {
      this.success = bCode.isSuccess();
      this.code = bCode.getCode();
      this.message = bCode.getMessage();
      return this;
    }

    private Builder<T> success(boolean success) {
      this.success = success;
      return this;
    }

    private Builder<T> code(int code) {
      this.code = code;
      return this;
    }

    public Builder<T> message(String message) {
      this.message = message;
      return this;
    }

    public Builder<T> data(T data) {
      this.data = data;
      return this;
    }

    public ApiResponse<T> build() {
      return new ApiResponse<>(this);
    }
  }

  // method success()
  /*
   * public ApiResponse<T> success2() {
   * success = true;
   * code = 20000;
   * message = "SUCESS";
   * return this;
   * }
   */

}

// {
// "success" : true
// "code" : 20000
// "message" "SUCCESS"
// "data" : {}
// }
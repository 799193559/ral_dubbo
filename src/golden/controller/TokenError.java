package golden.controller;

public class TokenError extends RuntimeException {
  private String code;
  
  private String message;
  
  public String getCode() {
    return this.code;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public TokenError(String code, String message) {
    this.code = code;
    this.message = message;
  }
}

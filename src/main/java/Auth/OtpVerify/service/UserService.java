package Auth.OtpVerify.service;

import Auth.OtpVerify.dto.LoginRequest;
import Auth.OtpVerify.dto.Request;
import Auth.OtpVerify.dto.Response;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Response> signup(Request request);
    ResponseEntity<Response> login(LoginRequest request);
    Response sendOtp();
    Response validateOtp();
    Response resetPassword();
    Response changePassword();
}

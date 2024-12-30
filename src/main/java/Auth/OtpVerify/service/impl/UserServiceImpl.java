package Auth.OtpVerify.service.impl;

import Auth.OtpVerify.dto.LoginRequest;
import Auth.OtpVerify.dto.Request;
import Auth.OtpVerify.dto.Response;
import Auth.OtpVerify.dto.UserInfo;
import Auth.OtpVerify.entity.User;
import org.modelmapper.ModelMapper;
import Auth.OtpVerify.repository.UserRepository;
import Auth.OtpVerify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Response> signup(Request request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body(Response.builder()
                    .statusCode(400)
                    .responseMessage("Attempt to save duplicate user record")
                    .build());
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName()).build();

        User saveUser = userRepository.save(user);
        return ResponseEntity.ok(Response.builder()
                .statusCode(200)
                .responseMessage("SUCCESS")
                .userInfo(modelMapper.map(saveUser, UserInfo.class))
                .build());
    }

    @Override
    public ResponseEntity<Response> login(LoginRequest request) {
        return null;
    }

    @Override
    public Response sendOtp() {
        return null;
    }

    @Override
    public Response validateOtp() {
        return null;
    }

    @Override
    public Response resetPassword() {
        return null;
    }

    @Override
    public Response changePassword() {
        return null;
    }
}

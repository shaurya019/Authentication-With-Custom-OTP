package Auth.OtpVerify.service.impl;

import Auth.OtpVerify.dto.*;
import Auth.OtpVerify.entity.Otp;
import Auth.OtpVerify.repository.OtpRepository;
import Auth.OtpVerify.utils.AppUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
@AllArgsConstructor
@Slf4j
public class OtpService {
    private final OtpRepository otpRepository;
    private final EmailService emailService;

    public Response sendOtp(OtpRequest otpRequest){
        Otp existingOtp = otpRepository.findByEmail(otpRequest.getEmail());
        if(existingOtp!=null){
            otpRepository.delete(existingOtp);
        }
        String otp = AppUtils.generateOtp();
        otpRepository.save(Otp.builder()
                        .otp(otp)
                        .email(otpRequest.getEmail())
                .expiresAt(LocalDateTime.now().plusMinutes(2))
                .build());

        emailService.sendEmail(EmailDetails.builder()
                .subject("DO NOT DISCLOSE!!!")
                .recipient(otpRequest.getEmail())
                .messageBody("This organization has sent you an otp. this otp expires in 2 minutes. " + otp)
                .build());

        return Response.builder()
                .statusCode(200)
                .responseMessage("SUCCESS").build();
    }

    public Response validateOtp(OtpValidationRequest otpValidationRequest){
        Otp otp = otpRepository.findByEmail(otpValidationRequest.getEmail());
        if(otp==null){
            return Response.builder()
                    .statusCode(400)
                    .responseMessage("You have not sent an otp")
                    .build();
        }
        if(otp.getExpiresAt().isBefore(LocalDateTime.now())){
            return Response.builder()
                    .statusCode(400)
                    .responseMessage("Expired Otp")
                    .build();
        }

        if(!otp.getOtp().equals(otpValidationRequest.getOtp())){
            return Response.builder()
                    .statusCode(400)
                    .responseMessage("Invalid otp")
                    .build();
        }

        return Response.builder()
                .statusCode(200)
                .responseMessage("SUCCESS")
                .otpResponse(OtpResponse.builder()
                        .isOtpValid(true)
                        .build())
                .build();
    }
}

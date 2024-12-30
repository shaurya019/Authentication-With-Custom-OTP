package Auth.OtpVerify.repository;

import Auth.OtpVerify.entity.Otp;
import Auth.OtpVerify.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp,Long> {
    Otp findByEmail(String email);
}

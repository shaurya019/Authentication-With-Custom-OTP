package Auth.OtpVerify.utils;

import java.util.Random;

public class AppUtils {

    public static String generateOtp(){
        StringBuilder otp = new StringBuilder();
        Random random = new Random();
        int c = 0;
        while(c<4){
            otp.append(random.nextInt(10));
            c++;
        }
        return otp.toString();
    }

    public static void main(String[] args){
        System.out.println(generateOtp());
    }
}

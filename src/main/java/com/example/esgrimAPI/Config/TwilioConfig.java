package com.example.esgrimAPI.Config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import com.twilio.Twilio;


@Configuration
@Getter
@Setter
public class TwilioConfig {

    @Value("${twilio.account.sid}")
    private String sid;

    @Value("${twilio.auth.token}")
    private String auth;

    @Value("${twilio.phone.number}")
    private String NumeroEnvio;

    public void twilioInitializer() {
        Twilio.init(sid, auth);
    }

}

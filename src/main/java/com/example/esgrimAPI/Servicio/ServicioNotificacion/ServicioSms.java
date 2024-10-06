package com.example.esgrimAPI.Servicio.ServicioNotificacion;
import com.example.esgrimAPI.Excepciones.SmsException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.esgrimAPI.Config.TwilioConfig;

@Service
public class ServicioSms {

    @Autowired
    private TwilioConfig twilioConfig;

    public void sendSms(String to, String body) throws SmsException {

        try {
            Twilio.init(twilioConfig.getSid(), twilioConfig.getAuth());
            Message message = Message.creator(new PhoneNumber(to), new PhoneNumber(twilioConfig.getNumeroEnvio()), body).create();
            System.out.println("Mensaje enviado con SID: " + message.getSid());
        } catch (Exception e) {
            throw new SmsException("Error al enviar el SMS");
        }
    }
}

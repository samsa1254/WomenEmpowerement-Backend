package tn.esprit.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import tn.esprit.spring.entities.SMS;
@Service
public class SmsService {
	
	private final String ACCOUNT_SID ="AC8ccf272a858a32fd75bc3f96f41a139a";

    private final String AUTH_TOKEN = "dab136fc97c0edc12a83d7fe4918f86e";

    private final String FROM_NUMBER = "+17319373302";

    public void send(SMS sms) {
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                .create();
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }

}

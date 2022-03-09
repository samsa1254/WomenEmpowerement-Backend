package tn.esprit.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import tn.esprit.spring.entities.SMS;
@Service
public class SmsService {
	
	private final String ACCOUNT_SID ="AC63730500d43eff29fe76589260761cd2";

    private final String AUTH_TOKEN = "6d111b09ad24f43369da0ec4ec3b9572";

    private final String FROM_NUMBER = "+12058815436";

    public void send(SMS sms) {
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(FROM_NUMBER), sms.getMessage())
                .create();
        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

    }

    public void receive(MultiValueMap<String, String> smscallback) {
    }

}

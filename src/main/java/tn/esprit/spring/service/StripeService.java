package tn.esprit.spring.service;

import com.stripe.Stripe;

import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.Cagnotte;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.CagnotteRepository;
import tn.esprit.spring.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StripeService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CagnotteRepository cagnotteRepository;


    @Value("${stripe.keys.secret}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }


    public String createStripeCustomer(long idUser) {

        // stripe key
        Stripe.apiKey = "sk_test_51KYTIPC1o50pbIHtWbBDZlBvBn1oGV49cvOK0xGFbekZqHc2bEJCQVULPylWWzKR6EtqRapH1gYJ36xe9Eta7KZh0030QRWcqN";

        User user = userRepository.findById((int) idUser).get();
        Map<String, Object> params = new HashMap<>();
        params.put("description",user.getName()+" "+user.getLastname());
        params.put("email", user.getEmail());

        // affichage id du customer
        try {
            Customer customer = Customer.create(params);

            System.out.println("create customer id: {}");
            return customer.getId();
        } catch (StripeException e) {

            throw new RuntimeException(e);
        }
        // TODO Auto-generated method stub
//		return null;
    }


    public String createCustumorStripe(String customerId, String carta, String expMonth, String expYear, String cvc)
            throws StripeException {
        // TODO Auto-generated method stub
        //return null;
        // stripe key
        Stripe.apiKey = "sk_test_51KYTIPC1o50pbIHtWbBDZlBvBn1oGV49cvOK0xGFbekZqHc2bEJCQVULPylWWzKR6EtqRapH1gYJ36xe9Eta7KZh0030QRWcqN";

        Customer customer = Customer.retrieve(customerId);

        Map<String, Object> cardParam = new HashMap<String, Object>();
        cardParam.put("number", carta);
        cardParam.put("exp_month", expMonth);
        cardParam.put("exp_year", expYear);
        cardParam.put("cvc", cvc);

        Map<String, Object> tokenParam = new HashMap<String, Object>();
        tokenParam.put("card", cardParam);

        Token token = Token.create(tokenParam);

        Map<String, Object> source = new HashMap<String, Object>();
        source.put("source", token.getId());

        customer.getSources().create(source);
        return token.getId();
    }


    public String paymentIntent(Charge chargerequest)throws StripeException{
        // TODO Auto-generated method stub
        //return null;
        // stripe key
        Stripe.apiKey = "sk_test_51KYTIPC1o50pbIHtWbBDZlBvBn1oGV49cvOK0xGFbekZqHc2bEJCQVULPylWWzKR6EtqRapH1gYJ36xe9Eta7KZh0030QRWcqN";

        // `source` is obtained with Stripe.js; see
        // https://stripe.com/docs/payments/accept-a-payment-charges#web-create-token
        List<String> paymentMethodTypes = new ArrayList();
        paymentMethodTypes.add("card");


        Map<String, Object> params = new HashMap<>();
        params.put("amount",chargerequest.getAmount());
        params.put("currency", chargerequest.getCurrency());
        params.put("description", chargerequest.getDescription());
        params.put("payment_method_types", paymentMethodTypes);

        PaymentIntent p = PaymentIntent.create(params);
        p.getId();
        //Charge charge = Charge.create(params);
        return p.getId();
    }



    /*
     * this methode is to confirm that your customer intends to pay with current
     * or provided payment method. Upon confirmation, the PaymentIntent will
     * attempt to initiate a payment
     */
    public PaymentIntent confirm(String id) throws StripeException {
        Stripe.apiKey = "sk_test_51KYTIPC1o50pbIHtWbBDZlBvBn1oGV49cvOK0xGFbekZqHc2bEJCQVULPylWWzKR6EtqRapH1gYJ36xe9Eta7KZh0030QRWcqN";
        PaymentIntent paymentIntent = PaymentIntent.retrieve(id);
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method", "pm_card_visa");
        // params.put("customer", "cus_H1OvsnwEn1KX36");
        paymentIntent.confirm(params);
        return paymentIntent;
    }
    //	public void payment_all(long idUser, String carta, String expMonth, String expYear, String cvc){
//		String a=createStripeCustomer(idUser);
//		String b=createCustumorStripe(a,carta,expMonth,expMonth,expYear,cvc);
//
//
//	}
    @Transactional
    public String Pay(int idCagnotte,String carta, int expMonth, int expYear, String cvc) throws StripeException{
        Cagnotte cagnotte = cagnotteRepository.getById((long) idCagnotte);


            Map<String, Object> params = new HashMap<>();
            Map<String, Object> tokenParams = new HashMap<>();
            Map<String, Object> cardParams = new HashMap<>();
            Stripe.apiKey = "sk_test_51KYTIPC1o50pbIHtWbBDZlBvBn1oGV49cvOK0xGFbekZqHc2bEJCQVULPylWWzKR6EtqRapH1gYJ36xe9Eta7KZh0030QRWcqN";
            cardParams.put("number", carta);
            cardParams.put("exp_month", expMonth);
            cardParams.put("exp_year", expYear);
            cardParams.put("cvc", cvc);
            int nMontant= (int) cagnotte.getAmount();
            tokenParams.put("card", cardParams);
            Token token =Token.create(tokenParams);
            if (token.getId()!=null){
                params.put("amount", nMontant);
                params.put("description", "payment cagnotte");
                params.put("currency", "eur");
                params.put("source", token.getId());
                Charge charge = Charge.create(params);
            }


        return "payment succeed";

    }




}

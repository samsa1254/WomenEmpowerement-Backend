package tn.esprit.spring.controllers;

import java.util.List;

import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.Cagnotte;

import tn.esprit.spring.service.CagnotteService;
import tn.esprit.spring.service.StripeService;

@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = "Cagnotte Management")
@RestController
@RequestMapping("/Cagnotte")

public class CagnotteRestController {

	@Autowired
	private CagnotteService cagnotteService;

	@Autowired
	private StripeService stripeService;

	
	
	@PostMapping("/addCagnotte")
    public ResponseEntity<Cagnotte> addCagnotte(@RequestBody Cagnotte cagnotte) {
		cagnotteService.addCagnotte(cagnotte);
		
		
		return ResponseEntity.ok().body(cagnotte);
    }
	
	
	@GetMapping("/getAllCagnotte")
    public ResponseEntity<List<Cagnotte>> getAllCagnotte() {
		List<Cagnotte> cag= cagnotteService.getAllCagnotte();
		return ResponseEntity.ok().body(cag);
		
    }
	
	 @GetMapping("/getCagnotteById/{id}")
	    public ResponseEntity<Cagnotte> getCagnotteById(@PathVariable("id") Long id) {
		 return ResponseEntity.status(HttpStatus.OK).body(cagnotteService.getCagnotteById(id));
	    }
	 
	 @DeleteMapping("/deleteCagnotte/{id}")
	    public  ResponseEntity<Void> deleteCagnotte(@PathVariable("id") Long id) {
		 	cagnotteService.deleteCagnotte(id);
		 	 return new ResponseEntity<>(HttpStatus.OK);
	    }

	    /*------------------------------Payment API--------------------------*/

	//http://localhost:8089/SpringMVC/Cagnotte/CreatecustomerStripe/
	@PostMapping("/CreatecustomerStripe/{iduser}")
	@ResponseBody
	public String createCustomer(@PathVariable("iduser") int iduser) {
		return stripeService.createStripeCustomer(iduser);
	}

	// http://localhost:8089/SpringMVC/Cagnotte/customer/customer_id_from_stripeApi_acount/4242424242424242/11/2026/123
	@PostMapping("/customerCard/{customerId}/{carta}/{expMonth}/{expYear}/{cvc}")
	@ResponseBody
	public String createCustumorStripe(@PathVariable("customerId") String customerId, @PathVariable("carta") String carta,
								 @PathVariable("expMonth") String expMonth, @PathVariable("expYear") String expYear,
								 @PathVariable("cvc") String cvc) throws StripeException {
		return stripeService.createCustumorStripe(customerId, carta, expMonth, expYear, cvc);
	}

	// http://localhost:8089/SpringMVC/Cagnotte/paymentintent
	/*
	 * { "description":"test la methode payment", "amount":"10000",
	 * "currency":"EUR" }
	 */
	@PostMapping("/paymentintent")
	public String payment(@RequestBody Charge chargeRequest) throws StripeException {
		return stripeService.paymentIntent(chargeRequest);

	}

	// http://localhost:8089/SpringMVC/Cagnotte/confirm/{id}
	@PostMapping("/confirm/{id}")
	public ResponseEntity<String> confirm(@PathVariable("id") String id) throws StripeException {
		PaymentIntent paymentIntent = stripeService.confirm(id);
		String paymentStr = paymentIntent.toJson();
		return new ResponseEntity<>(paymentStr, HttpStatus.OK);
	}

	//////              1/4242424242424242/11/2026/123
	@PostMapping("/pay/{idc}/{carta}/{expMonth}/{expYear}/{cvc}/{userAmount}")
	public void Pay(@PathVariable("idc") int idc, @PathVariable("carta") String carta,
					@PathVariable("expMonth") int expMonth, @PathVariable("expYear") int expYear,
					@PathVariable("cvc") String cvc,@PathVariable("userAmount") float userAmount) throws StripeException{
		stripeService.Pay(idc,carta,expMonth,expYear,cvc,userAmount);
	}

		/*--------------------------------------------------------*/
}

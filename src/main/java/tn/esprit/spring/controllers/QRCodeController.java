package tn.esprit.spring.controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import com.google.zxing.WriterException;


import tn.esprit.spring.entities.Cagnotte;
import tn.esprit.spring.entities.Candidacy;
import tn.esprit.spring.entities.Event;
import tn.esprit.spring.entities.QRCodeGenerator;
import tn.esprit.spring.service.CagnotteService;
import tn.esprit.spring.service.CandidacyService;
import tn.esprit.spring.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;


import java.util.Base64;
@CrossOrigin
@RestController
public class QRCodeController {

	@Autowired
	private EventService eventService;
	@Autowired
	private CandidacyService CS;
	
	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode.png";

	@GetMapping(value = "/genrateQRCode/{id}")
	public String getQRCode(Model model, @PathVariable("id") Long id) {
		Event ev = eventService.getEventById(id);
		
		String medium = "title :" + ev.getTitle() + "type:" +ev.getType()+ "dateDebut:"+ev.getDateDebut();
		String github = "title :" + ev.getTitle() + "type:" +ev.getType()+ "dateDebut:"+ev.getDateDebut();
		byte[] image = new byte[0];
		try {

			// Generate and Return Qr Code in Byte Array
			image = QRCodeGenerator.getQRCodeImage(medium, 250, 250);

			// Generate and Save Qr Code Image in static/image folder
			QRCodeGenerator.generateQRCodeImage(medium, 250, 250, QR_CODE_IMAGE_PATH);

		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
		// Convert Byte Array into Base64 Encode String
		String qrcode = Base64.getEncoder().encodeToString(image);

		model.addAttribute("medium", medium);
		model.addAttribute("github", github);
		model.addAttribute("qrcode", qrcode);

		return "medium";
	}
	
	@GetMapping(value = "/genrateQRCandid/{idc}")
	public String getQRCodeCand(Model model, @PathVariable("idc") Long id) {
		
		Candidacy ev = CS.retrievebyID(id);
		
		String medium = "Name : "+ev.getCandidName()+ "\n unit of Identification :" +ev.getId()+ "\n Location :"+ev.getOffer().getLocation()+"\n Interview at :"+ev.getDate_Of_Candid();
		String github = "Name : "+ev.getCandidName()+ "\n unit of Identification :" +ev.getId()+ "\n Location :"+ev.getOffer().getLocation()+"\n Interview at :"+ev.getDate_Of_Candid();
		byte[] image = new byte[0];
		try {

			// Generate and Return Qr Code in Byte Array
			image = QRCodeGenerator.getQRCodeImage(medium, 250, 250);

			// Generate and Save Qr Code Image in static/image folder
			QRCodeGenerator.generateQRCodeImage(medium, 250, 250, QR_CODE_IMAGE_PATH);

		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}
		// Convert Byte Array into Base64 Encode String
		String qrcode = Base64.getEncoder().encodeToString(image);

		model.addAttribute("medium", medium);
		model.addAttribute("github", github);
		model.addAttribute("qrcode", qrcode);

		return "medium";
	}
}
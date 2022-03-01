package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.Cagnotte;

import tn.esprit.spring.service.CagnotteService;

@Api(tags = "Cagnotte Management")

@RestController
@RequestMapping("/Cagnotte")

public class CagnotteRestController {

	@Autowired
	private CagnotteService cagnotteService;

	@PutMapping("/addCagnotte")
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
}

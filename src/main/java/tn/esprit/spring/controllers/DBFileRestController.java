package tn.esprit.spring.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import tn.esprit.spring.entities.DBFile;
import tn.esprit.spring.service.DBFileService;
@CrossOrigin
@Api(tags = "file Management")
@RestController
@RequestMapping("File")
public class DBFileRestController {
	@Autowired
	private DBFileService DBFileSer ;

	
	@PostMapping
	public List<DBFile> uploadFile ( @RequestParam("file") List<MultipartFile> files ) throws IOException
	{
		return DBFileSer.store(files);
	}

	
	
	@PostMapping("/{idPublication}")
	@ResponseBody
	public List<DBFile> uploadFileandaffect ( @RequestParam("file") List<MultipartFile> files, @PathVariable("idPublication") Long idPublication) throws IOException
	{
		return DBFileSer.storeandaffect(files, idPublication);
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public DBFile getFile ( @PathVariable Long id )
	{
		return DBFileSer.getFileById(id);
	}
	
	@GetMapping("/list")
	@ResponseBody
	public List<DBFile> getFileList()
	{
		return DBFileSer.getFileList();
	}
	
	
	@PostMapping("/Event/{idEvent}")
	@ResponseBody
	public DBFile uploadFileandaffectEevnt ( @RequestParam("file") MultipartFile file, @PathVariable("idEvent") Long idEvent1) throws IOException
	{
		return DBFileSer.storeandaffectEvent(file , idEvent1);
	}
	
}

package tn.esprit.spring.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.DBFile;
import tn.esprit.spring.entities.Event;
import tn.esprit.spring.repository.DBFileRepository;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.PublicationRepository;

@Service
public class DBFileServiceImpl implements DBFileService {
	
	@Autowired
	private DBFileRepository DBFileRep ;
	@Autowired
	private PublicationRepository PubRep ;
	@Autowired
	private EventRepository EventRep;
	

	

	@Override
	public List<DBFile> getFileList() {
		List<DBFile> dbFiles = (List<DBFile>) DBFileRep.findAll();
		return dbFiles;
	}

	@Override
	public List<DBFile> store(List<MultipartFile> files) throws IOException {
		List<DBFile> dbFiles = new ArrayList<DBFile>();
		for (MultipartFile multipartFile : files) {
			String fileName = multipartFile.getOriginalFilename();
			DBFile dbFile = new DBFile(UUID.randomUUID().getLeastSignificantBits(), fileName, multipartFile.getContentType(), multipartFile.getBytes());
			
		dbFiles.add(dbFile);
		}
		
		DBFileRep.saveAll(dbFiles);
		return dbFiles ;
	}

	@Override
	public List<DBFile> storeandaffect(List<MultipartFile> files, Long idPublication) throws IOException {
		List<DBFile> dbFiles = new ArrayList<DBFile>();
		for (MultipartFile multipartFile : files) {
			String fileName = multipartFile.getOriginalFilename();
			DBFile dbFile = new DBFile(UUID.randomUUID().getLeastSignificantBits(), fileName, multipartFile.getContentType(), multipartFile.getBytes());
			dbFile.setPublication(PubRep.findById(idPublication).get());
	        dbFiles.add(dbFile);
		}
		
		DBFileRep.saveAll(dbFiles);
		return dbFiles ;
		
	}

	@Override
	public DBFile getFileById(Long id) {
	DBFile d =	DBFileRep.findById(id).get();
		return d;
	}

	@Override
	public DBFile storeandaffectEvent ( MultipartFile file , Long idEvent1 ) throws IOException{
		String fileName = file.getOriginalFilename();
		DBFile dbFile = new DBFile(UUID.randomUUID().getLeastSignificantBits(), fileName, file.getContentType(), file.getBytes());
		DBFileRep.save(dbFile);

		
    	Event event = EventRep.findById(idEvent1).get();
		System.out.println(event);
		event.setPicture(dbFile);
		EventRep.save(event);
            
			

		return  dbFile;
		
	}


	

}

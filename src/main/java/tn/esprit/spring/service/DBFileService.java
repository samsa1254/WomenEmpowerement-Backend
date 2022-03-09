package tn.esprit.spring.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entities.DBFile;

public interface DBFileService {

	public List<DBFile> storeandaffect ( List<MultipartFile> files , Long idPublication ) throws IOException;
	public List<DBFile> store (List<MultipartFile> files ) throws IOException ;
	public DBFile getFileById( Long id ) ;
	public List<DBFile> getFileList();
	public DBFile storeandaffectEvent ( MultipartFile file , Long idEvent ) throws IOException;

}

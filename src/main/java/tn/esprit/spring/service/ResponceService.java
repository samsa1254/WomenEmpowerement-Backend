package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Response;
import tn.esprit.spring.entities.Training;
import tn.esprit.spring.repository.ResponceRepository;


@Service
public class ResponceService implements IResponceService {

	@Autowired
	ResponceRepository repo;
	
	@Override
	public Response add(Response b) {
		// TODO Auto-generated method stub
		return repo.save(b);
	}

	@Override
	public Response update(Response b) {
		// TODO Auto-generated method stub
		return repo.save(b);
	}

	@Override
	public List<Response> list() {
		// TODO Auto-generated method stub
		return (List<Response>) repo.findAll();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		
	}

	@Override
	public Response retrieve(Long id) {
		// TODO Auto-generated method stub
		return  repo.findById(id).get();
	}

	
}

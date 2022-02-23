package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.Response;


public interface IResponceService {
	public Response add (Response b);
	public Response update (Response b);
	public List<Response> list();
	void delete(Long id);
	Response retrieve(Long id);

}

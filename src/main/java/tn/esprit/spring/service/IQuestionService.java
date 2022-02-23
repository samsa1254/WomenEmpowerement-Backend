package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.Question;



public interface IQuestionService {
	public Question add (Question b);
	public Question update (Question b);
	public List<Question> list();
	void delete(Long id);
	Question retrieve(Long id);

}

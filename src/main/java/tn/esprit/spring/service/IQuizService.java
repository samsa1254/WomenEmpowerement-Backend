package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entities.Quiz;


public interface IQuizService {
	public Quiz add (Quiz b);
	public Quiz update (Quiz b);
	public List<Quiz> list();
	void delete(Long id);
	Quiz retrieve(Long id);

}

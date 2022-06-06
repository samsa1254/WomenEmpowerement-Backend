package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Appointment;
import tn.esprit.spring.entities.Disponibilite;
import tn.esprit.spring.entities.Report;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.AppointmentRepository;
import tn.esprit.spring.repository.DisponibiliteRepository;
import tn.esprit.spring.repository.ReportRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ReportRepository reportRep ; 
	@Autowired
	private UserRepository uRep ;
	@Autowired
	private AppointmentRepository aRep ;
	@Autowired
	private DisponibiliteRepository disRep ;
	@Autowired
	private IUserService uSer ;
	@Override
	public Report addReport(Report rep, int iduser) {
		User u=uRep.findById(iduser).get();
		rep.setUser(u);
		reportRep.save(rep);
		return rep;
	}

	@Override
	public Report updatereport(Report rep) {
		
		reportRep.save(rep);
		return rep;
	}

	@Override
	public void deleterepById(Long id) {
		reportRep.deleteById(id);
		
	}

	@Override
	public Report retrievebyId(Long id) {
		Report p = reportRep.findById(id).get();
		return p ;
		}

	@Override
	public List<Report> retrieveallreps() {
		List<Report> reps = (List<Report>) reportRep.findAll();
		return reps;
	}
	
	@Override
	public Appointment treataReportbyMakingappointment(Long id , int iduser2,int user3,Appointment a) {
	 boolean valide=false;
	 Report r= reportRep.findById(id).get();
	 User u1=uRep.findById(r.getUser().getIduser()).get();
	 User u2=uRep.findById(iduser2).get();
	 User u3=uRep.findById(user3).get();
     
	 
	 
	 List<Disponibilite> disps=u2.getDisponibilite();
	  for(Disponibilite dis:disps) {
	    	if (a.getDateAppointment().after(dis.getDatedebut()) && a.getDateAppointment().before(dis.getDatefin())&& dis.getEtat()!="booked"|| a.getDateAppointment().equals(dis.getDatedebut()) && dis.getEtat()!="booked" ||a.getDateAppointment().equals(dis.getDatefin()) && dis.getEtat()!="booked"   )
	    	{    
               valide= true ; 
               
	    	}
	    }
	  
	  if ((valide==true) && r.getStatus()!="treated") {
		  a.setUser(u1);
		  a.setUserexpert(u2);
		  a.setUsera(u3);
		  u1.getAppointmentsu().add(a);
		  u2.getAppointmentse().add(a);
		  u3.getAppointmentsa().add(a);
		  
		  aRep.save(a);
		  	
		     r.setStatus("treated");
		     reportRep.save(r);

		  
	  }
	  for(Disponibilite dis:disps) {
	    	if (a.getDateAppointment().after(dis.getDatedebut()) && a.getDateAppointment().before(dis.getDatefin())|| a.getDateAppointment().equals(dis.getDatedebut()) || a.getDateAppointment().equals(dis.getDatefin()))
	       {
	    		
	    		dis.setEtat("booked");
	    		disRep.save(dis);
	    	} 
	    	
	    }
	  
     
     return a;
	}

	@Override
	public void treataReportbyblockinguser(Report r, int iduser) {
      User u=uRep.findById(iduser).get();
      uSer.blockuseraccount(u);
      reportRep.save(r);
      }

	@Override
	public void treataReportbyunblockinguser(Report r, int iduser) {
		User u=uRep.findById(iduser).get();
	      uSer.unblockuseraccount(u);
	      reportRep.save(r);
	}

	@Override
	public List<Report> getuserreports(int iduser) {
		User u=uRep.findById(iduser).get();
		List<Report> r=u.getReports();
		return r;
	}

}
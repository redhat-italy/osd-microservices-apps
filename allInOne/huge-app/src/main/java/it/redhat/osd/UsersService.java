package it.redhat.osd;

import java.util.logging.Logger;

import it.redhat.osd.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class UsersService implements Serializable {

	private static Logger log = Logger.getLogger(UsersService.class.getName());

	private Hashtable<String,User> userRepo;
	
	public UsersService() {
		
		populateRepo();
	}

	
	
	public List<User> list() {
		log.info("Listing all users....");
		return new ArrayList<User>(userRepo.values());
	}

	public User retrieveById(String id) {
		log.info("Getting User by id "+id);
		try{
			User toRet = userRepo.get(id);
			log.info("Returning User: "+toRet);
			return toRet;
		}catch (NumberFormatException e) {
			log.warning(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public void deleteById(String id) {
		log.info("Deleting User by id "+id);
		userRepo.remove(id);
	}
	
  public void save(String id, User toSave) throws Exception {
			
		
		if(!id.equalsIgnoreCase(toSave.getId()))
		{
				log.warning("Object id does not match: "+id+" - "+toSave);
				toSave.setId(id);
		}
		
		save(toSave);
	}



	public void save(User toSave) {
		
		log.info("Saving object: "+toSave);
		
		userRepo.put(toSave.getId(),toSave);
		
	}
	
	private void populateRepo() {
		
		if(userRepo == null)
		{
			userRepo=new Hashtable<String,User>();
			userRepo.put("1",new User("1", "Ugo", "Landini","ulandini@redhat.com"));
			userRepo.put("2",new User("2", "Samuele", "Dell'Angelo","sdellang@redhat.com"));
			userRepo.put("3",new User("3", "Andrea", "Leoncini","aleoncin@redhat.com"));
			userRepo.put("4",new User("4", "Giuseppe", "Bonocore","gbonocor@redhat.com"));
			userRepo.put("5",new User("5", "Filippo", "Cala","fcala@redhat.com"));
			userRepo.put("6",new User("6", "Luca", "Bigotta","lbigotta@redhat.com"));
			
		}
	}
	
}

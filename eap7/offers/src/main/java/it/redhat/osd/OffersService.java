package it.redhat.osd;

import java.util.logging.Logger;

import it.redhat.osd.model.Offer;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;


public class OffersService {

	private static Logger log = Logger.getLogger(OffersService.class.getName());

	private static Hashtable<String,Offer> offerRepo;
	
	public OffersService() {
		
		populateRepo();
	}

	
	
	public List<Offer> list() {
		log.info("Listing all offers....");
		return new ArrayList<Offer>(offerRepo.values());
	}

	public Offer retrieveById(String id) {
		log.info("Getting offer by id "+id);
		try{
			Offer toRet = offerRepo.get(id);
			log.info("Returning offer: "+toRet);
			return toRet;
		}catch (NumberFormatException e) {
			log.warning(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public void deleteById(String id) {
		log.info("Deleting offer by id "+id);
		offerRepo.remove(id);
	}
	
  public void save(String id, Offer toSave) throws Exception {
			
		
		if(!id.equalsIgnoreCase(toSave.getId()))
		{
				log.warning("Object id does not match: "+id+" - "+toSave);
				toSave.setId(id);
		}
		
		save(toSave);
	}



	public void save(Offer toSave) {
		
		log.info("Saving object: "+toSave);
		
		offerRepo.put(toSave.getId(),toSave);
		
	}
	
	private static void populateRepo() {
		
		if(offerRepo == null)
		{
			String[] promoBuzzwords = {"Promo","Special","New","Summer","Christmas","Holyday","Black Friday","Giveaway","2016","3x2","For You"};
			int[] discounts = {10,15,20,15,30,50,60,75};
			offerRepo = new Hashtable<String,Offer>();
			Random rnd= new Random();
			for (int i = 0; i < 10; i++) {
				    
					StringBuilder description= new StringBuilder();
					for(int j=0; j< (rnd.nextInt(3)+1) ; j++)
					{
						description.append(promoBuzzwords[rnd.nextInt(promoBuzzwords.length)]).append(" ");
					}
					int discount = discounts[rnd.nextInt(discounts.length)];
					offerRepo.put(""+i,new Offer(""+i, description.toString(), discount));
			}
		}
	}
	
}

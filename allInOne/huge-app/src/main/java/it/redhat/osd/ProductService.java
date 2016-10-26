package it.redhat.osd;

import java.util.logging.Logger;

import it.redhat.osd.model.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class ProductService implements Serializable {

	private static Logger log = Logger.getLogger(ProductService.class.getName());

	private Hashtable<String,Product> productRepo;
	
	public ProductService() {
		
		populateRepo();
	}

	
	
	public List<Product> list() {
		log.info("Listing all Products....");
		return new ArrayList<Product>(productRepo.values());
	}

	public Product retrieveById(String id) {
		log.info("Getting Product by id "+id);
		try{
			Product toRet = productRepo.get(id);
			log.info("Returning Product: "+toRet);
			return toRet;
		}catch (NumberFormatException e) {
			log.warning(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public void deleteById(String id) {
		log.info("Deleting Product by id "+id);
		productRepo.remove(id);
	}
	
  public void save(String id, Product toSave) throws Exception {
			
		
		if(!id.equalsIgnoreCase(toSave.getId()))
		{
				log.warning("Object id does not match: "+id+" - "+toSave);
				toSave.setId(id);
		}
		
		save(toSave);
	}



	public void save(Product toSave) {
		
		log.info("Saving object: "+toSave);
		
		productRepo.put(toSave.getId(),toSave);
		
	}
	
	private void populateRepo() {
		
		if(productRepo == null)
		{
			productRepo=new Hashtable<String,Product>();
			productRepo.put("1",new Product("1","Description 1", 100));
			productRepo.put("2",new Product("2","Description 2", 200));
			productRepo.put("3",new Product("3","Description 3", 300));
		
			
		}
	}
	
}

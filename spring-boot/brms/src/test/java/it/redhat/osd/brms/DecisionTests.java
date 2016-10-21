package it.redhat.osd.brms;

import it.redhat.osd.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DecisionApp.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DecisionTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void getInfo() {
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity("http://localhost:8080/brms/ds/info", Map.class);
		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void noDiscount() {
		Order order = new Order().setCustomerId("leo").setProductId("ASDEE").setQuantity(1);
		Order calculatedOrder = this.testRestTemplate.postForObject("http://localhost:8080/brms/ds/order", order, Order.class);
		then(calculatedOrder.discount == 0);
	}

	@Test
	public void withDiscount() {
		Order order = new Order().setCustomerId("leo").setProductId("HGTTG").setQuantity(3);
		Order calculatedOrder = this.testRestTemplate.postForObject("http://localhost:8080/brms/ds/order", order, Order.class);
		then(calculatedOrder.discount == 5);
	}


}

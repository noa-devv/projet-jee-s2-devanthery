package ch.hearc.jee2024.projetjees2devanthery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc //configuration automatique du mock
class HelloSpringApplicationTests {

	@Autowired
	private MockMvc mvc; //Objet permettant de "simuler" un serveur web

	@Test
	public void callHelloShouldReturnHelloworld() throws Exception {

		this.mvc.perform(get("/hello"))  //GET /hello
				.andExpect(status().isOk())         //Status code = 200
				.andExpect(content().string("Hello World!")); //Body = "Hello World!
	}

	@Test
	public void newControllerExist() throws Exception {
		String controlleurExpectedFqdn = "ch.hearc.jee2024.projetjees2devanthery.MessagingController";

		try{
			Class controlleur = Class.forName(controlleurExpectedFqdn);
			boolean isAnnotation = controlleur.isAnnotationPresent(RestController.class);
			assertTrue(isAnnotation,String.format("Controlleur %s is not a RestController",controlleurExpectedFqdn));

		}catch(ClassNotFoundException ex){
			fail(String.format("Controlleur %s doesn't exist",controlleurExpectedFqdn));
		}
	}

	@Test
	public void callSendMailMustSendAnEmail() throws Exception {

		//Dans un premier temps on va tester le message du retour du service de mail
		this.mvc.perform(get("/send/mail"))
				.andExpect(status().isOk())
				.andExpect(content().string("Mail message successfully send!"));
	}

	@Test
	public void callSendSmsMustSendASms() throws Exception {

		//Dans un premier temps on va tester le message du retour du service de mail
		this.mvc.perform(get("/send/sms"))
				.andExpect(status().isOk())
				.andExpect(content().string("SMS message successfully send!"));
	}

	@Test
	public void callSendWithParamMusstSendWithChoosenType() throws Exception {

		//Dans un premier temps on va tester le message du retour du service de mail
		this.mvc.perform(get("/send")
						.param("type","sms"))
				.andExpect(status().isOk())
				.andExpect(content().string("SMS message successfully send!"));

		this.mvc.perform(get("/send")
						.param("type","mail"))
				.andExpect(status().isOk())
				.andExpect(content().string("Mail message successfully send!"));
	}

	@Test
	public void callSendWithHeaderMusstSendWithChoosenType() throws Exception {
		//Dans un premier temps on va tester le message du retour du service de mail
		this.mvc.perform(get("/send")
						.header("type","sms"))
				.andExpect(status().isOk())
				.andExpect(content().string("SMS message successfully send!"));

		this.mvc.perform(get("/send")
						.header("type","mail"))
				.andExpect(status().isOk())
				.andExpect(content().string("Mail message successfully send!"));
	}
}

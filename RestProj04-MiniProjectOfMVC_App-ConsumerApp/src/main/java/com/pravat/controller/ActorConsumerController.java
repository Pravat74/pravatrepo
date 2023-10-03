package com.pravat.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pravat.model.Actor;

@Controller
public class ActorConsumerController {

	@Autowired
	private RestTemplate template;
	@Autowired
	private Environment env;

	@GetMapping("/")
	public String showHome() {
		// return LVN
		return "home";
	}

	@GetMapping("/actor_report")
	public String fetchAllActors(Map<String, Object> map) throws Exception {
		/*
		 * provider url::http://localhost:7373/RestProj04-MiniProjectOfRest_App-ProviderApp/actor/api/report
		 * req mode:: GET
		 * path variables:: no
		 * response content type :: application/json(default)
		 * request headers :: no
		 * request body type :: no
		 */
		String serviceUrl = env.getProperty("fetchAllActors.serviceUrl");
		// invoke Provider -restcontroller operation /method using exchange(...) of
		// resttemplate
		ResponseEntity<String> response = template.exchange(serviceUrl, HttpMethod.GET, null, String.class);
		// get json rsponse from response obj
		String jsonBody = response.getBody();
		// convert json body into List<Actor> obj
		ObjectMapper mapper = new ObjectMapper();
		List<Actor> list = mapper.readValue(jsonBody, new TypeReference<List<Actor>>() {
		});

		// add results to map object
		map.put("actorsInfo", list);
		// return LVN
		return "show_report";

	}

	@GetMapping("/actor_add")
	public String showRegisterActorFormPage(@ModelAttribute("actor") Actor actor) {
		// return LVN
		return "register_actor";
	}

	@PostMapping("/actor_add")
	public String registerActor(@ModelAttribute("actor") Actor actor, RedirectAttributes attrs) throws Exception {

		// convert model class obj data to json content using ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString(actor); // Serialization

		System.out.println("register actor ::" + jsonContent);
		// invoke provider Apps operations to save actor object
		/*
		 * provider url::http://localhost:7373/RestProj04-MiniProjectOfRest_App-ProviderApp/actor/api/save
		 * req mode:: POST
		 * path variables:: no
		 * response content type :: text/plain
		 * request headers :: contentType :application/json(default)
		 * request body type :: json content
		 */
		String serviceUrl = env.getProperty("registerActor.serviceUrl");
		// prepare Http headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// prepare HttpEntity object having headers +body
		HttpEntity<String> entity = new HttpEntity<String>(jsonContent, headers);
		ResponseEntity<String> response = template.exchange(serviceUrl, HttpMethod.POST, entity, String.class);

		// get provider operation result
		String msg = response.getBody();

		// keep result in modelAtrribute
		attrs.addFlashAttribute("resultMsg", msg);

		return "redirect:actor_report";
	}

	@GetMapping("/actor_edit")
	public String showUpdateFormPage(@RequestParam("aid") int id, @ModelAttribute("artist") Actor actor)
			throws Exception {

		// invoke provider Apps operations to edit actor object
		/*
		 * provider url::http://localhost:7373/RestProj04-MiniProjectOfRest_App-ProviderApp/actor/api/getactor/{id}
		 * req mode:: GET
		 * path variables:: {id}
		 * response content type :: application/json(default)
		 * request headers :: no
		 * request body type :: default 
		 */
		String serviceUrl = env.getProperty("updateActor.serviceUrl1");
		ResponseEntity<String> response = template.exchange(serviceUrl, HttpMethod.GET, null, String.class,
				Map.of("id", id));
		// convert json response body to Model class obj
		ObjectMapper mapper = new ObjectMapper();
		Actor actor1 = mapper.readValue(response.getBody(), Actor.class);
		// copy actor1 object data to model attribute actor obj
		BeanUtils.copyProperties(actor1, actor);
		// return LVN
		return "update_actor";
	}

	@PostMapping("/actor_edit")
	public String updateActor(@ModelAttribute("actor") Actor actor, RedirectAttributes attrs) throws Exception {

		// convert model class obj data to json content using ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString(actor); // Serialization

		// invoke provider Apps operations to edit actor object
		/*
		 * provider url::http://localhost:7373/RestProj04-MiniProjectOfRest_App-ProviderApp/actor/api/modify
		 * req mode:: PUT
		 * path variables:: no
		 * response content type :: text/plain
		 * request headers :: contentType :application/json(default)
		 * request body type :: json content
		 */
		String serviceUrl = env.getProperty("updateActor.serviceUrl2");
		// prepare Http headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// prepare HttpEntity object having headers +body
		HttpEntity<String> entity = new HttpEntity<String>(jsonContent, headers);
		ResponseEntity<String> response = template.exchange(serviceUrl, HttpMethod.PUT, entity, String.class);

		// get provider operation result
		String msg = response.getBody();

		// keep result in modelAtrribute
		attrs.addFlashAttribute("resultMsg", msg);

		return "redirect:actor_report";
	}

	@GetMapping("/actor_delete")
	public String deleteActor(@RequestParam("aid") int id, RedirectAttributes attrs) throws Exception {

		// invoke provider delete operations to delete actor object
		/*
		 * provider url::http://localhost:7373/RestProj04-MiniProjectOfRest_App-ProviderApp/actor/api/delete/{id}
		 * req mode:: DELETE
		 * path variables:: {id}
		 * response content type :: plain text
		 * request headers :: no
		 * request body type :: no
		 */
		String serviceUrl = env.getProperty("deleteActor.serviceUrl");
		ResponseEntity<String> response = template.exchange(serviceUrl, HttpMethod.DELETE, null, String.class,
				Map.of("id", id));
		// keep the response body(plain text) in flash attributes
		String msg = response.getBody();

		// add result in flashAttribue
		attrs.addFlashAttribute("resultMsg", msg);
		// redirect the request
		return "redirect:actor_report";
	}
}

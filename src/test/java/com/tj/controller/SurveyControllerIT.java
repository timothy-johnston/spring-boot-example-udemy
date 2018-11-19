package com.tj.controller;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.tj.Application;
import com.tj.model.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerIT {
	
	@LocalServerPort
	private int port;
	
	private TestRestTemplate template = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();
	
	@Before
	public void setupJSONAcceptType() {
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}

	//TODO: Refactor
	@Test
	public void retrieveSurveyQuestion() throws Exception {
		
		String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia,options:[India,Russia,United States,China]}";
		
		ResponseEntity<String> response = template.exchange(createUrl("/surveys/Survey1/questions/Question1"), HttpMethod.GET, new HttpEntity<String>("DUMMY_DOESNT_MATTER",
                headers), String.class);
		
		JSONAssert.assertEquals(expected,  response.getBody(), false);
		
	}
	
	//TODO: Refactor
	@Test
	public void retrieveAllSurveyQuestions() throws Exception {
		
		String url = createUrl("/surveys/Survey1/questions");
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		ResponseEntity<List<Question>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>("DUMMY_DOESN'T MATTER", headers), 
				new ParameterizedTypeReference<List<Question>>() {} );
		
		Question sampleQuestion = new Question("Question1", "Largest Country in the World", "Russia", Arrays.asList("India","Russia","United States", "China"));
		
		assertTrue(response.getBody().contains(sampleQuestion));
		
	}
	
	//TODO: Refactor
	@Test
	public void testAddQuestion() {
		String url = createUrl("/surveys/Survey1/questions");
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		Question question = new Question("DOESNTMATTER", "Question1", "russia", Arrays.asList("India","Russia", "United States", "China"));
		
		HttpEntity entity = new HttpEntity<Question>(question, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(url,  HttpMethod.POST, entity, String.class);
		
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		
		assertTrue(actual.contains("/surveys/Survey1/questions/"));
	}
	
	private String createUrl(String uri) {
        return "http://localhost:" + port + uri;
    }

}

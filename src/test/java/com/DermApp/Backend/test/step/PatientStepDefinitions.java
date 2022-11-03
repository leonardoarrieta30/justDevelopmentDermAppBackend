package com.DermApp.Backend.test.step;

import com.DermApp.Backend.overview.resource.CreatePatientResource;
import com.DermApp.Backend.overview.resource.PatientResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@CucumberContextConfiguration
public class PatientStepDefinitions {


    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private String randomServerPort;

    private String endpointPath;

    private ResponseEntity<String> responseEntity;




    @And("A Patient Resource is included in Response Body, with values {string}, {int}, {string}, {string}, {string}")
    public void aPatientResourceIsIncludedInResponseBodyWithValues(String name, int age, String password, String address, String description) {
        PatientResource expectedResource = new PatientResource()
                .withName(name)
                .withAge(age)
                .withPassword(password)
                .withAddress(address)
                .withDescription(description);
        String value = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        PatientResource actualResource;
        try {
            actualResource = mapper.readValue(value, PatientResource.class);

        } catch (JsonProcessingException | NullPointerException e){
            actualResource = new PatientResource();
        }
        expectedResource.setId(actualResource.getId());
        assertThat(expectedResource).usingRecursiveComparison().isEqualTo(actualResource);
    }

    @Given("A Patient Resource with values {string}, {int}, {string}, {string}, {string} is already stored")
    public void aPatientResourceWithValuesIsAlreadyStored(String name, int age, String password, String address, String description) {
        CreatePatientResource resource = new CreatePatientResource()
                .withName(name)
                .withAge(age)
                .withPassword(password)
                .withAddress(address)
                .withDescription(description);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreatePatientResource> request = new HttpEntity<>(resource, headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath, request, String.class);
    }
}

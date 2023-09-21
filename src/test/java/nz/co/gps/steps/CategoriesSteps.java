package nz.co.gps.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.Assert;
import java.util.Map;
import static nz.co.gps.steps.Hooks.*;

import static io.restassured.RestAssured.given;

public class CategoriesSteps {

    Response response;

    @When("I send a GET request with following inputs:")
    public void sendGETRequest(DataTable dataTable) {
        Map<String, String> inputData = dataTable.asMap(String.class, String.class);
        String[] paramData = inputData.get("params").split("::");

        RestAssured.baseURI = baseURL;
        response = given()
                .contentType(ContentType.JSON)
                .param(paramData[0], paramData[1])
                .when()
                .get(inputData.get("endpoint"))
                .then()
                .extract().response();
    }

    @Then("I see the response code is {int}")
    public void validateResponseCode(int expectedResponseCode) {
        Assert.assertEquals(expectedResponseCode, response.statusCode());
    }

    @Then("I see the response contains the following data:")
    public void validateResponseData(DataTable dataTable) {
        Map<String, String> expectedResponseData = dataTable.asMap(String.class, String.class);

        // validate the attribute value of all those passed from the feature file
        for (String attribute : expectedResponseData.keySet()) {
            Assert.assertEquals(expectedResponseData.get(attribute), response
                    .jsonPath().getString(attribute));
        }
    }

    @Then("^The (.*) element with (.*) as (.*) has a (.*) that contains the text (.*)$")
    public void validateResponseData(String element,
                                     String attribute,
                                     String attributeValue,
                                     String sibling,
                                     String expectedSiblingValue) {
        Assert.assertTrue(
                response.jsonPath()
                        // filter elements that match a attribute value
                        // get the value of a sibling attribute that match the above filter criteria
                        .getList(String.format("%s.findAll{it.%s == '%s'}.%s",
                                element, attribute, attributeValue, sibling))
                        .contains(expectedSiblingValue));

    }

}

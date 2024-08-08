package com.nttdata.glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;

public class StoreSteps {
    private Response response;
    private String baseUrl = "https://petstore.swagger.io/v2";

    @Given("el API de Store está disponible")
    public void el_api_de_store_esta_disponible() {
        // Puedes realizar una solicitud simple para verificar que el API está disponible
        given().when().get(baseUrl).then().statusCode(200);
    }

    @When("se envía una solicitud POST a /store/order con el siguiente payload")
    public void se_envia_una_solicitud_post_a_store_order_con_el_siguiente_payload(String payload) {
        response = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post(baseUrl + "/store/order");
    }

    @When("se envía una solicitud GET a /store/order/{int}")
    public void se_envia_una_solicitud_get_a_store_order(Integer orderId) {
        response = given()
                .when()
                .get(baseUrl + "/store/order/" + orderId);
    }

    @Then("el status code de la respuesta debe ser {int}")
    public void el_status_code_de_la_respuesta_debe_ser(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("el body de la respuesta debe contener el id {string}")
    public void el_body_de_la_respuesta_debe_contener_el_id(String orderId) {
        response.then().body("id", equalTo(Integer.parseInt(orderId)));
    }

    @Then("el body de la respuesta debe contener el petId {string}")
    public void el_body_de_la_respuesta_debe_contener_el_petId(String petId) {
        response.then().body("petId", equalTo(Integer.parseInt(petId)));
    }

    @Then("el body de la respuesta debe contener el status {string}")
    public void el_body_de_la_respuesta_debe_contener_el_status(String status) {
        response.then().body("status", equalTo(status));
    }
}

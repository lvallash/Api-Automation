package com.nttdata.steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class OrderSteps {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private Response response;

    public void crearOrden(int id, int petId, String status) {
        String payload = "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"petId\": " + petId + ",\n" +
                "  \"status\": \"" + status + "\"\n" +
                "}";
        response = RestAssured.given()
                .contentType("application/json")
                .body(payload)
                .post(BASE_URL + "/store/order");
    }

    public void consultarOrden(int orderId) {
        response = RestAssured.given()
                .get(BASE_URL + "/store/order/" + orderId);
    }

    public void verificarStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    public void verificarId(int id) {
        response.then().body("id", equalTo(id));
    }

    public void verificarPetId(int petId) {
        response.then().body("petId", equalTo(petId));
    }

    public void verificarStatus(String status) {
        response.then().body("status", equalTo(status));
    }
}

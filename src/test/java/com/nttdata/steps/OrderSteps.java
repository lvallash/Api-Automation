package com.nttdata.steps;

import com.nttdata.model.Order;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OrderSteps {

    private static String BASE_URL = "https://petstore.swagger.io/v2";
    private Response response;

    public void crearOrden(Order order) {
        response = given()
                .contentType("application/json")
                .body(order)
                .post(BASE_URL + "/store/order");
    }

    public void consultarOrden(int orderId) {
        response = given()
                .get(BASE_URL + "/store/order/" + orderId);
    }

    public void verificarStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    public void verificarRespuestaOrden(Order order) {
        response.then().body("id", equalTo(order.getId()));
        response.then().body("petId", equalTo(order.getPetId()));
        response.then().body("status", equalTo(order.getStatus()));
    }
}

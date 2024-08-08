package com.nttdata.glue;

import com.nttdata.model.Order;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class StoreSteps {

    private Response response;
    private Order order;

    @Given("el API de Store está disponible")
    public void el_api_de_store_esta_disponible() {
        // Puedes hacer una verificación básica aquí si es necesario
        // Por ejemplo, verificar que el API responde
        RestAssured.given().get("/store/order/1").then().statusCode(200);
    }

    @When("consulto la orden con id {int}")
    public void consulto_la_orden_con_id(int id) {
        // Realiza la consulta a la API real
        response = RestAssured.given().get("/store/order/" + id);
        // Si estás usando un modelo mock, puedes configurar aquí los datos de respuesta mock
        if (id == 123) {
            order = new Order();
            order.setId(id);
            order.setPetId(456);
            order.setStatus("placed");
        } else {
            order = null;
        }
    }

    @Then("el código de respuesta es {int}")
    public void el_codigo_de_respuesta_es(int statusCode) {
        // Verifica el código de respuesta real de la API
        assertEquals("El código de respuesta es incorrecto", statusCode, response.getStatusCode());
    }

    @And("el body de la respuesta debe contener el id {int}")
    public void el_body_de_la_respuesta_debe_contener_el_id(int id) {
        assertNotNull("La respuesta no debe ser nula", response);
        assertEquals("El id de la orden es incorrecto", id, response.jsonPath().getInt("id"));
    }

    @And("el body de la respuesta debe contener el petId {int}")
    public void el_body_de_la_respuesta_debe_contener_el_petId(int petId) {
        assertNotNull("La respuesta no debe ser nula", response);
        assertEquals("El petId de la orden es incorrecto", petId, response.jsonPath().getInt("petId"));
    }

    @And("el body de la respuesta debe contener el status {string}")
    public void el_body_de_la_respuesta_debe_contener_el_status(String status) {
        assertNotNull("La respuesta no debe ser nula", response);
        assertEquals("El status de la orden es incorrecto", status, response.jsonPath().getString("status"));
    }
}

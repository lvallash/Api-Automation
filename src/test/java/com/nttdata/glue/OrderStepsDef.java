package com.nttdata.glue;

import com.nttdata.steps.OrderSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class OrderStepsDef {

    @Steps
    OrderSteps orderSteps;

    @When("creo una orden con id {int}, petId {int}, y status {string}")
    public void creoUnaOrdenConIdPetIdYStatus(int id, int petId, String status) {
        orderSteps.crearOrden(id, petId, status);
    }

    @Then("el código de respuesta es {int}")
    public void elCodigoDeRespuestaEs(int statusCode) {
        orderSteps.verificarStatusCode(statusCode);
    }

    @And("el body contiene el id {int}")
    public void elBodyContieneElId(int id) {
        orderSteps.verificarId(id);
    }

    @And("el body contiene el petId {int}")
    public void elBodyContieneElPetId(int petId) {
        orderSteps.verificarPetId(petId);
    }

    @And("el body contiene el status {string}")
    public void elBodyContieneElStatus(String status) {
        orderSteps.verificarStatus(status);
    }
}

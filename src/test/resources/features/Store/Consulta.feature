Feature: Consultar órdenes en PetStore

  @consultarOrden
  Scenario Outline: Consulta de una orden
    When consulto la orden con id <orderId>
    Then el código de respuesta es 200
    And el body contiene el id <orderId>
    And el body contiene el petId <petId>
    And el body contiene el status <status>

    Examples:
      | orderId | petId | status  |
      | 123     | 456   | placed  |
      | 124     | 457   | shipped |

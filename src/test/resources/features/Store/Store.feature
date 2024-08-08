Feature: Validación del API de Store en PetStore
  Para garantizar que el API de Store funcione correctamente
  Como automatizador principal de NTT
  Quiero crear y consultar órdenes en la tienda de mascotas

  Scenario Outline: Creación de Order
    Given el API de Store está disponible
    When se envía una solicitud POST a /store/order con el siguiente payload
      """
      {
        "id": "<orderId>",
        "petId": "<petId>",
        "quantity": "<quantity>",
        "shipDate": "<shipDate>",
        "status": "<status>",
        "complete": "<complete>"
      }
      """
    Then el status code de la respuesta debe ser 200
    And el body de la respuesta debe contener el id "<orderId>"
    And el body de la respuesta debe contener el petId "<petId>"
    And el body de la respuesta debe contener el status "<status>"

    Examples:
      | orderId | petId | quantity | shipDate                | status  | complete |
      | 1       | 123   | 2        | 2024-08-07T00:00:00.000Z | placed  | true     |
      | 2       | 456   | 1        | 2024-08-08T00:00:00.000Z | shipped | false    |

  Scenario: Consulta de Order
    Given el API de Store está disponible
    When se envía una solicitud GET a /store/order/1
    Then el status code de la respuesta debe ser 200
    And el body de la respuesta debe contener el id "1"
    And el body de la respuesta debe contener el petId "123"
    And el body de la respuesta debe contener el status "placed"

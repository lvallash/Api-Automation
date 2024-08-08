Feature: Crear una orden en PetStore

  @crearOrden
  Scenario: Crear una orden y verificar que se ha creado
    When creo una orden con id 123, petId 456, y status "placed"
    Then el código de respuesta es 200
    And el body contiene el id 123
    And el body contiene el petId 456
    And el body contiene el status "placed"

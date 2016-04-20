Feature: landing page 
  Scenario: client makes call to GET /principal.xhtml
    When the client calls /principal.xhtml
    Then the client receives status code of 200
    And the client receives the title "Criterios de búsqueda de un proceso electoral"
    And the client receives the option1 with string "Buscar por nombre:"
    And the client receives the option2 with string "Buscar por fecha de inicio:"
    And the client receives the option3 with string "Buscar por fecha de fin:"
    
  Scenario: client search by name
    Given the client write in the "option1" the "name"
    When the client click in search button to execute the search by "name"
    Then search by "name" should be successful
    
  Scenario: client search by open date
    Given the client write in the "option2" the "open date"
    When the client click in search button to execute the search by "open date"
    Then search by "open date" should be successful
    
  Scenario: client search by close date
   	Given the client write in the "option3" the "close date"
    When the client click in search button to execute the search by "close date"
    Then search by "close date" should be successful
    
  Scenario: the client want have a look of "Lista Elecciones"
  	When the client click on the link "Lista Elecciones"
  	Then the client makes call to Lista Elecciones GET /listaElecciones.xhtml
  	And the client receives Lista Elecciones status code of 200
  	
  Scenario: the client want see "Referendum" of "Tipos de Elecciones"
  	When the client choose "Referendum" with id "j_idt10"
  	Then the client makes call to Referendum GET /listaReferendum.xhtml
  	And the client receives Referendum status code of 200
  
  Scenario: the client want see "Listas Cerradas" of "Tipos de Elecciones"
  	When the client choose "Listas Cerradas" with id "j_idt11"
  	Then the client makes call to Listas Cerradas GET /listaCerradas.xhtml
  	And the client receives Listas Cerradas status code of 200
  	
  Scenario: the client want see "Listas Abiertas" of "Tipos de Elecciones"
  	When the client choose "Listas Abiertas" with id "j_idt12"
  	Then the client makes call to Listas Abiertas GET /listaAbiertas.xhtml
  	And the client receives Listas Abiertas status code of 404
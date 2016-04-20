# language: en
Feature: list page 
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
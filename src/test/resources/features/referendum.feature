# language: en
Feature: referendum page 
Scenario: the client want have a look of "Lista Elecciones"
  	When the client click on the link "Lista Elecciones"
  	Then the client makes call to Lista Elecciones GET /listaElecciones.xhtml
  	Then the cliente select a election 
  	And the client receives Lista Elecciones status code of 200
  	
  Scenario: the client want see "Referendum" of "Tipos de Elecciones"
  	When the client choose "Referendum" with id "j_idt10"
  	Then the client makes call to Referendum GET /listaReferendum.xhtml
  	And the client receives Referendum status code of 200 
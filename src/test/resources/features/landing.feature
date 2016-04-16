Feature: landing page 
  Scenario: client makes call to GET /principal.xhtml
    When the client calls /principal.xhtml
    Then the client receives status code of 200
    And the client receives the title "Criterios de búsqueda de un proceso electoral"
    And the client receives the option1 with string "Buscar por nombre:"
    And the client receives the option2 with string "Buscar por fecha de inicio:"
    And the client receives the option3 with string "Buscar por fecha de fin:"
  Scenario: client search 
    Given the client write in the option1 the name
    Given the client write in the option2 the open date
    Given the client write in the option3 the close date
    When the client click in search button
    Then search should be successful
    

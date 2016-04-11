Feature: landing page 
  Scenario: client makes call to GET /principal.xhtml
    When the client calls /principal.xhtml
    Then the client receives status code of 200
    And the client receives the string "Complete los siguientes datos"

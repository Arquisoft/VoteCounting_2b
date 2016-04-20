Feature: search page
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
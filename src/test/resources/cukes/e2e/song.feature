@e2e
Feature: Make browser calls

  Scenario: Open Selenium application
    Given The user opened a browser and passed "http://localhost:1199/songs/"
    When User adds id, which is 1 of a song record in the URL
    Then User receives song metadata "{'name': 'test','artist': 'test-artist','album': 'test-album','length': '2h','year': '2003','resourceId': '6'}"
@song
Feature: Customer functionalities

  Background:
  Given The application is available at "http://localhost:1199/songs/"

  Scenario: Upload song metadata with data provided
    When User provides song metadata to upload "{'name': 'The rock','artist': 'John Smith','album': 'One','length': '6h','year': '1995','resourceId': '5'}"
    Then User should receive id 1 of the saved record

  Scenario: Get song metadata by song id
    When User passes id 1 of a song record
    Then User should receive song metadata "{'name': 'The rock','artist': 'John Smith','album': 'One','length': '6h','year': '1995','resourceId': '5'}"
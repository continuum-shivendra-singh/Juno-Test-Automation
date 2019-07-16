#Author: shivendra
@registration 
Feature: Validate Registration, Re-registration, installation and uninstallation of Agent on Windows, Linux, MAC
 
  Scenario: Verify Agent is installated and registered successfully on Linux
    Given Linux agent present on the machine
    When User install agent on machine
    Then Agent install successfully on the machine
   
  
  Scenario: Verify Agent is installated and registered successfully on Windows
    Given Windwos agent present on the machine
    When User install agent on Windows machine
    Then Agent install successfully on the window machine

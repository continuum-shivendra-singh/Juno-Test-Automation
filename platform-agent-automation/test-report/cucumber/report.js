$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Agent_Registration_Installation.feature");
formatter.feature({
  "comments": [
    {
      "line": 1,
      "value": "#Author: shivendra"
    }
  ],
  "line": 3,
  "name": "Validate Registration, Re-registration, installation and uninstallation of Agent on Windows, Linux, MAC",
  "description": "",
  "id": "validate-registration,-re-registration,-installation-and-uninstallation-of-agent-on-windows,-linux,-mac",
  "keyword": "Feature",
  "tags": [
    {
      "line": 2,
      "name": "@registration"
    }
  ]
});
formatter.scenario({
  "line": 5,
  "name": "Verify Agent is installated and registered successfully on Linux",
  "description": "",
  "id": "validate-registration,-re-registration,-installation-and-uninstallation-of-agent-on-windows,-linux,-mac;verify-agent-is-installated-and-registered-successfully-on-linux",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 6,
  "name": "Linux agent present on the machine",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "User install agent on machine",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "Agent install successfully on the machine",
  "keyword": "Then "
});
formatter.match({
  "location": "Agent_Registration_Installation.linux_agent_present_on_the_machine()"
});

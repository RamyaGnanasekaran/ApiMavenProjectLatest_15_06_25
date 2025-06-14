Feature: Incident management in Service Now API

Background:
Given Set the endpoint for Incident management Api
And Set the user credentials for authenticating the system
And Set the queryparameter in the input request

@Smoke
Scenario: TC001 Create incident with no body in the request
And Set the Content Type for the input request
When User send the POST request to the Incident management APi
Then Validate the status code is 201

@Regression
Scenario: TC002 Get incident with query param
When User send the GET request to the Incident management APi
Then Validate the status code is 200

@Smoke @Sanity
Scenario Outline: TC003 Create incident with no body in the request
And Set the Content Type for the input request
And Set the accept for the input request
And Set the file '<file_Name>' in the input request
When User send the POST request to the Incident management APi
Then Validate the status code is 201

Examples:
|file_Name|
|Create_Incident|

@Regression
Scenario: TC004 Update incident with query param
And Set the Content Type for the input request
When User send the PUT request to the Incident management APi
Then Validate the status code is 200




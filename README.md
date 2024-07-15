# deben-challenge


### Architecture

The architecture is a layered architecture consisting of Controllers like `WeatherController` and Services like `WeatherService` and `ReportService` and Middleware clients like `WeatherClient`. The application is secured using OAuth2 from `Okta(Auth0)`


### How to run

- In order to run the application you have to generate the `appId` for `OpenWeatherMap
- Also you have to create API for Auth0 [here](https://auth0.com/docs/get-started/auth0-overview/set-up-apis)

- Create Regular Web Application [here](https://auth0.com/docs/get-started/auth0-overview/create-applications/regular-web-apps)

- Use authorization code flow [here](https://auth0.com/docs/get-started/authentication-and-authorization-flow/authorization-code-flow/call-your-api-using-the-authorization-code-flow)

- Use Postman to simulate `authorization code` flow and don't forget to add query param `audience` with the to the authorization url

- get the token returned to use with `/api/report/{city}` url

- run using `mvn spring-boot:run`

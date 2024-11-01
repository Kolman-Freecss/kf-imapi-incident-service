- IMAPI Angular service: https://github.com/Kolman-Freecss/kf-imapi-angular
- IMAPI Incident Service: https://github.com/Kolman-Freecss/kf-imapi-incident-service
- IMAPI Response Team Service: https://github.com/Kolman-Freecss/kf-imapi-response-service
- IMAPI Notification Service: https://github.com/Kolman-Freecss/kf-imapi-notification-service
- IMAPI Authentication Gateway Service: https://github.com/Kolman-Freecss/kf-imapi-auth-gateway
- IMAPI DevOps / Kafka Event Handling: https://github.com/Kolman-Freecss/kf-imapi-devops

## Brief Description

--- 
Auth Gateway Service for IMAPI (Incident Management API) project:
```
Features: 
- Implementation of security using Spring Boot 3.0 and Keycloak with JSON Web Tokens (JWT).
  - API Gateway for routing requests to the appropriate service.
    - Circuit Breaker using Resilience4j.
    - Also we hide the internal services from the outside world and KEYCLOAK (everything is behind the gateway).
  - SSO (Single Sign-On) using Keycloak.
  - OAuth2 Protocol.
  - Internal JWT signing to validate the token in every microservice verifying the authenticity of the API Gateway token. (X-Internal-Auth)
    - (Another robust option is to sign every token through TLS, but it is not implemented in this project).
```
---

## Tech stack:

- Spring Boot 3.0
- Keycloak
- Kafka
- Maven
- Docker
- Spring Cloud Netflix (Eureka)
- Observability - Micrometer Tracing and Zipkin (Distributed Tracing) (Sleuth is deprecated)
  - We use AOP support to decorate the methods with tracing annotations.
- Prometheus and Grafana (Monitoring)

---

Shield: [![CC-BY-NC-ND 4.0][CC-BY-NC-ND-shield]][CC-BY-NC-ND]

This work is licensed under a [Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.][CC-BY-NC-ND]

[![CC-BY-NC-ND 4.0][CC-BY-NC-ND-image]][CC-BY-NC-ND]

[CC-BY-NC-ND-shield]: https://img.shields.io/badge/License-CC--BY--NC--ND--4.0-lightgrey
[CC-BY-NC-ND]: http://creativecommons.org/licenses/by-nc-nd/4.0/
[CC-BY-NC-ND-image]: https://i.creativecommons.org/l/by-nc-nd/4.0/88x31.png

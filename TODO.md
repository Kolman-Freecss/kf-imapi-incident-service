# Checklist:

- [ ] Snowflake integration (or other data warehouse) to generate unique ID's in a distributed system (microservices).
  - This is important to avoid conflicts when generating unique identifiers across multiple services. (Data consistency)
  - Will be a centralized service to generate unique identifiers.
  - Or a identifier in the increment of Id's
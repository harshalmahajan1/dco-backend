# DCO Full Spring Boot Scaffold

This project is a scaffold for the Digital Customer Onboarding backend.
It includes:
- Entities: Customer, OTPDetails, Address, UserAccount
- Repositories, Services, Controllers
- Basic Twilio hooks for sending OTP (configure credentials)
- Basic Auth endpoints (register/login) + JWT utility (token generation only)
- H2 in-memory DB for quick start (switch to MySQL via application.properties)

## Run
- Build: `mvn package`
- Run: `mvn spring-boot:run`

## Notes
- JWT validation filter is not fully wired into security chain in this scaffold; login returns a token you can use for your frontend.
- Change `jwt.secret` in application.properties before production.

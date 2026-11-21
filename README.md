# Personal Finance Manager

A full-stack web application for logging, categorizing and tracking daily income and expense transactions, with per-user secure access.

## Features

- **Secure authentication:** Spring Security filter chain with stateless JWT tokens
- **Per-user data isolation:** each user can only view and modify their own transactions
- **Transaction management:** create, view, update and delete income and expense records
- **Classification and filtering:** income/expense categories and date-range queries
- **Input validation** on incoming transaction data
- **Relational persistence** in PostgreSQL via Spring Data JPA / Hibernate

## Tech stack

| Layer | Technologies |
|---|---|
| Backend | Java, Spring Boot, Spring Security, JWT, Spring Data JPA, Hibernate, Maven |
| Frontend | React, TypeScript, Vite, React Router, Axios |
| Database | PostgreSQL |

## Project structure

```
finance-manager/
├── Backend/financemanager   # Spring Boot REST API (Maven)
└── Frontend                 # React + TypeScript (Vite)
```

## Running locally

### Prerequisites
Java (matching the version in `pom.xml`), Node.js and npm, PostgreSQL

### Backend
Configuration is read from environment variables (see `src/main/resources/application.properties`):

| Variable | Purpose |
|---|---|
| `jdbc_url` | PostgreSQL JDBC URL |
| `jdbc_username` | Database user |
| `jdbc_pass` | Database password |
| `secret_key` | JWT signing secret |

```bash
cd Backend/financemanager
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```

### Frontend
```bash
cd Frontend
npm install
npm run dev
```

## Author
**Prasanth Reddy Mannem** · [LinkedIn](https://linkedin.com/in/prasanthreddymannem) · [GitHub](https://github.com/prasanthreddymannem)

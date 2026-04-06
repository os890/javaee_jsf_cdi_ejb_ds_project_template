# Jakarta EE / JSF / CDI / EJB / DeltaSpike Project Template

A minimal project template demonstrating how Jakarta EE technologies work together:

- **CDI** (Contexts and Dependency Injection) with multiple scopes
- **EJB** (Enterprise JavaBeans) integration with CDI
- **JSF** (Jakarta Server Faces) view layer
- **Apache DeltaSpike 2** extensions (window scope)

## Architecture

| Class | Role |
|---|---|
| `StatelessEJB` | Stateless EJB returning a fixed value |
| `ApplicationScopedBean` | Application-scoped CDI bean delegating to the EJB via `@EJB` |
| `WindowScopedBean` | DeltaSpike `@WindowScoped` JSF managed bean reading from the application bean |

## Requirements

- **Java 25+**
- **Maven 3.6.3+**

## Build

```bash
# Compile and run CDI SE tests (default profile)
mvn clean verify

# Run the TomEE/OpenEJB integration test
mvn clean verify -Ptomee

# Both profiles
mvn clean verify && mvn verify -Ptomee
```

## Test Profiles

| Profile | Container | What it tests |
|---|---|---|
| `cdi-test` (default) | CDI SE (OpenWebBeans) | CDI injection with mocked beans via [dynamic-cdi-test-bean-addon](https://github.com/os890/dynamic-cdi-test-bean-addon) |
| `tomee` | Embedded OpenEJB/TomEE | Full CDI + EJB + DeltaSpike window-scope integration |

## Quality Plugins

The build enforces:

- **Compiler** — Java 25, all lint warnings, fail on warning
- **Enforcer** — minimum Java/Maven versions, dependency convergence, banned `javax.*` dependencies
- **Checkstyle** — import rules, brace style, whitespace, modifier order
- **Apache RAT** — Apache License 2.0 header on all source/config files
- **JaCoCo** — code coverage reporting

## Test Addon

The CDI SE tests use the
[dynamic-cdi-test-bean-addon](https://github.com/os890/dynamic-cdi-test-bean-addon)
which automatically mocks unsatisfied CDI injection points during container
bootstrap. Clone and install it locally before building:

```bash
git clone https://github.com/os890/dynamic-cdi-test-bean-addon.git
cd dynamic-cdi-test-bean-addon
mvn clean install
```

## License

[Apache License, Version 2.0](LICENSE)

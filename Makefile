MVN_REPORT := target/site/surefire-report.html
TIMESTAMP := $(shell date +'%F %T')

compile: # compile project
	./mvnw clean compile test-compile

package:
	./mvnw package -DskipTests

start-api:
	./mvnw clean spring-boot:run

start-jar: package
	java -jar ./target/gestaoservicos-*.jar

start-docker:
	docker run --rm --name demo-aplicacao -p 8080:8080 -it gestaoservicos:latest

debug-api:
	./mvnw clean spring-boot:run -Dspring-boot.run.profiles=dev -Dspring.jmx.enabled=true

## Test
unit-test:
	./mvnw test

integration-test:
# ./mvnw failsafe:integration-test
	./mvnw test -P integration-test

system-test:
	./mvnw test -Psystem-test
	@echo $(TIMESTAMP) [INFO] cucumber HTML report generate in: target/cucumber-reports/cucumber.html

performance-test:
	./mvnw gatling:test -Pperformance-test

test: unit-test integration-test

report-maven: # Gerar relatorio HTML utilizando maven
	./mvnw surefire-report:report
	@echo $(TIMESTAMP) [INFO] maven report generate in: $(MVN_REPORT)

report-allure:
	allure serve target

## Docker

docker-image: package
	docker build -t demo/aplicacao -f ./docker/Dockerfile .
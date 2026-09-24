# das-tool REST API

Minimale REST API für das-tool.

Projekt wurde begonnen, um Erfahrungen mit Kotlin zu sammeln.

## Lokale Entwicklung

### JDK

* Gradle verwendet das JDK aus `gradle.properties` (`org.gradle.java.home`, aktuell jdk-20.0.2).
* Kompiliert wird für Java 17, auf dem Server läuft JDK 21.

### Nicht eingecheckte Dateien

* `rest/src/main/resources/secrets.properties` (`spring.datasource.password`, `app.jwtSecret`; wird in die Jar gepackt)
* `db/src/test/resources/secrets-mysql.properties` (für die MySQL-Integrationstests)
* `docker/.env` (MySQL-Passwörter und `COMPOSE_PROJECT_NAME=das-tool-rest`)
* `docker/mysql/scripts/*.sql` (Dump der Prod-Datenbank)

### Starten

1. MySQL in Docker starten: In Docker Desktop den Container `mysql-1` in der Gruppe `das-tool-rest` starten
   (bzw. `docker start das-tool-rest-mysql-1`).
   Neu anlegen: `docker compose up -d` im Verzeichnis `docker/` (Image `mysql-no-volume`, s. `docker/mysql-no-volume/Readme.MD`).
2. Backend starten: `./gradlew :rest:bootRun`
3. API ist erreichbar unter http://localhost:8090/das-tool-rest (Test: http://localhost:8090/das-tool-rest/index.html).

Die Angular-App (Projekt `angular-ui`) greift über `apiUrl` in `src/environments/environment.ts` auf das Backend zu.

## Deployment

### Erstes Deployment nach dem Update auf Spring Boot 4

Die Jar ist nicht mehr direkt ausführbar (kein Launch-Script). Auf dem Server deshalb **vor** dem Deployment
der neuen Jar die Service-Datei ersetzen (s. Install app as a service):

* `deployment/das-tool-rest.service` nach `/etc/systemd/system` kopieren.
* `sudo systemctl daemon-reload`

### Executable Jar bauen

`rest/src/main/resources/secrets.properties` (`spring.datasource.password`, `app.jwtSecret`) wird beim Build
in die Jar gepackt. Vor dem Bauen prüfen, dass die Datei die **Prod-Werte** enthält.

* `./gradlew :rest:bootJar`
* `rest/build/libs/das-tool-rest.jar` nach flower.de:/home/oblume/das-tool-rest kopieren.
* `sudo systemctl restart das-tool-rest`
* `sudo less /var/log/das-tool-rest/das-tool-rest.log`

### Install app as a service

s. https://docs.spring.io/spring-boot/reference/deployment/installing.html

* `deployment/das-tool-rest.service` ins Verzeichnis `/etc/systemd/system` kopieren.
* `sudo systemctl daemon-reload`
* `sudo systemctl start das-tool-rest`
* `sudo systemctl enable das-tool-rest.service`

Seit Spring Boot 4 gibt es kein eingebettetes Launch-Script mehr, die Jar wird im Service mit
`/usr/lib/jvm/openjdk-21/bin/java -jar` gestartet. Nach Änderungen an der Service-Datei `daemon-reload` nicht vergessen.

#### Running behind Apache

    a2enmod proxy
    a2enmod proxy_http

Reverse-Proxy-Config: `/etc/apache2/sites-available/flower.de-le-ssl.conf`

    # Proxy configuration for /das-tool-rest path
    ProxyPass "/das-tool-rest" "http://localhost:8090/das-tool-rest"
    ProxyPassReverse "/das-tool-rest" "http://localhost:8090/das-tool-rest"

### Logging

* `/var/log/das-tool-rest/das-tool-rest.log` (application log, konfiguriert in logback.xml)
* `journalctl -u das-tool-rest` (stdout/stderr des Service)

### Monitoring

- https://uptimerobot.com

  HTTP-Monitoring von https://flower.de/das-tool-rest/index.html

- Host panel bei hosteurope

  Generelle Serverüberwachung

- Log-Files s. oben

## Firewall

s. Readme.Md im Projekt 'rmt'.

## SSL-Certificate

Wird von Apache bereitgestellt.

## Testing

`./gradlew test`

Unit-Tests laufen mit einer H2, die über SQL-Skripte (`schema.sql`, `data.sql`) initialisiert wird.

Insgesamt ist die Testabdeckung noch sehr gering.

## Integration testing

Die Repository-Tests in `db` (Basisklasse `AbstractMysqlRepoTest`, Profil `mysql`) laufen gegen die MySQL im
Docker-Container (s. Lokale Entwicklung).

## Issue Tracking

https://flowerrrr.atlassian.net

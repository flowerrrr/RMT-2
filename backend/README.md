# das-tool REST API

Minimale REST API für das-tool.

Projekt wurde begonnen, um Erfahrungen mit Kotlin zu sammeln.

## Deployment

### Install JDK

* JDK 21 installieren und JAVA_HOME_21 setzen.

### Executable Jar bauen

* gradle assemble
* das-tool-rest.jar nach flower.de:/home/oblume/das-tool-rest kopieren.
* chmod a+x das-tool-rest.jar

### Install app as a service

s. https://docs.spring.io/spring-boot/docs/current/reference/html/deployment.html#deployment.installing.nix-services.system-d

* deployment/das-tool-rest.conf ins Verzeichnis /oblume/das-tool-rest kopieren.
* deployment/das-tool-rest.service ins Verzeichnis /etc/systemd/system kopieren.
* systemctl daemon-reload
* systemctl start das-tool-rest
* systemctl enable das-tool-rest.service

### Logging

* /var/log/das-tool-rest.log (wird von systemd geschrieben)
* /var/log/das-tool-rest/das-tool-rest.log (application log, konfiguriert in logback.xml)

### Monitoring

- https://uptimerobot.com

  HTTP-Monitoring von https://flower.de:8453/das-tool-rest/index.html

- Host panel bei hosteurope

  Generelle Serverüberwachung

- Log-Files s. oben

## Firewall

s. Readme.Md im Projekt 'rmt.'

## SSL-Certificate

Basierend auf dem Zertifikat des tomcat generiert.
s. https://dzone.com/articles/spring-boot-secured-by-lets-encrypt

* cd /etc/letsencrypt/live/flower.de/
* openssl pkcs12 -export -in fullchain.pem -inkey privkey.pem -out keystore.p12 -name flower -CAfile chain.pem -caname
  root
  Passwort wie im tomcat-Connector in server.xml
* keystore.p12 nach /rest/src/main/resources kopieren.

Keystore muss nach 3 Monaten manuell erneuert werden, falls die Clients nicht mit abgelaufenen Zertifikaten umgehen
können.
Automatisierung wäre möglich, indem man den Export monatlich durchführt, den so generierten Keystore in
application.properties einträgt
und dann die Anwendung neu startet.

## Testing

Unit-Tests laufen mit einer H2, die über SQL-Skripte initialisiert wird.

Insgesamt ist die Testabdeckung noch sehr gering.

## Integration testing

Die App kann mit einer MySQL, die in Docker läuft, getestet werden.
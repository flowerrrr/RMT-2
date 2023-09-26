# das-tool REST API

Minimale REST API für das-tool.

Projekt wurde begonnen, um Erfahrungen mit Kotlin zu sammeln.

## Deployment

* gradle assemble
* das-tool-rest.jar nach flower.de:/home/oblume/das-tool-rest kopieren.
*

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

## Testing

Unit-Tests laufen mit einer H2, die über SQL-Skripte initialisiert wird.

Insgesamt ist die Testabdeckung noch sehr gering.

## Integration testing

Die App kann mit einer MySQL, die in Docker läuft, getestet werden.
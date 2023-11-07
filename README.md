# das-tool REST API

Minimale REST API für das-tool.

Projekt wurde begonnen, um Erfahrungen mit Kotlin zu sammeln.

## Deployment

### Install JDK

* JDK 21 installieren und JAVA_HOME_21 setzen.

### Executable Jar bauen

* gradle assemble
* das-tool-rest.jar nach flower.de:/home/oblume/das-tool-rest kopieren.
* chmod a+rx das-tool-rest.jar

### Install app as a service

s. https://docs.spring.io/spring-boot/docs/current/reference/html/deployment.html#deployment.installing.nix-services.system-d

* deployment/das-tool-rest.conf ins Verzeichnis /oblume/das-tool-rest kopieren.
* deployment/das-tool-rest.service ins Verzeichnis /etc/systemd/system kopieren.
* systemctl daemon-reload
* systemctl start das-tool-rest
* systemctl enable das-tool-rest.service


#### Running behind Apache

  a2enmod proxy
  a2enmod proxy_http

- Reverse-Proxy-Config: /etc/apache2/sites-available/ flower.de-le-ssl.conf

  # Proxy configuration for /das-tool-rest path
  ProxyPass "/das-tool-rest" "http://localhost:8090/das-tool-rest"
  ProxyPassReverse "/das-tool-rest" "http://localhost:8090/das-tool-rest"


### Logging

* /var/log/das-tool-rest.log (wird von systemd geschrieben)
* /var/log/das-tool-rest/das-tool-rest.log (application log, konfiguriert in logback.xml)

### Monitoring

- https://uptimerobot.com

  HTTP-Monitoring von https://flower.de/das-tool-rest/index.html

- Host panel bei hosteurope

  Generelle Serverüberwachung

- Log-Files s. oben

## Firewall

s. Readme.Md im Projekt 'rmt.'

## SSL-Certificate

Wird von Apache bereitgestellt.


## Testing

Unit-Tests laufen mit einer H2, die über SQL-Skripte initialisiert wird.

Insgesamt ist die Testabdeckung noch sehr gering.

## Integration testing

Die App kann mit einer MySQL, die in Docker läuft, getestet werden.

## Issue Tracking

https://flowerrrr.atlassian.net
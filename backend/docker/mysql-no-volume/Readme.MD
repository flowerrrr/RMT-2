Es ist nicht standardmäßig nicht möglich, die Änderungen an der Mysql-DB in Docker-Container in ein neues Image zu commiten.
Siehe https://stackoverflow.com/questions/30740828/commit-data-in-a-mysql-container
Mit diesem Dockerfile lässt sich ein Image erstellen, dass keine Volumes enthält
und somit die Änderungen an der DB in das Image commited werden können.


# ServerStorage
Application for PROSE for accesing the storage of the PROSE Servers and how much storage is left. The ServerStorage was programmed to automate the server storage checks. The data can be viewed via a web view.

## Implementation

The application is divided into 3 layers: Frontend (web view), Backend (server of the application) and Database. The frontend was developed using Angular, a typescript framework for programming modern web applications. The graphics were implemented using [Swimlane/ngx-charts library](https://github.com/swimlane/ngx-charts).

The backend was implemented with Java and Spring Boot as framework. The controller is the REST interface and responsible for the http requests. The data sent or received was implemented as DTO to send only the most necessary. In the service class is the business logic, there the data is provided, possibly still calculated, and then stored in the database.  
In a second part of the application the scheduling was implemented. The scheduler of Spring executes periodically, according to the specified CRON job, the PowerShell scripts, which is responsible for the query of the server or volume data.

Two scripts have been created and are used. The Serverquery Script is responsible for querying server data, i.e., CPU Usage, RAM Usage and Ram. The volume query script is responsible for querying the available volume memory.

For generating a PDF report, a button was created that sends out a HTTP request to the backend. There the backend gets the data from the database and writes it into a HTML template using [Thymeleaf](https://www.thymeleaf.org/) which is a Java template enginge for processing and creating HTML, XML, JavaScript, CSS and text. The Java library [Flying Saucer](https://github.com/flyingsaucerproject/flyingsaucer) is used to create a PDF out of the HTML template.  
For the PDF creation a separate controller was created, which handles the request for creating a PDF.

The database used is MariaDB, an open source database based on MySQL.

## Installation
To install the application for a different location of prose a own database namespace is needed. The same database system as Winterthur can be used or a own can be used. It’s easiest if a MariaDB is used as the datasource URL is already correct. If another database is chosen, then the corresponding JDBC database URL is needed. In the application-mariadb.properties replace `jdbc://mariadb` in the `spring.datasource.url` with the the new JDBC datasource URL.  
Following an example with the MariaDB datasource URL
```
spring.datasource.url=jdbc:mariadb://${DB_URI}/${DB_NAME}
```

In the application.properties the `DB_URI`, `DB_NAME`, `DB_USER` and `DB_PW` need to be assigned. 
`DB_URI` stands for the address of the host in which the application is installed followed with port of the database (for MariaDB this is 3306, for other DBs this needs to be looked up).  
`DB_NAME` is the database namespace.  
`DB_USER` is the user with which the database should be accessed (it’s not recommended to use the root user for this)

The application is provided as a zip with all the necessary resources. Unzip it in a directory of your choosing.
To application will be installed as a service with the help of [NSSM](https://nssm.cc), a service manager that helps to install applications as Windows services with the necessary resources to make the application work. For the installation, a script was made that only had to be executed for the installation of the service. Starting the services must be done manually in the "Services" application of Windows. For the uninstallation, a script was also made, which also only must be executed to uninstall the service. But before that the service must be stopped.

Before executing the install script make sure to adjust the AppDirectory in the installService.ps1 script file. The `AppDirectory` has to be the location where the zip got unpackaged with all its resources! The `ObjectName` has to be adjusted to a username in the prose.one network with admin rights. Password also needs to be provided. This is needed because the PowerShell scripts execute some queries that need admin rights.

Also make sure that the database namespace is setup befor executing the install script.

To execute the install script, open PowerShell and go to the directory in which the application is unzipped and type in the following:
```
./installService “mariadb, log, scriptWindows”
```
This will install the application as a service with the necessary properties.  
Database scripts have also been created to delete the entire contents of the database and one to create the database. These are PowerShell and batch scripts that run a SQL script. They must be handled with care, because if the database is deleted, the data is completely gone!

## Add server
To add more servers to the query, you need to add it in the application.properties which can be found in the config folder of the installation path. In the application.properties file add the name of the server at the end of `scriptscomputer.targets` separating each with `:`. Finally the service needs to be restarted to apply the changes.
Following an example
```
scriptscomputer.targets=computer1:computer2:computer3
```

## CRON schedule
In the application.properties the schedule of the CRON job can be changed by adjusting the expression of `cron.expression`. To get a valid cron expression the cron expression generator by [Cronhub](https://crontab.cronhub.io/) is recommended.   
It’s important to note that Spring requires 6 fields instead of 5.
Following an example for a daily CRON job at 3pm
```
cron.expression=0 0 15 * * *
```


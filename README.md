# SeleniumTestProjekt

## Einleitung 


Dies ist ein kleines Tutorial Projekt zum Einstieg in das 
Thema "Automatisiertes Testen mit Selenium in Java". <br />
Eingangs geht es um die Grundlagen bzw das Arbeiten mit Selenium in Java später um Themen wie  
BDD mit Cucumber oder Logging mit Log4J. Es wird Beispielhaft eine konfiguration für<br /> 
Remote WebDriver in einem Selenium Grid gezeigt. Zum Abschluss gibt es einen kurzen <br />
Einblick in eine CI/CD Pipeline via Jenkins.


### Inhalt

1. Das Testprojekt
2. Docker
   1. Installation Docker 
   2. Installation Selenium Hub & Selenium Browser Nodes
   3. Prüfen der Konfiguration

3. Verbindung des Testprojekts mit Remote Webdriver
    
4. Konfiguration des Jenkins Jobs 
    


## 1. Das Testprojekt
   Das Projekt ist abgeleitet vom Udemy Kurs: <br />

   **"Testautomatisierung mit Selenium für Java + Python"**<br />

   von Dieter Schanz und Lucian Dünnwald

   (https://www.udemy.com/course/kurs-testautomatisierung-mit-selenium-4-java-python/)

   Hierbei wird eingangs mithilfe von jUnit ein einfacher Seitenaufruf auf die Google suchseite simluiert<br />
   und es wird nach einem bestimmten Begriff gesucht. Dabei gehen die Tutoren auf Themen wie z.b.<br />
   Lokatoren, Page Object Pattern und verschieden Wait Methoden ein. Es wird erklärt wie man Selenium installiert <br />
   und sich die nötigen Browser und deren Driver korrekt konfiguriert und ins Projekt einbindet.
   
   Es wird erklärt, was die Vorteile von Maven sind und warum man es in <br />
   Modernen Java Projekten nutzen sollte. Die Struktur eines pom.xml Files <br />
   wird kurz erläutert und dann zur Anwendung gebracht. 

   Später wird Cucumber eingebunden, es werden feature Files und deren StepDefinitions implementiert. 

## 2. Docker 
   ### i. Docker installation  


   
   Eine Docker installation lässt sich über Brew durchführen oder man nutzt die <br />
   komfortable installation von Docker Desktop (https://docs.docker.com/desktop/install/mac-install/). 
   <br />
   <br />
      
   ### ii. Installation Selenium Hub & Selenium Browser Nodes
   
   Das Selenium hub lässt sich entweder über die Imagesuche im Docker Desktop Client installieren<br />
   **ODER**<br />
   man installiert es über ein Terminal. 
   Für zweitere ist ein Blick in die [Dokumentation]( https://github.com/SeleniumHQ/docker-selenium) hilfreich. 
  

   Beispielhaft kann man dann via Copy&Paste eine einfache Konfiguration aus der [Hub and Nodes](https://github.com/SeleniumHQ/docker-selenium#hub-and-nodes) Doku ableiten. 

   ```Bash
   $ docker network create grid
$ docker run -d -p 4442-4444:4442-4444 --net grid --name selenium-hub selenium/hub:4.15.0-20231110
$ docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub \
    --shm-size="2g" \
    -e SE_EVENT_BUS_PUBLISH_PORT=4442 \
    -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 \
    selenium/node-chrome:4.15.0-20231110
$ docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub \
    --shm-size="2g" \
    -e SE_EVENT_BUS_PUBLISH_PORT=4442 \
    -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 \
    selenium/node-edge:4.15.0-20231110
$ docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub \
    --shm-size="2g" \
    -e SE_EVENT_BUS_PUBLISH_PORT=4442 \
    -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 \
    selenium/node-firefox:4.15.0-20231110
   ```
   Das ergibt folgende Konfiguration. 
   ```mermaid

    graph LR
        subgraph SeleniumGrid
            A[Hub] -->|Manages| B((Node 1))
            A -->|Manages| C((Node 2))
            A -->|Manages| D((Node 3))
        end
        style A fill:#6f757a,stroke:#6f757a,stroke-width:2px; 
        style B fill:#43b02a,stroke:#6f757a,stroke-width:2px;
        style C fill:#43b02a,stroke:#6f757a,stroke-width:2px;
        style D fill:#43b02a,stroke:#6f757a,stroke-width:2px;
   
        
   ```


> Der Hub und die Nodes werden im selben Netzwerk erstellt 
und erkennen sich gegenseitig an ihrem Containernamen. 
In einem ersten Schritt muss ein Docker-Netzwerk erstellt werden.

### iii. Prüfen der Konfiguration
   Wer jetzt seine Konfiguration anschauen möchte, <br />
   kann auf http://localhost:4444 auf das Webinterface<br />
   des Selenium Grids zugreifen und sich dort unter dem Menü "Overview"<br />
   die zugeordneten Browser Nodes anzeigen lassen.<br />

## 3. Verbindung des Testprojekts mit dem Remote Webdriver
   Nachdem das Selenium Hub und die Nodes installiert sind können nun innerhalb des Projekts
   die Heruntergeladenen Treiber<br /> 
   vernachlässigt und durch [RemoteWebDriver](https://www.selenium.dev/documentation/webdriver/drivers/remote_webdriver/) ersetzt werden.
   
   > In diesem Projekt werden die zu testenden Webbrowser über eine Configuration im File geladen <br />
     **src/test/configuration/AvailableBrowser.java**
  
   ```Java
   URL linkHub = new URL("http://localhost:4444/wd/hub"); // angabe der URL zum WebDriver Hub
   FirefoxOptions options = new FirefoxOptions(); // festlegene der Browser Optionene
   return new RemoteWebDriver(linkHub, options); // instanzieren des Remote WebDrivers 
   ```
   

## 4. Konfiguration des Jenkins Jobs 
   
ToDO 
 






   






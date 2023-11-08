#language: de
  Funktionalität: Google Suchfunktionen

   Szenariogrundriss: Nutzer besucht google und tippt etwas in die Suche ein
      Angenommen der Nutzer öffnet den <browser>
      Und der Benutzer befindet sich auf <url>
      Und der Nutzer akzeptiert die Cookie-Meldung
      Und der Nutzer tippt folgenden Suchbegriff in die Suchzeile ein: <suchbegriff>
      Und der Nutzer klickt auf suchen
      Wenn der Nutzer das erste Ergebnis der in der Ergebnisliste wählt
      Dann soll geprüft werden ob der Title der Seite folgender ist: <titelwunsch>
     Beispiele:
       | browser  | url | suchbegriff | titelwunsch |
       | "chrome" | "https://www.google.de"     | "a"     | "A – Wikipedia" |
       | "chrome"| "https://www.google.de"     | "b"     | "Aktuelle Nachrichten \| BILD.de" |
       | "firefox" | "https://www.google.de"     | "a"     | "A – Wikipedia" |
       | "firefox"| "https://www.google.de"     | "b"     | "Aktuelle Nachrichten \| BILD.de" |









# Zoo Adoptions App

### Desktop App

Steps to run desktop app with Maven:

1. Clone repo

         git clone git@github.com:gregszalay/banking-utilities-backend.git

2. Build (in main source folder)

         mvn clean install

3. Navigate to desktpo app folder

         cd adoptions-desktop/

4. Run with Maven

          mvn clean install exec:java

### Webapp

Steps to run webapp with Maven:

1. Clone repo

         git clone git@github.com:gregszalay/banking-utilities-backend.git

2. Build (in main source folder)

         mvn clean install

3. Navigate to desktop app folder

         cd adoptions-web/

4. Run with Maven

          mvn clean install tomcat7:run-war

5. Open your favorite browser:

         http://localhost:8080/adoptions-web/

5. Example details for login:

         email: bela.toth@tothek.com
         password: bela
         
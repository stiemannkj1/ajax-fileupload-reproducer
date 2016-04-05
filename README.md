# [JAVASERVERFACES-4120](https://java.net/jira/browse/JAVASERVERFACES-4120) Reproducer

This project is a reproducer for [JAVASERVERFACES-4120](https://java.net/jira/browse/JAVASERVERFACES-4120): Ajax file
upload fails when `com.sun.faces.namespaceParameters` is set to `true`.

## Steps to reproduce:

1. Clone the project:

        git clone https://github.com/stiemannkj1/ajax-fileupload-reproducer.git

2. Build the project with maven:

        cd ajax-fileupload-reproducer && mvn clean install

3. Deploy the project to tomcat:

        cp target/ajax-fileupload-reproducer*.war $TOMCAT_HOME/webapps/

4. Verify that Ajax file upload works when `com.sun.faces.namespaceParameters` is not set:

    1. Navigate to the webapp in the browser:

        [http://localhost:8080/ajax-fileupload-reproducer-1.0-SNAPSHOT/faces/index.xhtml](http://localhost:8080/ajax-fileupload-reproducer-1.0-SNAPSHOT/faces/index.xhtml)

    2. Click the *Browse...* button.

    3. Select this **`Readme.md`**. The text of this file should appear in the browser.

5. Uncomment the following line in the **`web.xml`** to set `com.sun.faces.namespaceParameters` to `true`:

        <!--    <context-param>
            <param-name>com.sun.faces.namespaceParameters</param-name>
            <param-value>true</param-value>
        </context-param>-->

6. Rebuild the project:

        mvn clean install

7. Redeploy the webapp:

        rm -r $TOMCAT_HOME/webapps/ajax-fileupload-reproducer*; cp target/ajax-fileupload-reproducer*.war $TOMCAT_HOME/webapps/

8. Navigate to the webapp in the browser:

    [http://localhost:8080/ajax-fileupload-reproducer-1.0-SNAPSHOT/faces/index.xhtml](http://localhost:8080/ajax-fileupload-reproducer-1.0-SNAPSHOT/faces/index.xhtml)

9. Click the *Browse...* button.

10. Select this **`Readme.md`**.

If the bug still exists, an Ajax `POST` will occur, but the file text will not appear in the browser.

## Steps to test the fix:

1. Fetch and checkout my fixed Mojarra branch:

        cd mojarra~git/ &&
        git remote add stiemannkj1 https://github.com/stiemannkj1/mojarra.git &&
        git fetch stiemannkj1 ajax-fileupload-namespaced-params-fix &&
        git checkout ajax-fileupload-namespaced-params-fix

2. Build Mojarra according to the instructions [here](http://stackoverflow.com/questions/10964606/how-to-build-mojarra-from-source).

3. Change the version of Mojarra in the **`pom.xml`** to `2.2.14-SNAPSHOT`.

4. Follow `Steps to Reproduce` 5-10. The text of the **`README.md`** should now appear in the browser.
Vaadin 8 SQLDataProvider Example
=================================

[![Build Status](https://travis-ci.org/mvysny/vaadin8-sqldataprovider-example.svg?branch=master)](https://travis-ci.org/mvysny/vaadin8-sqldataprovider-example)

Demonstrates the use of a full-blown SQLDataProvider in a Vaadin 8 Grid.

Only requires a Servlet 3.0 container to run. Developed in a pure Java. Also demoes an auto-generated
Grid filter bar, therefore this example serves as a full replacement for Teppo Kurki's
[FilteringTable](https://vaadin.com/directory/component/filteringtable).

The project uses code from [Vaadin-on-Kotlin](http://vaadinonkotlin.eu). The Kotlin stdlib is
only included as a run-time dependency - this project contains no Kotlin code and doesn't even
run the Kotlin compiler.

The [live demo](https://vaadin8-sqldataprovider.herokuapp.com/) of this app runs on Heroku.

You can read more about the SQLDataProvider in the [Vaadin-on-Kotlin Databases Guide](http://www.vaadinonkotlin.eu/databases.html).
In short, SQLDataProvider uses [Sql2o](https://www.sql2o.org/) to map JDBC ResultSet rows to Java objects.
The mapping directly maps column name to a bean property name; to modify the mapping just
alias the columns in your SELECT command.

Workflow
========

To compile the entire project, run "mvn install".

To run the application, run "mvn jetty:run" and open http://localhost:8080/ .

> Jetty is a bit broken - at first it will show you a list of contexts which would indicate that the
  app has not been deployed properly. Just refresh the page a couple of times - Jetty will eventually
  be able to pick up the app and open it properly.

To produce a deployable production mode WAR:
- change productionMode to true in the servlet class configuration (nested in the UI class)
- run "mvn clean package"
- test the war file with "mvn jetty:run-war"

Client-Side compilation
-------------------------

The generated maven project is using an automatically generated widgetset by default. 
When you add a dependency that needs client-side compilation, the maven plugin will 
automatically generate it for you. Your own client-side customizations can be added into
package "client".

Debugging client side code
  - run "mvn vaadin:run-codeserver" on a separate console while the application is running
  - activate Super Dev Mode in the debug window of the application

Developing a theme using the runtime compiler
-------------------------

When developing the theme, Vaadin can be configured to compile the SASS based
theme at runtime in the server. This way you can just modify the scss files in
your IDE and reload the browser to see changes.

To use the runtime compilation, open pom.xml and comment out the compile-theme 
goal from vaadin-maven-plugin configuration. To remove a possibly existing 
pre-compiled theme, run "mvn clean package" once.

When using the runtime compiler, running the application in the "run" mode 
(rather than in "debug" mode) can speed up consecutive theme compilations
significantly.

It is highly recommended to disable runtime compilation for production WAR files.

Using Vaadin pre-releases
-------------------------

If Vaadin pre-releases are not enabled by default, use the Maven parameter
"-P vaadin-prerelease" or change the activation default value of the profile in pom.xml .

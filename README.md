VAADIN Security Playground
==========================

This is a project for researching various aspects related to VAADIN web framework related to
typically security problems of web applications (especially XSS and CSRF). It uses JSoup for
HTML sanitation.

https://github.com/ecki/VaadinSecurityPlayground

Feel free to copy this code for any purpose.

This Maven project currently builds a WAR file containing VAADIN and some libraries.

Demo Site: http://tomcat-ecki.rhcloud.com/

Greetings
Bernd

Buidling
--------

To build a deployable WAR file run

    > mvn verify
   
This will produce the file `target/webapp-war.war`.

You can also directly start a built-in Jetty 8 server, using:

    > mvn jetty:run

The application will then be reachable with http://localhost:8080/

If you use Eclipse, you can also use the run-on-server function with a Apache Tomcat 7.

This project can also be used as the source for a RedHat OpenShift (EWS2.0 aka Tomcat 7) source
project (you need to add your .openshift/ directory).

Screenshots
-----------

![Demo Site with Firefox](https://raw.github.com/wiki/ecki/VaadinSecurityPlayground/images/screenshot-demo-firefox.png)

See Also
--------

- http://vaadin.com
- https://www.openshift.com/developers
- https://www.owasp.org/index.php/XSS_Filter_Evasion_Cheat_Sheet
- https://www.owasp.org/index.php/XSS

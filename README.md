VAADIN Security Playground
==========================

This is a project for researching various aspects related to VAADIN web framework related to
typically security problems of web applications (especially XSS and CSRF).

https://github.com/ecki/VaadinSecurityPlayground

Feel free to copy this code for any purpose.

This Maven project currently builds a WAR file containing VAADIN and some libraries.

You can preview the app in the build in Jetty 8 by using:

    > mvn jetty:run

Alternatively deploying with run-on-server within Eclipse to Apache Tomcat 7 is also possible.

This project can also be used as the source for a RedHat OpenShift (EWS2.0 aka Tomcat 7) source
project.

Demo Site: http://tomcat-ecki.rhcloud.com/

*TODO*: Currently there is no filter implemented.

Bernd

- http://vaadin.com
- https://www.openshift.com/developers
- https://www.owasp.org/index.php/XSS_Filter_Evasion_Cheat_Sheet
- https://www.owasp.org/index.php/XSS
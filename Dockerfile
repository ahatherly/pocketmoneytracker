FROM jetty:9.4-alpine
COPY target/pocketmoneytracker.war /var/lib/jetty/webapps/


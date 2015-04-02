mvn clean install
cp -f ./target/RaspberryPiSwitch.war /var/lib/tomcat7/webapps/RaspberryPiSwitch.war
echo "deployment complete"
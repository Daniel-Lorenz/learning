# Set-Location .\srv
# mvn -f .\srv\pom.xml com.sap.cds:cds-maven-plugin:clean@cds.clean
mvn -f srv\pom.xml com.sap.cds:cds-maven-plugin:cds@cds.build
mvn -f srv\pom.xml com.sap.cds:cds-maven-plugin:generate@cds.generate
# Set-Location ..
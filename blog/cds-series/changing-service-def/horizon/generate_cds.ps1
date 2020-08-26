Set-Location .\srv
mvn com.sap.cds:cds-maven-plugin:cds@cds.build
mvn com.sap.cds:cds-maven-plugin:generate@cds.generate
Set-Location ..
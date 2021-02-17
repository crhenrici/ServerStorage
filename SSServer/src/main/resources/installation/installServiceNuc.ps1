param (
    [string]$profile = "h2db", $db_name = "prose_test", $db_uri = "192.168.1.18:3306", $db_user = "testadmin", $db_pw = "test"
)
nssm install ServerStorage "java"
nssm set ServerStorage AppDirectory C:\test
nssm set ServerStorage AppParameters "-jar lib/SSServer-0.0.1-SNAPSHOT.jar --spring.config.location=config/ --spring.profiles.active=$profile"
nssm set ServerStorage DisplayName ServerStorage
nssm set ServerStorage Description Service of ServerStorage with
nssm set ServerStorage AppEnvironmentExtra DB_NAME=$db_name
nssm set ServerStorage AppEnvironmentExtra DB_URI=$db_uri
nssm set ServerStorage AppEnvironmentExtra DB_USER=$db_user
nssm set ServerStorage AppEnvironmentExtra DB_PW=$db_pw

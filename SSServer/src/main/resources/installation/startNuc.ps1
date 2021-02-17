param (
    [string]$profile = "h2db",$db_name = "prose_test",$db_uri = "192.168.1.18:3306",$db_user = "testadmin",$db_pw = "test"
)
$env:DB_NAME=$db_name
$env:DB_URI=$db_uri
$env:DB_USER=$db_user
$env:DB_PW=$db_pw
java -jar lib/SSServer-0.0.1-SNAPSHOT.jar --spring.config.location=config/ --spring.profiles.active=$profile


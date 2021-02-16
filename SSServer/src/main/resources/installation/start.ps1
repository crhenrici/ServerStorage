param (
	[string]$profile = "dev",$DB_NAME,$DB_URI,$DB_USER,$DB_PW
)
java -jar lib/SSServer-0.0.1-SNAPSHOT.jar --spring.config.location=config/ --spring.profiles.active=$profile


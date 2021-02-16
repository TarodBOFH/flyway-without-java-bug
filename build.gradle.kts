@Suppress("PropertyName")
val `mysql-version`: String = "8.0.23"

repositories {
    mavenCentral()
}

plugins {
    id("org.flywaydb.flyway") version "7.5.3"
}

val flywayDrivers: Configuration by configurations.creating

dependencies {
    flywayDrivers("mysql:mysql-connector-java:${`mysql-version`}")
}

val db = System.getProperty("FLYWAY_DB") ?: "localhost"

flyway {
    locations = arrayOf("filesystem:${projectDir}/src/main/sql")

    // db-config
    url = "jdbc:mysql://$db?autoReconnect=true&useSSL=false&useUnicode=yes&characterEncoding=UTF-8"
    user = "root"
    password = ""

    configurations = arrayOf("flywayDrivers")
}

tasks.wrapper {
    distributionType = Wrapper.DistributionType.ALL
    gradleVersion = "6.8.2"
}

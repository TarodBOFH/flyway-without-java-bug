buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
        classpath("org.flywaydb:flyway-gradle-plugin:0-SNAPSHOT")
    }
}

@Suppress("PropertyName")
val `mysql-version`: String = "8.0.23"

repositories {
    mavenCentral()
}

apply(plugin = "org.flywaydb.flyway")

val flywayDrivers: Configuration by configurations.creating

dependencies {
    flywayDrivers("mysql:mysql-connector-java:${`mysql-version`}")
}

val db = System.getProperty("FLYWAY_DB") ?: "localhost"

// From kotlinDSLAccessorsReport (plugin applied from classpath)
fun Project.flyway(configure: Action<org.flywaydb.gradle.FlywayExtension>) = (this as ExtensionAware).extensions.configure("flyway", configure)

flyway {
    locations = arrayOf("filesystem:${projectDir}/src/main/sql")

    // db-config
    url = "jdbc:mysql://$db?autoReconnect=true&useSSL=false&useUnicode=yes&characterEncoding=UTF-8"
    user = "root"
    password = ""

    configurations = arrayOf("flywayDrivers")
}

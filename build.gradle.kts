plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.slf4j:slf4j-nop:2.0.11")
    implementation("org.slf4j:slf4j-nop:2.0.11")
    implementation("com.jayway.jsonpath:json-path:2.9.0")
    implementation("net.minidev:json-smart:2.5.0")
    implementation("net.iakovlev:timeshape:2024a.24")
    implementation("com.jayway.jsonpath", "json-path", "2.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.10.1")
    implementation("org.json:json:20240303")

}

tasks.test {
    useJUnitPlatform()
}

javafx {
    version = "22-ea+16"
    modules("javafx.controls", "javafx.fxml")
}

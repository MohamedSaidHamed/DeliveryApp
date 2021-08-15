import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.21"
	kotlin("plugin.spring") version "1.5.21"
	kotlin("plugin.jpa") version "1.5.21"
	kotlin("plugin.allopen") version "1.4.32"
}

group = "com.mycompany"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.hibernate:hibernate-core:5.5.6.Final")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.expediagroup:graphql-kotlin-spring-server:4.1.1")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.auth0:java-jwt:3.18.1")
	implementation("org.jetbrains.kotlin:kotlin-maven-noarg:1.5.21")
	compileOnly("org.jetbrains.kotlin:kotlin-maven-plugin:1.5.21")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test"){
		exclude("org.junit.vintage", "junit-vsintage-engine")
	}
	testImplementation("io.projectreactor:reactor-test:3.2.3.RELEASE")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.Embeddable")
	annotation("javax.persistence.MappedSuperclass")
}
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version ("7.1.2")
}

buildscript {

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencies {

    implementation(libs.kxml)
    implementation(libs.apache.commons.lang)
    implementation(libs.apache.commons.io)
}

tasks {
    named<ShadowJar>("shadowJar") {
        manifest {
            attributes["Main-Class"] = "com.codyi.xml2axml.test.Main"
        }
        isZip64 = true
        // Ignores the "-all" classifier shadow uses by default
        archiveClassifier.set("")
    }
    register("executable") {
        logger.info("Packaging ${project.name} into an executable binary...")
        dependsOn("shadowJar")

        doLast {
            val jarFile = File(
                project.buildDir,
                "libs/${project.name}-${project.version}.jar"
            )
            require(jarFile.exists()) { "shadowJar output file at ${jarFile.canonicalPath} does not exist!" }
            val executableFile = File(project.buildDir, "libs/${project.name}")

            executableFile.apply {
                writeText("#!/usr/bin/env bash\nexec java -jar \$0 \"\$@\"\n")
                appendBytes(jarFile.readBytes())
                setExecutable(true)
            }
        }
    }
}

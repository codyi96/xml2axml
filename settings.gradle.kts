@file:Suppress("UnstableApiUsage")

enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            alias("kxml").to("net.sf.kxml:kxml2:2.3.0")
            alias("apache-commons-lang").to("org.apache.commons:commons-lang3:3.12.0")
            alias("apache-commons-io").to("commons-io:commons-io:1.4")
        }
    }
}

rootProject.name = "xml2axml"

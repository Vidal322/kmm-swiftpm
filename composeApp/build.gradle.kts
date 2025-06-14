import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.swiftklib)

}

kotlin {
    listOf(
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
        iosTarget.compilations {
            val main by getting {
                cinterops {
                    create("helloworld")
                    create("afnetworking") {
                        packageName("org.example.pedro.afnetworking")
                        defFile(project.file("src/nativeInterop/cinterop/afnetworking.def"))
                        compilerOpts("-I${project.file("src/nativeInterop/cinterop/afnetworking_include").absolutePath}")
                    }
                }
            }
        }
    }
    
    sourceSets {
        
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
swiftklib {

    create("helloworld") {
        path = file("../iosApp/iosApp/helloworld")
        packageName("org.example.pedro.helloworld")
    }

}




// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    // Adicionando ksp
    alias(libs.plugins.google.devtools.ksp) apply false

    // Adicionando serialization
    alias(libs.plugins.kotlin.serialization) apply false

    // Corrigindo o erro do hilt
    alias(libs.plugins.hilt.android) apply false
}
androidApplication {
    namespace = "org.example.app"

    dependencies {
        implementation("org.apache.commons:commons-text:1.11.0")
        implementation(project(":utilities"))

        // MVVM (ViewModel + LiveData) - AndroidX Lifecycle (no Compose)
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.4")

        // XML-based screen navigation (Jetpack Navigation, Views/Fragments)
        implementation("androidx.navigation:navigation-fragment-ktx:2.8.0")
        implementation("androidx.navigation:navigation-ui-ktx:2.8.0")
    }
}

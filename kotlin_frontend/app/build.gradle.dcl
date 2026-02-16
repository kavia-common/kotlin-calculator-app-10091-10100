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

        // UI toolkit (AppCompat + Fragments + Material Components)
        implementation("androidx.appcompat:appcompat:1.7.0")
        implementation("androidx.fragment:fragment-ktx:1.8.5")
        implementation("com.google.android.material:material:1.12.0")
        implementation("androidx.core:core-ktx:1.13.1")
    }
}

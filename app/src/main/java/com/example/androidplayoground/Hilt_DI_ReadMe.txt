For Setting up Hilt DI
1. Add Hilt Dependencies
2. Enable Hilt in your Application class
    @HiltAndroidApp annotation in Application class to trigger Hilt's code generation

    Note : Don't forget to declare this Application class in the AndroidManifest.xml
3. Define a Module for providing dependencies
    In Hilt, dependencies are provided via modules using @Module and @InstallIn annotations.
    These modules define how Hilt should provide dependencies for various components

4. Inject dependencies into Views (Activity, Fragment)


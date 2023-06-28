# library

[![](https://jitpack.io/v/MakMoinee/library.svg)](https://jitpack.io/#MakMoinee/library)

## Installation
## Step 1
- Add the JitPack repository to your settings.gradle
```java
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```
## Step 2
- Add the dependency in your module build.gradle
- You can get the tag from latest release number
```java
dependencies {
	        implementation 'com.github.MakMoinee:library:Tag'
}
```

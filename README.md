# library

[![](https://jitpack.io/v/MakMoinee/library.svg)](https://jitpack.io/#MakMoinee/library)

## Installation

## Step 1

- Add the JitPack repository to your settings.gradle

```java
dependencyResolutionManagement{
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories{
        google()
        jcenter()
        maven{url'https://jitpack.io'}
        }
}
```

## Step 2

- Add the dependency in your module build.gradle
- You can get the tag from latest release number

```java
android {
        useLibrary 'org.apache.http.legacy'
        compileSdk 34
        }
        
dependencies{
        //required dependencies
    implementation 'com.android.volley:volley:1.2.1'
    implementation 'org.projectlombok:lombok:1.18.36'
    annotationProcessor 'org.projectlombok:lombok:1.18.36'
    testImplementation 'org.projectlombok:lombok:1.18.36'
    androidTestImplementation 'org.projectlombok:lombok:1.18.36'
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.11.1'
    implementation 'org.apache.httpcomponents.client5:httpclient5:5.4.1'
    implementation 'com.google.zxing:core:3.3.2'
    implementation 'com.google.zxing:javase:3.3.2'
    implementation 'at.favre.lib:bcrypt:0.10.2'
    implementation platform('com.google.firebase:firebase-bom:32.5.0')
    implementation 'com.google.firebase:firebase-firestore'
    implementation 'com.google.firebase:firebase-database'
    implementation 'com.google.firebase:firebase-messaging'
    implementation 'com.sun.mail:jakarta.mail:2.0.1'
    implementation 'jakarta.activation:jakarta.activation-api:2.1.3'
}
```


## Available Classes,Functions,Models,Interfaces:

- [FirestoreRequest](https://github.com/MakMoinee/library/#FirestoreRequest)
- [FirestoreRequestBody](/#FirestoreRequestBody)
- [FirestoreRequestBodyBuilder](/#FirestoreRequestBodyBuilder)
- [MapForm](/#MapForm)
- [FirestoreListener](/#FirestoreListener)
- [HashPass](/#hashpass-encrypt-and-verify-password)
- [LoginPref](/#loginpref-on-the-go-sharedpreference)

## FirestoreRequest
- [findAll()](/#FindAll)


## FindAll
```java
FirestoreRequest request=new FirestoreRequest();
FirestoreRequestBody body=new FirestoreRequestBody.FirestoreRequestBody()
        .setCollectionName("collection_name") // replace collection name
        .setWhereFromField("field_to_look_up") // replace with field name you're looking up
        .setWhereValueField("value_to_look_up") // replace with value you're looking up
        .build();
request.findAll(body,new ody,new FirestoreListener(){
        @Override
        public<T> void onSuccess(T any){
            // do something
        }
        
        @Override
        public void onError(Error error){
              // do something
        }
})
```

## FirestoreRequestBody
```java
FirestoreRequestBody body = new FirestoreRequestBody();
```

## FirestoreRequestBodyBuilder
```java
FirestoreRequestBody body = new FirestoreRequestBody.FirestoreRequestBodyBuilder()
        .setCollectionName("collection_name") // replace collection name
        .setWhereFromField("field_to_look_up") // replace with field name you're looking up
        .setWhereValueField("value_to_look_up") // replace with value you're looking up
        .setParams(object) // replace with your Map<String,Object> data;
        .setEmail(email) // replace email, used for veryfing login using email
        .setDocumentID(documentID) // replace with desired documentID
        .build();
```
## MapForm
```java
FirestoreRequestBody body = new FirestoreRequestBody();
Map<String,Object> params = MapForm.convertObjectToMap(body); // convert data model to Map<String,Object>
```

## FirestoreListener
```java
FirestoreListener listener = new FirestoreListener(){
        @Override
        public<T> void onSuccess(T any){
            // do something
        }
        
        @Override
        public void onError(Error error){
              // do something
        }
};
```

## HashPass (Encrypt and Verify Password)
```java

// Encrypt password
String hashPass = new HashPass().makeHashPassword(password);

// Verify password 
boolean isEqualPassword = hashPass.verifyPassword(stringPassword, hashPassword);

```

## LoginPref (On the go sharedpreference)
```java

    new LoginPref(context).storeLogin(dataModel);

    // another example
    Users users = new Users();
    new LoginPref(context).storeLogin(users);

    // clear data in sharedpreference
    new LoginPref(context).clearLogin();


    // add your own methods by extending LoginPref
    public class UserPref extends LoginPref{
        // add more methods
    }

```



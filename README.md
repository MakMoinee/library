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
dependencies{
        implementation'com.github.MakMoinee:library:Tag'
        }
```

## Available Classes,Functions,Models,Interfaces:

- [FirestoreRequest](https://github.com/MakMoinee/library/#FirestoreRequest)
- FirestoreRequestBody
- FirestoreRequestBodyBuilder
- MapForm

## FirestoreRequest
- findAll()(https://github.com/MakMoinee/library/#FindAll)


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


# library

[![](library.svg)](https://jitpack.io/#MakMoinee/library)

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
    implementation "androidx.sqlite:sqlite:2.4.0"
    implementation "androidx.sqlite:sqlite-framework:2.4.0"
    implementation 'org.nanohttpd:nanohttpd:2.3.1'
            
    implementation 'com.github.MakMoinee:library:$releaseVersion'
 }
```


## Available Packages and Classes

This Android library provides a comprehensive set of packages for common Android development tasks. Here's a complete overview of all available packages and their classes:

### 📦 Package: `com.github.MakMoinee.library.common`
**Common utilities and helper classes**

- **`Detokens`** - Static class for managing device tokens
- **`Enums`** - Contains type constants for QR code generation (TEXT, EMAIL, PHONE, SMS, CONTACT, LOCATION)
- **`MapForm`** - Utility class for converting objects to Map<String, Object> and ContentValues

### 📦 Package: `com.github.MakMoinee.library.configs`
**Configuration management**

- **`GetMyProperty`** - Utility class for reading configuration properties from assets files

### 📦 Package: `com.github.MakMoinee.library.dialogs`
**Custom dialog components**

- **`MyDialog`** - Custom ProgressDialog with default settings and customizable messages

### 📦 Package: `com.github.MakMoinee.library.interfaces`
**Interface definitions for callbacks and listeners**

- **`DefaultBaseListener`** - Base interface with onSuccess and onError methods
- **`DefaultEventListener`** - Event listener interface
- **`FirestoreListener`** - Extends DefaultBaseListener for Firestore operations
- **`LocalVolleyRequestListener`** - Listener for Volley HTTP requests
- **`QRCodeLocal`** - Interface for QR code generation
- **`RealtimeDbListener`** - Listener for Firebase Realtime Database operations

### 📦 Package: `com.github.MakMoinee.library.models`
**Data models and request/response objects**

- **`DeviceToken`** - Model for device token management with builder pattern
- **`FirestoreRequestBody`** - Request body model for Firestore operations with builder pattern
- **`LocalVolleyRequestBody`** - Request body model for Volley HTTP requests
- **`RealtimeDBBody`** - Request body model for Firebase Realtime Database operations

### 📦 Package: `com.github.MakMoinee.library.preference`
**SharedPreferences management**

- **`LoginPref`** - Comprehensive SharedPreferences wrapper for storing and retrieving user login data

### 📦 Package: `com.github.MakMoinee.library.services`
**Core service classes for various functionalities**

- **`Demail`** - Email service utilities
- **`FirestoreRequest`** - Complete Firestore CRUD operations (findAll, insertUniqueData, insertOnly, upsert, delete)
- **`HashPass`** - Password hashing and verification using BCrypt
- **`LocalAndroidServer`** - Local server implementation
- **`LocalIP`** - IP address utilities
- **`LocalVolleyRequest`** - HTTP request service using Volley (GET, POST, PUT, Multipart)
- **`MSqliteDBHelper`** - SQLite database helper
- **`MultipartRequest`** - Multipart form data request handler
- **`NotificationUtil`** - Android notification management
- **`QRCodeLocalImpl`** - QR code generation implementation
- **`RealtimeDbRequest`** - Firebase Realtime Database operations
- **`Utils`** - General utility functions (date formatting, network checking, navigation setup, etc.)

### 📦 Package: `com.github.MakMoinee.library.views`
**Custom view components**

- **`JoyStickView`** - Custom joystick control view with touch interaction

### 📦 Package: `com.github.MakMoinee.library.widgets`
**Reusable widget components**

- **`DatePickerFragment`** - Custom date picker dialog fragment

## Key Features

- **Firebase Integration**: Complete Firestore and Realtime Database support
- **HTTP Networking**: Volley-based HTTP client with retry policies
- **Security**: Password hashing with BCrypt
- **UI Components**: Custom joystick, date picker, and dialog components
- **Utilities**: Date formatting, network checking, object conversion, and more
- **Notifications**: Android notification management
- **QR Code Generation**: Local QR code generation with various formats
- **Data Persistence**: SQLite and SharedPreferences helpers

## Usage Examples

### 🔥 Firestore Operations

#### FirestoreRequest - Complete CRUD Operations
```java
FirestoreRequest request = new FirestoreRequest();

// Find all documents
FirestoreRequestBody body = new FirestoreRequestBody.FirestoreRequestBodyBuilder()
        .setCollectionName("users")
        .setWhereFromField("email")
        .setWhereValueField("user@example.com")
        .build();

request.findAll(body, new FirestoreListener() {
    @Override
    public <T> void onSuccess(T any) {
        // Handle successful query
    }
    
    @Override
    public void onError(Error error) {
        // Handle error
    }
});

// Insert unique data (checks for duplicates)
request.insertUniqueData(body, listener);

// Insert data without duplicate checking
request.insertOnly(body, listener);

// Update or insert data
request.upsert(body, listener);

// Delete document
request.delete(body, listener);
```

#### FirestoreRequestBody Builder
```java
FirestoreRequestBody body = new FirestoreRequestBody.FirestoreRequestBodyBuilder()
        .setCollectionName("collection_name")
        .setWhereFromField("field_to_look_up")
        .setWhereValueField("value_to_look_up")
        .setParams(dataMap) // Map<String,Object> data
        .setEmail("user@example.com")
        .setDocumentID("document_id")
        .build();
```

### 🔐 Security & Authentication

#### HashPass - Password Encryption
```java
HashPass hashPass = new HashPass();

// Encrypt password
String hashedPassword = hashPass.makeHashPassword("userPassword");

// Verify password
boolean isValid = hashPass.verifyPassword("userPassword", hashedPassword);
```

#### LoginPref - SharedPreferences Management
```java
LoginPref loginPref = new LoginPref(context);

// Store login data
Map<String, Object> userData = new HashMap<>();
userData.put("email", "user@example.com");
userData.put("userId", "12345");
loginPref.storeLogin(userData);

// Retrieve data
String email = loginPref.getStringItem("email");
int userId = loginPref.getIntItem("userId");

// Clear all data
loginPref.clearLogin();
```

### 🌐 HTTP Networking

#### LocalVolleyRequest - HTTP Operations
```java
LocalVolleyRequest volleyRequest = new LocalVolleyRequest(context);

// GET Request
LocalVolleyRequestBody body = new LocalVolleyRequestBody();
body.setUrl("https://api.example.com/users");
volleyRequest.sendJSONGetRequest(body, new LocalVolleyRequestListener() {
    @Override
    public void onSuccessJSON(JSONObject response) {
        // Handle JSON response
    }
    
    @Override
    public void onError(Error error) {
        // Handle error
    }
});

// POST Request
body.setBody(jsonObject); // JSONObject data
volleyRequest.sendJSONPostRequest(body, listener);

// Multipart Request
body.setBodyMap(formDataMap); // Map<String, String> form data
volleyRequest.sendMultipartJSONPostRequest(body, listener);
```

### 🔔 Notifications

#### NotificationUtil - Android Notifications
```java
NotificationUtil notificationUtil = new NotificationUtil(context);

Intent intent = new Intent(context, MainActivity.class);
notificationUtil.showNotification(
    "Title", 
    "Message", 
    1, // notification ID
    R.drawable.ic_notification, // icon
    intent
);
```

### 📱 QR Code Generation

#### QRCodeLocalImpl - QR Code Creation
```java
// Generate QR code for text
QRCodeLocalImpl qrCode = new QRCodeLocalImpl("Hello World", Enums.Type.TEXT, 200);
Bitmap qrBitmap = qrCode.getBitmap();

// Generate QR code for location
Bundle locationBundle = new Bundle();
locationBundle.putFloat("LAT", 40.7128f);
locationBundle.putFloat("LONG", -74.0060f);
QRCodeLocalImpl locationQR = new QRCodeLocalImpl("", Enums.Type.LOCATION, 200);
```

### 🎮 Custom Views

#### JoyStickView - Custom Joystick Control
```java
JoyStickView joystick = findViewById(R.id.joystick);
joystick.setJoystickListener(new JoyStickView.JoystickListener() {
    @Override
    public void onJoystickMoved(float xPercent, float yPercent) {
        // Handle joystick movement
        // xPercent and yPercent range from -1 to 1
    }
});
```

#### DatePickerFragment - Custom Date Picker
```java
DatePickerFragment datePicker = new DatePickerFragment(new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // Handle date selection
    }
});
datePicker.show(getSupportFragmentManager(), "datePicker");
```

### 🛠️ Utilities

#### MapForm - Object Conversion
```java
// Convert object to Map
MyDataModel data = new MyDataModel();
Map<String, Object> params = MapForm.convertObjectToMap(data);

// Convert object to ContentValues (for SQLite)
ContentValues contentValues = MapForm.toContentValues(data);
```

#### Utils - General Utilities
```java
// Check internet connectivity
boolean isOnline = Utils.isInternetAvailable(context);

// Get current date
String currentDate = Utils.getCurrentDate("yyyy-MM-dd HH:mm:ss");

// Convert drawable to bitmap
Bitmap bitmap = Utils.drawableToBitmap(drawable);

// Send FCM message
Utils.sendMessage("Title", "Body", "device_token");
```

### 📧 Email Services

#### Demail - Email Utilities
```java
// Email service implementation
Demail emailService = new Demail();
// Configure and send emails
```

### 🗄️ Database

#### MSqliteDBHelper - SQLite Operations
```java
MSqliteDBHelper dbHelper = new MSqliteDBHelper(context);
// Perform SQLite database operations
```

### ⚙️ Configuration

#### GetMyProperty - Configuration Management
```java
// Read from assets/config.properties
String apiKey = GetMyProperty.getStringConfig("api_key", "config.properties", context);

// Read from InputStream
InputStream inputStream = context.getAssets().open("config.properties");
Properties properties = GetMyProperty.getConfigProperties(inputStream);
```



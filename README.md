[![](https://jitpack.io/v/SothearaOeurn/multi_permission.svg)](https://jitpack.io/#SothearaOeurn/multi_permission)

# Request Multi Permission
This library can request multi permission.

## Installation


1.Add it in your root build.gradle at the end of repositories:

```bash
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
  }
```
2.Add the dependency
```bash
  dependencies {
	      implementation 'com.github.SothearaOeurn:multi_permission:1.0.0'
  }
```

## Usage/AndroidManifest

```javascript
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

## Usage/Code

```javascript
package com.example.mypermission

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.thearapermission.utils.PermissionUtils

class MainActivity : AppCompatActivity() {

    private val list = listOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    private val permissionsRequestCode = 123
    private lateinit var permissionsUtils: PermissionUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        permissionsUtils = PermissionUtils(this, list, permissionsRequestCode)
    }

    fun onPermission(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) permissionsUtils.checkPermissions()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsUtils.processPermissionsResult(requestCode, permissions, grantResults)
    }
}
```



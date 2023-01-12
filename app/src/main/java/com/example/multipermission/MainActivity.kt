package com.example.multipermission

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.thearapermission.utils.PermissionUtils

class MainActivity : AppCompatActivity() {
    private val list = listOf(
        Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
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
package com.example.thearapermission.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionUtils(
    private val activity: Activity, private val list: List<String>, private val code: Int
) {

    // Check permissions at runtime
    fun checkPermissions() {
        if (isPermissionsGranted() != PackageManager.PERMISSION_GRANTED) {
            showAlert()
        } else {
            Toast.makeText(activity, "Permissions already granted.", Toast.LENGTH_SHORT).show()
        }
    }

    // Check permissions status
    private fun isPermissionsGranted(): Int {
        // PERMISSION_GRANTED : Constant Value: 0
        // PERMISSION_DENIED : Constant Value: -1
        var counter = 0
        for (permission in list) {
            counter += ContextCompat.checkSelfPermission(activity, permission)
        }
        return counter
    }

    // Find the first denied permission
    private fun deniedPermission(): String {
        for (permission in list) {
            if (ContextCompat.checkSelfPermission(
                    activity, permission
                ) == PackageManager.PERMISSION_DENIED
            ) return permission
        }
        return ""
    }

    // Show alert dialog to request permissions
    private fun showAlert() {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Need permissions")
        builder.setMessage("Some permissions are required to do the task.")
        builder.setPositiveButton("OK") { _, _ -> requestPermissions() }
        builder.setNeutralButton("Cancel", null)
        val dialog = builder.create()
        dialog.show()
    }

    // Request the permissions at run time
    private fun requestPermissions() {
        val permission = deniedPermission()
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            // Show an explanation asynchronously
            Toast.makeText(activity, "Should show an explanation.", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(activity, list.toTypedArray(), code)
        }
    }

    fun processPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ): Boolean {
        var result = 0
        if (grantResults.isNotEmpty()) {
            for (item in grantResults) {
                result += item
            }
        }
        return if (result == PackageManager.PERMISSION_GRANTED) {
            Log.v("check_permission", "true")
            true
        } else {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri: Uri = Uri.fromParts("package", activity.packageName, null)
            intent.data = uri
            activity.startActivity(intent)
            Log.v("check_permission", "false")
            false
        }
    }
}
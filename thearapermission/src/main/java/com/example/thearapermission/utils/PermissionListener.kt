package com.example.thearapermission.utils

interface PermissionListener {
    fun onPermissionGranted()
    fun onPermissionDenied()
}
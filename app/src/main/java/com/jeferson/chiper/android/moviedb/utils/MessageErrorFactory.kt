package com.jeferson.chiper.android.moviedb.utils

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.jeferson.chiper.android.moviedb.R

class MessageErrorFactory {
    companion object {
        const val GENERIC_ERROR = 0
        const val NETWORK_ERROR = 1
        const val BAD_REQUEST = 400
        const val UNAUTHORIZED = 401
        const val FORBIDDEN = 403
        const val NOT_FOUND = 404
        const val METHOD_NOT_ALLOWED = 405
        const val SERVER_ERROR = 500
        const val INVALID_SERVICE = 501
        const val SERVICE_OFFLINE = 503
    }

    fun showSnackBar(context: Context, type: Int?, view: View) {
        when (type) {
            NETWORK_ERROR -> {
                val snackBar = Snackbar.make(
                    view,
                    context.getString(R.string.text_network_error),
                    Snackbar.LENGTH_LONG
                )

                snackBar.view.setBackgroundColor(Color.parseColor("#fc645c"))
                snackBar.show()
            }
            BAD_REQUEST -> {
                val snackBar = Snackbar.make(
                    view,
                    context.getString(R.string.text_bad_request_error),
                    Snackbar.LENGTH_LONG
                )

                snackBar.view.setBackgroundColor(Color.parseColor("#fc645c"))
                snackBar.show()
            }
            UNAUTHORIZED -> {
                val snackBar = Snackbar.make(
                    view,
                    context.getString(R.string.text_unauthorized_error),
                    Snackbar.LENGTH_LONG
                )

                snackBar.view.setBackgroundColor(Color.parseColor("#ffb657"))
                snackBar.show()
            }
            FORBIDDEN -> {
                val snackBar = Snackbar.make(
                    view,
                    context.getString(R.string.text_generic_error),
                    Snackbar.LENGTH_LONG
                )

                snackBar.view.setBackgroundColor(Color.parseColor("#fc645c"))
                snackBar.show()
            }
            NOT_FOUND -> {
                val snackBar = Snackbar.make(
                    view,
                    context.getString(R.string.text_not_found_error),
                    Snackbar.LENGTH_LONG
                )

                snackBar.view.setBackgroundColor(Color.parseColor("#ffb657"))
                snackBar.show()
            }
            METHOD_NOT_ALLOWED -> {
                val snackBar = Snackbar.make(
                    view,
                    context.getString(R.string.text_not_allowed_error),
                    Snackbar.LENGTH_LONG
                )

                snackBar.view.setBackgroundColor(Color.parseColor("#ffb657"))
                snackBar.show()
            }
            SERVER_ERROR -> {
                val snackBar = Snackbar.make(
                    view,
                    context.getString(R.string.text_server_error),
                    Snackbar.LENGTH_LONG
                )

                snackBar.view.setBackgroundColor(Color.parseColor("#fc645c"))
                snackBar.show()
            }
            INVALID_SERVICE -> {
                val snackBar = Snackbar.make(
                    view,
                    context.getString(R.string.text_invalid_service),
                    Snackbar.LENGTH_LONG
                )

                snackBar.view.setBackgroundColor(Color.parseColor("#fc645c"))
                snackBar.show()
            }
            SERVICE_OFFLINE -> {
                val snackBar = Snackbar.make(
                    view,
                    context.getString(R.string.text_service_offline),
                    Snackbar.LENGTH_LONG
                )

                snackBar.view.setBackgroundColor(Color.parseColor("#fc645c"))
                snackBar.show()
            }
            else -> {
                Log.e("ERROR FACTORY", "Se debe agregar el mensaje de error para el código $type")

                val snackBar = Snackbar.make(
                    view,
                    context.getString(R.string.text_generic_error),
                    Snackbar.LENGTH_LONG
                )

                snackBar.view.setBackgroundColor(Color.parseColor("#fc645c"))
                snackBar.show()
            }
        }
    }
}
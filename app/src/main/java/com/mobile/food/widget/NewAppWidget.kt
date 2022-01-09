package com.mobile.food.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.navigation.NavDeepLinkBuilder
import com.mobile.food.MainActivity
import com.mobile.food.R


/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in [NewAppWidgetConfigureActivity]
 */
class NewAppWidget : AppWidgetProvider() {
    companion object {
        val NEWFALG = 0
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {

        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            val remoteViews =
                RemoteViews(context.packageName, R.layout.new_app_widget)
            val configIntent = Intent(context, MainActivity::class.java)
            configIntent.flags = NEWFALG

            val pendingIntent = NavDeepLinkBuilder(context)
                .setComponentName(MainActivity::class.java)
                .setGraph(R.navigation.mobile_navigation)
                .setDestination(R.id.navigation_dashboard)
                .createPendingIntent()
            remoteViews.setOnClickPendingIntent(
                R.id.appwidget_text,
                pendingIntent
            )
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        }
    }


    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created

    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}


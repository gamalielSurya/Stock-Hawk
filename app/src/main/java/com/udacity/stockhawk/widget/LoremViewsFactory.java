package com.udacity.stockhawk.widget;


import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.PrefUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LoremViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private List<String> stocks = new ArrayList<>();

    private Context ctxt=null;
    private int appWidgetId;

    public LoremViewsFactory(Context ctxt, Intent intent) {

        Set<String> stockPref = PrefUtils.getStocks(ctxt);

        for (String stock : stockPref) {
            stocks.add(stock);
        }


        this.ctxt=ctxt;
        appWidgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
        // no-op
    }

    @Override
    public void onDestroy() {
        // no-op
    }

    @Override
    public int getCount() {
        /*return(items.length);*/
        return (stocks.size());
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews row=new RemoteViews(ctxt.getPackageName(),
                R.layout.row);

        row.setTextViewText(android.R.id.text1, stocks.get(position).toString());

        Intent i=new Intent();
        Bundle extras=new Bundle();

        extras.putString(WidgetProvider.EXTRA_WORD, stocks.get(position).toString());
        i.putExtras(extras);
        row.setOnClickFillInIntent(android.R.id.text1, i);

        return(row);
    }

    @Override
    public RemoteViews getLoadingView() {
        return(null);
    }

    @Override
    public int getViewTypeCount() {
        return(1);
    }

    @Override
    public long getItemId(int position) {
        return(position);
    }

    @Override
    public boolean hasStableIds() {
        return(true);
    }

    @Override
    public void onDataSetChanged() {
        // no-op
    }
}

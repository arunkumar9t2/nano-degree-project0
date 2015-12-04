package com.arun.udacity.project0;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the string array resource
        final String[] appNames = getResources().getStringArray(R.array.app_names);


        ListView applauncherList = (ListView) findViewById(R.id.launcher_list);
        applauncherList.setAdapter(new AppListAdapter(
                this,
                appNames
        ));

        applauncherList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String textToToast = String.format(getResources().getString(R.string.toast_msg),
                        appNames[position]);
                Toast.makeText(MainActivity.this, textToToast, Toast.LENGTH_SHORT).show();
            }
        });
    }

    static private class ViewHolder {
        CardView view;
        TextView appName;
    }

    private class AppListAdapter extends ArrayAdapter<String> {
        final Context context;
        final int layoutResourceId;
        final String[] apps;

        public AppListAdapter(Context context, Object[] apps) {
            super(context, R.layout.list_item_template, (String[]) apps);
            this.layoutResourceId = R.layout.list_item_template;
            this.context = context;
            this.apps = (String[]) apps;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder;
            if (row == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(layoutResourceId, parent, false);
                holder = new ViewHolder();
                holder.appName = (TextView) row.findViewById(R.id.list_item_text);
                holder.view = (CardView) row.findViewById(R.id.card_view);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            holder.appName.setText(apps[position]);
            // Only for the last item apply special coloring
            if (position == apps.length - 1) {
                holder.view.setCardBackgroundColor(context
                        .getResources().getColor(R.color.highlightColor));
            }
            return row;
        }
    }
}

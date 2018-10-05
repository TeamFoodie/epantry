package com.example.teamfoodie.epantry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.teamfoodie.R;
import com.example.teamfoodie.models.Ingredient;
import com.example.teamfoodie.models.Procedure;

import java.util.List;

/*
This class is used to create the outline for the recipe list
outlining variable attributes
in which to streamline the performance
 */
public class CustomProcedureAdapter extends BaseAdapter {

    private List<Procedure> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomProcedureAdapter(Context aContext, List<Procedure> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.procedure_list, null);
            holder = new ViewHolder();
            holder.procedres = (TextView) convertView.findViewById(R.id.procedure_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Procedure procedure = this.listData.get(position);
        int textSize = procedure.getTextSize();
        holder.procedres.setText(procedure.getStep());

        return convertView;
    }

    static class ViewHolder {
        TextView procedres;
    }

}

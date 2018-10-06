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
import com.example.teamfoodie.models.Recipe;

import java.util.List;

/*
This class is used to create the outline for the recipe list
outlining variable attributes
in which to streamline the performance
 */
public class CustomProcedureAdapter extends BaseAdapter {

    private List<Object> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private int listType;

    public CustomProcedureAdapter(Context aContext, List<Object> listData, int type) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
        this.listType = type;
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

        if(listType == 1){
            Object obj = this.listData.get(position);
            Ingredient ingredient = (Ingredient) obj;
            holder.procedres.setText(ingredient.toString());

        }
        else if(listType == 2){
            Object obj = this.listData.get(position);
            Procedure procedure = (Procedure) obj;
            holder.procedres.setText(procedure.getStep());
        }


        return convertView;
    }

    public void setListType(int i,List<Object> listData){
        this.listData = listData;
        this.listType = i;
    }
    static class ViewHolder {
        TextView procedres;
    }


}

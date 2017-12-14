package fryer.kohler.familymapapp;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.function.Consumer;

import familymapapp.Modal.DataTree;
import familymapapp.Modal.DetailsRowDataObject;

public class FilterRowAdapter extends RecyclerView.Adapter<FilterRowViewHolder> {

    private final LayoutInflater inflater;
    private View view;
    private FilterRowViewHolder holder;

    public FilterRowAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return DataTree.getInstance().eventFilters.size()-1;
    }

    @Override
    public FilterRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.filter_row, parent, false);
        holder = new FilterRowViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FilterRowViewHolder holder, int position) {
        String filterName = DataTree.indexForEventFilters.get(position);
        boolean filterActive = DataTree.eventFilters.get(filterName) == 1 ? true : false;
        holder.filterSwitch.setChecked(filterActive);
        holder.filterSwitch.setText(filterName);
    }

}

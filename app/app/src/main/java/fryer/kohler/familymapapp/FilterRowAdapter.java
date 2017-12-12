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

/**
 * Created by programmer on 12/9/17.
 */

public class FilterRowAdapter extends RecyclerView.Adapter<FilterRowViewHolder> {

    private final LayoutInflater inflater;
    private View view;
    private FilterRowViewHolder holder;

    public FilterRowAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
//        Log.d("debug", "amount : " + DataTree.getInstance().getActiveEventTypeSize());
        //return DataTree.getInstance().getActiveEventTypeSize();
        return 0;
    }

    @Override
    public FilterRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.filter_row, parent, false);
        holder = new FilterRowViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FilterRowViewHolder holder, int position) {
//        String eventTypeKey = DataTree.getInstance().getActiveEventTypeKey(position);
//        boolean checked = DataTree.getInstance().getActiveEventType(eventTypeKey) == 0 ? true : false;
//        holder.filterSwitch.setChecked(checked);
//        holder.filterSwitch.setText(eventTypeKey);
    }

}

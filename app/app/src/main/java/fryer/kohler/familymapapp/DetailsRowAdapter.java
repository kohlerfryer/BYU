package fryer.kohler.familymapapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.function.Consumer;

import familymapapp.Modal.DetailsRowDataObject;


/**
 * Created by kittykatt on 12/4/17.
 */

public class DetailsRowAdapter extends RecyclerView.Adapter<DetailsRowViewHolder> {

    private final LayoutInflater inflater;
    private View view;
    private DetailsRowViewHolder holder;
    private Context context;
    private ArrayList<DetailsRowDataObject> dataObjects;
    private Consumer<String> onClickCallback;

    public void setRowContent(ArrayList<DetailsRowDataObject> dataObjects, Consumer<String> onClickCallback){
        //this.list_members=list_members;
        this.onClickCallback = onClickCallback;
        this.dataObjects = dataObjects;
        notifyItemRangeChanged(0, dataObjects.size()-1);

    }

    public DetailsRowAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return this.dataObjects.size();
    }

    @Override
    public DetailsRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.details_row, parent, false);
        holder = new DetailsRowViewHolder(view, this.onClickCallback);
        return holder;
    }

    @Override
    public void onBindViewHolder(DetailsRowViewHolder holder, int position) {
        //position in array of events. holder.title = event[position].type ....
        DetailsRowDataObject dataObject = dataObjects.get(position);
        holder.first_field.setText(dataObject.getFirstRow());
        holder.second_field.setText(dataObject.getSecondRow());
        holder.icon.setImageResource(dataObject.getIcon());
        holder.onclickCallBackArgument = dataObject.getId();
    }

}

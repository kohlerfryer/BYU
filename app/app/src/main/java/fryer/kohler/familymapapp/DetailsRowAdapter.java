package fryer.kohler.familymapapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import familymapapp.Modal.Event;
import familymapapp.UTIL.Triplet;

/**
 * Created by kittykatt on 12/4/17.
 */

public class DetailsRowAdapter extends RecyclerView.Adapter<DetailsRowViewHolder> {

//    private ArrayList<CustomPojo> list_members=new ArrayList<>();
    private final LayoutInflater inflater;
    private View view;
    private DetailsRowViewHolder holder;
    private Context context;
    private ArrayList<Triplet<String, String, Integer>> triplets;

    public void setRowContent(ArrayList<Triplet<String, String, Integer>> triplets){
        //this.list_members=list_members;
        notifyItemRangeChanged(0, triplets.size()-1);

    }

    public DetailsRowAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return this.triplets.size();
    }

    @Override
    public DetailsRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.details_row, parent, false);
        holder = new DetailsRowViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DetailsRowViewHolder holder, int position) {
        //position in array of events. holder.title = event[position].type ....
        holder.first_field.setText(triplets.get(position).getFirst());
        holder.second_field.setText(triplets.get(position).getSecond());
        holder.icon.setBackground(triplets.get(position).getThird());
    }

}

package fryer.kohler.familymapapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import familymapapp.Modal.Event;

/**
 * Created by kittykatt on 12/4/17.
 */

public class DetailsRowAdapter extends RecyclerView.Adapter<DetailsRowViewHolder> {

//    private ArrayList<CustomPojo> list_members=new ArrayList<>();
    private final LayoutInflater inflater;
    View view;
    DetailsRowViewHolder holder;
    private Context context;

    public void setRowContent(int fakeevents){
        //this.list_members=list_members;
        notifyItemRangeChanged(0, fakeevents);

    }

    public DetailsRowAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return 5;
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
        holder.user_name.setText("hello");
    }

}

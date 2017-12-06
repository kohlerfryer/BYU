package fryer.kohler.familymapapp;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kittykatt on 12/4/17.
 */

public class DetailsRowViewHolder extends ViewHolder implements View.OnClickListener{
    TextView first_field;
    TextView second_field;
    ImageView icon;


    public DetailsRowViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        first_field = (TextView)itemView.findViewById(R.id.first_field);
        second_field = (TextView)itemView.findViewById(R.id.second_field);
        icon = (ImageView)itemView.findViewById(R.id.icon);
    }


    @Override
    public void onClick(View v) {

    }
}


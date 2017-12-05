package fryer.kohler.familymapapp;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

/**
 * Created by kittykatt on 12/4/17.
 */

public class DetailsRowViewHolder extends ViewHolder implements View.OnClickListener{
    TextView user_name;
    TextView content;


    public DetailsRowViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        user_name=(TextView)itemView.findViewById(R.id.user_name);
        content=(TextView)itemView.findViewById(R.id.content);

    }


    @Override
    public void onClick(View v) {

    }
}


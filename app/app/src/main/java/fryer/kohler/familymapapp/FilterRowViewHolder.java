package fryer.kohler.familymapapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.function.Consumer;

import familymapapp.Modal.DataTree;

/**
 * Created by programmer on 12/9/17.
 */

public class FilterRowViewHolder extends RecyclerView.ViewHolder{
    public Switch filterSwitch;
    public FilterRowViewHolder(View itemView) {
        super(itemView);
        filterSwitch = (Switch) itemView.findViewById(R.id.switch_field);
        filterSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Integer active = isChecked == true ? 0 : 1;
                DataTree.getInstance().setActiveEventType(filterSwitch.getText().toString(), active);
            }
        });
    }


}

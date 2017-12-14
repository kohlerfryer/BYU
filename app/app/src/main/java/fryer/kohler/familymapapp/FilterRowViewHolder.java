package fryer.kohler.familymapapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.function.Consumer;

import familymapapp.Modal.DataTree;

public class FilterRowViewHolder extends RecyclerView.ViewHolder{
    public Switch filterSwitch;
    public FilterRowViewHolder(View itemView) {
        super(itemView);
        filterSwitch = (Switch) itemView.findViewById(R.id.switch_field);
        filterSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Integer active = isChecked == true ? 1 : 0;
                DataTree.eventFilters.put(filterSwitch.getText().toString(), active);
            }
        });
    }


}

package me.zhouzhuo810.magpie.ui.dialog.adapter;

import android.content.Context;

import java.util.List;

import me.zhouzhuo810.magpie.R;
import me.zhouzhuo810.magpie.ui.adapter.RvBaseAdapter;

public class ListDialogAdapter extends RvBaseAdapter<String> {

    public ListDialogAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_lv_dialog;
    }

    @Override
    protected void fillData(ViewHolder holder, String item, int position) {
        holder.setText(R.id.tv_name, item);
    }
}

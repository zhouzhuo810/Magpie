package me.zhouzhuo810.magpie.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import me.zhouzhuo810.magpie.R;
import me.zhouzhuo810.magpie.ui.adapter.RvBaseAdapter;
import me.zhouzhuo810.magpie.ui.dialog.adapter.ListDialogAdapter;
import me.zhouzhuo810.magpie.utils.ScreenAdapterUtil;

public class ListDialog extends DialogFragment {

    private List<String> items;
    private OnItemClick onItemClick;
    private ListDialogAdapter adapter;

    public interface OnItemClick {
        void onItemClick(int position, String item);
    }

    public ListDialog setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
        return this;
    }

    public ListDialog setItems(List<String> items) {
        this.items = items;
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        return this;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() == null) {
            return;
        }
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(dm.widthPixels * 4 / 5, getDialog().getWindow().getAttributes().height);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //添加这一行
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.layout_list_dialog, container, false);
        ScreenAdapterUtil.getInstance().loadView(rootView);
        RecyclerView rv = rootView.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
        adapter = new ListDialogAdapter(getActivity(), items);
        adapter.setOnItemClickListener(new RvBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                dismissDialog();
                if (onItemClick != null) {
                    onItemClick.onItemClick(position, items.get(position));
                }
            }
        });
        rv.setAdapter(adapter);
        return rootView;
    }


    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 注意,不要用super.dismiss(),bug 同上show()
     * super.onDismiss就没问题
     */
    public void dismissDialog() {
        if (getActivity() != null && !getActivity().isFinishing()) {
            super.dismissAllowingStateLoss();
        }
    }


}

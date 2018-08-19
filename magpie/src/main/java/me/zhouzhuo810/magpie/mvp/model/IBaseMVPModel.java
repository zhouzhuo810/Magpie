package me.zhouzhuo810.magpie.mvp.model;

import android.content.Context;

import java.util.List;

public interface IBaseMVPModel<T> {

    List<T> refreshData(String... params);

    List<T> loadMoreData(String... params);
}

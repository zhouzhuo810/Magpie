package me.zhouzhuo810.magpie.mvp.model;

import java.util.List;

public class BaseMVPModel<T> implements IBaseMVPModel<T>{

    @Override
    public List<T> refreshData(String... params) {
        return null;
    }

    @Override
    public List<T> loadMoreData(String... params) {
        return null;
    }
}

package com.nativo.mybooks.callBack;

import com.nativo.mybooks.entity.BookEntity;

import java.util.List;

public interface CallBack<T> {
    void onSuccess(T result);
}

package crabster.rudakov.sberschoollesson30hwk.utils;

import io.reactivex.*;
import io.reactivex.android.schedulers.*;
import io.reactivex.schedulers.*;

public class SchedulersImpl implements ISchedulers {

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

}

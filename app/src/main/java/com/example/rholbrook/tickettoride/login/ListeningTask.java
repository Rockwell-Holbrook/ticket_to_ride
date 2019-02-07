package com.example.rholbrook.tickettoride.login;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by chocobj on 11/15/18.
 */

public abstract class ListeningTask<T, U, V> extends AsyncTask<T, U, V> {
    public interface Listener {
        void onComplete(Object result);
    }

    private Listener mListener;

    public void setListener(Listener listener) {
        mListener = listener;
    }

    @Override
    protected void onPostExecute(V o) {
        Log.d("LoginFragment", "ListeningTask entered onPostExecute");
        super.onPostExecute(o);
        if (mListener != null) {
            mListener.onComplete(o);
        }
    }
}
package com.example.rholbrook.tickettoride.game;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.rholbrook.tickettoride.R;
import com.example.shared.model.GameHistory;

import java.util.List;

public class HistoryFragment extends Fragment implements HistoryContract.HistoryView {
    HistoryContract.HistoryPresenter presenter;
    private RecyclerView historyRecyclerView;
    private Button demoButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        presenter = new HistoryFragmentPresenter(this);
        historyRecyclerView = view.findViewById(R.id.history_recycler_view);
        presenter.getGameHistory();
        return view;
    }

    @Override
    public void updateGameHistory(final List<GameHistory> gameHistory) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                historyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                historyRecyclerView.setAdapter(new HistoryAdapter(gameHistory));
                historyRecyclerView.scrollToPosition(gameHistory.size() - 1);
            }
        });
    }
}

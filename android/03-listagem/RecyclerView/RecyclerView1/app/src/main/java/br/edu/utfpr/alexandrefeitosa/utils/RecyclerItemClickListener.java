package br.edu.utfpr.alexandrefeitosa.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

public class RecyclerItemClickListener extends RecyclerView.SimpleOnItemTouchListener {

    private RecyclerView        recyclerView;
    private OnItemClickListener listenerOnItemClick;
    private GestureDetector     gestureDetector;

    public interface OnItemClickListener {

        public void onItemClick(View view, int position);

        public void onLongItemClick(View view, int position);
    }

    public RecyclerItemClickListener(Context context,
                                     RecyclerView recycler,
                                     OnItemClickListener listener) {

        recyclerView = recycler;
        listenerOnItemClick = listener;

        gestureDetector = new GestureDetector(context,
                                              new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {

                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());

                if (child != null && listenerOnItemClick != null) {

                    listenerOnItemClick.onItemClick(child,
                                                    recyclerView.getChildAdapterPosition(child));

                    return true;
                }

                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());

                if (child != null && listenerOnItemClick != null) {

                    listenerOnItemClick.onLongItemClick(child,
                                                        recyclerView.getChildAdapterPosition(child));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        return gestureDetector.onTouchEvent(e);
    }
}

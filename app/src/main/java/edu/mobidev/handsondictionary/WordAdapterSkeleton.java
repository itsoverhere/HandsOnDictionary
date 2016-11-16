package edu.mobidev.handsondictionary;


import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by student on 12/11/2016.
 */

public class WordAdapterSkeleton extends CursorRecyclerViewAdapter<WordAdapterSkeleton.WordViewHolder> {

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public WordAdapterSkeleton(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(WordViewHolder viewHolder, Cursor cursor, int position) {
//        currentWord refers to the word represented by the cursor
//        currentWord.setPosition(position);

        // TODO bindviews here

//        viewHolder.tvWord.setTag(currentWord);

        // TODO call listeners here
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // TODO inflate layout here
        return null;
    }

    public class WordViewHolder extends RecyclerView.ViewHolder{
        TextView tvWord;

        public WordViewHolder(View itemView) {
            super(itemView);
            // TODO initialize tvWord here
        }
    }

    public OnItemClickListener getmOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public OnItemLongClickListener getmOnItemLongClickListener() {
        return mOnItemLongClickListener;
    }

    public void setmOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public interface OnItemClickListener{
        public void onItemClick(Word w);
    }

    public interface OnItemLongClickListener{
        public void onItemLongClick(long id, int position);
    }

}

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

public class WordAdapter extends CursorRecyclerViewAdapter<WordAdapter.WordViewHolder> {

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public WordAdapter(Context context, Cursor cursor, OnItemClickListener onItemClickListener) {
        super(context, cursor);
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(WordViewHolder viewHolder, Cursor cursor, int position) {
        Word w = new Word();
        w.setWord(cursor.getString(cursor.getColumnIndex(Word.COLUMN_WORD)));
        w.setDescription(cursor.getString(cursor.getColumnIndex(Word.COLUMN_DESCRIPTION)));
        w.setPosition(position);
        w.setId(cursor.getLong(cursor.getColumnIndex(Word.COLUMN_ID)));

        viewHolder.tvWord.setText(cursor.getString(cursor.getColumnIndex(Word.COLUMN_WORD)));
        viewHolder.tvWord.setTag(w);

        viewHolder.tvWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word w = (Word) v.getTag();
                mOnItemClickListener.onItemClick(w);
            }
        });

        viewHolder.tvWord.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                Word w = (Word) v.getTag();
                mOnItemLongClickListener.onItemLongClick(w.getId(), w.getPosition());
                return false;
            }
        });
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WordViewHolder(LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.list_item_word, parent, false));
    }

    public class WordViewHolder extends RecyclerView.ViewHolder{
        TextView tvWord;

        public WordViewHolder(View itemView) {
            super(itemView);
            tvWord = (TextView) itemView.findViewById(R.id.tv_word);
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

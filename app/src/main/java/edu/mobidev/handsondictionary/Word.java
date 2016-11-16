package edu.mobidev.handsondictionary;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 12/11/2016.
 */

public class Word implements Parcelable{

    public static final String TABLE = "word";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_WORD = "word";
    public static final String COLUMN_DESCRIPTION = "description";

    private long id;
    private int position;
    private String word;
    private String description;

    public Word(){}

    public Word(Parcel pc){
        id = pc.readLong();
        position = pc.readInt();
        word = pc.readString();
        description = pc.readString();
    }

    public Word(long id, String word, String description) {
        this.id = id;
        this.word = word;
        this.description = description;
    }

    public Word(String word, String description) {
        this.word = word;
        this.description = description;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(position);
        dest.writeString(word);
        dest.writeString(description);
    }

    public static final Parcelable.Creator<Word> CREATOR = new Parcelable.Creator<Word>() {
        public Word createFromParcel(Parcel pc) {
            return new Word(pc);
        }
        public Word[] newArray(int size) {
            return new Word[size];
        }
    };


}

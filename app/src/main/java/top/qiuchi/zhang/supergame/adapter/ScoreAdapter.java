package top.qiuchi.zhang.supergame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

import top.qiuchi.zhang.supergame.R;
import top.qiuchi.zhang.supergame.entity.Score;

public class ScoreAdapter extends BaseAdapter {

    private LinkedList<Score> scoreLinkedList;
    private Context context;

    public ScoreAdapter(LinkedList<Score> scoreLinkedList, Context context) {
        this.scoreLinkedList = scoreLinkedList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return scoreLinkedList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
        TextView textView1 = view.findViewById(R.id.username);
        TextView textView2 = view.findViewById(R.id.achievement);

        textView1.setText(scoreLinkedList.get(i).getUsername());
        textView2.setText(scoreLinkedList.get(i).getAchievement());
        return view;
    }
}

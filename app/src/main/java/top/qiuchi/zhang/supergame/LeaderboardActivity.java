package top.qiuchi.zhang.supergame;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import top.qiuchi.zhang.supergame.adapter.ScoreAdapter;
import top.qiuchi.zhang.supergame.entity.Score;
import top.qiuchi.zhang.supergame.sqlhelp.MyDBOpenHelper;

public class LeaderboardActivity extends AppCompatActivity {

    private Context context;
    private SQLiteDatabase db;
    private MyDBOpenHelper myDBHelper;

    private List<Score> list = null;
    private ScoreAdapter scoreAdapter = null;
    private ListView listView;

    private Intent intent1;
    private Intent intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        context = LeaderboardActivity.this;
        listView = findViewById(R.id.list_score);
        list = new LinkedList<Score>();

        intent1 = new Intent(LeaderboardActivity.this,GameActivity.class);
        intent2 = new Intent(LeaderboardActivity.this,MainActivity.class);

        myDBHelper = new MyDBOpenHelper(context,"my.db",null,1);
        db = myDBHelper.getWritableDatabase();

        list.add(new Score("zhang","120"));
        list.add(new Score("wang","60"));

        Cursor cursor = db.query("score", null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {

                String username = cursor.getString(cursor.getColumnIndex("username"));
                String achievement = cursor.getString(cursor.getColumnIndex("achievement"));
                list.add(new Score(username,achievement));
            }while (cursor.moveToNext());
        }
        cursor.close();
        scoreAdapter = new ScoreAdapter((LinkedList<Score>) list,context);
        listView.setAdapter(scoreAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.startgame:
                startActivity(intent1);
                LeaderboardActivity.this.finish();
                break;
            case R.id.quit:
                startActivity(intent2);
                LeaderboardActivity.this.finish();
                break;
            case R.id.showscore:
                Toast.makeText(context, "您正在查看积分榜", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

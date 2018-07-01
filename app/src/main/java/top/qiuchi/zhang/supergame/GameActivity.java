package top.qiuchi.zhang.supergame;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import top.qiuchi.zhang.supergame.entity.Score;
import top.qiuchi.zhang.supergame.sqlhelp.MyDBOpenHelper;


public class GameActivity extends AppCompatActivity {

    private String username;

    private int num_left;
    private int num_right;
    private EditText editText;
    private String re;
    private int i;
    private int achievement;
    private TextView textView_numleft;
    private TextView textView_numright;

    private Context context;
    private SQLiteDatabase db;
    private MyDBOpenHelper myDBHelper;

    private Intent intent1;
    private Intent intent2;

    private int cnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        cnt = 0;
        context = GameActivity.this;
        myDBHelper = new MyDBOpenHelper(context,"my.db",null,1);
        db = myDBHelper.getWritableDatabase();

        editText = findViewById(R.id.editText_re);
        textView_numleft = findViewById(R.id.textView_numleft);
        textView_numright = findViewById(R.id.textView_numright);

        intent1 = new Intent(GameActivity.this, LeaderboardActivity.class);
        intent2 = new Intent(GameActivity.this,MainActivity.class);

        i = 0;
        achievement = 0;
//        textView_numleft.setText(i);
        dogame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    public int generatorRandom(){
        Random rand = new Random();
        int max = 10;
        int min = 1;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public void generatorNum(){
        num_left = generatorRandom();
        num_right = generatorRandom();
    }

    public int calResult(){
        return num_right + num_left;
    }

    public void dogame(){
            i++;
            generatorNum();
            textView_numleft.setText(Integer.toString(num_left));
            textView_numright.setText(Integer.toString(num_right));
            re = editText.getText().toString();
            int result = calResult();
            String result_str = Integer.toString(result);
            if (result_str.equals(re)){
                achievement += 20;
            }

    }

    public void putanswer(View view) {
        if (i <= 5){
            re = editText.getText().toString();
            int result = calResult();
            String result_str = Integer.toString(result);
            if (result_str.equals(re)){
                achievement += 20;
            }

            editText.setText("");

            if (i <= 5){
                i++;
                generatorNum();
                textView_numleft.setText(Integer.toString(num_left));
                textView_numright.setText(Integer.toString(num_right));
                re = editText.getText().toString();
            }else {
                Toast.makeText(this, username + "your score is " + achievement, Toast.LENGTH_SHORT).show();
                if (cnt == 0){
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("username",username);
                    contentValues.put("achievement",Integer.toString(achievement));
                    db.insert("score",null,contentValues);
                    cnt = 1;
                }

            }
        }else {
            Toast.makeText(this, username + " your score is " + achievement, Toast.LENGTH_SHORT).show();
            if (cnt == 0) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("username", username);
                contentValues.put("achievement", Integer.toString(achievement));
                db.insert("score", null, contentValues);
                cnt = 1;
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.startgame:
                refresh();
                break;
            case R.id.quit:
                startActivity(intent2);
                GameActivity.this.finish();
                break;
            case R.id.showscore:
                startActivity(intent1);
                GameActivity.this.finish();
                break;
            case R.id.clear:
                db.execSQL("DELETE FROM score");
                Toast.makeText(context, "清空成功", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void refresh() {
        finish();
        Intent intent = new Intent(GameActivity.this, GameActivity.class);
        startActivity(intent);
    }
}

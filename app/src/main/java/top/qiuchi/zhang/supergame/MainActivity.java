package top.qiuchi.zhang.supergame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String username;
    private EditText editText;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(MainActivity.this,GameActivity.class);
        editText = findViewById(R.id.editText_username);


    }

    public void startall(View view) {
        username = editText.getText().toString();
        if (username.equals("")){
            username = "未命名";
        }
        intent.putExtra("username",username);
//        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
        startActivity(intent);
        MainActivity.this.finish();
    }
}

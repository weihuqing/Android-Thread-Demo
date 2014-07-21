package com.demo.thread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity
{
    
    private Button mode1, mode2;
    
    private EditText edit1, edit2;
    
    private TextView text1, text2;
    
    private class MyRunnable1 implements Runnable
    {
        Handler handler = new Handler();
        
        @Override
        public void run()
        {
            handler.postDelayed(new Runnable()
            {
                
                @Override
                public void run()
                {
                    text1.setText(edit1.getText().toString());
                }
            }, 2000);
        }
    }
    
    private class MyRunnable2 implements Runnable
    {
        @Override
        public void run()
        {
            
            Message message = Message.obtain();
            message.what = 1;
            handler.sendMessageDelayed(message, 2000);
        }
    }
    
    private Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case 1:
                    text2.setText(edit2.getText().toString());
                    break;
                
                default:
                    break;
            }
        };
    };
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mode1 = (Button) findViewById(R.id.mode_1);
        mode2 = (Button) findViewById(R.id.mode_2);
        text1 = (TextView) findViewById(R.id.text_1);
        text2 = (TextView) findViewById(R.id.text_2);
        edit1 = (EditText) findViewById(R.id.edit_1);
        edit2 = (EditText) findViewById(R.id.edit_2);
        
        mode1.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                new Thread(new MyRunnable1()).start();
            }
        });
        
        mode2.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                new Thread(new MyRunnable2()).start();
            }
        });
    }
}

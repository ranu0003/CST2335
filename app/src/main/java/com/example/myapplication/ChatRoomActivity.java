package com.example.myapplication;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatRoomActivity extends AppCompatActivity {

    ListView lv;
    EditText et;
    List<Message> msgList = new ArrayList<>();
    Button btnSend;
    Button btnReceive;
    DatabaseClass databaseHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);


        lv = (ListView)findViewById(R.id.lstView);
        et = (EditText)findViewById(R.id.chatEditText);
        btnSend = (Button)findViewById(R.id.SendBtn);
        btnReceive = (Button)findViewById(R.id.ReceiveBtn);

        databaseHelp = new DatabaseClass(this);




        final ChatAdapter messageAdapter = new ChatAdapter(msgList, this);
        lv.setAdapter(messageAdapter);

        btnReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (!et.getText().toString().equals("")) {

                    databaseHelp.insertData(et.getText().toString(), true);

                    et.setText("");

                    msgList.clear();

                    viewData();
                }
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!et.getText().toString().equals("")) {

                    databaseHelp.insertData(et.getText().toString(), false);

                    et.setText("");

                    msgList.clear();

                    viewData();
                }

            }});
        viewData();

        Log.e("ChatRoomActivity","onCreate");
    }
    private void viewData(){

        Cursor cursor = databaseHelp.viewData();

        if (cursor.getCount() != 0){

            while (cursor.moveToNext()){

                Message msg = new Message(cursor.getString(1), cursor.getInt(2) == 0);

                msgList.add(msg);

                ChatAdapter chatAdapter = new ChatAdapter(msgList, getApplicationContext());

                lv.setAdapter(chatAdapter);

            }

        }

    }


    public class ChatAdapter extends BaseAdapter {
        List<Message>msg;
        Context ctx;
        LayoutInflater inflater;


        public ChatAdapter(List<Message>msg, Context ctx) {
            this.ctx=ctx;
            this.msg=msg;
            this.inflater=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return msg.size();
        }



        @Override
        public Message getItem(int position) {
            return  msg.get(position);
        }


        @Override
        public long getItemId(int position) {
            return (long)position;
        }


        @Override
        public View getView(int position,  View convertView,  ViewGroup parent) {



            View result = convertView ;

            if(msg.get(position).isSend()){

                result = inflater.inflate(R.layout.send,null);

            }
            else { result=inflater.inflate(R.layout.receive,null);


            }


            TextView message = result.findViewById(R.id.messageText);
            message.setText(msg.get(position).getMsg()); // get the string at position


            return result;

        }
    }
}
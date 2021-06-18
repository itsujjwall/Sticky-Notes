package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText noteEdit;
    private Button increaseSizeBtn,decreaseSizeBtn, BoldBtn,ItalicBtn,UnderlineBtn;
    private TextView sizeTv;
    StickyNote note=new StickyNote();

    float current_text_size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        noteEdit=findViewById(R.id.edit_text_text);
        increaseSizeBtn=findViewById(R.id.btn_size_increaser);
        decreaseSizeBtn=findViewById(R.id.btn_size_reducer);
        BoldBtn=findViewById(R.id.bold_btn);
        ItalicBtn=findViewById(R.id.italic_btn);
        UnderlineBtn=findViewById(R.id.underline_btn);
        sizeTv=findViewById(R.id.tv_size);
        current_text_size=noteEdit.getTextSize();
        sizeTv.setText(""+current_text_size);


        increaseSizeBtn.setOnClickListener(v -> {
            sizeTv.setText(""+current_text_size);
            current_text_size++;
            noteEdit.setTextSize(current_text_size);
        });

        decreaseSizeBtn.setOnClickListener(v -> {
            sizeTv.setText(""+current_text_size);
            current_text_size--;
            noteEdit.setTextSize(current_text_size);
        });

        BoldBtn.setOnClickListener(v -> {
            ItalicBtn.setTextColor(getResources().getColor(R.color.white));
            ItalicBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
            if(noteEdit.getTypeface().isBold()) {
                noteEdit.setTypeface(Typeface.DEFAULT);
                BoldBtn.setTextColor(getResources().getColor(R.color.white));
                BoldBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
            }
            else {
                BoldBtn.setTextColor(getResources().getColor(R.color.purple_200));
                BoldBtn.setBackgroundColor(getResources().getColor(R.color.white));
                noteEdit.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

            }
        });

        ItalicBtn.setOnClickListener(v -> {
            BoldBtn.setTextColor(getResources().getColor(R.color.white));
            BoldBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
            if(noteEdit.getTypeface().isItalic()){
                noteEdit.setTypeface(Typeface.DEFAULT);
                ItalicBtn.setTextColor(getResources().getColor(R.color.white));
                ItalicBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
            }
            else {
                ItalicBtn.setTextColor(getResources().getColor(R.color.purple_200));
                ItalicBtn.setBackgroundColor(getResources().getColor(R.color.white));
                noteEdit.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
            }
        });

        UnderlineBtn.setOnClickListener(v->{
            if(noteEdit.getPaintFlags()==8){
                UnderlineBtn.setTextColor(getResources().getColor(R.color.white));
                UnderlineBtn.setBackgroundColor(getResources().getColor(R.color.purple_200));
                noteEdit.setPaintFlags(noteEdit.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
            }
            else {
                UnderlineBtn.setTextColor(getResources().getColor(R.color.purple_200));
                UnderlineBtn.setBackgroundColor(getResources().getColor(R.color.white));
                noteEdit.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
            }
        });
    }

    public void SaveButton(View view) {
        note.setStick(noteEdit.getText().toString(),this);
        updateWidget();
        Toast.makeText(this, "Note has been updated", Toast.LENGTH_SHORT).show();
    }

    private void updateWidget(){
        AppWidgetManager appWidgetManager= AppWidgetManager.getInstance(this);
        RemoteViews remoteViews=new RemoteViews(this.getPackageName(),R.layout.widget_layout);
        ComponentName thisWidget = new ComponentName(this,AppWidget.class);
        remoteViews.setTextViewText(R.id.tv_widget,noteEdit.getText().toString());
        appWidgetManager.updateAppWidget(thisWidget,remoteViews);

    }
}
package com.ultimaterockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    //FOR THE BUTTON //
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FOR THE BUTTON //
        button = findViewById(R.id.MenuStartButton); // assigns  the Button
        button.setOnClickListener(new View.OnClickListener() // Listener
        {
            @Override
            public void onClick(View v) // waits/Listens for the onClicked to be hit
            {
                GamePlayScreen(); // calls the method / action we want.
            }
        });
    }

    //METHOD CREATED FOR OPEN ACTIVITY 2//
    public void GamePlayScreen() // OPENS THE 2nd ACTIVITY
    {
        Intent intent = new Intent(this, GamePlayScreen.class);
        startActivity(intent);
    }

}

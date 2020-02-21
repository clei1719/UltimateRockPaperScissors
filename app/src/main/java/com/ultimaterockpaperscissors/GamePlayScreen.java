package com.ultimaterockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import static java.lang.Thread.sleep;

public class GamePlayScreen extends AppCompatActivity
{
    // FEATURES SPECIFIC
    private TextView edit;
    private ImageView ProfilePic;
    private TextView tv2;
    private ImageView compPic;

    // GAME PLAY SPECIFIC
    private TextView displayWinner;
    private String winnerResults = "";
    private short winner = 0;
    private ImageButton rock;
    private ImageButton paper;
    private ImageButton scissors;
    private Button playAgain; // button choice
    private boolean isPlayAgainClicked = false;
    private int id;
    String personPlay; //User's play -- "R", "P", or "S"
    String computerPlay = ""; //Computer's play -- "R", "P", or "S"
    private int computerInt; //Randomly generated number used to determine computer's play

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play_screen);

        // FEATURES Implementation ACTIVITY 2 FEATURE - EDIT PROFILE PICTURE////
        //FOR THE BUTTON //
        edit = findViewById(R.id.editPicPlayer); // assigns  the Button
        // FOR THE PICTURE IMAGE
        ProfilePic = findViewById(R.id.ProfilePic); // assigns the picture as clickable
        // FOR THE ANSWER
        displayWinner = findViewById(R.id.displayWinner);
        //Sets the play again? button to invisible until we want it.
        playAgain = findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);

        tv2 = findViewById(R.id.tv2);
        compPic = findViewById(R.id.ComputerPic);

        // FOR GAMEPLAY

        // action for button selected by player.
        rock = findViewById(R.id.rock);
        paper = findViewById(R.id.paper);
        scissors = findViewById(R.id.scissors);

        // FEATURES LISTENERS
        edit.setOnClickListener(new View.OnClickListener() // Listener
        {
            @Override
            public void onClick(View v) // waits/Listens for the onClicked to be hit
            {
                ProfileSelectScreen(); // calls the method / action we want.
            }
        });
        ProfilePic.setOnClickListener(new View.OnClickListener() // Listener
        {
            @Override
            public void onClick(View v) // waits/Listens for the onClicked to be hit
            {
                ProfileSelectScreen(); // calls the method / action we want.
            }
        });

        //GAMEPLAY LISTENERS //
        rock.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                personPlay = "R"; // sets player input string to selection.
                // Here we could skip to another screen and then do all the logic...simpler way maybe???
                computerRoll(); // calls method to set computer to roll.
                winnerResults = whoWon(); // calls method to determine who won.
                displayWinner.setText(winnerResults);
                setButtonsGone(); // removes the buttons and adjusts graphics
                setReadyToPlayButton(); // activates the clickable button to restart.

            }
        });

        paper.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                personPlay = "P";
                computerRoll(); // calls method to set computer to roll.
                winnerResults = whoWon(); // calls method to determine who won.
                displayWinner.setText(winnerResults);
                setButtonsGone(); // removes the buttons and adjusts graphics
                setReadyToPlayButton(); // activates the clickable button to restart.
            }
        });

        scissors.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                personPlay = "S";
                computerRoll(); // calls method to set computer to roll.
                winnerResults = whoWon(); // calls method to determine who won.
                displayWinner.setText(winnerResults);
                setButtonsGone(); // removes the buttons and adjusts graphics
                setReadyToPlayButton(); // activates the clickable button to restart.
            }
        });

        playAgain.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //Here we can restart
                isPlayAgainClicked = true;
                restart(); // this restarts a new game
            }
        });
    }

    //METHOD CREATED FOR ProfileSelectScreen FEATURE - EDIT PROFILE PICTURE//
    public void ProfileSelectScreen() // OPENS THE 2nd ACTIVITY
    {
        Intent intent = new Intent(this, ProfileSelectScreen.class);
        Intent intent2 = new Intent(this, ProfileSelectScreen.class);
        startActivity(intent);
        startActivity(intent2);
    }
    // METHOD FUNCTION FOR GAME //
    public void computerRoll()
    {
        // Generates a random number
        Random generator = new Random();
        //Generate computer's play (0,1,2)
        computerInt = generator.nextInt(3)+1;

        if (computerInt == 1)
            computerPlay = "R";
        else if (computerInt == 2)
            computerPlay = "P";
        else if (computerInt == 3)
            computerPlay = "S";
    }
    // METHOD FUNCTION FOR GAME //
    public String whoWon()
    {
        //See who won. Use nested ifs
        String result = "";
        winner = 0;
        if (personPlay.equals(computerPlay))
        {
            result = "It's a tie!";
            winner = 0;
        }
        else if (personPlay.equals("R"))
        {
            if (computerPlay.equals("S"))
            {
                result = "Rock crushes scissors. You win!!";
                winner = 1;
            }
            else if(computerPlay.equals("P"))
            {
                result = "Paper eats Rock. You lose!!";
                winner = 2;
            }
        }
        else if (personPlay.equals("P"))
        {
            if (computerPlay.equals("S"))
            {
                result = "Scissor cuts paper. You lose!!";
                winner = 2;
            }
            else if(computerPlay.equals("R"))
            {
                result = "Paper eats rock. You win!!";
                winner = 1;
            }
        }
        else if (personPlay.equals("S"))
        {
            if (computerPlay.equals("P"))
            {
                result = "Scissor cuts paper. You win!!";
                winner = 1;
            }
            else if(computerPlay.equals("R"))
            {
                result = "Rock crushes scissors. You lose!!";
                winner = 2;
            }
        }
        else
        {
            result = "Invalid user input.";
        }
        return result;
    }
    // restarts the program, refreshes the page for a new game to be played.
    public void restart()
    {

        Intent restart = new Intent(this, GamePlayScreen.class);
        startActivity(restart);
    }
    // Just sets the button to hidden, but they are still taking up space.
    public void setButtonsInvisible()
    {
        rock.setVisibility(View.INVISIBLE);
        paper.setVisibility(View.INVISIBLE);
        scissors.setVisibility(View.INVISIBLE);
    }
    // Removes the buttons from the UI until we want them.
    // We also do some adjusting of the buttons to make things look nice.
    public void setButtonsGone()
    {
        displayWinner.setTranslationY(60);
        tv2.setTranslationY(60);
        compPic.setTranslationY(60);

        rock.setVisibility(View.GONE);
        paper.setVisibility(View.GONE);
        scissors.setVisibility(View.GONE);
    }
    public void setReadyToPlayButton()
    {
        //playAgain.setVisibility(View.VISIBLE); // shows option for play Again?
        playAgain.setPadding(0,40,0,40);
        playAgain.setTranslationY(80);
        playAgain.setVisibility(View.VISIBLE); // shows option for play Again?
        //textAnimation();
    }
    public void textAnimation()
    {
        // This should do an animation of the text.
//        while(isPlayAgainClicked == false);
//        {
        int i = 0;
        while(i <= 25)
        {
            playAgain.setTextSize(i);
            sleep(8000);
            i++;
            if(i == 25)
                while(i > 21)
                {
                    i--;
                    playAgain.setTextSize(i);
                }
        }

//        }
    }
    public void sleep(int sleepTime)
    {
        try
        {
            Thread.sleep(sleepTime);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}

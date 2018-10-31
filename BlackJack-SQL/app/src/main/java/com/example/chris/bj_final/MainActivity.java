package com.example.chris.bj_final;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    boolean splitCheck = false, splitTurn = false, doubleDownCheck = true, clicked = false;
    int dealerSum = 0, playerSum = 0, playerSum2 = 0, splitSum = 0, splitSum2 = 0, randomInt, finish = 0, bank = 200, bet = 10;
    String dealerSumString, playerSumString, splitSumString, splitHitString, playerHitString = "", dealerHitString, tmpString;
    StringBuilder playerHitStringBuilder, splitHitStringBuilder, dealerHitStringBuilder;
    
    Card playerCard1, playerCard2, dealerCard1, dealerCard2;
    TextView viewPlayerCard1, viewPlayerCard2, viewDealerCard1, viewDealerCard2, viewDealerSum, viewCardSum, viewDealerHit, viewSplitSum, viewHitCard, viewSplitHit, viewCard2, viewBank, viewBet; 
    MediaPlayer oneUp, end, win, start, warp, musicSplit, push, split, stay, newGame;
    ImageView marioJumping;
    ObjectAnimator up, down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Refresh();
    }

    public void Refresh() {
        oneUp = MediaPlayer.create(this, R.raw.spash_sound);
        end = MediaPlayer.create(this, R.raw.gameover);
        win = MediaPlayer.create(this, R.raw.win);
        start = MediaPlayer.create(this, R.raw.fever);
        warp = MediaPlayer.create(this, R.raw.warp);
        musicSplit = MediaPlayer.create(this, R.raw.musicsplit);
        push = MediaPlayer.create(this, R.raw.push);
        split = MediaPlayer.create(this, R.raw.split);
        stay = MediaPlayer.create(this, R.raw.stay);
        newGame = MediaPlayer.create(this, R.raw.newgame);

        viewDealerCard1 = (TextView) findViewById(R.id.textViewDealerCard1);
        viewDealerCard2 = (TextView) findViewById(R.id.textViewDealerCard2);
        viewPlayerCard1 = (TextView) findViewById(R.id.textViewCard1);
        viewPlayerCard2 = (TextView) findViewById(R.id.textViewCard2);
        viewDealerSum = (TextView) findViewById(R.id.textViewDealerSum);
        viewCardSum = (TextView) findViewById(R.id.textViewCardSum);
        viewDealerHit = (TextView) findViewById(R.id.textViewDealerHitCard);
        viewSplitSum = (TextView) findViewById(R.id.textViewSplitSum);
        viewHitCard = (TextView) findViewById(R.id.textViewHitCard);
        viewSplitHit = (TextView) findViewById(R.id.textviewSplitHitCard);
        viewCard2 = (TextView) findViewById(R.id.viewCard2);
        viewBank = (TextView) findViewById(R.id.viewBank);
        viewBet = (TextView) findViewById(R.id.viewbetAmount);
        marioJumping = (ImageView) findViewById(R.id.imageViewMarioJumping);


        if (finish == 0) {
            start.start();
        }
        Random rand = new Random();
        playerCard2 = new Card();
        playerCard1 = new Card();
        dealerCard1 = new Card();
        dealerCard2 = new Card();

        dealerSum = 0;
        playerSum = 0;
        splitSum = 0;
        finish = 1;
        doubleDownCheck = true;
        splitCheck = false;
        splitTurn = false;
        splitSumString = "";
        splitHitString = "";
        playerHitString = "";
        dealerHitString = "";

        //randomize player Card 1
        randomInt = rand.nextInt(13) + 1;
        StartCardFunction();
        if (randomInt == 1) {
            playerCard1.softAce = true;
            playerCard1.setValue(11);
            playerSum = 11;
            tmpString = "A";
            viewPlayerCard1.setText(tmpString);
        } else {
            playerCard1.setValue(randomInt);
            playerSum = randomInt;
            viewPlayerCard1.setText(tmpString);
        }
        //ramdomize player Card 2
        randomInt = rand.nextInt(13) + 1;
        StartCardFunction();
        if (randomInt == 1) {
            playerCard2.softAce = true;
            playerCard2.setValue(11);
            playerSum += 11;
            tmpString = "A";
            viewPlayerCard2.setText(tmpString);
        } else {
            playerCard2.setValue(randomInt);
            playerSum += randomInt;
            viewPlayerCard2.setText(tmpString);
        }
        //if both aces
        if (playerSum == 22) {
            playerSum -= 10;
            playerCard1.softAce = false;
        }
        //randomize dealer Card 1
        randomInt = rand.nextInt(13) + 1;
        StartCardFunction();
        if (randomInt == 1) {
            dealerCard1.softAce = true;
            dealerCard1.setValue(11);
            dealerSum = 11;
            tmpString = "A";
            viewDealerCard1.setText(tmpString);
        } else {
            dealerCard1.setValue(randomInt);
            dealerSum = randomInt;
            viewDealerCard1.setText(tmpString);
        }
        //ramdomize dealer Card 2
        randomInt = rand.nextInt(13) + 1;
        StartCardFunction();
        if (randomInt == 1) {
            dealerCard2.softAce = true;
            dealerCard2.setValue(11);
            dealerSum += 11;
            tmpString = "A";
            viewDealerCard2.setText(tmpString);
        } else {
            dealerCard2.setValue(randomInt);
            dealerSum += randomInt;
            viewDealerCard2.setText(tmpString);
        }
        if (dealerSum == 22) {
            dealerSum -= 10;
        }
        playerHitStringBuilder = new StringBuilder();
        splitHitStringBuilder = new StringBuilder();
        dealerHitStringBuilder = new StringBuilder();
        UpdateString();
        Display();
    }

    public void UpdateString() {
        dealerSumString = String.valueOf(dealerSum);
        playerSumString = String.valueOf(playerSum);
        splitSumString = String.valueOf(splitSum);
        playerHitString = playerHitStringBuilder.toString();
        splitHitString = splitHitStringBuilder.toString();
        dealerHitString = dealerHitStringBuilder.toString();
    }

    public void Display() {
        viewBet.setText(Integer.toString(bet));
        viewBank.setText(Integer.toString(bank));
        viewDealerSum.setText(dealerSumString);
        if (dealerSum > 21) {
            viewDealerSum.setText("X");
        }
        viewCardSum.setText(playerSumString);
        if (playerSum > 21) {
            viewCardSum.setText("X");
        }

        if (splitCheck == true) {
            if (splitSum > 21) {
                viewSplitSum.setText("X");
            }
            viewSplitSum.setText(splitSumString);
        }
        viewDealerHit.setText(dealerHitString);
        viewHitCard.setText(playerHitString);
        viewSplitHit.setText(splitHitString);
    }

    public void GameOver() {
        finish = 2;
        start.stop();
        timerMedia(0);
        //split
        if (splitCheck == true) {
            timerMedia(1500);
            musicSplit.start();
            //dealer go
            while (dealerSum < 17) { //Dealer turn
                DealerGo();
            }
            if (dealerCard1.softAce == true) {
                if (dealerSum > 21) {
                    dealerSum -= 10;
                    while (dealerSum < 17) {
                        DealerGo();
                    }
                }
            }
            if (dealerCard2.softAce == true) {
                if (dealerSum > 21) {
                    dealerSum -= 10;
                    while (dealerSum < 17) {
                        DealerGo();
                    }
                }
            }
            UpdateString();
            Display();
            if (playerSum > 21) {
                Toast.makeText(this, "You Bust! Dealer Wins!", Toast.LENGTH_LONG).show();
                bank -= bet;
            }
            if (dealerSum > 21) { //1st Card turn
                Toast.makeText(this, "Dealer Busts! You Win!", Toast.LENGTH_LONG).show();
                bank += 2 * bet;
                finish = 2;
                return;
            } else if (dealerSum > playerSum) {
                Toast.makeText(this, "Dealer Wins!", Toast.LENGTH_LONG).show();
                bank -= bet;
            } else if (splitSum > 21) { //split Card turn
                Toast.makeText(this, "You Bust! You Lose!", Toast.LENGTH_LONG).show();
                bank -= bet;
            } else if (playerSum > dealerSum) {
                Toast.makeText(this, "You Win!", Toast.LENGTH_LONG).show();
                bank += bet;
            } else if (playerSum == dealerSum) {
                Toast.makeText(this, "PUSH!", Toast.LENGTH_LONG).show();
            }
            timerMedia(2000);
            if (splitSum > 21) {
                Toast.makeText(this, "Split Card Bust! You lose!", Toast.LENGTH_LONG).show();
                bank -= bet;
            } else if (dealerSum > splitSum) {
                Toast.makeText(this, "Dealer wins Split! You Lose!", Toast.LENGTH_LONG).show();
                bank -= bet;
            } else if (splitSum > dealerSum) {
                Toast.makeText(this, "Split Card Wins!", Toast.LENGTH_LONG).show();
                bet += bet;
            } else if (playerSum == dealerSum) {
                Toast.makeText(this, "Split Card Push!", Toast.LENGTH_LONG).show();
            }
            finish = 2;
            return;
        }
        //not split
        if (playerSum > 21) { //no split
            Toast.makeText(this, "You Lose!", Toast.LENGTH_LONG).show();
            end.start();
            finish = 2;
            bank -= bet;
            return;
        }
        //dealer go
        while (dealerSum < 17) { //Dealer turn
            DealerGo();
        }
        if (dealerCard1.softAce == true) {
            if (dealerSum > 21) {
                dealerSum -= 10;
                while (dealerSum < 17) {
                    DealerGo();
                }
            }
        }
        if (dealerCard2.softAce == true) {
            if (dealerSum > 21) {
                dealerSum -= 10;
                while (dealerSum < 17) {
                    DealerGo();
                }
            }
        }
        UpdateString();
        Display();
        if (dealerSum > 21) {
            Toast.makeText(this, "Dealer busts! You win!", Toast.LENGTH_LONG).show();
            bank += bet;
            win.start();
        } else if (playerSum > dealerSum) {
            Toast.makeText(this, "You Win!", Toast.LENGTH_LONG).show();
            bank += bet;
            win.start();
        } else if (dealerSum > playerSum) {
            Toast.makeText(this, "Dealer wins! You Lose!", Toast.LENGTH_LONG).show();
            bank -= bet;
            end.start();
        } else if (playerSum == dealerSum) {
            Toast.makeText(this, "Push!", Toast.LENGTH_LONG).show();
            push.start();
        }
    }

    public void OnClickStay(View view) {
        if (clicked == false) {
            if (finish == 2) {
                return;
            }
            if (splitCheck == true) {
                //split: player Card
                if (splitTurn == false) {
                    splitTurn = true;
                    doubleDownCheck = false;
                    timerMedia(2700);
                    stay.start();
                    Toast.makeText(this, "Split Turn!", Toast.LENGTH_LONG).show();
                    doubleDownCheck = true;
                    return;
                }
                //split Card
                else {
                    GameOver();
                    return;
                }
                //player Card
            }
            GameOver();
        }
    }

    public void OnClickSplit(View view) {
        if (clicked == false) {
            if (finish == 2) {
                return;
            }
            if (playerCard2.getValue() == playerCard1.getValue()) {
                if (splitCheck == true) {
                    return;
                }
                //time to split
                viewCard2.setText("Split Card");
                Toast.makeText(this, "First Card Turn!", Toast.LENGTH_LONG).show();
                playerSum = playerCard1.getValue();
                splitSum = playerCard2.getValue();
                UpdateString();
                timerMedia(1600);
                split.start();
                splitCheck = true;
            }
        }
    }

    public void OnClickDoubleDown(View view) {
        if (clicked == false) {
            if (finish == 2) {
                return;
            }
            if (doubleDownCheck == true) {
                Random rand = new Random();
                randomInt = rand.nextInt(13) + 1;
                //split: player Card
                if (splitCheck == true) {
                    if (splitTurn == false) {
                        if (playerSum > 10) {
                            FaceCardPlayerFunction();
                            splitTurn = true;
                        } else if (playerSum < 11) {
                            playerSum += randomInt;
                            playerHitStringBuilder.append((Integer.toString(randomInt) + " "));
                            UpdateString();
                            splitTurn = true;
                        }
                        timerMedia(1000);
                        warp.start();
                        Toast.makeText(this, "Split Card Turn!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    //split Card turn
                    else if (splitTurn == true) {
                        FaceCardSplitFunction();
                        splitSum += randomInt;
                        splitHitStringBuilder.append((Integer.toString(randomInt) + " "));
                        GameOver();
                        return;
                    }
                    //player Card
                }
                playerSum += randomInt;
                playerHitStringBuilder.append((Integer.toString(randomInt) + " "));
                UpdateString();
                finish = 2;
                GameOver();
            }
        }
    }

    public void OnClickHit(View view) {
        if (clicked == false) {
            if (finish == 2) {
                return;
            }
            Random rand = new Random();
            randomInt = rand.nextInt(13) + 1;
            //split:player turn
            if (splitCheck == true && splitTurn == false) {
                FaceCardPlayerFunction();
                if (randomInt < 11) {
                    playerSum += randomInt;
                    playerHitStringBuilder.append((Integer.toString(randomInt) + " "));
                    UpdateString();
                }
                if (playerSum > 21 || playerSum2 > 21) {
                    splitTurn = true;
                    timerMedia(4000);
                    end.start();
                    Toast.makeText(this, "Split Card Turn!", Toast.LENGTH_LONG).show();
                    return;
                }
                timerMedia(1300);
                Jump();
                oneUp.start();
                return;
            }
            //split Turn
            else if (splitCheck == true && splitTurn == true) {
                FaceCardSplitFunction();
                if (randomInt < 11) {
                    splitSum += randomInt;
                    splitHitStringBuilder.append((Integer.toString(randomInt) + ""));
                    UpdateString();
                }
                if (splitSum > 21) {
                    finish = 2;
                    GameOver();
                    return;
                }
                timerMedia(1300);
                Jump();
                oneUp.start();
                return;
            }
            //no split
            tmpString = Integer.toString(randomInt);
            FaceCardPlayerFunction();
            if (randomInt < 11) {
                playerSum += randomInt;
                playerHitStringBuilder.append(tmpString);
                UpdateString();
            }
            if (playerSum > 21) {
                GameOver();
                return;
            }
            TimerMedia(1300);
            Jump();
            oneUp.start();
            doubleDownCheck = false;
        }
    }

    public void OnClickNewGame(View view) {
        if (clicked == false) {
            timerMedia(3000);
            newGame.start();
            finish = 1;
            if (bank < 0) {
                Toast.makeText(this, "Bank is empty! Start again.", Toast.LENGTH_LONG).show();
                bank = 200;
            }
            refresh();
        }
    }

    public void OnClickPlus(View view) {
        if (bet == bank) {
            return;
        }
        bet += 10;
        viewBet.setText(Integer.toString(bet));
    }

    public void OnClickMinus(View view) {
        if (bet == 0) {
            return;
        }
        bet -= 10;
        viewBet.setText(Integer.toString(bet));
    }

    public void DealerGo() {
        Random rand = new Random();
        randomInt = rand.nextInt(13) + 1;
        if (randomInt == 1) {
            dealerSum += 11;
            if (dealerSum > 21) {
                dealerSum -= 10;
            }
            dealerHitStringBuilder.append("A");
        } else {
            StartCardFunction();
            dealerSum += randomInt;
            dealerHitStringBuilder.append(tmpString + " ");
            UpdateString();
        }
    }

    public void StartCardFunction() {
        tmpString = Integer.toString(randomInt);
        if (randomInt == 11) {
            randomInt = 10;
            tmpString = "J";
        } else if (randomInt == 12) {
            randomInt = 10;
            tmpString = "Q";
        } else if (randomInt == 13) {
            randomInt = 10;
            tmpString = "K";
        }
    }

    public void FaceCardPlayerFunction() {
        if (randomInt == 1) {
            playerSum += 11;
            if (playerSum > 21) {
                playerSum -= 10;
            }
            playerHitStringBuilder.append("A");
            UpdateString();
        } else if (randomInt == 11) {
            playerSum += 10;
            playerHitStringBuilder.append("J");
            UpdateString();
        } else if (randomInt == 12) {
            playerSum += 10;
            playerHitStringBuilder.append("Q");
            UpdateString();
        } else if (randomInt == 13) {
            playerSum += 10;
            playerHitStringBuilder.append("K");
            UpdateString();
        }
    }

    public void FaceCardSplitFunction() {
        if (randomInt == 1) {
            splitSum += 11;
            if (splitSum > 21) {
                splitSum -= 10;
            }
            splitHitStringBuilder.append("A");
            UpdateString();
        } else if (randomInt == 11) {
            splitSum += 10;
            splitHitStringBuilder.append("J");
            UpdateString();
        } else if (randomInt == 12) {
            splitSum += 10;
            splitHitStringBuilder.append("Q");
            UpdateString();
        } else if (randomInt == 13) {
            splitSum += 10;
            splitHitStringBuilder.append("K");
            UpdateString();
        }
    }

    public void TimerMedia(final int time) {
        Thread timer = new Thread() {
            public void run() {
                try {
                    start.pause();
                    clicked = true;
                    Display();
                    sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    clicked = false;
                    if (finish == 1) {
                        start.start();
                    }
                }
            }
        };
        timer.start();
    }

    public void Jump() {
        up = ObjectAnimator.ofFloat(marioJumping, "translationY", 0 f, -114 f);
        down = ObjectAnimator.ofFloat(marioJumping, "translationY", -114 f, 0 f);
        up.setDuration(400);
        down.setDuration(400);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(down).after(up);
        animSet.start();
    }
}
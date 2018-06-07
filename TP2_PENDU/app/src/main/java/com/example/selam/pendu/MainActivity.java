package com.example.selam.pendu;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mVerification;
    private TextView mMot;
    private EditText mProposition;
    private ImageView mPendu;
    private int mIntChoix = 0, mCompteur = 0;
    private char[] mMotJuste = new char[]{' '};
    private String[] mTirets2;
    private char mChar;
    private String mTirets, mMotEntre;
    private boolean mVrai;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVerification = (Button) findViewById(R.id.verifie);
        mMot = (TextView) findViewById(R.id.etat_mot);
        mProposition = (EditText) findViewById(R.id.proposition);
        mPendu = (ImageView) findViewById(R.id.etat_pendu);
        Random rand = new Random();
        mIntChoix = rand.nextInt(11) + 1;
        mVerification.setOnClickListener(this);
        this.initialisation();
    }

    public void initialisation(){
        Random rand = new Random();
        mIntChoix = rand.nextInt(11) + 1;
        mMot.setText("");
        mTirets = "";
        mCompteur=0;
        this.choixMotJuste();
        this.setMot();

    }

    public void choixMotJuste(){
        switch (mIntChoix){
            case 1:
                this.mMotJuste = new char[]{'L', 'Y', 'S'};
                break;
            case 2:
                this.mMotJuste = new char[]{'V','O','I','E'};
                break;
            case 3:
                this.mMotJuste = new char[]{'R','U','E'};
                break;
            case 4:
                this.mMotJuste = new char[]{'S','A','C'};
                break;
            case 5:
                this.mMotJuste = new char[]{'S','A','V','A','T','E'};
                break;
            case 6:
                this.mMotJuste = new char[]{'P','A','N','N','E','A','U'};
                break;
            case 7:
                this.mMotJuste = new char[]{'C','H','A','T'};
                break;
            case 8:
                this.mMotJuste = new char[]{'L','U','N','E'};
                break;
            case 9:
                this.mMotJuste = new char[]{'C','A','N','A','R','D'};
                break;
            case 10:
                this.mMotJuste = new char[]{'T','U','R','Q','U','O','I','S','E'};
                break;
            case 11:
                this.mMotJuste = new char[]{'T','R','O','U','B','L','E'};
                break;
            default:
        }
    }

    public void setMot(){
        int longueurMot = mMotJuste.length;
        mTirets2 = new String[longueurMot];
        mTirets="";
        for(int i = 0;i<longueurMot;i++){
            mTirets2[i] = "_  ";
            mTirets = mTirets + mTirets2[i];
        }
        mMot.setText(mTirets);
    }

    //@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.verifie:
                if (mVerification.getText() == "Recommencer"){
                    initialisation();
                }else{
                    mMotEntre = String.valueOf(mProposition.getText()).toUpperCase();
                    mChar = mMotEntre.charAt(0);
                    this.verification();
                }
                break;
            default:
        }
    }

    public void verification(){
        mProposition.setText("");
        int longueurMot = mMotJuste.length;
       // mTirets2 = new String[longueurMot];
        mTirets="";
        for(int i = 0;i<longueurMot;i++){
            if(mMotJuste[i] == mChar){
                mVrai=true;
                mTirets2[i] = mMotEntre + " ";
                //mChar.equals(mMotJuste[i])
                //mTirets = mTirets + mMotEntre + "  ";
                //mPendu.setImageDrawable(getResources().getDrawable(R.drawable.pendu_tp_1));

            }
            mTirets = mTirets + mTirets2[i];
        }
                if(mVrai==false){
                    mCompteur++;
                }else{
                    mVrai=true;
                }
                switch (mCompteur){
                    case 1:
                        mPendu.setImageDrawable(getResources().getDrawable(R.drawable.pendu_tp_1));
                        break;
                    case 2:
                        mPendu.setImageDrawable(getResources().getDrawable(R.drawable.pendu_tp_2));
                        break;
                    case 3:
                        mPendu.setImageDrawable(getResources().getDrawable(R.drawable.pendu_tp_3));
                        break;
                    case 4:
                        mPendu.setImageDrawable(getResources().getDrawable(R.drawable.pendu_tp_4));
                        break;
                    case 5:
                        mPendu.setImageDrawable(getResources().getDrawable(R.drawable.pendu_tp_5));
                        break;
                    default:
                }
                mVrai=false;
        mMot.setText(""+mTirets);
        perdu();
        //gagner();
    }

    public void perdu(){
        if(mCompteur == 5){
            mVerification.setText("Recommencer");
            mProposition.setText("Perdu");
        }
    }

    /*public void gagner(){

    }*/

}


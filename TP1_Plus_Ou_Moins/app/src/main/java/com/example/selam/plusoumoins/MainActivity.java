package com.example.selam.plusoumoins;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mVerifie;
    private TextView mResultat, mMode, mEssai;
    private EditText mReponse;
    private int mValeurExacte, mValeurEntrer, mNbEssai = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVerifie = (Button) findViewById(R.id.verifie);
        mResultat = (TextView) findViewById(R.id.resultat);
        mMode = (TextView) findViewById(R.id.mode);
        mEssai = (TextView) findViewById(R.id.essai);
        mReponse = (EditText) findViewById(R.id.reponse);
        mVerifie.setOnClickListener(this);
        this.initialisation();
    }

    public void initialisation(){
            Random rand = new Random();
            mValeurExacte = rand.nextInt(100) + 1;
            mValeurEntrer = 0;
            mNbEssai = 0;
            mReponse.setText("");
            mEssai.setText("Nombre d'essai :" + mNbEssai);
            mMode.setText("Mode 1 Joueur");
            mVerifie.setText("Vérifier");
            mReponse.setHint("Réponse");
            mResultat.setText("");
    }

    public void changemode(){
        if(mMode.getText() == "Mode 1 Joueur"){
            mResultat.setText("");
            mMode.setText("Mode 2 Joueurss");
            mVerifie.setText("Entrer la valeur");
            mReponse.setText("");
            mReponse.setHint("Valeur exacte");
        }else{
            this.initialisation();
        }
    }

    @SuppressLint("SetTextI18n")
    public void verification(){
        mReponse.setText("");
        mNbEssai++;
        mEssai.setText("Nombre d'essai :" + mNbEssai);
        if(mValeurEntrer < mValeurExacte){
            mResultat.setText("Trop petit !");
        }else if(mValeurEntrer > mValeurExacte){
            mResultat.setText("Trop grand !");
        }else if(mValeurEntrer == mValeurExacte){
            mResultat.setText("Bon résultat");
            mReponse.setText("Réponse trouver en " + mNbEssai + " essai") ;
            mEssai.setText("");
            mVerifie.setText("Recommencer");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.verifie:
                if(mVerifie.getText() == "Entrer la valeur") {
                    this.mValeurExacte = Integer.parseInt(String.valueOf(mReponse.getText()));
                    mVerifie.setText("Vérifier");
                }else if(mVerifie.getText() == "Vérifier"){
                    try {
                        this.mValeurEntrer = Integer.parseInt(String.valueOf(mReponse.getText()));
                        this.verification();
                    } catch (Exception e) {

                    }
                }else {
                   this.initialisation();
                }
                break;
            default:
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.solution:
                this.mReponse.setText("La solution est : " + mValeurExacte);
                break;
            case R.id.reset:
                this.initialisation();
                break;
            case R.id.change_mode:
                this.changemode();
                break;
            default:
        }
        return true;
    }
}

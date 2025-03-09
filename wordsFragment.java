package com.example.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class wordsFragment extends Fragment {
    public ArrayList<String> dataCambridge = new ArrayList<String>();
    public String cont1 = null;
    public  Integer delayData=370;
    public String slctLevel = "select one";
    public String enWord,trWord,wordSyn,exSenEn,exSenTR,photoAdd,voiceAdd,wordLvl,wordType,svDate,wordTR2,preveousSyn;
    public String sltcType = "select one";
    Date date = new Date();
    public static ArrayList<String> wordDataDB = new ArrayList<String>();
    private ProgressDialog progressDialog;
    public RecyclerView recyclerWiev;
    ArrayList<String> t1 = new ArrayList<String>();ArrayList<String> t2 = new ArrayList<String>();ArrayList<String> t3 = new ArrayList<String>();ArrayList<String> t4 = new ArrayList<String>();
    ArrayList<String> t5 = new ArrayList<String>();ArrayList<String> t6 = new ArrayList<String>();ArrayList<String> t7 = new ArrayList<String>();ArrayList<String> t8 = new ArrayList<String>();


    public boolean saveDataCont = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_words, container, false);
        //ArrayList<String> t1 = new ArrayList<String>();ArrayList<String> t2 = new ArrayList<String>();ArrayList<String> t3 = new ArrayList<String>();ArrayList<String> t4 = new ArrayList<String>();
        //ArrayList<String> t5 = new ArrayList<String>();ArrayList<String> t6 = new ArrayList<String>();ArrayList<String> t7 = new ArrayList<String>();ArrayList<String> t8 = new ArrayList<String>();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        databaseControl dbControl = new databaseControl();
        //wordDataDB = dbControl.getData();
        wordDataDB = dbControl.getData();
        jsoupScraping jsoupScraping_ = new jsoupScraping();


        String ali = "ali=ler";

        System.out.println(ali.substring(ali.indexOf("=")+1));


        /*t1.add("sit");t1.add("all");t1.add("home");t1.add("say");t1.add("dog");
        t2.add("otur");t2.add("hepsi");t2.add("ev");t2.add("soyle");t2.add("kopek");
        t3.add("Verb");t3.add("Noun");t3.add("Noun");t3.add("verb");t3.add("noun");
        t4.add("A1");t4.add("B2");t4.add("B1");t4.add("A2");t4.add("C1");
        t1.add("sit");t1.add("all");t1.add("home");t1.add("say");t1.add("dog");
        t2.add("otur");t2.add("hepsi");t2.add("ev");t2.add("soyle");t2.add("kopek");
        t3.add("Verb");t3.add("Noun");t3.add("Noun");t3.add("verb");t3.add("noun");
        t4.add("A1");t4.add("B2");t4.add("B1");t4.add("A2");t4.add("C1");
        t1.add("sit");t1.add("all");t1.add("home");t1.add("say");t1.add("dog");
        t2.add("otur");t2.add("hepsi");t2.add("ev");t2.add("soyle"); t2.add("kopek");
        t3.add("Verb");t3.add("Noun");t3.add("Noun");t3.add("verb");t3.add("noun");
        t4.add("A1");t4.add("B2");t4.add("B1");t4.add("A2");t4.add("C1");*/

        RecyclerView recyclerWiev = view.findViewById(R.id.recyclerView);
        ConstraintLayout wordsCons = view.findViewById(R.id.wordsCons);
        ConstraintLayout addCons = view.findViewById(R.id.AddCons);
        ConstraintLayout consSaveDB = view.findViewById(R.id.conslayoutSaveDB);
        RadioGroup TypeCons = view.findViewById(R.id.radioType);
        RadioGroup LvlCons = view.findViewById(R.id.radioLevel);

        Button butSelectLvl = view.findViewById(R.id.butLevel);
        Button butSelectType = view.findViewById(R.id.butType);
        Button butAutoFill = view.findViewById(R.id.butAutoFilllWord);
        Button butPrononciation = view.findViewById(R.id.butCycPrononcation);
        Button butAdd = view.findViewById(R.id.buttonAdd);
        Button butSaveDB = view.findViewById(R.id.butSave);
        Button butBack = view.findViewById(R.id.butBackAddAddPage);

        EditText textTurkishSentence = view.findViewById(R.id.textExTurkishSentence);
        EditText textExEngSentence = view.findViewById(R.id.textExEngSentence);
        EditText textEnglishWord = view.findViewById(R.id.textEnglishWord);
        EditText textWordTurkish = view.findViewById(R.id.textWordTurMeaning);
        EditText textSynonyms = view.findViewById(R.id.textSynonyms);

        CardView wordSingleScreen = view.findViewById(R.id.wordSingleScreen);

        EditText textCycEnSentence = view.findViewById(R.id.wordCycExEn);
        EditText textCycTRSentence = view.findViewById(R.id.wordCycExTR);
        EditText textCycEnglishWord = view.findViewById(R.id.wordCycEng);
        EditText textCycWordTurkish = view.findViewById(R.id.wordCycTurk);
        EditText textCycSynonyms = view.findViewById(R.id.wordCycSyn);
        EditText textCycType = view.findViewById(R.id.wordCycType);
        EditText textCycLevel = view.findViewById(R.id.wordCycLvl);
        TextView textCycDate = view.findViewById(R.id.textCycDate);
        Button butCycPrononcation = view.findViewById(R.id.butCycPrononcation);
        Button butCycDelete = view.findViewById(R.id.butCycDelete);

        wordsCons.setVisibility(View.VISIBLE);
        addCons.setVisibility(View.INVISIBLE);
        TypeCons.setVisibility(View.INVISIBLE);
        LvlCons.setVisibility(View.INVISIBLE);
        wordSingleScreen.setVisibility(View.INVISIBLE);
        //butCalcelPager.setVisibility(View.INVISIBLE);

        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordsCons.setVisibility(View.INVISIBLE);
                addCons.setVisibility(View.VISIBLE);
                //lvlCons.setVisibility(View.INVISIBLE);




            }
        });

        butBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordsCons.setVisibility(View.VISIBLE);
                addCons.setVisibility(View.INVISIBLE);
            }
        });

        butSelectType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textEnglishWord.setEnabled(false);textTurkishSentence.setEnabled(false);textWordTurkish.setEnabled(false);textExEngSentence.setEnabled(false);textSynonyms.setEnabled(false);
                TypeCons.setVisibility(View.VISIBLE);
                butSelectType.setVisibility(View.INVISIBLE);
                butSelectLvl.setVisibility(View.INVISIBLE);
                butAutoFill.setVisibility(View.INVISIBLE);
                butPrononciation.setVisibility(View.INVISIBLE);
                textTurkishSentence.setVisibility(View.INVISIBLE);
                consSaveDB.setVisibility(View.INVISIBLE);
                cont1 = "type";
                textEnglishWord.setEnabled(true);textTurkishSentence.setEnabled(true);textWordTurkish.setEnabled(true);textExEngSentence.setEnabled(true);textSynonyms.setEnabled(true);
            }
        });

        butSelectLvl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textEnglishWord.setEnabled(false);textTurkishSentence.setEnabled(false);textWordTurkish.setEnabled(false);textExEngSentence.setEnabled(false);textSynonyms.setEnabled(false);
                LvlCons.setVisibility(View.VISIBLE);
                butSelectType.setVisibility(View.INVISIBLE);
                butSelectLvl.setVisibility(View.INVISIBLE);
                butAutoFill.setVisibility(View.INVISIBLE);
                butPrononciation.setVisibility(View.INVISIBLE);
                textTurkishSentence.setVisibility(View.INVISIBLE);
                consSaveDB.setVisibility(View.INVISIBLE);
                textEnglishWord.setEnabled(true);textTurkishSentence.setEnabled(true);textWordTurkish.setEnabled(true);textExEngSentence.setEnabled(true);textSynonyms.setEnabled(true);
                cont1 = "level";
            }
        });


        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    int startX = (int) motionEvent.getX();
                    int startY = (int) motionEvent.getY();
                    Log.e("x = ",Integer.toString(startX));
                    Log.e("y = ",Integer.toString(startY));
                    if(startX > 75 && startX < 723 && startY > 1110 && startY<1950){
                    }
                    else{
                        if(cont1 == "type"){
                        TypeCons.setVisibility(View.INVISIBLE);
                        butSelectType.setVisibility(View.VISIBLE);
                        butSelectLvl.setVisibility(View.VISIBLE);
                        butAutoFill.setVisibility(View.VISIBLE);
                        butPrononciation.setVisibility(View.VISIBLE);
                        textTurkishSentence.setVisibility(View.VISIBLE);
                        consSaveDB.setVisibility(View.VISIBLE);
                        cont1 = "null";}
                    }
                    if(startX > 390 && startX < 1020 && startY > 1110 && startY<1950){
                    }
                    else{
                        if(cont1 == "level"){
                            LvlCons.setVisibility(View.INVISIBLE);
                            butSelectType.setVisibility(View.VISIBLE);
                            butSelectLvl.setVisibility(View.VISIBLE);
                            butAutoFill.setVisibility(View.VISIBLE);
                            butPrononciation.setVisibility(View.VISIBLE);
                            textTurkishSentence.setVisibility(View.VISIBLE);
                            consSaveDB.setVisibility(View.VISIBLE);
                            cont1 = "null";}
                    }

                }
                return false;
            }
        });


        LvlCons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb=(RadioButton) view.findViewById(i);
                butSelectLvl.setText(rb.getText().toString());
                Log.e("mesaj = " , Integer.toString(i));
                LvlCons.setVisibility(View.INVISIBLE);
                butSelectType.setVisibility(View.VISIBLE);
                butSelectLvl.setVisibility(View.VISIBLE);
                butAutoFill.setVisibility(View.VISIBLE);
                butPrononciation.setVisibility(View.VISIBLE);
                textTurkishSentence.setVisibility(View.VISIBLE);
                consSaveDB.setVisibility(View.VISIBLE);
                slctLevel = rb.getText().toString();
            }
        });


        TypeCons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb2=(RadioButton) view.findViewById(i);
                butSelectType.setText(rb2.getText().toString());
                TypeCons.setVisibility(View.INVISIBLE);
                butSelectType.setVisibility(View.VISIBLE);
                butSelectLvl.setVisibility(View.VISIBLE);
                butAutoFill.setVisibility(View.VISIBLE);
                butPrononciation.setVisibility(View.VISIBLE);
                textTurkishSentence.setVisibility(View.VISIBLE);
                consSaveDB.setVisibility(View.VISIBLE);
                sltcType = rb2.getText().toString();
            }
        });



        butSaveDB.setOnClickListener(new View.OnClickListener() {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            public String nowDate = formatter.format(date);
            @Override
            public void onClick(View view) {
                if (textEnglishWord.getText().toString().matches("")) {
                    Toast.makeText(view.getContext(), "You did not enter a English Word", Toast.LENGTH_SHORT).show();saveDataCont=false;
                }
                if (textWordTurkish.getText().toString().matches("")) {
                    Toast.makeText(view.getContext(), "You did not enter a Turkish Meaning", Toast.LENGTH_SHORT).show();saveDataCont=false;
                }
                if (slctLevel == "select one") {
                    Toast.makeText(view.getContext(), "You did not chose Word Level", Toast.LENGTH_SHORT).show();saveDataCont=false;slctLevel="0";
                }
                if (sltcType == "select one") {
                    Toast.makeText(view.getContext(), "You did not chose a Word Type", Toast.LENGTH_SHORT).show();saveDataCont=false;sltcType="0";
                }

                if(saveDataCont && sltcType != "0" && slctLevel != "0"){
                    dbControl.addData("omaronaltrs@gmail.com",textEnglishWord.getText().toString(),textSynonyms.getText().toString(),textWordTurkish.getText().toString(),"",textExEngSentence.getText().toString(),textTurkishSentence.getText().toString()," "," ",sltcType,slctLevel,nowDate);
                    Toast.makeText(view.getContext(), "Your word has been successfully saved.", Toast.LENGTH_SHORT).show();
                    textEnglishWord.getText().clear();textWordTurkish.getText().clear();textTurkishSentence.getText().clear();textExEngSentence.getText().clear();textSynonyms.getText().clear();
                    butSelectType.setText("TYPE");
                    butSelectLvl.setText("LEVEL");
                    LvlCons.setEnabled(false);
                    TypeCons.setEnabled(false);
                    sltcType ="select one";
                    slctLevel ="select one";
                }
                else{Toast.makeText(view.getContext(), "Please enter the information completely.", Toast.LENGTH_SHORT).show();saveDataCont=false;
                }

                saveDataCont=true;


            }
        });


        butAutoFill.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                textWordTurkish.setText("");
                textExEngSentence.setText("");
                textTurkishSentence.setText("");
                textSynonyms.setText("");
                butSelectType.setText("");
                butSelectLvl.setText("");
                slctLevel = "select one";
                sltcType = "select one";
                textEnglishWord.setEnabled(false);

                try{
                dataCambridge = jsoupScraping_.getDataCambridge(textEnglishWord.getText().toString());
                ProgressDialog pd = new ProgressDialog(view.getContext());
                pd.setMessage("loading");
                pd.show();
                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    public void run() {
                        dataCambridge = jsoupScraping_.getDataCambridge(textEnglishWord.getText().toString());
                        //System.out.println(dataCambridge.get(0) + "----------------++++++++++++++++++++++++5555555555");
                        if(preveousSyn != dataCambridge.get(7)){
                        textWordTurkish.setText(dataCambridge.get(0));
                        textExEngSentence.setText(dataCambridge.get(2));
                        textTurkishSentence.setText(dataCambridge.get(3));
                        textSynonyms.setText(dataCambridge.get(7));
                        butSelectType.setText(dataCambridge.get(5));
                        butSelectLvl.setText(dataCambridge.get(6));
                        slctLevel = dataCambridge.get(6);
                        sltcType = dataCambridge.get(5);
                        preveousSyn = dataCambridge.get(7);
                        textEnglishWord.setEnabled(true);
                        pd.dismiss();}
                        else{delayData=1500;
                        }

                        //------------------------------------------------------

                        if(delayData == 1500){
                            handler2.postDelayed(new Runnable() {
                                public void run() {
                                        dataCambridge = jsoupScraping_.getDataCambridge(textEnglishWord.getText().toString());
                                        //System.out.println(dataCambridge.get(0) + "----------------++++++++++++++++++++++++5555555555");
                                        if(preveousSyn != dataCambridge.get(7)) {
                                            textWordTurkish.setText(dataCambridge.get(0));
                                            textExEngSentence.setText(dataCambridge.get(2));
                                            textTurkishSentence.setText(dataCambridge.get(3));
                                            textSynonyms.setText(dataCambridge.get(7));
                                            butSelectType.setText(dataCambridge.get(5));
                                            butSelectLvl.setText(dataCambridge.get(6));
                                            slctLevel = dataCambridge.get(6);
                                            sltcType = dataCambridge.get(5);
                                            preveousSyn = dataCambridge.get(7);}

                                        else{Toast.makeText(view.getContext(), "Check your internet connection.", Toast.LENGTH_SHORT).show();
                                            textWordTurkish.setText("");
                                            textExEngSentence.setText("");
                                            textTurkishSentence.setText("");
                                            textSynonyms.setText("");
                                            butSelectType.setText("");
                                            butSelectLvl.setText("");
                                            slctLevel = "select one";
                                            sltcType = "select one";}


                                        delayData = 350;
                                        textEnglishWord.setEnabled(true);
                                        pd.dismiss();

                                }
                            }, delayData);}


                    }
                }, delayData);}
                catch (Exception e){Toast.makeText(view.getContext(), "Fatal Error..", Toast.LENGTH_SHORT).show();}






                /*dataCambridge = jsoupScraping_.getDataCambridge(textEnglishWord.getText().toString());

                textWordTurkish.setText(dataCambridge.get(0));
                textExEngSentence.setText(dataCambridge.get(2));
                textTurkishSentence.setText(dataCambridge.get(3));
                textSynonyms.setText(dataCambridge.get(7));
                butSelectType.setText(dataCambridge.get(5));
                butSelectLvl.setText(dataCambridge.get(6));
                slctLevel = dataCambridge.get(6);
                sltcType = dataCambridge.get(5);*/

            }
        });


        recyclerWiev.addOnItemTouchListener(
                new RecyclerItemClickListener(view.getContext(), recyclerWiev ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        System.out.println(position);
                        wordSingleScreen.setVisibility(View.VISIBLE);
                        textCycEnglishWord.setText(t1.get(position));
                        textCycWordTurkish.setText(t2.get(position));
                        textCycLevel.setText(t3.get(position));
                        textCycType.setText(t4.get(position));
                        System.out.println(t8.get(2) + "///////////////////////////////");
                        textCycDate.setText(t8.get(position));
                        textCycSynonyms.setText(t5.get(position));
                        textCycTRSentence.setText(t7.get(position));
                        textCycEnSentence.setText(t6.get(position));
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        butCycPrononcation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        butCycDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordSingleScreen.setVisibility(View.INVISIBLE);
            }
        });


        //new listCycler().execute();



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Log.e("mesaj2>>>>>>",Integer.toString(wordDataDB.size()));
                for(int i=0; i < wordDataDB.size(); i++){
                    String wordData1 = (wordDataDB.get(i)).replace("{","").replace("}","");
                    String[] wordDataEnd = wordData1.split(",");
                    System.out.println("**************************************************");
                    for(int z=0;z<10;z++){
                        //(wordDataEnd[z].replace(" ", "").substring(0,wordDataEnd[z].indexOf("="))) == "date"
                        //System.out.println(wordDataEnd[z].replace(" ","").startsWith("L"));
                        if(wordDataEnd[z].replace(" ","").startsWith("L")){svDate = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t8.add(svDate);}
                        else if(wordDataEnd[z].replace(" ","").startsWith("A")){enWord = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t1.add(enWord);}
                        else if(wordDataEnd[z].replace(" ","").startsWith("G")){exSenEn = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t6.add(exSenEn);}
                        else if(wordDataEnd[z].replace(" ","").startsWith("X")){wordType = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t3.add(wordType);System.out.println(wordType);}
                        else if(wordDataEnd[z].replace(" ","").startsWith("K")){exSenTR = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t7.add(exSenTR);}
                        else if(wordDataEnd[z].replace(" ","").startsWith("F")){photoAdd = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);}
                        else if(wordDataEnd[z].replace(" ","").startsWith("B")){trWord = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t2.add(trWord);}
                        else if(wordDataEnd[z].replace(" ","").startsWith("D")){wordSyn = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t5.add(wordSyn);}
                        //else if(wordDataEnd[z].replace(" ","").startsWith("C")){wordTR2 = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t8.add(wordTR2);}   //date
                        else if(wordDataEnd[z].replace(" ","").startsWith("E")){voiceAdd = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);}
                        else if(wordDataEnd[z].replace(" ","").startsWith("M")){wordLvl = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t4.add(wordLvl);}
                        // else if(wordDataEnd[z].replace(" ","").startsWith("L")){svDate = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t8.add(svDate);System.out.println(svDate);}
                    }
                    System.out.println("**************************************************");
                }
                //t1.add("abc");t2.add("abc");t3.add("abc");t4.add("abc");
                //t1.add("a");t2.add("a");t3.add("a");t4.add("a");
                //t1.add("edf");t2.add("edf");t3.add("edf");t4.add("edf");
                Log.e("mesaj3>>>>>>",Integer.toString(t3.size()));
                MyAdapter myAdapter = new MyAdapter(view.getContext(),t1,t2,t3,t4);
                recyclerWiev.setHasFixedSize(false);
                recyclerWiev.setLayoutManager(new LinearLayoutManager(view.getContext()));
                recyclerWiev.setAdapter(myAdapter);
                //recyclerWiev.setNestedScrollingEnabled(false);
                inflater.inflate(R.layout.fragment_words, container, false);

            }
        }, 250);


        return view;
    }


/*
    private class listCycler extends AsyncTask<Void,Void,Void> {
        databaseControl dbControl = new databaseControl();

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            databaseControl dbControl = new databaseControl();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Bekle");
            progressDialog.setMessage("Geliyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();


        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDataDB = dbControl.getData();
            Log.e("Mesaj +++ +++ +++",Integer.toString(wordDataDB.size()));
            return null;
        }

        @Override
        protected void onPostExecute(Void avoid){
            super.onPostExecute(avoid);
            /*for(int i=0; i < wordDataDB.size(); i++){
                String wordData1 = (wordDataDB.get(i)).replace("{","").replace("}","");
                String[] wordDataEnd = wordData1.split(",");
                System.out.println("**************************************************");
                for(int z=0;z<10;z++){
                    //(wordDataEnd[z].replace(" ", "").substring(0,wordDataEnd[z].indexOf("="))) == "date"
                    //System.out.println(wordDataEnd[z].replace(" ","").startsWith("L"));
                    if(wordDataEnd[z].replace(" ","").startsWith("L")){svDate = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t8.add(svDate);}
                    else if(wordDataEnd[z].replace(" ","").startsWith("A")){enWord = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t1.add(enWord);}
                    else if(wordDataEnd[z].replace(" ","").startsWith("G")){exSenEn = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t6.add(exSenEn);}
                    else if(wordDataEnd[z].replace(" ","").startsWith("K")){exSenTR = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t7.add(exSenTR);}
                    else if(wordDataEnd[z].replace(" ","").startsWith("F")){photoAdd = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);}
                    else if(wordDataEnd[z].replace(" ","").startsWith("B")){trWord = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t2.add(trWord);}
                    else if(wordDataEnd[z].replace(" ","").startsWith("D")){wordSyn = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t5.add(wordSyn);System.out.println(wordSyn);}
                    //else if(wordDataEnd[z].replace(" ","").startsWith("C")){wordTR2 = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t8.add(wordTR2);}   //date
                    else if(wordDataEnd[z].replace(" ","").startsWith("E")){voiceAdd = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);}
                    else if(wordDataEnd[z].replace(" ","").startsWith("M")){wordLvl = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t4.add(wordLvl);}
                    else if(wordDataEnd[z].replace(" ","").startsWith("J")){wordType = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t3.add(wordType);}
                    // else if(wordDataEnd[z].replace(" ","").startsWith("L")){svDate = wordDataEnd[z].substring(wordDataEnd[z].indexOf("=")+1);t8.add(svDate);System.out.println(svDate);}
                }
                System.out.println("**************************************************");
            }

            MyAdapter myAdapter = new MyAdapter(getActivity(),t1,t2,t3,t4);
            recyclerWiev.setHasFixedSize(false);
            recyclerWiev.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerWiev.setAdapter(myAdapter);
            recyclerWiev.setNestedScrollingEnabled(false);
            progressDialog.dismiss();*/

        }
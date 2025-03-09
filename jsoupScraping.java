package com.example.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.data.SingleRefDataBufferIterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class jsoupScraping {
    public static String wordTR,wordEN,wordLvl,wordType,wordExEN,wordExTR,wordSyn,wordVoice,wordENLink;
    public Exception error;
    public void getDatalar(){
        getData getData = new getData();
        getData.execute();
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        System.out.println(wordTR);
        System.out.println(wordEN);
        System.out.println(wordLvl);
        System.out.println(wordType);
        System.out.println(wordExEN);
        System.out.println(wordSyn);
    }


    public static class getData extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute(){
            try {
                super.onPreExecute();
                //ProgressDialog m_Dialog = new ProgressDialog(jsoupScraping.this);
            }
            catch (Exception e){
                Log.e("Başaramadık" , "Abi");}

        }




        @Override
        protected Void doInBackground(Void... voids) {

                org.jsoup.nodes.Document document =  null;
                try {
                    document = Jsoup.connect("https://dictionary.cambridge.org/dictionary/english-turkish/" + wordENLink).timeout(3000).get();
                }
                catch (Exception e){
                    Log.e("Hata","Oluştu");
                }
                finally {
                    Elements elements = document.select("div>span[class='trans dtrans dtrans-se ']");
                    wordTR = elements.text();  wordTR = wordTR.substring(0,wordTR.indexOf(","));

                    elements = document.select("span>div>div>h2>span[class='hw dhw']");
                    wordEN = elements.get(0).text();

                    elements = document.select("span[class='def-info ddef-info']");
                    wordLvl = elements.get(0).text(); //wordLvl = wordLvl.substring(0,wordLvl.indexOf(" "));

                    elements = document.select("span[class='pos dpos']");
                    wordType = elements.get(0).text();  //wordType = wordType.substring(0,wordType.indexOf(" "));

                    elements = document.select("span[class='eg deg']");
                    wordExEN = elements.get(0).text();

                    elements = document.select("div[class='def ddef_d db']");
                    wordSyn = elements.get(0).text();  //wordEn = wordEn.substring(0,wordEn.indexOf(","));

                    Elements elementsVoice = document.select("span[class='daud']");

                    wordExTR = "empty";
                    wordVoice = "empty";


                    //String wordExTR = elements.text();wordEn = wordEn.substring(0,wordEn.indexOf(","));
                    //String wordVoice = elements.text();wordEn = wordEn.substring(0,wordEn.indexOf(","));


                    //System.out.println(elementsVoice.size());
                    //System.out.println("++++++++++++++++++++++++++++++++++++++++");
                    //for(Element elements111 : elementsVoice){System.out.println(elements111.text());}
                }
            return null;
        }


        @Override
        protected void onPostExecute(Void avoid){
            //System.out.println("oldu **********");
        }




    }

    public ArrayList<String> getDataCambridge(String wordEnLink){
        wordENLink = wordEnLink;

        ArrayList<String> datas = new ArrayList<String>();

        try{getData getData = new getData();
            datas.add(wordTR);datas.add(wordEN);datas.add(wordExEN);datas.add(wordExTR);datas.add(wordVoice);datas.add(wordType);datas.add(wordLvl);datas.add(wordSyn);
            getData.execute();}
        catch (Exception e){
            System.out.println("Başaramadık abi...");}
        finally {
                datas.add("abc");datas.add("abc");datas.add("abc");datas.add("abc");datas.add("abc");datas.add("abc");datas.add("abc");datas.add("abc");
        }
        return datas;
    }
}

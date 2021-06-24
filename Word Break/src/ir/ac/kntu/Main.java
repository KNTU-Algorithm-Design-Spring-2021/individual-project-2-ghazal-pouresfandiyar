package ir.ac.kntu;

import java.io.*;
import java.util.ArrayList;
import java.util.*;

public class Main {

    static HashMap<String , Boolean>  DICTIONARY ;
    static List<String> result = new ArrayList<>();

    public static void main(String []args) {
        DICTIONARY = readDictionaryFromFile();

        sentenceBreak("HELLOEVERYBODYTHISISME"); //hello everybody this is me
        reverseAndPrintResult();

        sentenceBreak("YOUCANLEARNENGLISHLANGUAGE"); //you van leatn english language
        reverseAndPrintResult();

        sentenceBreak("TREEISSTREET"); //tree is street
        reverseAndPrintResult();

        sentenceBreak("THEREISATREEINTHESTREET"); //there is a tree in the street
        reverseAndPrintResult();

        sentenceBreak("CALLSECURITYATMIAMIAIRPORTBECAUSEITHINKABOMBISABOUTTOGOOFF");
        reverseAndPrintResult();

    }

    public static HashMap<String , Boolean> readDictionaryFromFile (){
        HashMap<String , Boolean> DICTIONARY = new HashMap<>();
        File file = new File("words_dictionary.txt");

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String st;
        try {
            while ((st = br.readLine()) != null) {
                if(st.length() == 1 && !st.equals("a") && !st.equals("i")) {
                    DICTIONARY.put(st, false);
                }else {
                    DICTIONARY.put(st, true);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return DICTIONARY;
    }

    public static boolean sentenceBreak(String sentence) {
        sentence = sentence.toLowerCase();
        int size = sentence.length();

        if (size == 0)
            return true;

        if (isValid(sentence)) {
            result.add(sentence);
            return true;
        }

        for (int i = size; i >0 ; i--) {
            if ( isValid ( sentence.substring(0, i) )
                    && sentenceBreak (sentence.substring(i, size) )) {
                result.add(sentence.substring(0,i));
                return true;
            }
        }

        return false;
    }

    public static boolean isValid(String word){
        if(DICTIONARY.get(word) == null) {
            return false;
        } else if(DICTIONARY.get(word)) {
            return true;
        }
        return false;
    }

    public static void reverseAndPrintResult(){
        Collections.reverse(result);
        for(String str : result){
            System.out.print(str + " ");
        }
        System.out.println();
        result = new ArrayList<>();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package nfatodfa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;


/**
 *
 * @author rodri
 */
public class NFAtoDFA {

    public static ArrayList<ArrayList> stateSet = new ArrayList<>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //The HashMap will be used to save the NFA on the file
        HashMap<Integer, ArrayList<String>> states = new HashMap<>();
        String line;
        String fileName = "src\\NFAtoDFA\\Test_4.txt";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            
        } catch (FileNotFoundException e) {
            System.err.println("The File was not found");
            System.exit(0);
        }
        
        try {
            int currentState = 1;
            while ((line = br.readLine()) != null) {
                //The array s will be used so only the numbers will be used
                String [] s = line.split(",");
                ArrayList<String> temp = new ArrayList<>();
                //remove any special characters attached to the numbers
                for (int i = 0; i < s.length; i++){
                    s[i] = s[i].replaceAll(",", "");
                    s[i] = s[i].replaceAll("\\{", "");
                    s[i] = s[i].replaceAll("}", "");
                    s[i] = s[i].trim();  
                    //if the bufferedReader read empty, it will put the current 
                    //state number in place since it will not visit any other state
                    if (!s[i].equals("empty")){
                        temp.add(s[i]);
                    }
                }
                states.put(currentState, temp);
                currentState = currentState + 1;
            }
        } catch (IOException ex) {
            System.out.println("Error, nothing inside the text file.");
            System.exit(0);
        }
        //The array will be filled of the string values equivalent to the states 
        String [] stateNums = new String[states.size()];
        for (int c = 0; c < states.size(); c ++){
            int tempNum = c + 1;
            stateNums[c] = Integer.toString(tempNum);
        }
        stateSet = generateSubsets(stateNums);
        String finalPrint = stateSet.toString().replace("[", "{").replace("]", "}");
        System.out.println("State set of the equivalent DFA = " + finalPrint);
        displayEs(states);
        
    }
    
    private static ArrayList<ArrayList> generateSubsets(String [] stateNums) {
        //allSubs will contain the entire list of subsets
        ArrayList<ArrayList> allSubs = new ArrayList<>();
        //This arraylist will be used to replace the 
        //empty set with the string "empty"
        ArrayList<String> empty = new ArrayList<>();
        empty.add("empty");
        allSubs.add(new ArrayList<Integer>());
        //loop through each number as the leading number in each list 
        for (String nums : stateNums){
            //temp will be used to save the lists with the same leading number
            ArrayList<ArrayList> temp = new ArrayList<>();
            for (ArrayList<String> sub : allSubs){
                ArrayList<String> subSet = new ArrayList<>(sub);
                subSet.add(nums);
                temp.add(subSet);
            }
            allSubs.addAll(temp);
        }
        allSubs.set(0, empty);
        return allSubs;
    }

    private static void displayEs(HashMap<Integer, ArrayList<String>> states) {
        
        //Queue will hold the children of the current state 
        Queue<Integer> q = new LinkedList<Integer>();
        //Loop through the HashMap and get the final Children 
        for (Integer c : states.keySet()){
            //Holder will hold the hold the unchanged children to convert to queue
            ArrayList<String> holder = new ArrayList<>();
            holder = states.get(c);
            ArrayList<String> comp = new ArrayList<>();
            comp = states.get(c);
            //push all children to queue
            for (int w = 0; w < holder.size(); w++){
                int e = Integer.parseInt(holder.get(w));
                q.add(e);    
            }
            //loop through the queue
            for (Integer target: q) {
                //the ArrayList comp will hold the Children of the states in the queue 
                comp = states.get(target);
                int size = comp.size();
                //for loop to go though list and add any states that have not
                //been visited yet and add to the current State's list
                for (int k = 0;k < size; k++){
                    if (!target.equals(comp.get(k))){
                        holder.add(comp.get(k));
                    }
                }    
            }
            List<String> finalArrayList = holder.stream().distinct().collect(Collectors.toList());
            //clear the queue to prepare for the next state
            q.clear();
            Collections.sort(finalArrayList);
            //replace all the square brackets with correct brackets
            String finalPrint = finalArrayList.toString().replace("[", "{").replace("]", "}");
            //print the values
            System.out.println("E(" + c + ") = " + finalPrint);
            
        } 
    }
}



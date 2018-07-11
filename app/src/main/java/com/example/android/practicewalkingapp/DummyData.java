package com.example.android.practicewalkingapp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

public class DummyData {



        // public static HashMap<String, Integer> walks;
        private static ArrayList<String> walks;
        private static ArrayList<Double> distances;
        /**
         public static void populateMap() {
         walks = new HashMap<String, Integer>();
         walks.put("Cotefield", 1);
         walks.put("Woods", 1);
         walks.put("H and R", 2);
         walks.put("Castle walk", 3);
         walks.put("Beach walk", 4);
         walks.put("Island walk", 2);
         walks.put("Hill walk", 3);
         walks.put("Mountain walk", 4);
         walks.put("Nature walk", 6);
         walks.put("Trail walk", 3);
         walks.put("Farm walk", 4);
         walks.put("Sheep walk", 1);
         walks.put("English walk", 3);
         walks.put("Welsh walk", 4);
         walks.put("Scottish walk", 6);
         walks.put("Irish walk", 3);
         walks.put("Birdwatch walk", 4);
         walks.put("European walk", 1);
         walks.put("Football walk", 3);



         }
         */


        public static ArrayList<String> getWalks() {
            walks = new ArrayList<String>();
            walks.add("woods");
            walks.add("Castle");
            walks.add("Farm");
            walks.add("woods");
            walks.add("Castle");
            walks.add("Farm");
            walks.add("woods");
            walks.add("Castle");
            walks.add("Farm");
            walks.add("woods");
            walks.add("Castle");
            walks.add("Farm");
            walks.add("woods");
            walks.add("Castle");
            walks.add("Farm");
            walks.add("woods");
            walks.add("Castle");
            walks.add("Farm");
            return walks;
        }

        public static ArrayList<Double> getDistances() {
            distances = new ArrayList<Double>();
            distances.add(1.0);
            distances.add(3.0);
            distances.add(5.2);
            distances.add(1.0);
            distances.add(3.0);
            distances.add(5.2);
            distances.add(1.0);
            distances.add(3.0);
            distances.add(5.2);
            distances.add(1.0);
            distances.add(3.0);
            distances.add(5.2);
            distances.add(1.0);
            distances.add(3.0);
            distances.add(5.2);
            distances.add(1.0);
            distances.add(3.0);
            distances.add(5.2);
            return distances;
        }
/**
 public static HashMap<String, Integer> getAllData() {
 walks = new HashMap<String, Integer>();
 walks.put("Cotefield", 1);
 walks.put("Woods", 1);
 walks.put("H and R", 2);
 walks.put("Castle walk", 3);
 walks.put("Beach walk", 4);
 walks.put("Island walk", 2);
 walks.put("Hill walk", 3);
 walks.put("Mountain walk", 4);
 walks.put("Nature walk", 6);
 walks.put("Trail walk", 3);
 walks.put("Farm walk", 4);
 walks.put("Sheep walk", 1);
 walks.put("English walk", 3);
 walks.put("Welsh walk", 4);
 walks.put("Scottish walk", 6);
 walks.put("Irish walk", 3);
 walks.put("Birdwatch walk", 4);
 walks.put("European walk", 1);
 walks.put("Football walk", 3);

 return walks;
 }
 */
    }



package training;
import Interaction.InputOutput;
import Store.Item;
import facade.Facade;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class arrayList {

    /*private ArrayList<Item> itemList;

    public arrayList() {
        this.itemList = new ArrayList<>();
    }*/
    public static void main(String[] args) {

        /*String id = "id134";
        ArrayList<String> trainingList = new ArrayList<>();
        trainingList.add(new String("Ivan"));
        trainingList.add(new String("John"));
        trainingList.add(id);
        trainingList.add(new String("Sofia"));
        trainingList.add(new String("Empty"));
        trainingList.add(new String("English"));

        *//*for (String variable : trainingList) {
            if (variable.equals(id)) {
                trainingList.remove(variable);
               // System.out.println(variable.contains(id));
            }
        }*//* //error when removing during iteration
        System.out.println(trainingList);

        for(int i = 0; i<trainingList.size(); i++){
            String variable = trainingList.get(i);
            if(variable.contains(id)){
                trainingList.remove(variable);
                i--;
            }
        }
        System.out.println(trainingList);
    }*/
       /* ArrayList <Item> itemList = new ArrayList<>();
        Item item1 = new Item( "id123", "jeans", 100.50);
        Item item2 = new Item( "id124", "socks", 30.30);
        Item item3 = new Item( "id125", "white trousers", 250.30);
        Item item4 = new Item( "id126", "black trousers", 190.20);
        Item item5 = new Item( "id127", "hat", 530.30);
        Item item6 = new Item( "id128", "boots", 280.30);
        Item item7 = new Item( "id129", "gloves", 20.90);
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
        itemList.add(item5);
        itemList.add(item6);
        itemList.add(item7);

        System.out.println(itemList);*/
        DecimalFormat df = new DecimalFormat("#.00");
        double value = 100.0;
            /*value = value * 100;
            int temp = (int) value;
            value = temp / Math.pow(10, 2);*/
        System.out.println(df.format(value));
        }
    }
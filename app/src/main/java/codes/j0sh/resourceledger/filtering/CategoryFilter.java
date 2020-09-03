package codes.j0sh.resourceledger.filtering;

import java.util.ArrayList;

import codes.j0sh.resourceledger.objects.Category;
import codes.j0sh.resourceledger.objects.Item;
import codes.j0sh.resourceledger.utils.DatabaseHandler;

public class CategoryFilter {
    /**
     * Parse through the desired list of categories and check if an item possesses the category
     * @param set of desired categories
     * @return ArrayList of Items which contain at least one of the desired categories
     */
    /*
    public static ArrayList<Item> cateSort(ArrayList<Category> categories){
        ArrayList<Item> sorted = new ArrayList<>();
        DatabaseHandler dbhelper = new DatabaseHandler(getApplicationContext());
        ArrayList<Item> items = dbhelper.getAllItems();
        for(Category c: categories){
            for(Item i: items){
                if(i.getCatergories().contains(c)){
                    sorted.add(i);
                }
            }
        }
        return sorted;
    }
    */
}

package de.n04h.mc.industrial.recipeManager;

import org.bukkit.Material;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class vanillaRecipes {

    private static Material[] removable = {Material.IRON_INGOT, Material.IRON_NUGGET, Material.IRON_BLOCK,
                                            Material.GOLD_BLOCK, Material.GOLD_NUGGET, Material.GOLD_INGOT,
                                            Material.COAL, Material.COAL_BLOCK,
                                            Material.DIAMOND_BLOCK,
                                            Material.REDSTONE_BLOCK,
                                            Material.LAPIS_BLOCK};
    private static Material[] removableE = {};
    private static List<Material> remove = Arrays.asList(removableE);

    public static void removeRecipes(Plugin plugin) {
        Iterator<Recipe> it = plugin.getServer().recipeIterator();
        while(it.hasNext()){
            Recipe recipe = it.next();
            if(remove.contains(recipe.getResult().getType())){
                System.out.println("Removed: " + recipe.toString());
                it.remove();
            }
        }
    }

}

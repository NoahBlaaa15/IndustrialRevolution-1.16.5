package de.n04h.mc.industrial.recipeManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class industrialRecipe {
    
    private char[] alpha = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

    public industrialRecipe(Plugin plugin, Material material, int amount, String name, String[] lore, Enchantment[] enchantment, boolean unbreak, String key, String l1, String l2, String l3, Material...  recipe) {

        ItemStack i = new ItemStack(material);
        i.setAmount(amount);
        ItemMeta im = i.getItemMeta();
        if(name != null){
            im.setDisplayName(name);
        }
        if(lore != null){
            im.setLore(Arrays.asList(lore));
        }
        if(enchantment != null){
            for (Enchantment entch: enchantment
                 ) {
                im.addEnchant(entch, entch.getMaxLevel(), true);
            }
        }
        im.setUnbreakable(unbreak);
        i.setItemMeta(im);
        NamespacedKey keg = new NamespacedKey(plugin, key);
        ShapedRecipe shaped = new ShapedRecipe(keg,i);
        if(l2 == null) {
            shaped.shape(l1);
        }else if(l3 == null){
            shaped.shape(l1, l2);
        }else {
            shaped.shape(l1, l2, l3);
        }
        for(int n=0; n < recipe.length ; n++) {
            shaped.setIngredient(alpha[n], recipe[n]);
        }

        Bukkit.addRecipe(shaped);
    }

    public industrialRecipe(Plugin plugin, Material material, int amount, String name, String[] lore, Enchantment[] enchantment, boolean unbreak, String key, String l1, String l2, String l3, Material recipe) {
        ItemStack i = new ItemStack(material);
        i.setAmount(amount);
        ItemMeta im = i.getItemMeta();
        if(name != null){
            im.setDisplayName(name);
        }
        if(lore != null){
            im.setLore(Arrays.asList(lore));
        }
        if(enchantment != null){
            for (Enchantment entch: enchantment
            ) {
                im.addEnchant(entch, entch.getMaxLevel(), true);
            }
        }
        im.setUnbreakable(unbreak);
        i.setItemMeta(im);
        NamespacedKey keg = new NamespacedKey(plugin, key);
        ShapedRecipe shaped = new ShapedRecipe(keg,i);
        if(l2 == null) {
            shaped.shape(l1);
        }else if(l3 == null){
            shaped.shape(l1, l2);
        }else {
            shaped.shape(l1, l2, l3);
        }
        shaped.setIngredient('A', recipe);


        Bukkit.addRecipe(shaped);
    }
}

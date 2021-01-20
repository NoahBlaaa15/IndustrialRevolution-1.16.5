package de.n04h.mc.industrial;


import de.n04h.mc.industrial.machineManager.machineEvents;
import de.n04h.mc.industrial.recipeManager.industrialRecipes;
import de.n04h.mc.industrial.recipeManager.vanillaRecipes;
import org.bukkit.plugin.java.JavaPlugin;

public class IndustrialRevolution extends JavaPlugin{

    @Override
    public void onEnable() {
        super.onEnable();
        System.out.println("IndustrialRevolution ready!");

        vanillaRecipes.removeRecipes(this);
        industrialRecipes ir = new industrialRecipes(this);

        getServer().getPluginManager().registerEvents(new machineEvents(this), this);

        this.getCommand("sit").setExecutor(new machineEvents(this));
        this.getCommand("head").setExecutor(new machineEvents(this));
    }

    @Override
    public void onDisable() {
        super.onDisable();
        System.out.println("IndustrialRevolution disabled!");
    }

}

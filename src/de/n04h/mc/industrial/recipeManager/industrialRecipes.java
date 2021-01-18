package de.n04h.mc.industrial.recipeManager;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.Plugin;

public class industrialRecipes {

    public industrialRecipes(Plugin plugin){

        new industrialRecipe(plugin, Material.STRING, 4, "§aStripped String",
                new String[]{"§8Works just as a normal String",
                        "",
                        "§a§lUNCOMMON ITEM"},
                null,
                false,
                "string",
                "A", null, null,
                Material.WHITE_WOOL);

        new industrialRecipe(plugin, Material.LEATHER,1, "§aRecycled Leather",
                new String[]{"§8Works just as normal Leather",
                        "",
                        "§a§lUNCOMMON ITEM"},
                null,
                false,
                "leather",
                "AA", "AA", null,
                Material.ROTTEN_FLESH);

        new industrialRecipe(plugin, Material.QUARTZ_BLOCK,1, "§9Fake Quartz",
                new String[]{"§8Works just as normal Quartz",
                        "",
                        "§9§lRARE ITEM"},
                null,
                false,
                "quartz_block",
                "AA", "AA", null,
                Material.SUGAR);

        new industrialRecipe(plugin, Material.NAME_TAG,1, "§9Cheap Nametag",
                new String[]{"§8Works just as a normal Nametag",
                        "",
                        "§9§lRARE ITEM"},
                    null,
                    false,
                "name_tag",
                "AAA", "ABA", "ABA",
                Material.WHITE_WOOL, Material.IRON_INGOT);

        new industrialRecipe(plugin, Material.FIREWORK_ROCKET,1, "§6Propane powered Rocket",
                new String[]{"§8Requires no Elytra", "§8Cooldown: 3 seconds",
                        "",
                        "§6Item Ability: Rocket Boost §e§lRIGHT CLICK",
                        "§7Allows you to boost yourself",
                        "§7forward infinitely",
                        "",
                        "§6§lLEGENDARY ITEM" },
                null,
                true,
                "ppr",
                "AAA", "BAB", "AAA",
                Material.FIREWORK_ROCKET, Material.NETHERITE_SCRAP);

        new industrialRecipe(plugin, Material.BOW,1, "§cNetherite Orbital Marker",
                new String[]{"§8Used to D3ztr0y Raik", "§8Cooldown: 0 seconds",
                        "",
                        "§cItem Ability: Orbital Marker §e§lRIGHT CLICK",
                        "§7Allows you to fuck Raik",
                        "§7everywhere he is with a",
                        "§7fucking Orbital Strike",
                        "",
                        "§c§lTECHNICAL WONDER" },
                Enchantment.ARROW_INFINITE,
                true,
                "nom",
                "BBB", "BAB", "BBB",
                Material.BOW, Material.NETHERITE_INGOT);

        new industrialRecipe(plugin, Material.BEDROCK, 1, "§cNetherite God's Fortune",
                new String[]{"§8When everything fails,",
                        "he comes",
                        "§8Cooldown: 60 seconds",
                        "",
                        "§cItem Ability: God's Fortune §e§lAUTOMATIC",
                        "§7When you have one hearth left",
                        "§7you get completly healed and every Entity",
                        "§7gets pushed away from you",
                        "",
                        "§c§lTECHNICAL WONDER" },
                Enchantment.ARROW_INFINITE,
                true,
                "ngf",
                "AAA", "ABA", "AAA",
                Material.OBSIDIAN, Material.NETHERITE_BLOCK);

        new industrialRecipe(plugin, Material.IRON_INGOT,1, "§5Magnetized Iron",
                new String[]{"§8Attracks nearby Items", "§8Cooldown: 0 seconds",
                        "",
                        "§5Item Ability: Magnet §e§lPASSIV",
                        "§7Allows you to pick up",
                        "§7nearby Items",
                        "",
                        "§5§lEPIC ITEM" },
                Enchantment.ARROW_INFINITE,
                true,
                "mi",
                "BBB", "BAB", "BBB",
                Material.IRON_BLOCK, Material.GOLD_INGOT);

        new industrialRecipe(plugin, Material.BAMBOO,1, "§aArtificial Bamboo",
                new String[]{"§8Works just as normal Bamboo",
                        "§a§lUNCOMMON ITEM"},
                null,
                false,
                "bamboo",
                "A", "A", "A",
                Material.SUGAR_CANE);

        new industrialRecipe(plugin, Material.SEA_LANTERN,4, "§aSea Lantern",
                new String[]{"§8Blub Blub",
                        "§a§lUNCOMMON ITEM"},
                null,
                false,
                "sea_lantern",
                "BBB", "BAB", "BBB",
                Material.GLOWSTONE_DUST, Material.GLASS);

    }

}

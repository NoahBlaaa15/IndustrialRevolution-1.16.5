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

        new industrialRecipe(plugin, Material.SLIME_BALL, 4, "§aSlimey Ballsack",
                new String[]{"§8Works just as a normal Slyme",
                        "",
                        "§a§lUNCOMMON ITEM"},
                null,
                false,
                "slime",
                "AA", "AA", null,
                Material.MAGMA_CREAM);

        new industrialRecipe(plugin, Material.BLACK_DYE, 2, "§aCrushed Coal",
                new String[]{"§8Works just as a normal Black Dye",
                        "",
                        "§a§lUNCOMMON ITEM"},
                null,
                false,
                "black_dye",
                "A", null, null,
                Material.COAL);

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
                new Enchantment[]{Enchantment.ARROW_INFINITE},
                true,
                "nom",
                "BBB", "BAB", "BBB",
                Material.BOW, Material.NETHERITE_INGOT);

        new industrialRecipe(plugin, Material.NETHERITE_HELMET,1, "§cNetherite Toxic Personality",
                new String[]{"§8*OwO* why you so angry", "§8Cooldown: 0 seconds",
                        "",
                        "§cItem Ability: Toxic Personality §e§lPASSIV",
                        "§7If someone hurt you you hurt them",
                        "",
                        "§cSet Ability: God's Fortune §e§lAUTOMATIC",
                        "§7When you have one hearth left",
                        "§7you get completely healed and every Entity",
                        "§7gets pushed away from you",
                        "",
                        "§c§lTECHNICAL WONDER" },
                new Enchantment[]{Enchantment.ARROW_INFINITE, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE, Enchantment.PROTECTION_EXPLOSIONS},
                true,
                "ntp",
                "CCC", "CBC", "ADA",
                Material.OBSIDIAN, Material.NETHERITE_HELMET, Material.NETHERITE_INGOT, Material.FEATHER);

        new industrialRecipe(plugin, Material.NETHERITE_LEGGINGS,1, "§cNetherite Summer's Dream",
                new String[]{"§8*UwU* shits aggressively", "§8Cooldown: 0 seconds",
                        "",
                        "§cItem Ability: Summer's Dream §e§lRIGHT CLICK + SHIFT",
                        "§7You are strong like monkeyyy",
                        "",
                        "§cSet Ability: God's Fortune §e§lAUTOMATIC",
                        "§7When you have one hearth left",
                        "§7you get completely healed and every Entity",
                        "§7gets pushed away from you",
                        "",
                        "§c§lTECHNICAL WONDER" },
                new Enchantment[]{Enchantment.ARROW_INFINITE, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE, Enchantment.PROTECTION_EXPLOSIONS},
                true,
                "nsd",
                "CCC", "CBC", "ADA",
                Material.OBSIDIAN, Material.NETHERITE_LEGGINGS, Material.NETHERITE_INGOT, Material.ANVIL);

        new industrialRecipe(plugin, Material.NETHERITE_BOOTS,1, "§cNetherite Autumn's Wish",
                new String[]{"§8Light like a Feather", "§8Cooldown: 0 seconds",
                        "",
                        "§cItem Ability: Autumn's Wish §e§lPASSIV",
                        "§7Fly like the wind back in 1945",
                        "",
                        "§cSet Ability: God's Fortune §e§lAUTOMATIC",
                        "§7When you have one hearth left",
                        "§7you get completely healed and every Entity",
                        "§7gets pushed away from you",
                        "",
                        "§c§lTECHNICAL WONDER" },
                new Enchantment[]{Enchantment.ARROW_INFINITE, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE},
                true,
                "naw",
                "CCC", "CBC", "ADA",
                Material.OBSIDIAN, Material.NETHERITE_BOOTS, Material.NETHERITE_INGOT, Material.ELYTRA);


        new industrialRecipe(plugin, Material.NETHERITE_CHESTPLATE, 1, "§cNetherite Energy Condenser",
                new String[]{"§8When everything fails,",
                        "§8he comes",
                        "§8Cooldown: 60 seconds",
                        "",
                        "§cItem Ability: Overload §e§lRIGHT CLICK + SHIFT",
                        "§7Pushes every Entity away from you",
                        "",
                        "§cSet Ability: God's Fortune §e§lAUTOMATIC",
                        "§7When you have one hearth left",
                        "§7you get completely healed and every Entity",
                        "§7gets pushed away from you",
                        "",
                        "§c§lTECHNICAL WONDER" },
                new Enchantment[]{Enchantment.ARROW_INFINITE, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE},
                true,
                "ngf",
                "CCC", "CBC", "ADA",
                Material.OBSIDIAN, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_INGOT, Material.TOTEM_OF_UNDYING);

        new industrialRecipe(plugin, Material.IRON_INGOT,1, "§5Magnetized Iron",
                new String[]{"§8Attracks nearby Items", "§8Cooldown: 0 seconds",
                        "",
                        "§5Item Ability: Magnet §e§lPASSIV",
                        "§7Allows you to pick up",
                        "§7nearby Items",
                        "",
                        "§5§lEPIC ITEM" },
                new Enchantment[]{Enchantment.ARROW_INFINITE},
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

package de.n04h.mc.industrial.machineManager;


import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R3.EntityThrownTrident;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftTrident;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class machineEvents implements Listener, CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("sit")){
            if(commandSender instanceof Player){
                Player p = (Player) commandSender;
                p.sendMessage("§aYou sit down.");
                Location loc = p.getLocation();
                loc.setY(loc.getY() - 0.6D);
                Entity arrow = p.getWorld().spawnEntity(loc, EntityType.ARROW);
                arrow.setGravity(false);
                arrow.setPersistent(true);
                arrow.addPassenger(p);
                return true;
            }
        } else if(command.getName().equalsIgnoreCase("head")){
            if(commandSender instanceof Player) {
                Player p = (Player) commandSender;
                p.sendMessage("§aMa' Lady");
                ItemStack head = p.getInventory().getHelmet();
                ItemStack inhand = p.getInventory().getItemInMainHand();
                p.getInventory().setHelmet(inhand);
                p.getInventory().setItemInMainHand(head);
                return true;
            }
        }

        return false;
    }


    private List<Player> lockedRocket = new ArrayList<Player>();
    private List<Player> lockedChestplate = new ArrayList<Player>();

    private Plugin plugin;

    public machineEvents(Plugin pl){
        plugin = pl;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.hasItem()) {
                if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Propane powered Rocket")) {
                    e.setCancelled(true);
                    if (!(lockedRocket.contains(e.getPlayer())) && !(e.getPlayer().isSneaking()) ) {
                        Vector vector = e.getPlayer().getLocation().getDirection().multiply(3D);
                        e.getPlayer().setVelocity(vector);
                        e.getPlayer().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§6§lItem Ability: Rocket Boost"));
                        Location locP = e.getPlayer().getLocation();
                        locP.setY(locP.getY() + 1);
                        e.getPlayer().getWorld().spawnParticle(Particle.REDSTONE, locP, 15, new Particle.DustOptions(Color.fromRGB(255, 255, 255), 30));
                        e.getPlayer().getWorld().playEffect(locP, Effect.WITHER_SHOOT, 0);
                        lockedRocket.add(e.getPlayer());
                        BukkitScheduler scheduler = getServer().getScheduler();
                        scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                            @Override
                            public void run() {
                                lockedRocket.remove(e.getPlayer());
                            }
                        }, 45L);
                    }
                }
            }

        }else if(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR){
            try {
                if(e.getPlayer().isSneaking() && e.getPlayer().getInventory().getChestplate() != null && e.getPlayer().getInventory().getChestplate().getItemMeta().getDisplayName().equalsIgnoreCase("§cNetherite Energy Condenser") && !(lockedChestplate.contains(e.getPlayer()))){
                    List<Entity> ent = e.getPlayer().getNearbyEntities(10, 10, 10);
                    for (int i = 0; i < ent.size(); i++) {
                        Entity en = ent.get(i);
                        if(en instanceof LivingEntity && !(en instanceof Item) && !(en instanceof Villager) && !(en instanceof Dolphin)) {
                            Location difference = e.getPlayer().getLocation().subtract(en.getLocation());
                            Vector normalizedDifference = difference.toVector().normalize();
                            Vector multiplied = normalizedDifference.multiply(-10f);
                            multiplied.setY(10f);
                            en.setVelocity(multiplied);
                        }
                    }
                    e.getPlayer().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§c§lItem Ability: Overload"));
                    lockedChestplate.add(e.getPlayer());
                    BukkitScheduler scheduler = getServer().getScheduler();
                    scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            lockedChestplate.remove(e.getPlayer());
                        }
                    }, 45L);
                    e.getPlayer().getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, e.getPlayer().getLocation(), 50);
                    e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation(), Effect.ANVIL_LAND, 100);
                }
            }catch (NullPointerException ex){ }
        }

        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getClickedBlock().getBlockData() instanceof Stairs && !(e.getPlayer().isSneaking())){
                Location loc = e.getClickedBlock().getLocation();
                loc.setX(loc.getX() + 0.5D);
                loc.setZ(loc.getZ() + 0.5D);

                Player p = e.getPlayer();
                p.sendMessage("§6You sit down.");
                Entity arrow = p.getWorld().spawnEntity(loc, EntityType.ARROW);
                arrow.setGravity(false);
                arrow.setPersistent(true);
                arrow.addPassenger(p);
            }

        }

    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent e){
        if(e.getEntityType() == EntityType.VEX){
            e.getEntity().remove();
        }
    }

    @EventHandler
    public void onEntityClick(PlayerInteractEntityEvent e){
        if(e.getPlayer().isSneaking()) {
            if (e.getPlayer().getInventory().getLeggings() != null && e.getPlayer().getInventory().getLeggings().getItemMeta().getDisplayName().equalsIgnoreCase("§cNetherite Summer's Dream")) {
                e.setCancelled(true);
                e.getRightClicked().setInvulnerable(true);
                e.getPlayer().addPassenger(e.getRightClicked());
                e.getPlayer().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§c§lItem Ability: Summer's Dream"));
            }
        }
    }

    @EventHandler
    public void onExit(EntityDismountEvent e){
        e.getEntity().setInvulnerable(false);
        if(e.getDismounted().getType() == EntityType.ARROW){
            e.getEntity().sendMessage("§6You stand up.");
            e.getDismounted().remove();
        }
    }

    @EventHandler
    public void onPlayerInventory(InventoryCloseEvent e){
        if(e.getInventory() != null && e.getPlayer().getPassengers().toString() != "[]"){
            for (Entity et: e.getPlayer().getPassengers()
            ) {
                et.setInvulnerable(false);
                e.getPlayer().removePassenger(et);
            }
        }
    }

    @EventHandler
    public void onPlayerJump(PlayerJumpEvent e){
        if(e.getFrom().getBlock().getRelative(BlockFace.DOWN).getType() == Material.WHITE_CONCRETE){
            Location loc = e.getFrom();
            loc.setY(loc.getY() + 1);
            while(loc.getBlock().getType() != Material.WHITE_CONCRETE){
                loc.setY(loc.getY() + 1);
                if(loc.getY() >= 256){
                    return;
                }
            }
            if(loc.getBlock().getType() == Material.WHITE_CONCRETE){
                loc.setY(loc.getY() + 1);
                e.getPlayer().teleport(loc);
                e.getPlayer().playSound(loc, Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 5f);
            }

        }
    }

    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent e){
        if(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.WHITE_CONCRETE && e.isSneaking()){
            Location loc = e.getPlayer().getLocation();
            loc.setY(loc.getY() - 2);
            while(loc.getBlock().getType() != Material.WHITE_CONCRETE){
                loc.setY(loc.getY() - 1);
                if(loc.getY() <= 0){
                    return;
                }
            }
            if(loc.getBlock().getType() == Material.WHITE_CONCRETE){
                loc.setY(loc.getY() + 1);
                e.getPlayer().teleport(loc);
                e.getPlayer().playSound(loc, Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 1f);
            }
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e){
        if(e.getEntity().getShooter() instanceof Player){
            Player p = (Player) e.getEntity().getShooter();
            if(e.getEntity().getType() == EntityType.TRIDENT){
                try {
                    EntityThrownTrident t = ((CraftTrident) e.getEntity()).getHandle();
                    ItemStack thrownTrident = CraftItemStack.asBukkitCopy(t.trident);
                    if (thrownTrident.getEnchantments().containsKey(Enchantment.CHANNELING)) {
                        e.getHitEntity().getWorld().strikeLightning(e.getHitEntity().getLocation());
                    }
                }catch(NullPointerException ex){}
            }
        }
    }

    @EventHandler
    public void onShot(EntityShootBowEvent e){
        if(e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if(e.getBow().getItemMeta().getDisplayName().equalsIgnoreCase("§6Netherite Homing Bow")){

            }else if (e.getBow().getItemMeta().getDisplayName().equalsIgnoreCase("§cNetherite Orbital Marker")){
                e.getProjectile().remove();
                Entity orb = p.getTargetEntity(120);
                if(orb instanceof  LivingEntity) {
                    orb.getWorld().strikeLightningEffect(orb.getLocation());
                    orb.getWorld().playEffect(orb.getLocation(), Effect.END_GATEWAY_SPAWN, 0);
                    ((LivingEntity) orb).damage(((LivingEntity) orb).getHealth() * 10D);
                    if (orb instanceof Player) {
                        Bukkit.broadcastMessage("§c§lTECHNICAL WONDER§r: " + p.getDisplayName() + " orbital striked " + ((Player) orb).getDisplayName());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        try {
            if (e.getPlayer().getInventory().getItemInOffHand() != null && e.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName().equalsIgnoreCase("§5Magnetized Iron")) {
                List<Entity> ent = e.getPlayer().getNearbyEntities(4, 4, 4);
                for (int i = 0; i < ent.size(); i++) {
                    Entity en = ent.get(i);
                    if(en instanceof Item) {
                        Location difference = e.getPlayer().getLocation().subtract(en.getLocation());
                        Vector normalizedDifference = difference.toVector().normalize();
                        Vector multiplied = normalizedDifference.multiply(1f);
                        en.setVelocity(multiplied);
                    }
                }
            }
        }catch (NullPointerException ex){ }
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        e.setJoinMessage("§5The Big Brain §r" + p.getDisplayName() + "§5 joined and got §6§lelectrocuted");
        p.getWorld().strikeLightningEffect(p.getLocation());
        try {
            if (e.getPlayer().getInventory().getBoots() != null && e.getPlayer().getInventory().getBoots().getItemMeta().getDisplayName().equalsIgnoreCase("§cNetherite Autumn's Wish")) {
                p.setAllowFlight(true);
                p.setFlying(true);
            }
        }catch(NullPointerException ex){ }
    }
    @EventHandler
    public void onChange(PlayerArmorChangeEvent e){
        if(e.getSlotType() == PlayerArmorChangeEvent.SlotType.FEET){
            e.getPlayer().setAllowFlight(false);
            e.getPlayer().setFlying(false);
            try {
                if (e.getNewItem() != null && e.getNewItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cNetherite Autumn's Wish")) {
                    e.getPlayer().setAllowFlight(true);
                    e.getPlayer().setFlying(true);
                }
            }catch(NullPointerException ex){
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (p.getHealth() - e.getFinalDamage() <= 0) {
                if (p.getInventory().getChestplate() != null && p.getInventory().getChestplate().getItemMeta().getDisplayName().equalsIgnoreCase("§cNetherite Energy Condenser") && !(lockedChestplate.contains(p))) {
                    e.setCancelled(true);
                    p.setHealth(20);
                    List<Entity> ent = p.getNearbyEntities(10, 10, 10);
                    for (int i = 0; i < ent.size(); i++) {
                        Entity en = ent.get(i);
                        if (en instanceof LivingEntity && !(en instanceof Item) && !(en instanceof Villager) && !(en instanceof Dolphin)) {
                            Location difference = p.getLocation().subtract(en.getLocation());
                            Vector normalizedDifference = difference.toVector().normalize();
                            Vector multiplied = normalizedDifference.multiply(-10f);
                            multiplied.setY(10f);
                            en.setVelocity(multiplied);
                        }
                    }
                    p.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§c§lSet Ability: God's Fortune"));
                    lockedChestplate.add(p);
                    BukkitScheduler scheduler = getServer().getScheduler();
                    scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            lockedChestplate.remove(p);
                        }
                    }, 500L);
                    p.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, p.getLocation(), 50);
                    p.getWorld().playEffect(p.getLocation(), Effect.ANVIL_LAND, 100);
                }
            }
        }
    }

    @EventHandler
    public void onEDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            try {
                if (p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase("§cNetherite Toxic Personality")) {
                    if (e.getDamager() instanceof LivingEntity) {
                        ((LivingEntity) e.getDamager()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 400, 1));
                    }
                }
            } catch (NullPointerException ex) {
            }
        }
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            try {
                if (p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getItemMeta().getDisplayName().equalsIgnoreCase("§cNetherite Toxic Personality")) {
                    if (e.getEntity() instanceof LivingEntity) {
                        ((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 400, 1));
                    }
                }
            } catch (NullPointerException ex) {
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();
        e.setQuitMessage("§5The Big Brain §r" + p.getDisplayName() + "§5 left :(");
        p.getWorld().strikeLightningEffect(p.getLocation());
    }

    @EventHandler
    public void SoulBound(PlayerDropItemEvent e) {
        if (e.getItemDrop().getItemStack().getItemMeta().hasLore() && (e.getItemDrop().getItemStack().getItemMeta().getLore().contains("§6§lLEGENDARY ITEM")  || e.getItemDrop().getItemStack().getItemMeta().getLore().contains("§c§lTECHNICAL WONDER"))) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void SoulBoundDed(PlayerDeathEvent e){
        e.setKeepLevel(true);
        for (ItemStack i: e.getDrops()
             ) {
            if(i.getItemMeta().hasLore() && (i.getItemMeta().getLore().contains("§6§lLEGENDARY ITEM") || i.getItemMeta().getLore().contains("§5§lEPIC ITEM") || i.getItemMeta().getLore().contains("§c§lTECHNICAL WONDER"))){
                e.getItemsToKeep().add(i);
            }
        }
        for (ItemStack i : e.getItemsToKeep()){
            e.getDrops().remove(i);
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent e){
        if (e.getInventory().getResult().getItemMeta().hasLore() && e.getInventory().getResult().getItemMeta().getLore().contains("§c§lTECHNICAL WONDER")) {
            Bukkit.broadcastMessage("§c§lTECHNICAL WONDER§r: " + e.getWhoClicked().getName() + " crafted a " + e.getInventory().getResult().getItemMeta().getDisplayName());
            e.getWhoClicked().getWorld().strikeLightningEffect(e.getWhoClicked().getLocation());
        }
    }

    @EventHandler
    public void onDead(EntityDeathEvent e){
        if(e.getEntity() instanceof EnderDragon){
            e.getDrops().add(new ItemStack(Material.DRAGON_EGG));
        }
    }

    /*@EventHandler
    public void onSignChange(SignChangeEvent e){
            System.out.println(e.getLine(0));
    }*/

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(e.getBlock().getType() == Material.BEDROCK){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if(e.getBlock().getType().toString().contains("LOG")){
            if(e.getPlayer().isSneaking()){
                if(e.getPlayer().getInventory().getItemInMainHand().getType() == Material.NETHERITE_AXE){
                Location loc = e.getBlock().getLocation();

                breakLogsArround(loc);

                while(loc.getBlock().getType().toString().contains("LOG")) {
                    loc.getBlock().breakNaturally();
                    loc.setY(loc.getY() + 1);

                    breakLogsArround(loc);

                    if (loc.getY() >= 256) {
                        break;
                    }
                }
            }
        }
        }else if(e.getBlock().getType() == Material.BEDROCK){
            e.setCancelled(true);
        }
    }

    private void breakLogsArround(Location loc){
        if(loc.getBlock().getRelative(BlockFace.NORTH).getType().toString().contains("LOG")){
            loc.getBlock().getRelative(BlockFace.NORTH).breakNaturally();
        }
        if(loc.getBlock().getRelative(BlockFace.EAST).getType().toString().contains("LOG")){
            loc.getBlock().getRelative(BlockFace.EAST).breakNaturally();
        }
        if(loc.getBlock().getRelative(BlockFace.SOUTH).getType().toString().contains("LOG")){
            loc.getBlock().getRelative(BlockFace.SOUTH).breakNaturally();
        }
        if(loc.getBlock().getRelative(BlockFace.WEST).getType().toString().contains("LOG")){
            loc.getBlock().getRelative(BlockFace.WEST).breakNaturally();
        }
        if(loc.getBlock().getRelative(BlockFace.NORTH_EAST).getType().toString().contains("LOG")){
            loc.getBlock().getRelative(BlockFace.NORTH_EAST).breakNaturally();
        }
        if(loc.getBlock().getRelative(BlockFace.NORTH_WEST).getType().toString().contains("LOG")){
            loc.getBlock().getRelative(BlockFace.NORTH_WEST).breakNaturally();
        }
        if(loc.getBlock().getRelative(BlockFace.SOUTH_EAST).getType().toString().contains("LOG")){
            loc.getBlock().getRelative(BlockFace.SOUTH_EAST).breakNaturally();
        }
        if(loc.getBlock().getRelative(BlockFace.SOUTH_WEST).getType().toString().contains("LOG")){
            loc.getBlock().getRelative(BlockFace.SOUTH_WEST).breakNaturally();
        }
    }


}

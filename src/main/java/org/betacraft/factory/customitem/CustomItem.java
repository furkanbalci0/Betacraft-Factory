package org.betacraft.factory.customitem;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class CustomItem {

    //VARIABLES
    private ItemStack itemStack;
    private String name;
    private String lore;


    //CONSTRUCTORS
    public CustomItem(ItemStack itemStack) {
        this(itemStack, null, null);
    }

    public CustomItem(Material material) {
        this(new ItemStack(material), null, null);
    }

    public CustomItem(Material material, String name, String lore) {
        this.itemStack = new ItemStack(material);
        this.name = name;
        this.lore = lore;
    }

    public CustomItem(ItemStack itemStack, String name, String lore) {
        this.itemStack = itemStack;
        this.name = name;
        this.lore = lore;
        this.updateItem();
    }

    //GETTER FUNCTIONS
    public ItemStack getItemStack() {
        return itemStack;
    }

    public String getName() {
        return name;
    }

    public String getLore() {
        return lore;
    }

    //SETTER FUNCTIONS
    public CustomItem setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.updateItem();
        return this;
    }

    public CustomItem setName(String name) {
        this.name = name;
        this.updateItem();
        return this;
    }

    public CustomItem setLore(String lore) {
        this.lore = lore;
        this.updateItem();
        return this;
    }

    //OTHER FUNCTIONS
    public CustomItem setHideAttributes(boolean hide) {

        ItemMeta itemMeta = this.itemStack.getItemMeta();

        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        this.itemStack.setItemMeta(itemMeta);

        return this;

    }

    public CustomItem setHideEnchants(boolean hide) {

        ItemMeta itemMeta = this.itemStack.getItemMeta();

        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        this.itemStack.setItemMeta(itemMeta);

        return this;

    }

    public CustomItem setHideUnbreakable(boolean hide) {

        ItemMeta itemMeta = this.itemStack.getItemMeta();

        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        this.itemStack.setItemMeta(itemMeta);

        return this;

    }


    public CustomItem setGlow(boolean glow) {

        this.itemStack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);

        ItemMeta itemMeta = this.itemStack.getItemMeta();

        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);

        this.itemStack.setItemMeta(itemMeta);

        return this;

    }

    //STATIC FUNCTIONS
    public static ItemStack create(final ItemStack itemStack, String name, String lore){

        ItemStack item = itemStack.clone();

        final ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        if (name != null) {
            meta.setDisplayName(name);
        }
        if (lore != null) {
            meta.setLore(Arrays.asList(lore.split("\\|\\|")));
        }
        item.setItemMeta(meta);
        return item;

    }
    public static ItemStack create(final Material material, String name, String lore){
        return create(new ItemStack(material), name, lore);
    }

    public static ItemStack createSkull(String playerName) {

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());

        SkullMeta meta = (SkullMeta) skull.getItemMeta();

        meta.setOwner(playerName);

        skull.setItemMeta(meta);

        return skull;

    }

    private void updateItem() {

        ItemMeta itemMeta = this.itemStack.getItemMeta();

        if (name != null) {
            itemMeta.setDisplayName(name);
        }

        if (lore != null) {
            itemMeta.setLore(Arrays.asList(lore.split("\\|\\|")));
        }

        this.itemStack.setItemMeta(itemMeta);
    }
}

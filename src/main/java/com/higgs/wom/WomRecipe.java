package com.higgs.wom;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class WomRecipe
{
    private static ArrayList<WomRecipe> recipeRegistry = new ArrayList<WomRecipe>();
    private ItemStack result;
    private ArrayList<WomItemStack> ingredients = new ArrayList<WomItemStack>();
    private int[] skills;
    private EnumRecipe enumType;
    private String displayName;

    public enum EnumRecipe
    {
        MINING, HERBALISM, BLACKSMITHING, ENGINEERING, COOKING, JEWELCRAFTING
    }

    public WomRecipe(ItemStack result, int[] skills, EnumRecipe enumType, String displayName, ArrayList<WomItemStack> ingredients)
    {
        this.result = result;
        this.skills = skills; //format: [0] = lowest level able to be crafted at, [1] = when crafting gives 50% chance to level, [2] = when crafting gives 25% chance to level, and [3] = when crafting gives no level
        this.enumType = enumType;
        this.displayName = displayName;
        this.ingredients = ingredients;
    }

    public WomRecipe(ItemStack result, int[] skills, EnumRecipe enumType, String displayName)
    {
        this.result = result;
        this.skills = skills; //format: [0] = lowest level able to be crafted at, [1] = when crafting gives 50% chance to level, [2] = when crafting gives 25% chance to level, and [3] = when crafting gives no level
        this.enumType = enumType;
        this.displayName = displayName;
    }

    public ItemStack getResult()
    {
        return this.result;
    }

    public ArrayList<WomItemStack> getIngredients()
    {
        return this.ingredients;
    }

    public int getResultAmount()
    {
        return this.result.stackSize;
    }

    public EnumRecipe getEnumType()
    {
        return this.enumType;
    }

    public int getMaxSkillUpLevel()
    {
        return this.skills[0];
    }

    public int getMedSkillUpLevel()
    {
        return this.skills[1];
    }

    public int getMinSkillUpLevel()
    {
        return this.skills[2];
    }

    public int getNoSkillUpLevel()
    {
        return this.skills[3];
    }

    public void setDisplayName(String set)
    {
        this.displayName = set;
    }

    public String getDisplayName()
    {
        return this.displayName;
    }

    public void addIngredients(WomItemStack womItemStack)
    {
        this.ingredients.add(womItemStack);
    }

    public static void addRecipe(WomRecipe newRecipe)
    {
        recipeRegistry.add(newRecipe);
    }

    public static ArrayList<WomRecipe> getRecipes(EnumRecipe type)
    {
        ArrayList<WomRecipe> result = new ArrayList<WomRecipe>();

        for(WomRecipe recipe : recipeRegistry)
        {
            if(recipe.getEnumType() == type)
            {
                result.add(recipe);
            }
        }

        return result;
    }
}

package com.higgs.wom.client.gui;

import com.higgs.wom.WomRecipe;

public class WomGuiListItem
{
    private int id;
    private int defaultY;
    private boolean selected;
    private boolean highlighted;
    private WomRecipe recipe;
    private boolean craftable = true;
    private boolean hasSkill;
    private boolean hasIngredients;
    private boolean isInvFull;
    private String displayString;

    public WomGuiListItem(WomRecipe recipe, int listItemId, int defaultY, String displayString)
    {
        this.recipe = recipe;
        this.id = listItemId;
        this.defaultY = defaultY;
        this.displayString = displayString;
    }

    public void setDisplayString(String set)
    {
        this.displayString = set;
    }

    public String getDisplayString()
    {
        return this.displayString;
    }

    public void setHasSkill(boolean set)
    {
        this.hasSkill = set;
    }

    public boolean getHasSkill()
    {
        return this.hasSkill;
    }

    public void setCraftable(boolean set)
    {
        this.craftable = set;
    }

    public boolean getCraftable()
    {
        return this.hasSkill && this.hasIngredients && !this.isInvFull;
    }

    public void setIsInvFull(boolean set)
    {
        this.isInvFull = set;
    }

    public void setDefaultY(int set)
    {
        this.defaultY = set;
    }

    public int getDefaultY()
    {
        return this.defaultY;
    }

    public void setRecipe(WomRecipe set)
    {
        this.recipe = set;
    }

    public WomRecipe getRecipe()
    {
        return this.recipe;
    }

    public boolean getSelected()
    {
        return this.selected;
    }

    public void setSelected(boolean set)
    {
        this.selected = set;
    }

    public boolean getHighlighted()
    {
        return this.highlighted;
    }

    public void setHighlighted(boolean set)
    {
        this.highlighted = set;
    }

    public void setHasIngredients(boolean set)
    {
        this.hasIngredients = set;
    }

    public boolean getHasIngredients()
    {
        return this.hasIngredients;
    }
}

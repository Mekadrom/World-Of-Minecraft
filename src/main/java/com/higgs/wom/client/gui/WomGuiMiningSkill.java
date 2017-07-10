package com.higgs.wom.client.gui;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.WomItemStack;
import com.higgs.wom.WomRecipe;
import com.higgs.wom.entitydata.WomPlayerData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class WomGuiMiningSkill extends WomGuiWindow
{
    private ArrayList<WomGuiListItem> listItems = new ArrayList<WomGuiListItem>();
    private WomGuiListItem selected = null;
    private int craftingButtonState;
    private int craftingAllButtonState;
    private int craftingExitButtonState;

    public WomGuiMiningSkill(EntityPlayer player)
    {
        this.player = player;
        this.width = 512;
        this.height = 512;
        displayX = 150;
        displayY = 100;//(this.mc.displayWidth / 2) - (256) + 100;
        this.title = "Mining";
        this.playerData = WomPlayerData.get(player);
    }

    public void initGui()
    {
        super.initGui();

        int y = 0;

        int index = 0;
        int defaultListItemWidth = 13;

        for(WomRecipe recipe : WomRecipe.getRecipes(WomRecipe.EnumRecipe.MINING))
        {
            this.listItems.add(new WomGuiListItem(recipe, index, y + (index * defaultListItemWidth), recipe.getDisplayName()));
            index++;
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float depth, int mouseX, int mouseY)
    {
        if(this.selected == null)
        {
            this.craftingButtonState = -1;
        }

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        if(dragging)
        {
            windowDragged(mouseX, mouseY);
        }

        this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowSkill.png"));

        drawTexturedModalRect(getGuiX(), getGuiY(), 0, 0, 256, 256);

        this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowElements.png"));

        drawTexturedModalRect(getGuiX() + 4, getGuiY() + 2, 0, 64, 16, 16);

        this.fontRendererObj.drawString(title, getGuiX() - (fontRendererObj.getStringWidth(title) / 2) + 129, getGuiY() + 2, 0xf4d442);

        this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowElements.png"));

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        int miningSkill = playerData.getMiningSkill();
        String skillLevelString = miningSkill + "/";
        if(miningSkill <= 75)
        {
            drawSkillBar(miningSkill, 75);
            skillLevelString += "75";
        }
        else if(miningSkill > 75 && miningSkill <= 150)
        {
            drawSkillBar(miningSkill, 150);
            skillLevelString += "150";
        }
        else if(miningSkill > 150 && miningSkill <= 225)
        {
            drawSkillBar(miningSkill, 225);
            skillLevelString += "225";
        }
        else if(miningSkill > 225 && miningSkill <= 300)
        {
            drawSkillBar(miningSkill, 300);
            skillLevelString += "300";
        }
        this.fontRendererObj.drawString(skillLevelString, getGuiX() - (fontRendererObj.getStringWidth(title) / 2) + 127, getGuiY() + 14, 0xffffff);

        this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowElements.png"));

        if(this.closeButtonDepressed)
        {
            drawTexturedModalRect(getGuiX() + (249), getGuiY() + (3), 49, 64, 8, 8);
        }
        else
        {
            drawTexturedModalRect(getGuiX() + (249), getGuiY() + (2), 49, 64, 8, 8);
        }

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowElements.png"));

        drawCraftingButtons();

        if(this.player.inventory.getFirstEmptyStack() == -1)
        {
            if(this.selected != null)
            {
                selected.setIsInvFull(true);
            }
        }
        else
        {
            if(this.selected != null)
            {
                selected.setIsInvFull(false);
            }
        }

        drawListItems(mouseX, mouseY);
    }

    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        int miningSkill = playerData.getMiningSkill();

        this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowElements.png"));

        if(miningSkill <= 75)
        {
            drawTexturedModalRect(88, 31, map(miningSkill, 75,  341), 9, 341, 9);
        }
        else if(miningSkill > 75 && miningSkill < 150)
        {
            drawTexturedModalRect(88, 31, map(miningSkill, 150, 341), 9, 341, 9);
        }
    }

    private void drawCraftingButtons()
    {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        if(this.craftingButtonState == -1)
        {
            drawTexturedModalRect(getGuiX() + 192, getGuiY() + 181, 0, 121, 29, 8);
        }
        else if(this.craftingButtonState == 0)
        {
            drawTexturedModalRect(getGuiX() + 192, getGuiY() + 181, 0, 112, 29, 8);
        }
        else if(this.craftingButtonState == 1)
        {
            drawTexturedModalRect(getGuiX() + 192, getGuiY() + 180, 0, 112, 29, 8);
        }

        if(this.craftingAllButtonState == -1)
        {
            drawTexturedModalRect(getGuiX() + 130, getGuiY() + 181, 0, 139, 30, 8);
        }
        else if(this.craftingAllButtonState == 0)
        {
            drawTexturedModalRect(getGuiX() + 130, getGuiY() + 181, 0, 130, 30, 8);
        }
        else if(this.craftingAllButtonState == 1)
        {
            drawTexturedModalRect(getGuiX() + 130, getGuiY() + 180, 0, 130, 30, 8);
        }

        if(this.craftingExitButtonState == -1)
        {
            drawTexturedModalRect(getGuiX() + 224, getGuiY() + 181, 0, 157, 29, 8);
        }
        else if(this.craftingExitButtonState == 0)
        {
            drawTexturedModalRect(getGuiX() + 224, getGuiY() + 181, 0, 148, 29, 8);
        }
        else if(this.craftingExitButtonState == 1)
        {
            drawTexturedModalRect(getGuiX() + 224, getGuiY() + 180, 0, 148, 29, 8);
        }
    }

    private void drawSkillBar(int skill, int max)
    {
        int rightmostBound = map(skill, max, 171);

        drawTexturedModalRect(getGuiX() + 44, getGuiY() + 14, 0, 0, rightmostBound, 7);
    }

    private void drawListItems(int mouseX, int mouseY)
    {
        int skillLevel = player.getDataWatcher().getWatchableObjectInt(WomPlayerData.MINING_WATCHER);//WomPlayerData.get(player).getMiningSkill();

        for(WomGuiListItem listItem : this.listItems)
        {
            Color textColor;
            Color listItemBoxColor = new Color(0xff, 0xff, 0xff, 0x00);//0x00000000;

            if(listItem.getSelected())
            {
                if(skillLevel < listItem.getRecipe().getMaxSkillUpLevel())
                {
                    listItemBoxColor = new Color(0xff, 0x00, 0x00, 0xb0);//boxColor = 16711680;//0xff0000ff; //red
                    listItem.setHasSkill(false);
                }
                else if(skillLevel >= listItem.getRecipe().getMaxSkillUpLevel() && skillLevel < listItem.getRecipe().getMedSkillUpLevel())
                {
                    listItemBoxColor = new Color(0xd3, 0x6d, 0x00, 0xb0);//boxColor = 13856000;//0xd36d0050; //orange
                    listItem.setHasSkill(true);
                }
                else if(skillLevel >= listItem.getRecipe().getMedSkillUpLevel() && skillLevel < listItem.getRecipe().getMinSkillUpLevel())
                {
                    listItemBoxColor = new Color(0xff, 0xff, 0x00, 0xb0);//boxColor = 16776969;//0xffff0950; //yellow
                    listItem.setHasSkill(true);
                }
                else if(skillLevel >= listItem.getRecipe().getMinSkillUpLevel() && skillLevel < listItem.getRecipe().getNoSkillUpLevel())
                {
                    listItemBoxColor = new Color(0x01, 0x89, 0x01, 0xb0);//boxColor = 100609;//0x01890150; //green
                    listItem.setHasSkill(true);
                }
                else
                {
                    listItemBoxColor = new Color(0x4f, 0x4f, 0x4f, 0xb0);//boxColor = 5197647;//0x4f4f4f50; //grey
                }

                drawCraftingRecipeAndCheckForIngredients(listItem);

                textColor = new Color(0xff, 0xff, 0xff, 0xff);//textColor = 16777215;//0xffffff; //white
            }
            else
            {
                if(listItem.getHighlighted())
                {
                    listItemBoxColor = new Color(0xff, 0xff, 0xff, 0x50);//boxColorFaded = 16777215;//0xffffff50; //white
                }
                else
                {
                    listItemBoxColor = new Color(0x00, 0x00, 0x00, 0x00);//boxColorFaded = 0;//0x00000000; //black, but with no opacity
                }

                if(skillLevel < listItem.getRecipe().getMaxSkillUpLevel())
                {
                    textColor = new Color(0xff, 0x00, 0x00, 0xff);//textColor = 16711680;//0xff0000ff; //red
                    listItem.setHasSkill(false);
                }
                else if(skillLevel >= listItem.getRecipe().getMaxSkillUpLevel() && skillLevel < listItem.getRecipe().getMedSkillUpLevel())
                {
                    textColor = new Color(0xd3, 0x6d, 0x00, 0xff);//textColor = 13856000;//0xd36d00ff; //orange
                    listItem.setHasSkill(true);
                }
                else if(skillLevel >= listItem.getRecipe().getMedSkillUpLevel() && skillLevel < listItem.getRecipe().getMinSkillUpLevel())
                {
                    textColor = new Color(0xff, 0xff, 0x00, 0xff);//textColor = 16776960;//0xffff00ff; //yellow
                    listItem.setHasSkill(true);
                }
                else if(skillLevel >= listItem.getRecipe().getMinSkillUpLevel() && skillLevel < listItem.getRecipe().getNoSkillUpLevel())
                {
                    textColor = new Color(0x01, 0x89, 0x01, 0xff);//textColor = 100609;//0x018901ff; //green
                    listItem.setHasSkill(true);
                }
                else
                {
                    textColor = new Color(0x4f, 0x4f, 0x4f, 0xff);//textColor = 5197647;//0x4f4f4fff; //grey
                    listItem.setHasSkill(true);
                }
            }

            if(isInListItemArea(mouseX, mouseY))
            {
                if(isInListItem(listItem.getDefaultY(), mouseX, mouseY))
                {
                    listItem.setHighlighted(true);
                }
                else
                {
                    listItem.setHighlighted(false);
                }
            }
            else
            {
                listItem.setHighlighted(false);
            }

            drawRect(getGuiX() + 5, getGuiY() + listItem.getDefaultY() + scrollY + 35,
                    getGuiX() + 117, getGuiY() + listItem.getDefaultY() + scrollY + 35 + 11, listItemBoxColor.getRGB());
            fontRendererObj.drawString(listItem.getDisplayString(), getGuiX() + 11, getGuiY() + scrollY + listItem.getDefaultY() + 36, textColor.getRGB(), true);
        }
    }

    private void drawCraftingRecipeAndCheckForIngredients(WomGuiListItem listItem)
    {
        WomRecipe recipe = listItem.getRecipe();

        this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowElements.png"));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(getGuiX() + 133, getGuiY() + 42, 48, 80, 19, 19);
        guiDrawItemIcon(recipe.getResult(), getGuiX() + 135, getGuiY() + 43);

        fontRendererObj.drawString("Requires: forge.", getGuiX() + 134, getGuiY() + 64, 0xf3d442, true);
        fontRendererObj.drawString("Reagents:", getGuiX() + 134, getGuiY() + 76, 0xf3d442, true);
        fontRendererObj.drawString(recipe.getResult().getDisplayName(), getGuiX() + 160, getGuiY() + 44, 0xf4d442, true);

        int yOffset = 55;

        for(WomItemStack womItemStack : recipe.getIngredients())
        {
            this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowElements.png"));
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            drawTexturedModalRect(getGuiX() + 133, getGuiY() + yOffset + 32, 48, 80, 19, 19);
            drawTexturedModalRect(getGuiX() + 151, getGuiY() + yOffset + 33, 48, 104, 53, 21);
            fontRendererObj.drawSplitString(womItemStack.getItemStack().getDisplayName(), getGuiX() + 156, getGuiY() + yOffset + 33, 40, 0xffffff);
            guiDrawItemIcon(womItemStack.getItemStack(), getGuiX() + 134, getGuiY() + yOffset + 32);

            yOffset += 35;
        }

        InventoryPlayer inventory = player.inventory;

        boolean[] args = new boolean[selected.getRecipe().getIngredients().size()];

        for(boolean bool : args)
        {
            bool = false;
        }

        for(int i = 0; i < inventory.getSizeInventory(); i++)
        {
            ItemStack stackInSlot = inventory.getStackInSlot(i);

            for(int j = 0; j < selected.getRecipe().getIngredients().size(); j++)
            {
                WomItemStack womItemStack = selected.getRecipe().getIngredients().get(j);

                if(stackInSlot != null && womItemStack != null)
                {
                    if(stackInSlot.getItem() == womItemStack.getItemStack().getItem() && stackInSlot.stackSize >= womItemStack.getItemStack().stackSize)
                    {
                        args[j] = true;
                    }
                }
            }
        }

        selected.setHasIngredients(areAllTrue(args));
    }

    private void guiDrawItemIcon(ItemStack item, int x, int y)
    {
        this.mc.getTextureManager().bindTexture(TextureMap.locationItemsTexture);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        IIcon icon = item.getItem().getIcon(item, 0);
        GL11.glEnable(GL11.GL_BLEND);
        this.drawTexturedModelRectFromIcon(x, y, icon, 16, 16);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public void scrollUp()
    {
        scrollY -= scrollIncrement;
    }

    public void scrollDown()
    {
        scrollY += scrollIncrement;
    }

    private int map(int toMap, int toMapHighBound, int mapHighBound)
    {
        double mapDiv = (double)toMap / (double)toMapHighBound;

        return (int)(mapDiv * (double)mapHighBound);
    }

    /**
     * Called when the mouse is clicked.
     */
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button)
    {
        super.mouseClicked(mouseX, mouseY, button);

        if(isInDragArea(mouseX, mouseY))
        {
            dragging = true;
            lastDragX = mouseX - dragX;
            lastDragY = mouseY - dragY;
        }
        else if(isInXButtonArea(mouseX, mouseY))
        {
            dragging = false;
            player.getDataWatcher().updateObject(WomPlayerData.MINING_WATCHER, player.getDataWatcher().getWatchableObjectInt(WomPlayerData.MINING_WATCHER) + 1);
            WomPlayerData.get(this.player).syncAll();
            this.closeButtonDepressed = true;
        }
        else if(isInCraftingButtonArea(mouseX, mouseY))
        {
            if(isOnCreateButton(mouseX, mouseY))
            {
                dragging = false;
                if(this.selected != null)
                {
                    if(selected.getCraftable())
                    {
                        craftCurrentRecipe();
                    }
                }
                this.craftingButtonState = 0;
                this.craftingAllButtonState = 1;
                this.craftingExitButtonState = 1;
                this.closeButtonDepressed = false;
            }
            else if(isOnCreateAllButton(mouseX, mouseY))
            {
                dragging = false;
                if(this.selected != null)
                {
                    while(selected.getCraftable())
                    {
                        craftCurrentRecipe();
                    }
                }
                this.craftingButtonState = 1;
                this.craftingAllButtonState = 0;
                this.craftingExitButtonState = 1;
                this.closeButtonDepressed = false;
            }
            else if(isOnCreateExitButton(mouseX, mouseY))
            {
                dragging = false;
                if(this.selected != null)
                {
                    while(selected.getCraftable())
                    {
                        craftCurrentRecipe();
                    }
                }
                this.craftingButtonState = 1;
                this.craftingAllButtonState = 1;
                this.craftingExitButtonState = 0;
                this.closeButtonDepressed = false;
            }
        }
        else
        {
            dragging = false;
            this.closeButtonDepressed = false;
            this.craftingButtonState = 1;
            this.craftingAllButtonState = 1;
            this.craftingExitButtonState = 1;
        }

        if(isInListItemArea(mouseX, mouseY))
        {
            for(WomGuiListItem listItem : this.listItems)
            {
                if(isInListItem(listItem.getDefaultY(), mouseX, mouseY))
                {
                    listItem.setSelected(true);
                    this.selected = listItem;
                }
                else
                {
                    listItem.setSelected(false);
                }
            }
        }
        else if(isInButtonsArea(mouseX, mouseY))
        {

        }
        else
        {
            for(WomGuiListItem listItem : this.listItems)
            {
                listItem.setSelected(false);
            }

            this.selected = null;
        }
    }

    /**
     * Called when a mouse button is pressed and the mouse is moved around. Parameters are : mouseX, mouseY,
     * lastButtonClicked & timeSinceMouseClick.
     */
    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int lastButtonClicked, long timeSinceMouseClick)
    {
        if(isInListItemScrollArea(mouseX, mouseY))
        {

        }
    }

    /**
     * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
     * mouseMove, which==0 or which==1 is mouseUp
     */
    @Override
    protected void mouseMovedOrUp(int mouseX, int mouseY, int which)
    {
        super.mouseMovedOrUp(mouseX, mouseY, which); //Forge, Call parent to release buttons

        if(which == 0)
        {
            if(isInXButtonArea(mouseX, mouseY))
            {
                this.player.closeScreen();
                this.closeButtonDepressed = false;
            }
            else if(isInCraftingButtonArea(mouseX, mouseY))
            {
                this.craftingButtonState = 1;
            }
            else if(isOnCreateAllButton(mouseX, mouseY))
            {
                this.craftingAllButtonState = 1;
            }
            else if(isOnCreateExitButton(mouseX, mouseY))
            {
                this.craftingExitButtonState = 1;
                this.player.closeScreen();
            }

            dragging = false;
        }
    }

    private void craftCurrentRecipe()
    {
        InventoryPlayer inventoryPlayer = player.inventory;
        Item resultItem = selected.getRecipe().getResult().getItem();
        int resultAmount = selected.getRecipe().getResultAmount();

        if(player.worldObj.isRemote)
        {
            for(int i = 9; i < inventoryPlayer.getSizeInventory(); i++)
            {
                ItemStack stackInSlot = inventoryPlayer.getStackInSlot(i);

                //taking ingredients
                for(WomItemStack womItemStack : selected.getRecipe().getIngredients())
                {
                    ItemStack wom = womItemStack.getItemStack();

                    if(stackInSlot != null)
                    {
                        if(Item.getIdFromItem(stackInSlot.getItem()) == Item.getIdFromItem(wom.getItem()) && stackInSlot.stackSize >= wom.stackSize)
                        {
                            if(stackInSlot.stackSize == wom.stackSize)
                            {
                                inventoryPlayer.setInventorySlotContents(i, null);
                            }
                            else
                            {
                                inventoryPlayer.setInventorySlotContents(i, new ItemStack(stackInSlot.getItem(), stackInSlot.stackSize - wom.stackSize));
                            }

                        }
                    }
                }
            }

            for(int i = 9; i < inventoryPlayer.getSizeInventory(); i++)
            {
                //adding result
                ItemStack stackInSlot = inventoryPlayer.getStackInSlot(i);

                if(stackInSlot == null)
                {
                    inventoryPlayer.setInventorySlotContents(i, new ItemStack(resultItem, resultAmount));
                    break;
                }
                else
                {
                    if(Item.getIdFromItem(stackInSlot.getItem()) == Item.getIdFromItem(resultItem))
                    {
                        inventoryPlayer.setInventorySlotContents(i, new ItemStack(resultItem, stackInSlot.stackSize + resultAmount));
                        break;
                    }
                    else
                    {
                        continue;
                    }
                }
            }
        }

        determineSkillUp();
    }

    private void determineSkillUp()
    {
        int skillLevel = player.getDataWatcher().getWatchableObjectInt(WomPlayerData.MINING_WATCHER);//WomPlayerData.get(player).getMiningSkill();


        if(skillLevel < selected.getRecipe().getMaxSkillUpLevel())
        {
            HiggsWom.logger.error("Cannot craft recipe; you should not be getting this.");
        }
        else if(skillLevel >= selected.getRecipe().getMaxSkillUpLevel() && skillLevel < selected.getRecipe().getMedSkillUpLevel())
        {
            WomPlayerData.get(player).incMiningSkill(1);
            WomPlayerData.get(this.player).syncAll();
        }
        else if(skillLevel >= selected.getRecipe().getMedSkillUpLevel() && skillLevel < selected.getRecipe().getMinSkillUpLevel())
        {
            float rand = player.worldObj.rand.nextFloat();

            WomPlayerData.get(player).incMiningSkill(rand < 0.5 ? 1 : 0);
            WomPlayerData.get(this.player).syncAll();
        }
        else if(skillLevel >= selected.getRecipe().getMinSkillUpLevel() && skillLevel < selected.getRecipe().getNoSkillUpLevel())
        {
            float rand = player.worldObj.rand.nextFloat();

            WomPlayerData.get(player).incMiningSkill(rand < 0.25 ? 1 : 0);
            WomPlayerData.get(this.player).syncAll();
        }
        else
        {
            WomPlayerData.get(player).incMiningSkill(0);
            WomPlayerData.get(this.player).syncAll();
        }
    }

    private int getGuiX()
    {
        return displayX + dragX;
    }

    private int getGuiY()
    {
        return displayY + dragY;
    }

    private static boolean areAllTrue(boolean[] array)
    {
        for(boolean b : array)
        {
            if(!b)
            {
                return false;
            }
        }
        return true;
    }

    private static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY)
    {
        return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }

    private boolean isInListItemArea(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 9, getGuiY() + 36, 113, 149, mouseX, mouseY);
    }

    private boolean isInListItem(int listItemDefaultY, int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 9, getGuiY() + listItemDefaultY + scrollY + 36, 113, 8, mouseX, mouseY);
    }

    private boolean isInCraftingButtonArea(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 128, getGuiY() + 181, 127, 9, mouseX, mouseY);
    }

    public boolean isOnCreateButton(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 192, getGuiY() + 179, 29, 9, mouseX, mouseY);
    }

    public boolean isOnCreateAllButton(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 129, getGuiY() + 181, 30, 9, mouseX, mouseY);
    }

    private boolean isInListItemScrollArea(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 119, getGuiY() + 40, 7, 141, mouseX, mouseY);
    }

    private boolean isInDragArea(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 23, getGuiY() + 1, 224, 9, mouseX, mouseY);
    }

    private boolean isInXButtonArea(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 248, getGuiY() + 3, 7, 7, mouseX, mouseY);
    }

    private boolean isInButtonsArea(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 128, getGuiY() + 178, 127, 12, mouseX, mouseY);
    }

    private boolean isOnCreateExitButton(int mouseX, int mouseY)
    {
        return isInRect(getGuiX() + 224, getGuiY() + 181, 29, 9, mouseX, mouseY);
    }
}

package com.higgs.wom.client.gui;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.WomItemStack;
import com.higgs.wom.WomRecipe;
import com.higgs.wom.entitydata.WomPlayerData;
import com.higgs.wom.network.PacketDispatcher;
import com.higgs.wom.network.server.WomPacketDropExcessCrafting;
import com.higgs.wom.proxy.ClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class WomGuiMiningSkill extends WomGuiProfession
{
    public WomGuiMiningSkill(EntityPlayer player, Container container)
    {
        super(container);
        this.player = player;
        this.width = 512;
        this.height = 512;
        displayX = 140;
        displayY = 100;
        this.title = "Mining";
        this.playerData = WomPlayerData.get(player);
        this.mc = Minecraft.getMinecraft();
        this.fontRendererObj = Minecraft.getMinecraft().fontRenderer;
        this.enumWindowType = EnumWindowType.PROFESSION_MINING;
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
        super.drawGuiContainerBackgroundLayer(depth, mouseX, mouseY);

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowSkill.png"));

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

        if(this.selected == null)
        {
            this.craftingButtonState = -1;
            this.craftingAllButtonState = -1;
        }

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        drawCraftingButtons();
        drawCreateAllGui();
        drawScrollBars();
        drawListItems(mouseX, mouseY);
    }

    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowElements.png"));

        int miningSkill = playerData.getMiningSkill();

        if(miningSkill <= 75)
        {
            drawTexturedModalRect(88, 31, map(miningSkill, 75,  341), 9, 341, 9);
        }
        else if(miningSkill > 75 && miningSkill < 150)
        {
            drawTexturedModalRect(88, 31, map(miningSkill, 150, 341), 9, 341, 9);
        }
    }

    private void drawCreateAllGui()
    {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        fontRendererObj.drawString("" + createAllAmount, getGuiX() + 170, getGuiY() + 180, 0xffffff, true);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowElements.png"));

        if(this.createAllDecButtonState == 0)
        {
            drawTexturedModalRect(getGuiX() + 161, getGuiY() + 181, 32, 72, 6, 6);
        }
        else if(this.createAllDecButtonState == 1)
        {
            drawTexturedModalRect(getGuiX() + 161, getGuiY() + 180, 32, 72, 6, 6);
        }

        if(this.createAllIncButtonState == 0)
        {
            drawTexturedModalRect(getGuiX() + 183, getGuiY() + 181, 40, 72, 6, 6);
        }
        else if(this.createAllIncButtonState == 1)
        {
            drawTexturedModalRect(getGuiX() + 183, getGuiY() + 180, 40, 72, 6, 6);
        }
    }

    private void drawCraftingButtons()
    {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowElements.png"));

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

    private void drawScrollBars()
    {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowElements.png"));
        drawTexturedModalRect(getGuiX() + scrollBar1X, getGuiY() + scrollBar1Y + (scroll1Y / 2), 48, 55, 8, 7);
        drawTexturedModalRect(getGuiX() + scrollBar2X, getGuiY() + scrollBar2Y + (scroll2Y / 2), 48, 55, 8, 7);
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
            Color listItemBoxColor;// = new Color(0xff, 0xff, 0xff, 0x00);//0x00000000;

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
                    listItemBoxColor = new Color(0x55, 0xff, 0x01, 0xb0);//boxColor = 100609;//0x55ff0150; //green
                    listItem.setHasSkill(true);
                }
                else
                {
                    listItemBoxColor = new Color(0x4f, 0x4f, 0x4f, 0xb0);//boxColor = 5197647;//0x4f4f4f50; //grey
                }

                drawCraftingRecipe(listItem);
                checkForIngredients();

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
                    textColor = new Color(0x55, 0xff, 0x01, 0xff);//textColor = 100609;//0x55ff0150; //green
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

            if(listItem.getDefaultY() + scroll1Y >= 0 && listItem.getDefaultY() + scroll1Y < 150)
            {
                drawRect(getGuiX() + 5, getGuiY() + listItem.getDefaultY() + scroll1Y + 35,
                        getGuiX() + 117, getGuiY() + listItem.getDefaultY() + scroll1Y + 35 + 11, listItemBoxColor.getRGB());
            }
            fontRendererObj.drawString(listItem.getDisplayString(), getGuiX() + 11, getGuiY() + scroll1Y + listItem.getDefaultY() + 36, textColor.getRGB(), true);
        }
    }

    private void drawCraftingRecipe(WomGuiListItem listItem)
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
            drawTexturedModalRect(getGuiX() + 151, getGuiY() + yOffset + 33, 48, 104, 63, 21);
            fontRendererObj.drawSplitString(womItemStack.getItemStack().getDisplayName(), getGuiX() + 154, getGuiY() + yOffset + 34, 54, 0xffffff);
            guiDrawItemIcon(womItemStack.getItemStack(), getGuiX() + 134, getGuiY() + yOffset + 32);

            yOffset += 35;
        }

//        checkForIngredients();
    }

    private void checkForIngredients()
    {
        InventoryPlayer inventory = player.inventory;

        boolean[] args = new boolean[selected.getRecipe().getIngredients().size()];

        for(int i = 0; i < args.length; i++)
        {
            args[i] = false;
        }

        for(int i = 0; i < selected.getRecipe().getIngredients().size(); i++)
        {
            for(int j = 0; j < inventory.mainInventory.length; j++)
            {
                if(inventory.mainInventory[j] != null)
                {
                    if(Item.getIdFromItem(inventory.mainInventory[j].getItem()) == Item.getIdFromItem(selected.getRecipe().getIngredients().get(i).getItemStack().getItem()))
                    {
                        if(inventory.mainInventory[j].stackSize >= selected.getRecipe().getIngredients().get(i).getItemStack().stackSize)
                        {
                            args[i] = true;
                        }
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

    public void scroll1Up()
    {
        scroll1Y += scroll1Increment;
    }

    public void scroll1Down()
    {
        scroll1Y -= scroll1Increment;
    }

    public void scroll2Up()
    {
        scroll2Y += scroll2Increment;
    }

    public void scroll2Down()
    {
        scroll2Y -= scroll2Increment;
    }

    private int map(int toMap, int toMapHighBound, int mapHighBound)
    {
        double mapDiv = (double)toMap / (double)toMapHighBound;

        return (int)(mapDiv * (double)mapHighBound);
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    @Override
    protected void keyTyped(char charId, int keyId)
    {
        super.keyTyped(charId, keyId);
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
        if(isInXButtonArea(mouseX, mouseY))
        {
            dragging = false;
            this.craftingButtonState = 1;
            this.craftingAllButtonState = 1;
            this.craftingExitButtonState = 1;
            this.createAllDecButtonState = 1;
            this.createAllIncButtonState = 1;
            this.closeButtonDepressed = true;
        }
        if(isInCraftingButtonArea(mouseX, mouseY))
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
                this.createAllDecButtonState = 1;
                this.createAllIncButtonState = 1;
                this.closeButtonDepressed = false;
            }

            if(isOnCreateAllButton(mouseX, mouseY))
            {
                dragging = false;
                if(this.selected != null)
                {
                    for(int i = 0; i < this.createAllAmount; i++)
                    {
                        if(!selected.getCraftable())
                        {
                            break;
                        }
                        craftCurrentRecipe();
                        checkForIngredients();
                    }
                }
                this.craftingButtonState = 1;
                this.craftingAllButtonState = 0;
                this.craftingExitButtonState = 1;
                this.createAllDecButtonState = 1;
                this.createAllIncButtonState = 1;
                this.closeButtonDepressed = false;
            }

            if(isOnCreateExitButton(mouseX, mouseY))
            {
                dragging = false;
                this.craftingButtonState = 1;
                this.craftingAllButtonState = 1;
                this.craftingExitButtonState = 0;
                this.createAllDecButtonState = 1;
                this.createAllIncButtonState = 1;
                this.closeButtonDepressed = false;

                ClientProxy.playerGui.closeWindow(this);
//                player.closeScreen();
            }

            if(isInCreateAllDecButton(mouseX, mouseY))
            {
                if(createAllAmount > 0)
                {
                    createAllAmount--;
                }
                else
                {
                    createAllAmount = 0;
                }

                dragging = false;
                this.craftingButtonState = 1;
                this.craftingAllButtonState = 1;
                this.craftingExitButtonState = 1;
                this.createAllDecButtonState = 0;
                this.createAllIncButtonState = 1;
                this.closeButtonDepressed = false;
            }

            if(isInCreateAllIncButton(mouseX, mouseY))
            {
                if(createAllAmount < 64)
                {
                    createAllAmount++;
                }
                else
                {
                    createAllAmount = 64;
                }

                dragging = false;
                this.craftingButtonState = 1;
                this.craftingAllButtonState = 1;
                this.craftingExitButtonState = 1;
                this.createAllDecButtonState = 1;
                this.createAllIncButtonState = 0;
                this.closeButtonDepressed = false;
            }
        }
        else
        {
            this.craftingButtonState = 1;
            this.craftingAllButtonState = 1;
            this.craftingExitButtonState = 1;
            this.createAllDecButtonState = 1;
            this.createAllIncButtonState = 1;
            this.closeButtonDepressed = false;
        }

        if(isInListItemArea(mouseX, mouseY))
        {
            boolean flag = false;
            for(WomGuiListItem listItem : this.listItems)
            {
                if(isInListItem(listItem.getDefaultY(), mouseX, mouseY))
                {
                    listItem.setSelected(true);
                    this.selected = listItem;
                    flag = true;
                }
                else
                {
                    listItem.setSelected(false);
                }
            }
            if(!flag)
            {
                this.craftingButtonState = -1;
                this.craftingAllButtonState = -1;
                this.craftingExitButtonState = -1;
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

            this.craftingButtonState = -1;
            this.craftingAllButtonState = -1;
            this.craftingExitButtonState = -1;

            this.selected = null;
        }

        this.craftingButtonState = 1;
        this.craftingAllButtonState = 1;
        this.craftingExitButtonState = 1;
        this.createAllDecButtonState = 1;
        this.createAllIncButtonState = 1;
        this.closeButtonDepressed = false;
    }

    /**
     * Called when a mouse button is pressed and the mouse is moved around. Parameters are : mouseX, mouseY,
     * lastButtonClicked & timeSinceMouseClick.
     */
    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int lastButtonClicked, long timeSinceMouseClick)
    {
        if(timeSinceMouseClick <= 2000)
        {
            if(isInDragArea(mouseX, mouseY))
            {
                dragging = true;
                lastDragX = mouseX - dragX;
                lastDragY = mouseY - dragY;
            }
        }
//        if(timeSinceMouseClick <= 2000)
//        {
//            if(isInListItemScrollArea(mouseX, mouseY))
//            {
//                if(isInListItemScrollBarArea(mouseX, mouseY))
//                {
//                    if(Math.abs(lastMouse1Y - mouseY) > scroll1Increment)
//                    {
//                        if(lastMouse1Y - mouseY > 0)
//                        {
//                            scroll1Up();
//                        }
//                        else
//                        {
//                            scroll1Down();
//                        }
//                    }
//                }
//                lastMouse1Y = mouseY;
//            }
//            else if(isInDetailsScrollArea(mouseX, mouseY))
//            {
//                if(isInDetailsScrollBarArea(mouseX, mouseY))
//                {
//                    if(Math.abs(lastMouse2Y - mouseY) > scroll2Increment)
//                    {
//                        if(lastMouse2Y - mouseY > 0)
//                        {
//                            scroll2Up();
//                        }
//                        else
//                        {
//                            scroll2Down();
//                        }
//                    }
//                }
//                lastMouse2Y = mouseY;
//            }
//        }
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
                ClientProxy.playerGui.closeWindow(this);
//                this.player.closeScreen();
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
                ClientProxy.playerGui.closeWindow(this);
//                this.player.closeScreen();
            }
            else if(isInCreateAllDecButton(mouseX, mouseY))
            {
                this.createAllDecButtonState = 1;
            }
            else if(isInCreateAllIncButton(mouseX, mouseY))
            {
                this.createAllIncButtonState = 1;
            }

            dragging = false;
        }
    }

    private void craftCurrentRecipe()
    {
        if(player.worldObj.isRemote)
        {
            if(selected.getHasIngredients())
            {
                if(consumeOneSet())
                {
                    produceOneSet();
                }
                player.inventoryContainer.detectAndSendChanges();
                checkForIngredients();
                determineSkillUp();
            }
        }
    }

    private boolean consumeOneSet()
    {
        InventoryPlayer inventoryPlayer = player.inventory;

        for(int i = 0; i < inventoryPlayer.mainInventory.length; i++)
        {
            ItemStack stackInSlot = inventoryPlayer.getStackInSlot(i);
            if(stackInSlot != null)
            {
                for(int j = 0; j < selected.getRecipe().getIngredients().size(); j++)
                {
                    ItemStack wom = selected.getRecipe().getIngredients().get(j).getItemStack();
                    if(wom != null)
                    {
                        if(Item.getIdFromItem(stackInSlot.getItem()) == Item.getIdFromItem(wom.getItem()))
                        {
                            if(stackInSlot.stackSize > wom.stackSize)
                            {
                                inventoryPlayer.setInventorySlotContents(i, new ItemStack(stackInSlot.getItem(), stackInSlot.stackSize - wom.stackSize));
                                return true;
                            }
                            else if(stackInSlot.stackSize == wom.stackSize)
                            {
                                inventoryPlayer.setInventorySlotContents(i, null);
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private void produceOneSet()
    {
        InventoryPlayer inventoryPlayer = player.inventory;
        Item resultItem = selected.getRecipe().getResult().getItem();
        int resultAmount = selected.getRecipe().getResultAmount();

        while(resultAmount > 0)
        {
            for(int i = 9; i < inventoryPlayer.mainInventory.length; i++)
            {
                ItemStack itemStack = inventoryPlayer.getStackInSlot(i);

                if(itemStack != null)
                {
                    if(Item.getIdFromItem(itemStack.getItem()) == Item.getIdFromItem(resultItem))
                    {
                        if(itemStack.stackSize + resultAmount <= itemStack.getItem().getItemStackLimit(itemStack))
                        {
                            inventoryPlayer.setInventorySlotContents(i, new ItemStack(resultItem, itemStack.stackSize + resultAmount));
                            resultAmount = 0;
                            break;
                        }
                        else
                        {
                            int diff = itemStack.getItem().getItemStackLimit(itemStack) - itemStack.stackSize;
                            inventoryPlayer.setInventorySlotContents(i, new ItemStack(resultItem, itemStack.getItem().getItemStackLimit(itemStack)));
                            resultAmount -= diff;
                            continue;
                        }
                    }

                    if(i == inventoryPlayer.mainInventory.length - 1)
                    {
                        EntityItem itemDrops = new EntityItem(player.worldObj, player.posX, player.posY + 0.25d, player.posZ);

                        PacketDispatcher.sendToServer(new WomPacketDropExcessCrafting(itemDrops, player));
                    }
                }
                else
                {
                    inventoryPlayer.setInventorySlotContents(i, new ItemStack(resultItem, resultAmount));
                    resultAmount = 0;
                    break;
                }
            }
        }
    }

//    private void craftCurrentRecipeOld()
//    {
//        InventoryPlayer inventoryPlayer = player.inventory;
//        Item resultItem = selected.getRecipe().getResult().getItem();
//        int resultAmount = selected.getRecipe().getResultAmount();
//
//        if(player.worldObj.isRemote)
//        {
//            for(int i = 9; i < inventoryPlayer.getSizeInventory(); i++)
//            {
//                ItemStack stackInSlot = inventoryPlayer.getStackInSlot(i);
//
//                //taking ingredients
//                for(WomItemStack womItemStack : selected.getRecipe().getIngredients())
//                {
//                    ItemStack wom = womItemStack.getItemStack();
//
//                    if(stackInSlot != null)
//                    {
//                        if(Item.getIdFromItem(stackInSlot.getItem()) == Item.getIdFromItem(wom.getItem()) && stackInSlot.stackSize >= wom.stackSize)
//                        {
//                            if(stackInSlot.stackSize == wom.stackSize)
//                            {
//                                inventoryPlayer.setInventorySlotContents(i, null);
//                            }
//                            else
//                            {
//                                inventoryPlayer.setInventorySlotContents(i, new ItemStack(stackInSlot.getItem(), stackInSlot.stackSize - wom.stackSize));
//                            }
//
//                        }
//                    }
//                }
//            }
//
//            while(resultAmount > 0)
//            {
//                for(int i = 0; i < inventoryPlayer.mainInventory.length; i++)
//                {
//                    ItemStack itemStack = inventoryPlayer.getStackInSlot(i);
//
//                    if(itemStack != null)
//                    {
//                        if(Item.getIdFromItem(itemStack.getItem()) == Item.getIdFromItem(resultItem))
//                        {
//                            if(itemStack.stackSize + resultAmount <= itemStack.getItem().getItemStackLimit(itemStack))
//                            {
//                                inventoryPlayer.setInventorySlotContents(i, new ItemStack(resultItem, itemStack.stackSize + resultAmount));
//                                resultAmount = 0;
//                                break;
//                            }
//                            else
//                            {
//                                int diff = itemStack.getItem().getItemStackLimit(itemStack) - itemStack.stackSize;
//                                inventoryPlayer.setInventorySlotContents(i, new ItemStack(resultItem, itemStack.getItem().getItemStackLimit(itemStack)));
//                                resultAmount -= diff;
//                                continue;
//                            }
//                        }
//
//                        if(i == inventoryPlayer.mainInventory.length - 1)
//                        {
//                            EntityItem itemDrops = new EntityItem(player.worldObj, player.posX, player.posY + 0.25d, player.posZ);
//
//                            PacketDispatcher.sendToServer(new WomPacketDropExcessCrafting(itemDrops, player));
//                        }
//                    }
//                    else
//                    {
//                        inventoryPlayer.setInventorySlotContents(i, new ItemStack(resultItem, resultAmount));
//                        resultAmount = 0;
//                        break;
//                    }
//                }
//            }
//        }
//
//        determineSkillUp();
//    }

    private void determineSkillUp()
    {
        int skillLevel = player.getDataWatcher().getWatchableObjectInt(WomPlayerData.MINING_WATCHER);//WomPlayerData.get(player).getMiningSkill();


        if(skillLevel < selected.getRecipe().getMaxSkillUpLevel())
        {
            HiggsWom.logger.error("Cannot craft recipe; you should not be seeing this.");
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
}

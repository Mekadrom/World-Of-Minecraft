package com.higgs.wom.client.gui;

import com.higgs.wom.HiggsWom;
import com.higgs.wom.WomRecipe;
import com.higgs.wom.entitydata.WomPlayerData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class WomGuiMiningSkill extends WomGuiWindow
{
    ArrayList<WomGuiListItem> listItems = new ArrayList<WomGuiListItem>();

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

//        int x = 9;
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
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        if(dragging)
        {
            windowDragged(mouseX, mouseY);
        }

        this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowSkill.png"));

        drawTexturedModalRect(displayX + dragX, displayY + dragY, 0, 0, 256, 256);

        this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowElements.png"));

        drawTexturedModalRect(displayX + dragX + 4, displayY + dragY + 2, 0, 64, 16, 16);

        this.fontRendererObj.drawString(title, displayX + dragX - (fontRendererObj.getStringWidth(title) / 2) + 129, displayY + dragY + 2, 0x917719);

        int miningSkill = playerData.getMiningSkill();

        String skillLevelString = miningSkill + "/";

        if(miningSkill <= 75)
        {
            skillLevelString += "75";
        }
        else if(miningSkill > 75 && miningSkill < 150)
        {
            skillLevelString += "150";
        }
        else
        {
            skillLevelString += "NaN";
        }

        this.fontRendererObj.drawString(skillLevelString, displayX + dragX - (fontRendererObj.getStringWidth(title) / 2) + 131, displayY + dragY + 14, 0xffffff);

        this.mc.getTextureManager().bindTexture(new ResourceLocation(HiggsWom.MODID + ":textures/gui/guiWindowElements.png"));

        if(this.closeButtonDepressed)
        {
            drawTexturedModalRect(displayX + dragX + (249), displayY + dragY + (3), 49, 64, 8, 8);
        }
        else
        {
            drawTexturedModalRect(displayX + dragX + (249), displayY + dragY + (2), 49, 64, 8, 8);
        }

//        drawTexturedModalRect(displayX + dragX + 4, displayY + dragY + 2, 0, 64, 16, 16);
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

    public void drawListItems(int mouseX, int mouseY)
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
                    listItem.setCraftable(false);
                }
                else if(skillLevel >= listItem.getRecipe().getMaxSkillUpLevel() && skillLevel < listItem.getRecipe().getMedSkillUpLevel())
                {
                    listItemBoxColor = new Color(0xd3, 0x6d, 0x00, 0xb0);//boxColor = 13856000;//0xd36d0050; //orange
                    listItem.setCraftable(true);
                }
                else if(skillLevel >= listItem.getRecipe().getMedSkillUpLevel() && skillLevel < listItem.getRecipe().getMinSkillUpLevel())
                {
                    listItemBoxColor = new Color(0xff, 0xff, 0x00, 0xb0);//boxColor = 16776969;//0xffff0950; //yellow
                    listItem.setCraftable(true);
                }
                else if(skillLevel >= listItem.getRecipe().getMinSkillUpLevel() && skillLevel < listItem.getRecipe().getNoSkillUpLevel())
                {
                    listItemBoxColor = new Color(0x01, 0x89, 0x01, 0xb0);//boxColor = 100609;//0x01890150; //green
                    listItem.setCraftable(true);
                }
                else
                {
                    listItemBoxColor = new Color(0x4f, 0x4f, 0x4f, 0xb0);//boxColor = 5197647;//0x4f4f4f50; //grey
                }

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
                    listItem.setCraftable(false);
                }
                else if(skillLevel >= listItem.getRecipe().getMaxSkillUpLevel() && skillLevel < listItem.getRecipe().getMedSkillUpLevel())
                {
                    textColor = new Color(0xd3, 0x6d, 0x00, 0xff);//textColor = 13856000;//0xd36d00ff; //orange
                    listItem.setCraftable(true);
                }
                else if(skillLevel >= listItem.getRecipe().getMedSkillUpLevel() && skillLevel < listItem.getRecipe().getMinSkillUpLevel())
                {
                    textColor = new Color(0xff, 0xff, 0x00, 0xff);//textColor = 16776960;//0xffff00ff; //yellow
                    listItem.setCraftable(true);
                }
                else if(skillLevel >= listItem.getRecipe().getMinSkillUpLevel() && skillLevel < listItem.getRecipe().getNoSkillUpLevel())
                {
                    textColor = new Color(0x01, 0x89, 0x01, 0xff);//textColor = 100609;//0x018901ff; //green
                    listItem.setCraftable(true);
                }
                else
                {
                    textColor = new Color(0x4f, 0x4f, 0x4f, 0xff);//textColor = 5197647;//0x4f4f4fff; //grey
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

            drawRect(displayX + dragX + 5, displayY + dragY + listItem.getDefaultY() + scrollY + 35,
                    displayX + dragX + 117, displayY + dragY + listItem.getDefaultY() + scrollY + 35 + 11, listItemBoxColor.getRGB());
            fontRendererObj.drawString(listItem.getDisplayString(), displayX + dragX + 11, displayY + dragY + scrollY + listItem.getDefaultY() + 36, textColor.getRGB(), true);
        }
    }

    public void scrollUp()
    {
        scrollY -= scrollIncrement;
    }

    public void scrollDown()
    {
        scrollY += scrollIncrement;
    }

    public int map(int toMap, int toMapHighBound, int mapHighBound)
    {
        int map;

        map = toMap / toMapHighBound;

        map *= mapHighBound;

        return map;
    }

    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY)
    {
        return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }

    /**
     * Called when the mouse is clicked.
     */
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button)
    {
        super.mouseClicked(mouseX, mouseY, button);

        if(isInRect(displayX + dragX + 46, displayY + dragY + 6, 400, 14, mouseX, mouseY))
        {
            dragging = true;
            lastDragX = mouseX - dragX;
            lastDragY = mouseY - dragY;
        }
        else if(isInRect(displayX + dragX + 255, displayY + dragY + 2, 7, 7, mouseX, mouseY))
        {
            dragging = false;
//            WomPlayerData.get(this.player).incMiningSkill(5);
            player.getDataWatcher().updateObject(WomPlayerData.MINING_WATCHER, player.getDataWatcher().getWatchableObjectInt(WomPlayerData.MINING_WATCHER) + 1);
            WomPlayerData.get(this.player).syncAll();
            this.closeButtonDepressed = true;
        }
        else
        {
            dragging = false;
            this.closeButtonDepressed = false;
        }

        if(isInListItemArea(mouseX, mouseY))
        {
            for(WomGuiListItem listItem : this.listItems)
            {
                if(isInListItem(listItem.getDefaultY(), mouseX, mouseY))
                {
                    listItem.setSelected(true);
                }
                else
                {
                    listItem.setSelected(false);
                }
            }
        }
        else
        {
            for(WomGuiListItem listItem : this.listItems)
            {
                listItem.setSelected(false);
            }
        }
    }

    /**
     * Called when a mouse button is pressed and the mouse is moved around. Parameters are : mouseX, mouseY,
     * lastButtonClicked & timeSinceMouseClick.
     */
    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int lastButtonClicked, long timeSinceMouseClick)
    {

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
            if(isInRect(displayX + dragX + 248, displayY + dragY + 2, 7, 7, mouseX, mouseY))
            {
                this.mc.thePlayer.closeScreen();
                this.closeButtonDepressed = false;
            }

            dragging = false;
        }

        if(isInListItemArea(mouseX, mouseY))
        {
            for(WomGuiListItem listItem : this.listItems)
            {
                if(isInListItem(listItem.getDefaultY(), mouseX, mouseY))
                {

                }
            }
        }
    }

    public boolean isInListItemArea(int mouseX, int mouseY)
    {
        return isInRect(displayX + dragX + 9, displayY + dragY + 36, 113, 149, mouseX, mouseY);
    }

    public boolean isInListItem(int listItemDefaultY, int mouseX, int mouseY)
    {
        return isInRect(displayX + dragX + 9, displayY + dragY + listItemDefaultY + scrollY + 36, 113, 8, mouseX, mouseY);
    }
}

package com.evan.slammod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = SlamMod.MODID, version = SlamMod.VERSION)
public class SlamMod
{
    public static final String MODID = "Slam Mod";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	SlamHandler handler = new SlamHandler();
		FMLCommonHandler.instance().bus().register(handler);
    }
}

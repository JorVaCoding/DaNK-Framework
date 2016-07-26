package com.dank.framework;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

@Mod(
		modid = Framework.MOD_ID,
		name = Framework.MOD_NAME,
		version = Framework.VERSION
)
public class Framework {

	public static final String MOD_ID = "framework";
	public static final String MOD_NAME = "Framework";
	public static final String VERSION = "0.0.0";

	@SidedProxy(clientSide = "com.dank.framework.ClientProxy", serverSide = "com.dank.framework.CommonProxy")
	public static CommonProxy proxy;

	@Instance
	public static Framework INSTANCE;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModItems.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}

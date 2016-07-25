package com.dank.framework;

import net.minecraft.util.IStringSerializable;

public enum MachineConfig implements IStringSerializable {
	INPUT,
	DISABLED,
	SECONDARY_OUTPUT,
	PRIMARY_OUTPUT,
	COMBINED_OUTPUT,
	;

	public static MachineConfig getConfigFromOrdinal(int i) {
		for(MachineConfig config : values()) {
			if(config.ordinal()==i)
				return config;
		}
		return MachineConfig.DISABLED;
	}

	@Override
	public String getName() {
		return name().toLowerCase();
	}
}

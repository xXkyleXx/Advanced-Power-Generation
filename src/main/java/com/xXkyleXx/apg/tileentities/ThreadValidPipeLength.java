package com.xXkyleXx.apg.tileentities;

public class ThreadValidPipeLength extends Thread {

	private TileEntityGeothermalPump pump;
	
	public ThreadValidPipeLength(TileEntityGeothermalPump tegp) {
		pump = tegp;
	}
	
	
	
	@Override
	public void run() {
		if (pump.getPipeLength() > 15 && pump.hasGrate()) {
			int length = 0;
			int pipeLength = pump.getPipeLength();
			for (int i = 1; i < pipeLength; i++) {
				if (pump.yCoord - i < 71 && getAirBlocksInLayer(pump.xCoord, pump.yCoord - i, pump.zCoord, 3) < 3 && getAirBlocksInLayer(pump.xCoord, pump.yCoord - i, pump.zCoord, pump.getSearchSize()) < Math.pow(pump.getSearchSize(), 2) / 3) {
					length++;
				}

			}
			pump.setValidPipeLength(length);

		}
	}

	
	public int getAirBlocksInLayer(int x, int y, int z, int size) {
		int airBlocks = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (pump.getWorldObj().isAirBlock(x - (size / 2) + i, y, z - (size / 2) + j)) {
					airBlocks++;
				}
			}
		}
		return airBlocks;
	}
	
}

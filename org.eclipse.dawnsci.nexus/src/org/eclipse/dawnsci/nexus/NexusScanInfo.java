package org.eclipse.dawnsci.nexus;

import java.util.List;

/**
 * 
 * This class represents information about the scan which the NeXus device is running in.
 * 
 * For instance, names of scannables in the axes and the rank of the scan.
 * 
 * @author Matthew Gerring
 *
 */
public class NexusScanInfo {

	private int rank;
	private List<String> axisNames;
	public NexusScanInfo() {
		
	}
	
	/**
	 * 
	 * @param axisNames must be ordered correctly into indices
	 */
	public NexusScanInfo(List<String> axisNames) {
		super();
		this.axisNames = axisNames;
		this.rank = axisNames.size();
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public List<String> getAxisNames() {
		return axisNames;
	}
	public void setAxisNames(List<String> axisNames) {
		this.axisNames = axisNames;
	}

	/**
	 * Attempts to make a chunk size from the scan.
	 * NOTE This assumes that the datashape is a resonable size currently.
	 * If the datashape is small, the chunking can become too small to usefully
	 * read.
	 * 
	 * @param datashape
	 * @return
	 */
	public int[] createChunk(int... datashape) {
		
		final int[] chunk = new int[rank+datashape.length];
		for (int i = 0; i < chunk.length-2; i++) {
			chunk[i] = 1;
		}
		int index = 0;
		for (int i = datashape.length; i>0; i--) {
			chunk[chunk.length-i] = datashape[index];
			index++;
		}
		return chunk;
	}
}
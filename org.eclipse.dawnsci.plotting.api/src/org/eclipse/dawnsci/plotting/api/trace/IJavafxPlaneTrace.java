/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.plotting.api.trace;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

/**
 * This trace is currently only available with Java8 and there is
 * no DAWN executable based on Java8 available on the website.
 * 
 * Probably DAWN 2.0 in 2015 will be Java8 based.
 * 
 * @author Joel Ogden
 *
 */
public interface IJavafxPlaneTrace extends IImage3DTrace {

	/**
	 * Plot a volume on the plotting system.
	 * 
	 * Requires the images for each plane in the volume
	 * 
	 * Thread safe
	 * 
	 * @param 
	 */
	public void setData(
			final int[] size,
			final IDataset data, 
			final double [] offsets,
			final double[] planeNormal);
	
	/**
	 * sets the new opacity
	 * @param opacity - between 0.0 - 1.0
	 */
	public void setOpacity(double opacity);
	
}
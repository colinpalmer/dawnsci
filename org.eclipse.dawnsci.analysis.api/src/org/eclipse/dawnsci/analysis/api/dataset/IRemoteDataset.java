/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.dataset;

import java.util.concurrent.TimeUnit;

/**
 * A Remote dataset is a lazy dataset which exists in a remote
 * location. It uses a connection to the DataServer to provide the
 * implementation of the slicing required remotely.
 * 
 * You may also listen to data changing in the dataset
 */
public interface IRemoteDataset extends ILazyDataset, IDynamicDataset {

	/**
	 * DataServer path, a local path on the server used to locate the remote dataset.
	 * @return the file path to the data in the file system of the remote machine
	 */
	public String getPath();
	
	/**
	 * DataServer path, a local path on the server used to locate the remote dataset.
	 * This path may also be a directory where the data collection will happen. In this
	 * case when the first file is written, the dataset must be made up of files with the
	 * same extension.
	 * 
	 * @param path to the data in the file system of the remote machine
	 */
	public void setPath(String path);

	/**
	 * The data set location/name in the file
	 * @return Data set 
	 */
	public String getDataset();
	
	/**
	 * The data set location/name in the file
	 * @param dataset
	 */
	public void setDataset(String dataset);

	/**
	 * Same as calling connect(500, TimeUnit.MILLISECOND)
	 * 
	 * Cannot will connect with the DataServer to start listening
	 * to any updates to the file should it be written in the remote file system.
	 * When connect it called, the remote file must exist and the dataset properties
	 * are read. These properties must not change in the file while you are connected.
	 * For instance if the file is ints when you connect, it must not change data class.
	 * 
	 * @return the name of the thread started to run the connection or null if each event
	 * is driven from the event thread of the service (for instance web sockets provide the
	 * thread and this runs the connection)
	 * 
	 */
	public String connect() throws Exception;
	
	/**
	 * Cannot will connect with the DataServer to start listening
	 * to any updates to the file should it be written in the remote file system.
	 * When connect it called, the remote file must exist and the dataset properties
	 * are read. These properties must not change in the file while you are connected.
	 * For instance if the file is ints when you connect, it must not change data class.
	 * 
	 * @return the name of the thread started to run the connection or null if each event
	 * is driven from the event thread of the service (for instance web sockets provide the
	 * thread and this runs the connection)
	 */
	public String connect(long time, TimeUnit unit) throws Exception;

	/**
	 * Stops listening to the dataset changing and disconnects from the server.
	 * A remote dataset may be connected and disconnected multiple times.
	 */
	public void disconnect() throws Exception;
	
	/**
	 * If set to true the DataServer will not cache the dataset. 
	 * If left as false: if the data server can figure out that the 
	 * file is writing, it will reshape. However it cannot always 
	 * determine this depending on the file and what is writing to 
	 * it (SWMR can write without changing date stamp for instance)
	 * 
	 * Setting this boolean ensures that a given path, never will
	 * cache on the data server.
	 * 
	 * Default value is false for MJPG streams and true for standard
	 * remote datasets.
	 *  
	 * @param expectWrite
	 */
	public void setWritingExpected(boolean expectWrite);
	
	/**
	 * 
	 * @return true if the remote dataset has been warned that writing is expected.
	 */
	public boolean isWritingExpected();
}

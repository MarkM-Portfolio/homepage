/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2013                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class FileIOHandler {

	public FileIOHandler() {

	}

	public static Properties getProperties(String filePath) {

		Properties properties = new Properties();
		try {
			properties.load(FileIOHandler.class.getResourceAsStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public Properties getConf(String fileName) {

		FileReader fileReader = null;
		try {
			File confFile = new File(RunConfigManager.getInstance().testConfigFolder, fileName);
			fileReader = new FileReader(confFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties conf = new Properties();
		try {
			conf.load(fileReader);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return conf;
	}

	public File buildFolderPath(boolean isUnix, boolean isWindows, boolean isMac, String... folders) {

		String path = "";
		String separator = getEnvironmentSeparator(isUnix, isWindows, isMac);
		int i = 0;
		if (folders[0].toLowerCase().startsWith("c:") || folders[0].toLowerCase().startsWith("/")) {
			path = folders[0];
			i = 1;
			if (path.endsWith(separator)) {
				path = path.substring(0, path.indexOf(separator));
			}
		}
		else {
			if (isWindows) {
				path = "C:";
			}
		}

		while (i < folders.length) {
			String folder = folders[i];
			path = path + separator + folder;
			i++;
		}
		File dir = new File(path);
		return dir;
	}

	public String getAbsoluteFilePath(boolean isUnix, boolean isWindows, boolean isMac, File dir, String fileName) {

		String separator = getEnvironmentSeparator(isUnix, isWindows, isMac);
		return dir + separator + fileName;
	}

	private String getEnvironmentSeparator(boolean isUnix, boolean isWindows, boolean isMac) {

		String separator;
		if (isWindows) {
			separator = "\\";
		}
		else {
			separator = "/";
		}

		return separator;
	}

	public void writeRawDataToFile(String filepath, byte[] data) {

		File targetDir = new File(filepath);

		//make folders and file
		targetDir.getParentFile().mkdirs();
		if (!targetDir.exists()) {
			try {
				targetDir.createNewFile();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}

		//write
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filepath);
			fos.write(data);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static File createFolderFromPath(String folderPath) {

		return createFolderFromPath(folderPath, false);
	}

	public static File createFolderFromPath(String folderPath, boolean overwrite) {

		File targetDir = new File(folderPath);
		if (!targetDir.exists()) {
			targetDir.mkdir();
		}
		else {
			if (overwrite) {
				deleteDir(targetDir);
				targetDir.mkdir();
			}
		}
		return targetDir;
	}

	public static Document getXMLConfig(File file) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document doc = null;
		try {
			doc = builder.parse(file);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();

		return doc;
	}

	public static void writeXmlFile(Document doc, File file) {

		try {
			// Prepare the DOM document for writing
			Source source = new DOMSource(doc);

			// Prepare the output file
			Result result = new StreamResult(file);

			// Write the DOM document to the file
			Transformer xformer = TransformerFactory.newInstance().newTransformer();
			xformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
		} catch (TransformerException e) {
		}
	}

	// Deletes dir, files and subdirs under dir.
	private static boolean deleteDir(File dir) {

		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}
}

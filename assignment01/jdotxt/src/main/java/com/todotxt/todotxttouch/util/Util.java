/**
 * This file is part of Todo.txt Touch, an Android app for managing your todo.txt file (http://todotxt.com).
 *
 * Copyright (c) 2009-2013 Todo.txt contributors (http://todotxt.com)
 *
 * LICENSE:
 *
 * Todo.txt Touch is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any
 * later version.
 *
 * Todo.txt Touch is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with Todo.txt Touch.  If not, see
 * <http://www.gnu.org/licenses/>.
 *
 * @author Todo.txt contributors <todotxt@yahoogroups.com>
 * @license http://www.gnu.org/licenses/gpl.html
 * @copyright 2009-2013 Todo.txt contributors (http://todotxt.com)
 */
package com.todotxt.todotxttouch.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;

import com.chschmid.jdotxt.gui.controls.JdotxtToolbar;
import com.todotxt.todotxttouch.TodoException;

public class Util {

	/*private static String TAG = Util.class.getSimpleName();
	private static final int CONNECTION_TIMEOUT = 120000;
	private static final int SOCKET_TIMEOUT = 120000;*/

	private Util() {
	}

	/*public static HttpParams getTimeoutHttpParams() {
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(params, SOCKET_TIMEOUT);
		return params;
	}*/

	public static void closeStream(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
				stream = null;
			} catch (IOException e) {
				System.out.print("Close stream exception" + e.getMessage());
			}
		}
	}

	/*public static InputStream getInputStreamFromUrl(String url)
			throws ClientProtocolException, IOException {
		HttpGet request = new HttpGet(url);
		DefaultHttpClient client = new DefaultHttpClient(getTimeoutHttpParams());
		HttpResponse response = client.execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			System.out.print("Failed to get stream for: " + url);
			throw new IOException("Failed to get stream for: " + url);
		}
		return response.getEntity().getContent();
	}

	public static String fetchContent(String url)
			throws ClientProtocolException, IOException {
		InputStream input = getInputStreamFromUrl(url);
		try {
			int c;
			byte[] buffer = new byte[8192];
			StringBuilder sb = new StringBuilder();
			while ((c = input.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, c));
			}
			return sb.toString();
		} finally {
			closeStream(input);
		}
	}*/

	public static String readStream(InputStream is) {
		if (is == null) {
			return null;
		}
		try {
			int c;
			byte[] buffer = new byte[8192];
			StringBuilder sb = new StringBuilder();
			while ((c = is.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, c));
			}
			return sb.toString();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			closeStream(is);
		}
		return null;
	}

	public static void writeFile(InputStream is, File file)
			throws IOException {
		FileOutputStream os = new FileOutputStream(file);
		try {
			int c;
			byte[] buffer = new byte[8192];
			while ((c = is.read(buffer)) != -1) {
				os.write(buffer, 0, c);
			}
		} finally {
			closeStream(is);
			closeStream(os);
		}
	}

	/*public static void showToastLong(Context cxt, int resid) {
		Toast.makeText(cxt, resid, Toast.LENGTH_LONG).show();
	}

	public static void showToastShort(Context cxt, int resid) {
		Toast.makeText(cxt, resid, Toast.LENGTH_SHORT).show();
	}

	public static void showToastLong(Context cxt, String msg) {
		Toast.makeText(cxt, msg, Toast.LENGTH_LONG).show();
	}

	public static void showToastShort(Context cxt, String msg) {
		Toast.makeText(cxt, msg, Toast.LENGTH_SHORT).show();
	}

	public interface OnMultiChoiceDialogListener {
		void onClick(boolean[] selected);
	}

	public static Dialog createMultiChoiceDialog(Context cxt,
			CharSequence[] keys, boolean[] values, Integer titleId,
			Integer iconId, final OnMultiChoiceDialogListener listener) {
		final boolean[] res;
		if (values == null) {
			res = new boolean[keys.length];
		} else {
			res = values;
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(cxt);
		if (iconId != null) {
			builder.setIcon(iconId);
		}
		if (titleId != null) {
			builder.setTitle(titleId);
		}
		builder.setMultiChoiceItems(keys, values,
				new DialogInterface.OnMultiChoiceClickListener() {
					public void onClick(DialogInterface dialog,
							int whichButton, boolean isChecked) {
						res[whichButton] = isChecked;
					}
				});
		builder.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						listener.onClick(res);
					}
				});
		builder.setNegativeButton(R.string.cancel, null);
		return builder.create();
	}

	public static void showDialog(Context cxt, int titleid, int msgid) {
		AlertDialog.Builder builder = new AlertDialog.Builder(cxt);
		builder.setTitle(titleid);
		builder.setMessage(msgid);
		builder.setPositiveButton(android.R.string.ok, null);
		builder.setCancelable(true);
		builder.show();
	}

	public static void showDialog(Context cxt, int titleid, String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(cxt);
		builder.setTitle(titleid);
		builder.setMessage(msg);
		builder.setPositiveButton(android.R.string.ok, null);
		builder.setCancelable(true);
		builder.show();
	}

	public static void showConfirmationDialog(Context cxt, int msgid,
			OnClickListener oklistener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(cxt);
		// builder.setTitle(cxt.getPackageName());
		builder.setMessage(msgid);
		builder.setPositiveButton(android.R.string.ok, oklistener);
		builder.setNegativeButton(android.R.string.cancel, null);
		builder.setCancelable(true);
		builder.show();
	}

	public static void showConfirmationDialog(Context cxt, int msgid,
			OnClickListener oklistener, int titleid) {
		AlertDialog.Builder builder = new AlertDialog.Builder(cxt);
		// builder.setTitle(cxt.getPackageName());
		builder.setTitle(titleid);
		builder.setMessage(msgid);
		builder.setPositiveButton(android.R.string.ok, oklistener);
		builder.setNegativeButton(android.R.string.cancel, null);
		builder.setCancelable(true);
		builder.show();
	}

	public static void showDeleteConfirmationDialog(Context cxt,
			OnClickListener oklistener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(cxt);
		builder.setTitle(R.string.delete_task_title);
		builder.setMessage(R.string.delete_task_message);
		builder.setPositiveButton(R.string.delete_task_confirm, oklistener);
		builder.setNegativeButton(R.string.cancel, null);
		builder.show();
	}*/

	public static boolean isDeviceWritable() {
		/*String sdState = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(sdState)) {
			return true;
		} else {
			return false;
		}*/
		return true;
	}

	public static boolean isDeviceReadable() {
		/*String sdState = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(sdState)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(sdState)) {
			return true;
		} else {
			return false;
		}*/
		return true;
	}

	public interface InputDialogListener {
		void onClick(String input);
	}

	public interface LoginDialogListener {
		void onClick(String username, String password);
	}

	public static void createParentDirectory(File dest) throws TodoException {
		if (dest == null) {
			throw new TodoException("createParentDirectory: dest is null");
		}
		File dir = dest.getParentFile();
		if (dir != null && !dir.exists()) {
			createParentDirectory(dir);
		}
		if (!dir.exists()) {
			if (!dir.mkdirs()) {
				System.out.print("Could not create dirs: " + dir.getAbsolutePath());
				throw new TodoException("Could not create dirs: "
						+ dir.getAbsolutePath());
			}
		}
	}

	public static void renameFile(File origFile, File newFile, boolean overwrite) {
		if (!origFile.exists()) {
			System.out.print("Error renaming file: " + origFile + " does not exist");
			throw new TodoException("Error renaming file: " + origFile
					+ " does not exist");
		}

		createParentDirectory(newFile);

		if (overwrite && newFile.exists()) {
			if (!newFile.delete()) {
				System.out.print("Error renaming file: failed to delete " + newFile);
				throw new TodoException(
						"Error renaming file: failed to delete " + newFile);
			}
		}

		if (!origFile.renameTo(newFile)) {
			System.out.print("Error renaming " + origFile + " to " + newFile);
			throw new TodoException("Error renaming " + origFile + " to "
					+ newFile);
		}
	}

	public static void copyFile(File origFile, File newFile, boolean overwrite) {
		if (!origFile.exists()) {
			System.out.print("Error copying file: " + origFile
					+ " does not exist");
		}

		createParentDirectory(newFile);

		if (!overwrite && newFile.exists()) {
			System.out.print("Error copying file: destination exists: " + newFile);
			throw new TodoException("Error copying file: destination exists: "
					+ newFile);
		}

		try {
			FileInputStream fis = new FileInputStream(origFile);
			FileOutputStream fos = new FileOutputStream(newFile);
			FileChannel in = fis.getChannel();
			fos.getChannel().transferFrom(in, 0, in.size());
			fis.close();
			fos.close();
		} catch (Exception e) {
			System.out.print("Error copying " + origFile + " to " + newFile);
			throw new TodoException("Error copying " + origFile + " to "
					+ newFile, e);
		}
	}

	/*public static ArrayAdapter<String> newSpinnerAdapter(Context cxt,
			List<String> items) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(cxt,
				android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		return adapter;
	}

	public static void setGray(SpannableString ss, List<String> items) {
		String data = ss.toString();
		for (String item : items) {
			int i = data.indexOf("@" + item);
			if (i != -1) {
				ss.setSpan(new ForegroundColorSpan(Color.GRAY), i,
						i + 1 + item.length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
			int j = data.indexOf("+" + item);
			if (j != -1) {
				ss.setSpan(new ForegroundColorSpan(Color.GRAY), j,
						j + 1 + item.length(),
						Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
		}
	}

	public static boolean isOnline(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		return cm.getActiveNetworkInfo() != null
				&& cm.getActiveNetworkInfo().isConnected();
	}*/

	public static String join(Collection<?> s, String delimiter) {
		StringBuilder builder = new StringBuilder();
		if (s == null) {
			return "";
		}
		Iterator<?> iter = s.iterator();
		while (iter.hasNext()) {
			builder.append(iter.next());
			if (!iter.hasNext()) {
				break;
			}
			builder.append(delimiter);
		}
		return builder.toString();
	}
	
	public static ArrayList<String> split(String s, String delimeter) {
		if (Strings.isBlank(s)) {
			return new ArrayList<String>();
		}
		return new ArrayList<String>(Arrays.asList(s.split(delimeter)));
	}
	
    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = JdotxtToolbar.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
	public static void prependString(ArrayList<String> list, String prepend) {
		for (int k1=0; k1 < list.size(); k1++) {
			list.set(k1, prepend + list.get(k1));
		}
	}
	
	public static int[] integerList2IntArray(List<Integer> integerlist) {
		int[] array = new int[integerlist.size()];
		for (int k1 = 0; k1 < integerlist.size(); k1++) {
			array[k1] = (int) integerlist.get(k1);
		}
		return array;
	}
}

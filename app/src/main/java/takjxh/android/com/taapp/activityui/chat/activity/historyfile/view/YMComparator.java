package takjxh.android.com.taapp.activityui.chat.activity.historyfile.view;


import java.util.Comparator;

import takjxh.android.com.taapp.activityui.chat.entity.FileItem;

public class YMComparator implements Comparator<FileItem> {

	@Override
	public int compare(FileItem o1, FileItem o2) {
		return o1.getDate().compareTo(o2.getDate());
	}

}

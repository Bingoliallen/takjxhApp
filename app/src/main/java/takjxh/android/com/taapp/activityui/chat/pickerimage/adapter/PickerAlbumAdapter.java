package takjxh.android.com.taapp.activityui.chat.pickerimage.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import takjxh.android.com.taapp.R;
import takjxh.android.com.taapp.activityui.chat.pickerimage.model.AlbumInfo;
import takjxh.android.com.taapp.activityui.chat.pickerimage.utils.ThumbnailsUtil;
import takjxh.android.com.taapp.activityui.chat.utils.ImageLoadUtils;


public class PickerAlbumAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<AlbumInfo> mList;
	private Context mContext;

	public PickerAlbumAdapter(Context context, List<AlbumInfo> list){
		mContext = context;
		mInflater = LayoutInflater.from(context);
		this.mList = list;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mList.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.picker_photofolder_item, null);
			viewHolder.folderCover = (ImageView)convertView.findViewById(R.id.picker_photofolder_cover);
			viewHolder.folderName = (TextView)convertView.findViewById(R.id.picker_photofolder_info);
			viewHolder.folderFileNum = (TextView)convertView.findViewById(R.id.picker_photofolder_num);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		final AlbumInfo albumInfo = mList.get(position);
		Log.e("TAG","-----------albumInfo.getFilePath-----:"+albumInfo.getFilePath());
		String thumbPath = ThumbnailsUtil.getThumbnailWithImageID(albumInfo.getImageId(), albumInfo.getFilePath());
		/*PickerImageLoadTool.disPlay(thumbPath, new RotateImageViewAware(viewHolder.folderCover, albumInfo.getAbsolutePath()),
				R.drawable.image_default);*/

		Log.e("TAG","-----------thumbPath-----:"+thumbPath);
		try {
			ImageLoadUtils.displayImageFromFile(albumInfo.getFilePath(), viewHolder.folderCover,R.drawable.image_default);
		}catch (Exception e){
			e.printStackTrace();
		}
		/*File file = new File(thumbPath);
		if (file.exists()) {
            Glide.with(mContext).load(file).error(R.drawable.image_default).fitCenter().into(viewHolder.folderCover);

        }else{
            viewHolder.folderCover.setImageResource(R.drawable.image_default);
        }*/

		viewHolder.folderName.setText(albumInfo.getAlbumName());
		viewHolder.folderFileNum.setText(String.format(mContext.getResources().getString(
				R.string.picker_image_folder_info), mList.get(position).getList().size()));
		return convertView;
	}

	public class ViewHolder{
		public ImageView folderCover;
		public TextView folderName;
		public TextView folderFileNum;
	}
}

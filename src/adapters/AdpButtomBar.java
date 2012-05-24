package adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class AdpButtomBar extends BaseAdapter {
	private Context mContext;
	private ImageView[] imgItems;
	private int selResId;

	
	public AdpButtomBar (Context mContext,int[] picIds,int width,int height,int selResId) {
		this.mContext=mContext;
		this.selResId=selResId;
		imgItems=new ImageView[picIds.length];
		 for(int i=0;i<picIds.length;i++)  
	        {  
	            imgItems[i] = new ImageView(mContext);   
	            imgItems[i].setLayoutParams(new GridView.LayoutParams(width, height));//设置ImageView宽高   
	            imgItems[i].setAdjustViewBounds(false);   
	            //imgItems[i].setScaleType(ImageView.ScaleType.CENTER_CROP);   
	            imgItems[i].setPadding(2, 2, 2, 2);   
	            imgItems[i].setImageResource(picIds[i]);   
	        }  
	}

	@Override
	public int getCount() {
		return imgItems.length;
	}

	@Override
	public Object getItem(int position) {
		return position;

	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void SetFocus(int index)    
    {    
        for(int i=0;i<imgItems.length;i++)    
        {    
            if(i!=index)    
            {    
                imgItems[i].setBackgroundResource(0);//恢复未选中的样式  
            }    
        }    
        imgItems[index].setBackgroundResource(selResId);//设置选中的样式  
    }    
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;   
        if (convertView == null) {   
            imageView=imgItems[position];  
        } else {   
            imageView = (ImageView) convertView;   
        }   
        return imageView;   
	}

}

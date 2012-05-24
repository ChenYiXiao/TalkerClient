package talker.acts;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.view.KeyEvent;

public class MyActivity extends Activity {

	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
    {  
                if(keyCode == KeyEvent.KEYCODE_BACK){  
                	Builder qury =new AlertDialog.Builder(this) ;
            		Resources rs=this.getResources();
            		qury.setTitle("是否退出"+ rs.getString(R.string.app_name)+"?");
            		qury.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            			@Override
            			public void onClick(DialogInterface dialog, int which) {
            				MyActivity.this.finish();
            			}
            		});
            		qury.setNegativeButton("取消", null);
            		qury.show();
                }
		return super.onKeyDown(keyCode, event);
	}
}

package talker.acts;

import adapters.AdpButtomBar;
import android.R.color;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;

public class ActMain extends ActivityGroup {
    /** Called when the activity is first created. */
	private GridView gvTopBar;  
    private AdpButtomBar buttomBarAdapter;  
    public LinearLayout container;// 装载sub Activity的容器  
  
    /** 顶部按钮图片 **/  
    int[] buttombar_image_array = { R.drawable.buttombar_home,  
    		R.drawable.buttombar_chat, R.drawable.buttombar_user,  
    		R.drawable.buttombar_system };  //底部导航栏的图标集
  

    @Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) {   
          
        if(keyCode==KeyEvent.KEYCODE_MENU) {   
              
            this.getLocalActivityManager().getCurrentActivity().openOptionsMenu();   
        }else if(keyCode == KeyEvent.KEYCODE_BACK){   
              
        	  if(keyCode == KeyEvent.KEYCODE_BACK){  
              	Builder qury =new AlertDialog.Builder(this) ;
          		Resources rs=this.getResources();
          		qury.setTitle("是否退出"+ rs.getString(R.string.app_name)+"?");
          		qury.setPositiveButton("确定", new DialogInterface.OnClickListener() {
          			@Override
          			public void onClick(DialogInterface dialog, int which) {
          				ActMain.this.finish();
          			}
          		});
          		qury.setNegativeButton("取消", null);
          		qury.show();
              }
        }   
          
        return super.onKeyDown(keyCode, event);   
    }  

	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.main);  
        gvTopBar = (GridView) this.findViewById(R.id.gvButtombar);  
        gvTopBar.setNumColumns(buttombar_image_array.length);// 设置每行列数  
        // gvTopBar.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gvTopBar.setSelector(new ColorDrawable(color.transparent));// 选中的时候为透明色  
        gvTopBar.setGravity(Gravity.CENTER);// 位置居中  
        gvTopBar.setVerticalSpacing(0);// 垂直间隔  
        int width = this.getWindowManager().getDefaultDisplay().getWidth()  
                / buttombar_image_array.length;  
        buttomBarAdapter = new AdpButtomBar(this, buttombar_image_array, width, 48,  
                R.drawable.buttombar_bg);  //最后一个参数为选中背景
        gvTopBar.setAdapter(buttomBarAdapter);// 设置菜单Adapter  
        gvTopBar.setOnItemClickListener((OnItemClickListener) new ItemClickEvent());// 项目点击事件  
        container = (LinearLayout) findViewById(R.id.llMain);  
        SwitchActivity(0);//默认打开第0页  
    }  
  
    
        

	class ItemClickEvent implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			SwitchActivity(arg2);
		}  
		
    }  
    /** 
     * 根据ID打开指定的Activity 
     * @param id GridView选中项的序号 
     */  
    void SwitchActivity(int id)  
    {  
        buttomBarAdapter.SetFocus(id);//选中项获得高亮  
        container.removeAllViews();//必须先清除容器中所有的View  
        Intent intent =null;  
        if (id == 0 || id == 2) {  
            intent = new Intent(ActMain.this, ActHome.class);  
        } else if (id == 1 || id == 3) {  
            intent = new Intent(ActMain.this, ActUser.class);  
        }   
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
        //Activity 转为 View  
        Window subActivity = getLocalActivityManager().startActivity(  
                "subActivity", intent);  
        //容器添加View  
        container.addView(subActivity.getDecorView(),  
                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);  
    }  
}
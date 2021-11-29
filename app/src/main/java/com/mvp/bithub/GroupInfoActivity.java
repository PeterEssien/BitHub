package com.mvp.bithub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.ImageView;
import de.hdodenhof.circleimageview.*;
import android.widget.TextView;
import android.widget.Switch;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.view.View;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.graphics.Typeface;
import android.content.ClipData;
import android.content.ClipboardManager;
import javax.activation.*;
import myjava.awt.datatransfer.*;
import com.swrevo.gmailapi.*;
import javax.mail.*;
import com.udevel.widgetlab.*;
import com.suke.widget.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class GroupInfoActivity extends AppCompatActivity {
	
	private ArrayList<HashMap<String, Object>> result = new ArrayList<>();
	
	private LinearLayout linear1;
	private ScrollView vscroll1;
	private LinearLayout linear3;
	private ImageView imageview1;
	private CircleImageView circleimageview1;
	private LinearLayout linear5;
	private TextView textview1;
	private TextView textview2;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private LinearLayout linear14;
	private TextView textview3;
	private TextView textview4;
	private TextView textview6;
	private TextView textview5;
	private TextView textview7;
	private Switch switch1;
	private TextView textview10;
	private LinearLayout linear13;
	private ListView listview1;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.group_info);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		vscroll1 = findViewById(R.id.vscroll1);
		linear3 = findViewById(R.id.linear3);
		imageview1 = findViewById(R.id.imageview1);
		circleimageview1 = findViewById(R.id.circleimageview1);
		linear5 = findViewById(R.id.linear5);
		textview1 = findViewById(R.id.textview1);
		textview2 = findViewById(R.id.textview2);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		linear11 = findViewById(R.id.linear11);
		linear12 = findViewById(R.id.linear12);
		linear14 = findViewById(R.id.linear14);
		textview3 = findViewById(R.id.textview3);
		textview4 = findViewById(R.id.textview4);
		textview6 = findViewById(R.id.textview6);
		textview5 = findViewById(R.id.textview5);
		textview7 = findViewById(R.id.textview7);
		switch1 = findViewById(R.id.switch1);
		textview10 = findViewById(R.id.textview10);
		linear13 = findViewById(R.id.linear13);
		listview1 = findViewById(R.id.listview1);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		textview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", textview5.getText().toString()));
				SketchwareUtil.showMessage(getApplicationContext(), "Circle key copied to clipboard !");
			}
		});
	}
	
	private void initializeLogic() {
		_NavStatusBarColor("#151E27", "#151E27");
		_font();
		textview1.setText(getIntent().getStringExtra("groupName"));
		textview4.setText(getIntent().getStringExtra("groupDescription"));
		textview5.setText(getIntent().getStringExtra("groupKey"));
		Glide.with(getApplicationContext()).load(Uri.parse(getIntent().getStringExtra("groupPic"))).into(circleimageview1);
		_removeScollBar(listview1);
		result = new Gson().fromJson(getIntent().getStringExtra("members"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		_setHeight(listview1, result.size() * SketchwareUtil.getDip(getApplicationContext(), (int)(80)));
		listview1.setAdapter(new Listview1Adapter(result));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		textview2.setText(String.valueOf((long)(result.size())).concat(" members in this circle"));
		if (getIntent().getStringExtra("creatorID").equals(getIntent().getStringExtra("myUid"))) {
			
		}
		else {
			if (getIntent().getStringExtra("visibleLink").equals("yes")) {
				
			}
			else {
				textview5.setTransformationMethod(android.text.method.PasswordTransformationMethod.getInstance());
				textview5.setEnabled(false);
			}
		}
	}
	
	public void _NavStatusBarColor(final String _color1, final String _color2) {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
			Window w = this.getWindow();	w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);	w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(Color.parseColor("#" + _color1.replace("#", "")));	w.setNavigationBarColor(Color.parseColor("#" + _color2.replace("#", "")));
		}
	}
	
	
	public void _font() {
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/font.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 1);
		textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
		textview10.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		switch1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
	}
	
	
	public void _setHeight(final View _view, final double _num1) {
		_view.getLayoutParams().height=(int)(_num1);
	}
	
	
	public void _removeScollBar(final View _view) {
		_view.setVerticalScrollBarEnabled(false); _view.setHorizontalScrollBarEnabled(false);
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.users, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final de.hdodenhof.circleimageview.CircleImageView circleimageview1 = _view.findViewById(R.id.circleimageview1);
			final TextView title = _view.findViewById(R.id.title);
			
			title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
			title.setText(result.get((int)_position).get("username").toString());
			Glide.with(getApplicationContext()).load(Uri.parse(result.get((int)_position).get("myPic").toString())).into(circleimageview1);
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}
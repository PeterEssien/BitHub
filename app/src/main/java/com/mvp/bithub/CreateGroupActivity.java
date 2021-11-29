package com.mvp.bithub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import android.graphics.Typeface;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import javax.activation.*;
import myjava.awt.datatransfer.*;
import com.swrevo.gmailapi.*;
import javax.mail.*;
import com.udevel.widgetlab.*;
import com.suke.widget.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class CreateGroupActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private FloatingActionButton _fab;
	private double selectedTotal = 0;
	private boolean isSearch = false;
	private double n2 = 0;
	private double n = 0;
	private HashMap<String, Object> cacheMap = new HashMap<>();
	private double len = 0;
	private String myPic = "";
	
	private ArrayList<HashMap<String, Object>> itemList = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> resultData = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> map_address = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ListView listview1;
	private EditText search;
	private ImageView imageview1;
	
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private Intent intent = new Intent();
	private FirebaseAuth mauth;
	private OnCompleteListener<AuthResult> _mauth_create_user_listener;
	private OnCompleteListener<AuthResult> _mauth_sign_in_listener;
	private OnCompleteListener<Void> _mauth_reset_password_listener;
	private OnCompleteListener<Void> mauth_updateEmailListener;
	private OnCompleteListener<Void> mauth_updatePasswordListener;
	private OnCompleteListener<Void> mauth_emailVerificationSentListener;
	private OnCompleteListener<Void> mauth_deleteUserListener;
	private OnCompleteListener<Void> mauth_updateProfileListener;
	private OnCompleteListener<AuthResult> mauth_phoneAuthListener;
	private OnCompleteListener<AuthResult> mauth_googleSignInListener;
	
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.create_group);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_fab = findViewById(R.id._fab);
		
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		listview1 = findViewById(R.id.listview1);
		search = findViewById(R.id.search);
		imageview1 = findViewById(R.id.imageview1);
		mauth = FirebaseAuth.getInstance();
		
		search.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (isSearch) {
					isSearch = false;
					search.setVisibility(View.GONE);
					search.setText("");
					android.view.inputmethod.InputMethodManager imm = (android.view.inputmethod.InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE); imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
					imageview1.setImageResource(R.drawable.ic_search_white);
					listview1.setVisibility(View.VISIBLE);
				}
				else {
					isSearch = true;
					search.setVisibility(View.VISIBLE);
					search.requestFocus();
					android.view.inputmethod.InputMethodManager inputMethodManager = (android.view.inputmethod.InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); if (inputMethodManager != null) { inputMethodManager.toggleSoftInput(android.view.inputmethod.InputMethodManager.SHOW_FORCED, 0); }
					imageview1.setImageResource(R.drawable.ic_clear_white);
				}
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				{
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("username", getIntent().getStringExtra("myName"));
					itemList.add(_item);
				}
				
				itemList.get((int)itemList.size() - 1).put("myPic", getIntent().getStringExtra("myPic"));
				itemList.get((int)itemList.size() - 1).put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				itemList.get((int)itemList.size() - 1).put("select", "1");
				n2 = 0;
				resultData.clear();
				for(int _repeat12 = 0; _repeat12 < (int)(itemList.size()); _repeat12++) {
					if (itemList.get((int)n2).get("select").toString().equals("1")) {
						cacheMap = itemList.get((int)n2);
						resultData.add(cacheMap);
					}
					n2++;
				}
				intent.setClass(getApplicationContext(), ConfirmGroupActivity.class);
				intent.putExtra("myPic", getIntent().getStringExtra("myPic"));
				intent.putExtra("myName", getIntent().getStringExtra("myName"));
				intent.putExtra("myUid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				intent.putExtra("data", new Gson().toJson(resultData));
				intent.putExtra("members", String.valueOf((long)(selectedTotal)));
				startActivity(intent);
			}
		});
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				_addItems(_childValue.get("username").toString(), _childValue.get("myPic").toString(), _childValue.get("uid").toString());
				listview1.setAdapter(new Listview1Adapter(itemList));
				_sort(itemList, "username");
				{
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("address", _childValue.get("address").toString());
					map_address.add(_item);
				}
				
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
				_refreshSelected();
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				_addItems(_childValue.get("username").toString(), _childValue.get("myPic").toString(), _childValue.get("uid").toString());
				listview1.setAdapter(new Listview1Adapter(itemList));
				_sort(itemList, "username");
				{
					HashMap<String, Object> _item = new HashMap<>();
					_item.put("address", _childValue.get("address").toString());
					map_address.add(_item);
				}
				
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
				_refreshSelected();
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		users.addChildEventListener(_users_child_listener);
		
		mauth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		mauth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		mauth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		mauth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		mauth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		mauth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		mauth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_mauth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_mauth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_mauth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		_NavStatusBarColor("#151E27", "#151E27");
		listview1.setOnScrollListener(new ListView.OnScrollListener() {
			
			private int mLastFirstVisibleItem;
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				
				if(mLastFirstVisibleItem<firstVisibleItem) {
					_fab.hide();
				}
				
				if(mLastFirstVisibleItem>firstVisibleItem) {
					_fab.show();
				}
				
				mLastFirstVisibleItem = firstVisibleItem;
				
			}
			
		});
		imageview1.setColorFilter(0xFF34D186, PorterDuff.Mode.MULTIPLY);
		search.setVisibility(View.GONE);
	}
	
	public void _NavStatusBarColor(final String _color1, final String _color2) {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
			Window w = this.getWindow();	w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);	w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(Color.parseColor("#" + _color1.replace("#", "")));	w.setNavigationBarColor(Color.parseColor("#" + _color2.replace("#", "")));
		}
	}
	
	
	public void _refreshSelected() {
		if (selectedTotal > 0) {
			if (selectedTotal > 100) {
				SketchwareUtil.showMessage(getApplicationContext(), "A circle can only contain 100 members");
			}
			else {
				_fab.setEnabled(true);
			}
		}
		else {
			_fab.setEnabled(false);
		}
	}
	
	
	public void _setRipple1(final View _view) {
		TypedValue typedValue = new TypedValue();
		
		getApplicationContext().getTheme().resolveAttribute(16843868, typedValue, true);
		
		_view.setBackgroundResource(typedValue.resourceId);
		
		_view.setClickable(true);
	}
	
	
	public void _selectItem(final double _pos) {
		if (itemList.get((int)_pos).get("select").toString().equals("1")) {
			itemList.get((int)_pos).put("select", "0");
			selectedTotal--;
		}
		else {
			itemList.get((int)_pos).put("select", "1");
			selectedTotal++;
		}
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		_refreshSelected();
	}
	
	
	public void _sort(final ArrayList<HashMap<String, Object>> _mylist, final String _key) {
		Comparator list_sorter = new Comparator<HashMap<String, Object>>(){
			@Override
			public int compare(HashMap<String, Object> map2, HashMap<String, Object> map1){
				
				try {
					Double num1 = Double.parseDouble(map1.get(_key).toString());
					Double num2 = Double.parseDouble(map2.get(_key).toString());
					return Double.compare(num2, num1);
				} catch (NumberFormatException e){
					return map2.get(_key).toString().compareTo(map1.get(_key).toString());
				}
				
			}
		};
		
		Collections.sort(_mylist, list_sorter);
	}
	
	
	public void _addItems(final String _username, final String _pic, final String _uid) {
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("username", _username);
			itemList.add(_item);
		}
		
		itemList.get((int)itemList.size() - 1).put("myPic", _pic);
		itemList.get((int)itemList.size() - 1).put("uid", _uid);
		itemList.get((int)itemList.size() - 1).put("select", "0");
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
				_view = _inflater.inflate(R.layout.select_users, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final de.hdodenhof.circleimageview.CircleImageView circleimageview1 = _view.findViewById(R.id.circleimageview1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			
			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
			textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_medium.ttf"), 0);
			textview1.setText(itemList.get((int)_position).get("username").toString());
			Glide.with(getApplicationContext()).load(Uri.parse(itemList.get((int)_position).get("myPic").toString())).into(circleimageview1);
			textview2.setText(map_address.get((int)_position).get("address").toString());
			if (itemList.get((int)_position).get("select").toString().equals("1")) {
				imageview1.setImageResource(R.drawable.ic_brightness_1_white);
				imageview1.setColorFilter(0xFF34D186, PorterDuff.Mode.MULTIPLY);
			}
			else {
				imageview1.setImageResource(R.drawable.ic_panorama_fisheye_white);
				imageview1.setColorFilter(0xFFBDBDBD, PorterDuff.Mode.MULTIPLY);
			}
			if (itemList.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
				imageview1.setImageResource(R.drawable.ic_brightness_1_white);
				imageview1.setColorFilter(0xFF34D186, PorterDuff.Mode.MULTIPLY);
			}
			_setRipple1(imageview1);
			imageview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					_selectItem(_position);
				}
			});
			
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
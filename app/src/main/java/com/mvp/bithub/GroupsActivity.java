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
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import java.util.Timer;
import java.util.TimerTask;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.graphics.Typeface;
import com.bumptech.glide.Glide;
import javax.activation.*;
import myjava.awt.datatransfer.*;
import com.swrevo.gmailapi.*;
import javax.mail.*;
import com.udevel.widgetlab.*;
import com.suke.widget.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class GroupsActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private FloatingActionButton _fab;
	private HashMap<String, Object> map = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> maplist = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> result = new ArrayList<>();
	
	private LinearLayout linear1;
	private ListView listview1;
	
	private Intent i = new Intent();
	private DatabaseReference groups = _firebase.getReference("groups");
	private ChildEventListener _groups_child_listener;
	private TimerTask t;
	private AlertDialog.Builder dialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.groups);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_fab = findViewById(R.id._fab);
		
		linear1 = findViewById(R.id.linear1);
		listview1 = findViewById(R.id.listview1);
		dialog = new AlertDialog.Builder(this);
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (maplist.get((int)_position).containsKey(getIntent().getStringExtra("myUid"))) {
					i.putExtra("myName", getIntent().getStringExtra("myName"));
					i.putExtra("creatorID", maplist.get((int)_position).get("joinKey").toString());
					i.putExtra("key", maplist.get((int)_position).get("key").toString());
					i.putExtra("visibleLink", maplist.get((int)_position).get("visibleLink").toString());
					i.putExtra("groupDescription", maplist.get((int)_position).get("groupDescription").toString());
					i.putExtra("groupKey", maplist.get((int)_position).get("joinKey").toString());
					i.putExtra("groupName", maplist.get((int)_position).get("groupName").toString());
					i.putExtra("groupPic", maplist.get((int)_position).get("groupPic").toString());
					i.putExtra("members", maplist.get((int)_position).get("members").toString());
					i.setClass(getApplicationContext(), GroupChatActivity.class);
					startActivity(i);
				}
				else {
					if (maplist.get((int)_position).get("privacy").toString().equals("public")) {
						map = new HashMap<>();
						map.put(getIntent().getStringExtra("myUid"), getIntent().getStringExtra("myUid"));
						result = new Gson().fromJson(maplist.get((int)_position).get("members").toString(), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
						{
							HashMap<String, Object> _item = new HashMap<>();
							_item.put("username", getIntent().getStringExtra("myName"));
							result.add(_item);
						}
						
						result.get((int)result.size() - 1).put("myPic", getIntent().getStringExtra("myPic"));
						result.get((int)result.size() - 1).put("uid", getIntent().getStringExtra("myUid"));
						result.get((int)result.size() - 1).put("select", "0");
						map.put("members", new Gson().toJson(result));
						groups.child(maplist.get((int)_position).get("key").toString()).updateChildren(map);
						_Custom_Loading(true);
						t = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										_Custom_Loading(false);
										SketchwareUtil.showMessage(getApplicationContext(), "Successfully joined circle");
										i.putExtra("myName", getIntent().getStringExtra("myName"));
										i.putExtra("creatorID", maplist.get((int)_position).get("joinKey").toString());
										i.putExtra("key", maplist.get((int)_position).get("key").toString());
										i.putExtra("visibleLink", maplist.get((int)_position).get("visibleLink").toString());
										i.putExtra("groupDescription", maplist.get((int)_position).get("groupDescription").toString());
										i.putExtra("groupKey", maplist.get((int)_position).get("joinKey").toString());
										i.putExtra("groupName", maplist.get((int)_position).get("groupName").toString());
										i.putExtra("groupPic", maplist.get((int)_position).get("groupPic").toString());
										i.putExtra("members", maplist.get((int)_position).get("members").toString());
										i.setClass(getApplicationContext(), GroupChatActivity.class);
										startActivity(i);
									}
								});
							}
						};
						_timer.schedule(t, (int)(1200));
					}
					else {
						final AlertDialog dialog1 = new AlertDialog.Builder(GroupsActivity.this).create();
						View inflate = getLayoutInflater().inflate(R.layout.bottom_sheet_p2,null); 
						dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
						dialog1.setView(inflate);
						TextView t1 = (TextView) inflate.findViewById(R.id.t1);
						
						TextView t2 = (TextView) inflate.findViewById(R.id.t2);
						
						TextView b1 = (TextView) inflate.findViewById(R.id.b1);
						
						TextView b2 = (TextView) inflate.findViewById(R.id.b2);
						
						LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
						t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
						t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_light.ttf"), 0);
						b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
						b2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/en_medium.ttf"), 0);
						t1.setText("Private Circle !");
						t2.setText("This is a private circle and you would require a key in order to join !");
						b1.setText("Back");
						b2.setText("I have key");
						_rippleRoundStroke(bg, "#212D3B", "#000000", 15, 0, "#000000");
						_rippleRoundStroke(b1, "#F5F5F5", "#E0E0E0", 15, 0, "#000000");
						_rippleRoundStroke(b2, "#24FEB7", "#40FFFFFF", 15, 0, "#000000");
						b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
								dialog1.dismiss();
							}
						});
						b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
								dialog1.dismiss();
								dialog.setMessage("Please provide the circle key !");
								final EditText key= new EditText(GroupsActivity.this);
								LinearLayout.LayoutParams lpar = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
								key.setLayoutParams(lpar);
								dialog.setView(key);
								dialog.setPositiveButton("JOIN", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										if (key.getText().toString().equals("")) {
											
										}
										else {
											if (key.getText().toString().equals(maplist.get((int)_position).get("joinKey").toString())) {
												map = new HashMap<>();
												map.put(getIntent().getStringExtra("myUid"), getIntent().getStringExtra("myUid"));
												result = new Gson().fromJson(maplist.get((int)_position).get("members").toString(), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
												{
													HashMap<String, Object> _item = new HashMap<>();
													_item.put("username", getIntent().getStringExtra("myName"));
													result.add(_item);
												}
												
												result.get((int)result.size() - 1).put("myPic", getIntent().getStringExtra("myPic"));
												result.get((int)result.size() - 1).put("uid", getIntent().getStringExtra("myUid"));
												result.get((int)result.size() - 1).put("select", "0");
												map.put("members", new Gson().toJson(result));
												groups.child(maplist.get((int)_position).get("key").toString()).updateChildren(map);
												_Custom_Loading(true);
												t = new TimerTask() {
													@Override
													public void run() {
														runOnUiThread(new Runnable() {
															@Override
															public void run() {
																_Custom_Loading(false);
																SketchwareUtil.showMessage(getApplicationContext(), "Successfully joined circle");
																i.putExtra("myName", getIntent().getStringExtra("myName"));
																i.putExtra("creatorID", maplist.get((int)_position).get("joinKey").toString());
																i.putExtra("key", maplist.get((int)_position).get("key").toString());
																i.putExtra("visibleLink", maplist.get((int)_position).get("visibleLink").toString());
																i.putExtra("groupDescription", maplist.get((int)_position).get("groupDescription").toString());
																i.putExtra("groupKey", maplist.get((int)_position).get("joinKey").toString());
																i.putExtra("groupName", maplist.get((int)_position).get("groupName").toString());
																i.putExtra("groupPic", maplist.get((int)_position).get("groupPic").toString());
																i.putExtra("members", maplist.get((int)_position).get("members").toString());
																i.setClass(getApplicationContext(), GroupChatActivity.class);
																startActivity(i);
															}
														});
													}
												};
												_timer.schedule(t, (int)(1200));
											}
											else {
												SketchwareUtil.showMessage(getApplicationContext(), "Invalid key");
											}
										}
									}
								});
								dialog.setNegativeButton("BACK", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface _dialog, int _which) {
										i.setClass(getApplicationContext(), HomeActivity.class);
										startActivity(i);
									}
								});
								dialog.create().show();
							}
						});
						dialog1.setCancelable(false);
						dialog1.show();
					}
				}
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.putExtra("myPic", getIntent().getStringExtra("myPic"));
				i.putExtra("myName", getIntent().getStringExtra("myName"));
				i.setClass(getApplicationContext(), CreateGroupActivity.class);
				startActivity(i);
			}
		});
		
		_groups_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				groups.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						maplist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								maplist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(maplist));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				groups.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						maplist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								maplist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview1.setAdapter(new Listview1Adapter(maplist));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
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
		groups.addChildEventListener(_groups_child_listener);
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
	}
	
	@Override
	public void onBackPressed() {
		i.setClass(getApplicationContext(), HomeActivity.class);
		startActivity(i);
	}
	public void _NavStatusBarColor(final String _color1, final String _color2) {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
			Window w = this.getWindow();	w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);	w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(Color.parseColor("#" + _color1.replace("#", "")));	w.setNavigationBarColor(Color.parseColor("#" + _color2.replace("#", "")));
		}
	}
	
	
	public void _Custom_Loading(final boolean _ifShow) {
		if (_ifShow) {
			if (coreprog == null){
				coreprog = new ProgressDialog(this);
				coreprog.setCancelable(false);
				coreprog.setCanceledOnTouchOutside(false);
				
				coreprog.requestWindowFeature(Window.FEATURE_NO_TITLE);  coreprog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
				
			}
			coreprog.setMessage(null);
			coreprog.show();
			View _view = getLayoutInflater().inflate(R.layout.custom_dialog, null);
			LinearLayout linear_base = (LinearLayout) _view.findViewById(R.id.linear_base);
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor("#FFFFFF"));
			gd.setCornerRadius(100);
			linear_base.setBackground(gd);
			coreprog.setContentView(_view);
		}
		else {
			if (coreprog != null){
				coreprog.dismiss();
			}
		}
	}
	private ProgressDialog coreprog;
	{
	}
	
	
	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
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
			textview1.setText(maplist.get((int)_position).get("groupName").toString());
			textview2.setText(maplist.get((int)_position).get("groupDescription").toString());
			Glide.with(getApplicationContext()).load(Uri.parse(maplist.get((int)_position).get("groupPic").toString())).into(circleimageview1);
			imageview1.setImageResource(R.drawable.ic_brightness_1_white);
			if (maplist.get((int)_position).containsKey(getIntent().getStringExtra("myUid"))) {
				imageview1.setColorFilter(0xFF34D186, PorterDuff.Mode.MULTIPLY);
			}
			else {
				if (maplist.get((int)_position).get("privacy").toString().equals("public")) {
					imageview1.setColorFilter(0xFFBDBDBD, PorterDuff.Mode.MULTIPLY);
				}
				else {
					imageview1.setColorFilter(0xFFF44336, PorterDuff.Mode.MULTIPLY);
				}
			}
			
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